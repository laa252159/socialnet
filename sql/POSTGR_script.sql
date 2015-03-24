-- Sequence: "PERSONS_id_seq"

-- DROP SEQUENCE "PERSONS_id_seq";

CREATE SEQUENCE "PERSONS_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 60
  CACHE 1;
ALTER TABLE "PERSONS_id_seq"
  OWNER TO postgres;

  
  -- Sequence: album_id_seq

-- DROP SEQUENCE album_id_seq;

CREATE SEQUENCE album_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE album_id_seq
  OWNER TO postgres;

  
  
  -- Sequence: photo_id_seq

-- DROP SEQUENCE photo_id_seq;

CREATE SEQUENCE photo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE photo_id_seq
  OWNER TO postgres;

  
  -- Sequence: message_id_seq

-- DROP SEQUENCE message_id_seq;

CREATE SEQUENCE message_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 175
  CACHE 1;
ALTER TABLE message_id_seq
  OWNER TO postgres;




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
  has_been_read boolean DEFAULT false
)
WITH (
  OIDS=FALSE
);
ALTER TABLE messages
  OWNER TO postgres;
  
  
  
  -- Table: albums

-- DROP TABLE albums;

CREATE TABLE albums
(
  id bigint NOT NULL DEFAULT nextval('album_id_seq'::regclass),
  person_id bigint NOT NULL,
  name character varying(128),
  description character varying(256),
  CONSTRAINT albums_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE albums
  OWNER TO postgres;
  
  
  
  
  
-- Table: photos

-- DROP TABLE photos;

CREATE TABLE photos
(
  id bigint NOT NULL DEFAULT nextval('photo_id_seq'::regclass),
  album_id bigint NOT NULL,
  file_name character varying(50),
  name character varying(50),
  description character varying(200),
  upload_date date,
  img bytea,
  img_preview bytea
)
WITH (
  OIDS=FALSE
);
ALTER TABLE photos
  OWNER TO postgres;

