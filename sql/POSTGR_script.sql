-- Table: persons

-- DROP TABLE persons;

CREATE TABLE persons
(
  id bigint NOT NULL DEFAULT nextval('"PERSONS_id_seq"'::regclass),
  login character varying(20) NOT NULL,
  password character varying(64),
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
ALTER TABLE persons
  OWNER TO postgres;
GRANT ALL ON TABLE persons TO postgres;
GRANT ALL ON TABLE persons TO public;



-- Table: friendship

-- DROP TABLE friendship;

CREATE TABLE friendship
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
ALTER TABLE friendship
  OWNER TO postgres;

-- Index: "FRIENDSHIP_first_person_id_second_person_id_idx"

-- DROP INDEX "FRIENDSHIP_first_person_id_second_person_id_idx";

CREATE INDEX "FRIENDSHIP_first_person_id_second_person_id_idx"
  ON friendship
  USING btree
  (first_person_id, second_person_id);


  
  -- Table: messages

-- DROP TABLE messages;

CREATE TABLE messages
(
  id bigint DEFAULT nextval('message_id_seq'::regclass),
  senderid bigint NOT NULL,
  receiverid bigint NOT NULL,
  messagedate time without time zone NOT NULL,
  value character varying(200),
  has_bean_read boolean DEFAULT false
)
WITH (
  OIDS=FALSE
);
ALTER TABLE messages
  OWNER TO postgres;
