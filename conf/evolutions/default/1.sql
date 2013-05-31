# --- !Ups
CREATE SEQUENCE s_logentries_id;
CREATE TABLE logEntries (
	id bigint NOT NULL DEFAULT nextval('s_logEntries_id'),
	date timestamp,
	name varchar,
	comment varchar);

# --- !Downs
DROP TABLE IF EXISTS logEntries;
DROP SEQUENCE IF EXISTS s_logentries_id;

