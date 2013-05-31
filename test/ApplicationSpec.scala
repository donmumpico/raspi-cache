import controllers.routes
import models.{Database, LogEntry}

import org.squeryl.PrimitiveTypeMode.inTransaction

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends Specification {
  "A request to the addLogEntries action" should {
    "respond" in new WithApplication() { 
      val result = controllers.LogEntries.save(FakeRequest().withFormUrlEncodedBody(
        "name" -> "donmumpico", 
        "comment" -> "Cooler Cache ;-)"))
      status(result) must equalTo(SEE_OTHER)
      redirectLocation(result) must equalTo(Some(routes.LogEntries.list.url))
    }
  } 
}

