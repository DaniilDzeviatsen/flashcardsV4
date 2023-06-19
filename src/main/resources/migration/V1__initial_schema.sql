CREATE TABLE chapter
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE card
(
    id            BIGSERIAL PRIMARY KEY,
    question      TEXT    NOT NULL,
    answer        TEXT    NOT NULL,
    is_remembered BOOLEAN NOT NULL,
    chapter_id    BIGINT  NOT NULL REFERENCES chapter ON DELETE CASCADE
);