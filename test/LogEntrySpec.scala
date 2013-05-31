import models.{Database, LogEntry}

import org.squeryl.PrimitiveTypeMode.inTransaction

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._


class LogEntrySpec extends Specification {
  "A LogEntry" should {
    "be creatable" in new WithApplication(FakeApplication(additionalConfiguration = inMemoryDatabase())) { 
      inTransaction {
        val logEntry = Database.logEntryTable insert LogEntry("donmumpico", "Cooler Cache ;-)")
        logEntry.id must not be equalTo(0)
      }
    }
  } 
}
