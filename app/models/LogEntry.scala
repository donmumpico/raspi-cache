package models

import org.squeryl.KeyedEntity
import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table
import org.squeryl.Query

import collection.Iterable


case class LogEntry(name: String, comment: String) 
  extends KeyedEntity[Long] {
    val id: Long = 0
    val date: java.sql.Timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
}


object Database extends Schema {
  val logEntryTable = table[LogEntry]("logEntries")
  on(logEntryTable) { l => declare {
    l.id is(autoIncremented)
  }}
}


object LogEntry {
  import Database.{logEntryTable}
  
  def allQ: Query[LogEntry] = from(logEntryTable) {
    logEntry => select(logEntry) orderBy(logEntry.date desc)
  }

  def findAll: List[LogEntry] = inTransaction {
    allQ.toList
  }
  
  def add(logEntry: LogEntry): LogEntry = inTransaction {
    logEntryTable.insert(logEntry.copy())
  }
}

