CREATE TABLE films (
  id          INT          NOT NULL,
  title       VARCHAR(100) NOT NULL,
  duration    INT          NOT NULL,
  year        INT          NOT NULL,
  description VARCHAR(1000),
  director_id INT          NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (director_id) REFERENCES directors (id) ON DELETE CASCADE
);