DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;

CREATE TABLE genres
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE AUTHORS
(
    id IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255)
);

CREATE TABLE books
(
    id IDENTITY NOT NULL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id BIGINT       NOT NULL,
    genre_id  BIGINT       NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT unique_book UNIQUE (title, author_id)
);