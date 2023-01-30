-- H2 2.1.214;
SET DB_CLOSE_DELAY -1;
CREATE USER IF NOT EXISTS "IHATERUNNING" SALT '35d79d6f6236951a' HASH '56f9f994301c3e9df72e72e9a23ca10c4b1e1a33d6e4e08dc41ee87e8fcfa821' ADMIN;
CREATE SEQUENCE "PUBLIC"."SEQUENCE_GENERATOR" START WITH 1050 RESTART WITH 1350 INCREMENT BY 50;
CREATE CACHED TABLE "PUBLIC"."DATABASECHANGELOGLOCK"(
    "ID" INTEGER NOT NULL,
    "LOCKED" BOOLEAN NOT NULL,
    "LOCKGRANTED" TIMESTAMP,
    "LOCKEDBY" CHARACTER VARYING(255)
);
ALTER TABLE "PUBLIC"."DATABASECHANGELOGLOCK" ADD CONSTRAINT "PUBLIC"."PK_DATABASECHANGELOGLOCK" PRIMARY KEY("ID");
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.DATABASECHANGELOGLOCK;
INSERT INTO "PUBLIC"."DATABASECHANGELOGLOCK" VALUES(1, FALSE, NULL, NULL);
CREATE CACHED TABLE "PUBLIC"."DATABASECHANGELOG"(
    "ID" CHARACTER VARYING(255) NOT NULL,
    "AUTHOR" CHARACTER VARYING(255) NOT NULL,
    "FILENAME" CHARACTER VARYING(255) NOT NULL,
    "DATEEXECUTED" TIMESTAMP NOT NULL,
    "ORDEREXECUTED" INTEGER NOT NULL,
    "EXECTYPE" CHARACTER VARYING(10) NOT NULL,
    "MD5SUM" CHARACTER VARYING(35),
    "DESCRIPTION" CHARACTER VARYING(255),
    "COMMENTS" CHARACTER VARYING(255),
    "TAG" CHARACTER VARYING(255),
    "LIQUIBASE" CHARACTER VARYING(20),
    "CONTEXTS" CHARACTER VARYING(255),
    "LABELS" CHARACTER VARYING(255),
    "DEPLOYMENT_ID" CHARACTER VARYING(10)
);
-- 8 +/- SELECT COUNT(*) FROM PUBLIC.DATABASECHANGELOG;
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('00000000000000', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', TIMESTAMP '2022-12-20 15:36:59.672955', 1, 'EXECUTED', '8:b8c27d9dc8db18b5de87cdb8c38a416b', 'createSequence sequenceName=sequence_generator', '', NULL, '4.15.0', NULL, NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', TIMESTAMP '2022-12-20 15:36:59.715614', 2, 'EXECUTED', '8:9452e8659f5e195517b11ac91ff810a8', 'createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...', '', NULL, '4.15.0', NULL, NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('20221220203608-1', 'jhipster', 'config/liquibase/changelog/20221220203608_added_entity_Race.xml', TIMESTAMP '2022-12-20 15:36:59.717009', 3, 'EXECUTED', '8:9e444ce1299016689290c3bedef3092d', 'createTable tableName=race', '', NULL, '4.15.0', NULL, NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('20221220203608-1-data', 'jhipster', 'config/liquibase/changelog/20221220203608_added_entity_Race.xml', TIMESTAMP '2022-12-20 15:36:59.720822', 4, 'EXECUTED', '8:65d5bd7443dd71d2efd91cde922b5152', 'loadData tableName=race', '', NULL, '4.15.0', 'faker', NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('20221220203609-1', 'jhipster', 'config/liquibase/changelog/20221220203609_added_entity_Run.xml', TIMESTAMP '2022-12-20 15:36:59.722048', 5, 'EXECUTED', '8:32b3b3db3aa78b1fafe1418b38cbf879', 'createTable tableName=run', '', NULL, '4.15.0', NULL, NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('20221220203609-1-data', 'jhipster', 'config/liquibase/changelog/20221220203609_added_entity_Run.xml', TIMESTAMP '2022-12-20 15:36:59.726375', 6, 'EXECUTED', '8:d5b992b25aef8875641c1060b805776d', 'loadData tableName=run', '', NULL, '4.15.0', 'faker', NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('20221220203608-2', 'jhipster', 'config/liquibase/changelog/20221220203608_added_entity_constraints_Race.xml', TIMESTAMP '2022-12-20 15:36:59.727925', 7, 'EXECUTED', '8:64f098b02c675479e33cc6b6014b8ee9', 'addForeignKeyConstraint baseTableName=race, constraintName=fk_race__user_id, referencedTableName=jhi_user', '', NULL, '4.15.0', NULL, NULL, '1568619567');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES('20221220203609-2', 'jhipster', 'config/liquibase/changelog/20221220203609_added_entity_constraints_Run.xml', TIMESTAMP '2022-12-20 15:36:59.729386', 8, 'EXECUTED', '8:27015c18b7a55669ea7c3397f4b78990', 'addForeignKeyConstraint baseTableName=run, constraintName=fk_run__user_id, referencedTableName=jhi_user', '', NULL, '4.15.0', NULL, NULL, '1568619567');
CREATE CACHED TABLE "PUBLIC"."JHI_USER"(
    "ID" BIGINT NOT NULL,
    "LOGIN" CHARACTER VARYING(50) NOT NULL,
    "PASSWORD_HASH" CHARACTER VARYING(60) NOT NULL,
    "FIRST_NAME" CHARACTER VARYING(50),
    "LAST_NAME" CHARACTER VARYING(50),
    "EMAIL" CHARACTER VARYING(191),
    "IMAGE_URL" CHARACTER VARYING(256),
    "ACTIVATED" BOOLEAN NOT NULL,
    "LANG_KEY" CHARACTER VARYING(10),
    "ACTIVATION_KEY" CHARACTER VARYING(20),
    "RESET_KEY" CHARACTER VARYING(20),
    "CREATED_BY" CHARACTER VARYING(50) NOT NULL,
    "CREATED_DATE" TIMESTAMP DEFAULT NULL,
    "RESET_DATE" TIMESTAMP,
    "LAST_MODIFIED_BY" CHARACTER VARYING(50),
    "LAST_MODIFIED_DATE" TIMESTAMP
);
ALTER TABLE "PUBLIC"."JHI_USER" ADD CONSTRAINT "PUBLIC"."PK_JHI_USER" PRIMARY KEY("ID");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.JHI_USER;
INSERT INTO "PUBLIC"."JHI_USER" VALUES(1, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', TRUE, 'en', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO "PUBLIC"."JHI_USER" VALUES(2, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'John', 'Wayne', 'user@localhost.com', '', TRUE, 'en', NULL, NULL, 'system', NULL, NULL, 'user', TIMESTAMP '2022-12-22 23:52:53.857021');
CREATE CACHED TABLE "PUBLIC"."JHI_AUTHORITY"(
    "NAME" CHARACTER VARYING(50) NOT NULL
);
ALTER TABLE "PUBLIC"."JHI_AUTHORITY" ADD CONSTRAINT "PUBLIC"."PK_JHI_AUTHORITY" PRIMARY KEY("NAME");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.JHI_AUTHORITY;
INSERT INTO "PUBLIC"."JHI_AUTHORITY" VALUES('ROLE_ADMIN');
INSERT INTO "PUBLIC"."JHI_AUTHORITY" VALUES('ROLE_USER');
CREATE CACHED TABLE "PUBLIC"."JHI_USER_AUTHORITY"(
    "USER_ID" BIGINT NOT NULL,
    "AUTHORITY_NAME" CHARACTER VARYING(50) NOT NULL
);
ALTER TABLE "PUBLIC"."JHI_USER_AUTHORITY" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("USER_ID", "AUTHORITY_NAME");
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.JHI_USER_AUTHORITY;
INSERT INTO "PUBLIC"."JHI_USER_AUTHORITY" VALUES(1, 'ROLE_ADMIN');
INSERT INTO "PUBLIC"."JHI_USER_AUTHORITY" VALUES(1, 'ROLE_USER');
INSERT INTO "PUBLIC"."JHI_USER_AUTHORITY" VALUES(2, 'ROLE_USER');
CREATE CACHED TABLE "PUBLIC"."RACE"(
    "ID" BIGINT NOT NULL,
    "RACE_NAME" CHARACTER VARYING(255) NOT NULL,
    "RACE_DATE" DATE,
    "RACE_DISTANCE" CHARACTER VARYING(255),
    "USER_ID" BIGINT
);
ALTER TABLE "PUBLIC"."RACE" ADD CONSTRAINT "PUBLIC"."PK_RACE" PRIMARY KEY("ID");
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.RACE;
INSERT INTO "PUBLIC"."RACE" VALUES(1, 'Honolulu Marathon', DATE '2023-12-10', 'MARATHON', 2);
INSERT INTO "PUBLIC"."RACE" VALUES(2, 'Delaware Running Festival', DATE '2023-04-22', 'HALF', 2);
INSERT INTO "PUBLIC"."RACE" VALUES(3, 'Tyler Brown''s I Hate to Run Memorial 5K & 10K', DATE '2023-01-08', 'TENK', 2);
INSERT INTO "PUBLIC"."RACE" VALUES(4, 'Fight Cancer with Running', DATE '2022-12-20', 'FIVEK', 1);
INSERT INTO "PUBLIC"."RACE" VALUES(1001, 'Burn off the Bird', DATE '2022-11-26', 'FIVEK', 1);
INSERT INTO "PUBLIC"."RACE" VALUES(1002, 'Burn off the Bird', DATE '2023-11-25', 'FIVEK', 2);
CREATE CACHED TABLE "PUBLIC"."RUN"(
    "ID" BIGINT NOT NULL,
    "RUN_NAME" CHARACTER VARYING(255) NOT NULL,
    "RUN_DATE" DATE NOT NULL,
    "DISTANCE" DOUBLE PRECISION NOT NULL,
    "TIME" DOUBLE PRECISION NOT NULL,
    "PACE" DOUBLE PRECISION,
    "USER_ID" BIGINT
);
ALTER TABLE "PUBLIC"."RUN" ADD CONSTRAINT "PUBLIC"."PK_RUN" PRIMARY KEY("ID");
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.RUN;
INSERT INTO "PUBLIC"."RUN" VALUES(1151, 'Fartlek', DATE '2022-12-23', 3.1, 24.5, 7.9, 1);
INSERT INTO "PUBLIC"."RUN" VALUES(1152, 'Christmas Run', DATE '2022-12-25', 5.0, 45.0, 9.0, 1);
INSERT INTO "PUBLIC"."RUN" VALUES(1201, '1.1', DATE '2022-12-19', 3.0, 30.0, 10.0, 2);
INSERT INTO "PUBLIC"."RUN" VALUES(1202, '1.2', DATE '2022-12-21', 2.0, 20.0, 10.0, 2);
INSERT INTO "PUBLIC"."RUN" VALUES(1251, '2.1', DATE '2023-01-01', 6.0, 60.0, 10.0, 2);
ALTER TABLE "PUBLIC"."JHI_USER" ADD CONSTRAINT "PUBLIC"."UX_USER_LOGIN" UNIQUE("LOGIN");
ALTER TABLE "PUBLIC"."JHI_USER" ADD CONSTRAINT "PUBLIC"."UX_USER_EMAIL" UNIQUE("EMAIL");
ALTER TABLE "PUBLIC"."RACE" ADD CONSTRAINT "PUBLIC"."FK_RACE__USER_ID" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."JHI_USER"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."JHI_USER_AUTHORITY" ADD CONSTRAINT "PUBLIC"."FK_USER_ID" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."JHI_USER"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."RUN" ADD CONSTRAINT "PUBLIC"."FK_RUN__USER_ID" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."JHI_USER"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."JHI_USER_AUTHORITY" ADD CONSTRAINT "PUBLIC"."FK_AUTHORITY_NAME" FOREIGN KEY("AUTHORITY_NAME") REFERENCES "PUBLIC"."JHI_AUTHORITY"("NAME") NOCHECK;