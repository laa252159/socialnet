-- Table: "PERSONS"

-- DROP TABLE "PERSONS";

CREATE TABLE "PERSONS"
(
  id bigserial NOT NULL,
  login character varying(20)[] NOT NULL,
  password character varying(20)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "PERSONS"
  OWNER TO postgres;
GRANT ALL ON TABLE "PERSONS" TO postgres;
GRANT ALL ON TABLE "PERSONS" TO public;
