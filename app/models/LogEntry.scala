package models

import java.util.Calendar
import java.util.Date

case class LogEntry(
  date: Date,
  name: String,
  comment: String
)

object LogEntry {
  val cal1 = Calendar.getInstance()
  cal1.add(Calendar.DAY_OF_MONTH, -5)
  val cal2 = Calendar.getInstance()
  cal2.add(Calendar.DAY_OF_MONTH, -4)
  val cal3 = Calendar.getInstance()
  cal3.add(Calendar.DAY_OF_MONTH, -3)
  
  var logEntries = Set(
    LogEntry(cal1.getTime(), "donmumpico", "DfdC ;-)"),
    LogEntry(cal2.getTime(), "croquepiraten", "Super"),
    LogEntry(cal3.getTime(), "cityzombies", "Klasse Cache")
  )

  def Desc[T : Ordering] = implicitly[Ordering[T]].reverse
  def findAll = this.logEntries.toList.sortBy(_.date)(Desc)
  
  def add(logEntry: LogEntry) {
    this.logEntries = this.logEntries + logEntry
  }
}
