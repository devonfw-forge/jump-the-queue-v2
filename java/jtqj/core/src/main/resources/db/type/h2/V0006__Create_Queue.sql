create table Queue(
  id BIGINT NOT NULL AUTO_INCREMENT,
  createdDate TIMESTAMP NOT NULL,
  modificationCounter INTEGER NOT NULL,
  minAttentionTime INTEGER NOT NULL,
  started BOOL DEFAULT '0',
  CONSTRAINT PK_Queue PRIMARY KEY(id),
);