create table AccessCode(
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  code VARCHAR(4) NOT NULL,
  uuid VARCHAR(255) NOT NULL,
  created TIMESTAMP NOT NULL,
  startTime TIMESTAMP,
  endTime TIMESTAMP,
  status VARCHAR(255) NOT NULL,
  idQueue BIGINT NOT NULL,
  CONSTRAINT PK_AccessCode PRIMARY KEY(id),
  CONSTRAINT FK_AccessCode_idQueue FOREIGN KEY(idQueue) REFERENCES Queue(id),
);