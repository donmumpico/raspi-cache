package controllers

import play.api.mvc.{Action, Controller, Flash}
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, text}
import play.api.i18n.Messages
import models.LogEntry

object LogEntries extends Controller {
  def list = Action { implicit request => 
    val logEntries = LogEntry.findAll
    
    Ok(views.html.logEntries.list(logEntries))
  }

  def save = Action { implicit request =>
    val newLogEntryForm = this.logEntryForm.bindFromRequest()
    
    newLogEntryForm.fold(
      hasErrors = { form =>
        Redirect(routes.LogEntries.newLogEntry()).
          flashing(Flash(form.data) +
            ("error" -> Messages("validation.errors")))
      },
      
      success = { newLogEntry =>
        LogEntry.add(newLogEntry)
          val message = Messages("logEntries.new.success", newLogEntry.name)
          Redirect(routes.LogEntries.list()).
            flashing("success" -> message)
      }
    )  
  }
  
  def newLogEntry = Action { implicit request =>
      val form = if (flash.get("error").isDefined)
        this.logEntryForm.bind(flash.data)
      else
        this.logEntryForm
      
      Ok(views.html.logEntries.edit(form))
  }    
  
  private val logEntryForm: Form[LogEntry] = Form(
    mapping(
      "name" -> nonEmptyText,
      "comment" -> text
    )(LogEntry.apply)(LogEntry.unapply)
  )  
}
