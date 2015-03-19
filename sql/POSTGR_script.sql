 -- Table: "PERSONS"

-- DROP TABLE "PERSONS";

CREATE TABLE "PERSONS"
(
  id bigserial NOT NULL,
  login character varying(20) NOT NULL,
  password character varying(20),
  fn character varying(50),
  ln character varying(50),
  dob date,
  phone character varying(20),
  address character varying(200),
  photo bytea
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "PERSONS"
  OWNER TO postgres;
GRANT ALL ON TABLE "PERSONS" TO postgres;
GRANT ALL ON TABLE "PERSONS" TO public;




-- Table: "FRIENDSHIP"

-- DROP TABLE "FRIENDSHIP";

CREATE TABLE "FRIENDSHIP"
(
  first_person_id bigint NOT NULL,
  second_person_id bigint NOT NULL,
  date_of_friendship_starting date,
  approve boolean NOT NULL DEFAULT false,
  CONSTRAINT "FRIENDSHIP_first_person_id_second_person_id_key" UNIQUE (first_person_id, second_person_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "FRIENDSHIP"
  OWNER TO postgres;

-- Index: "FRIENDSHIP_first_person_id_second_person_id_idx"

-- DROP INDEX "FRIENDSHIP_first_person_id_second_person_id_idx";

CREATE INDEX "FRIENDSHIP_first_person_id_second_person_id_idx"
  ON "FRIENDSHIP"
  USING btree
  (first_person_id, second_person_id);

  
  

-- Constraint: "FRIENDSHIP_first_person_id_second_person_id_key"

-- ALTER TABLE "FRIENDSHIP" DROP CONSTRAINT "FRIENDSHIP_first_person_id_second_person_id_key";

ALTER TABLE "FRIENDSHIP"
  ADD CONSTRAINT "FRIENDSHIP_first_person_id_second_person_id_key" UNIQUE(first_person_id, second_person_id);


  
  
  
-- Index: "FRIENDSHIP_first_person_id_second_person_id_idx"

-- DROP INDEX "FRIENDSHIP_first_person_id_second_person_id_idx";

CREATE INDEX "FRIENDSHIP_first_person_id_second_person_id_idx"
  ON "FRIENDSHIP"
  USING btree
  (first_person_id, second_person_id);


  
-- Table: "MESSAGES"

-- DROP TABLE "MESSAGES";

CREATE TABLE "MESSAGES"
(
  id bigint DEFAULT nextval('message_id_seq'::regclass),
  "senderId" bigint NOT NULL,
  "receiverId" bigint NOT NULL,
  "messageDate" time without time zone NOT NULL,
  value character varying(200),
  "hasBeanRead" boolean
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "MESSAGES"
  OWNER TO postgres;


  
  