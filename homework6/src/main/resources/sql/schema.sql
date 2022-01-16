DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS book_comments;
DROP TABLE IF EXISTS books_authors;
DROP TABLE IF EXISTS books_genres;

CREATE TABLE genres
(
    id   IDENTITY     NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE authors
(
    id         IDENTITY     NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255)
);

CREATE TABLE books
(
    id    IDENTITY     NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE book_comments
(
    id      IDENTITY NOT NULL PRIMARY KEY,
    text    VARCHAR  NOT NULL,
    book_id BIGINT   NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE books_authors
(
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    UNIQUE (book_id, author_id)
);


CREATE TABLE books_genres
(
    book_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
)
