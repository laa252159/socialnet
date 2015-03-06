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
  photo bytea[]
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "PERSONS"
  OWNER TO postgres;
GRANT ALL ON TABLE "PERSONS" TO postgres;
GRANT ALL ON TABLE "PERSONS" TO public;
