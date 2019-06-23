CREATE USER IF NOT EXISTS "SA" SALT '1053461345fef889' HASH 'ef9eb0df2e4232c7f2f34cc39b3b8471bcc43a7d83e95ec5d9ddc9c625b60dfe' ADMIN;

CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_1A4B97F9_FC58_4A6A_8C7F_B2EBD0DC19F5" START WITH 1 BELONGS_TO_TABLE;

CREATE MEMORY TABLE "PUBLIC"."TBL_POSTS"(
    "ID" BIGINT DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_1A4B97F9_FC58_4A6A_8C7F_B2EBD0DC19F5") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_1A4B97F9_FC58_4A6A_8C7F_B2EBD0DC19F5",
    "CREATED_BY" VARCHAR(255),
    "CREATED_DATE" TIMESTAMP,
    "CONTENT" VARCHAR(255),
    "STATE" VARCHAR(255),
    "TITLE" VARCHAR(255)
);

ALTER TABLE "PUBLIC"."TBL_POSTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY("ID");
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TBL_POSTS;
ALTER TABLE "PUBLIC"."TBL_POSTS" ADD CONSTRAINT "PUBLIC"."UK_8UJCNHFIL4ODNUEVQOUVYL0XL" UNIQUE("TITLE");
