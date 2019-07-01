create table Owner(
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  username VARCHAR(255),
  password VARCHAR(255),
  userType BOOL DEFAULT '0',
  CONSTRAINT PK_Owner PRIMARY KEY(id),
);