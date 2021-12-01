DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
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

--     todo: что делать с таблицей books_authors если из этой таблицы удаляется книга?
CREATE TABLE books
(
    id    IDENTITY     NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
--     FOREIGN KEY (author_id) REFERENCES authors (id)
--         ON DELETE CASCADE
--         ON UPDATE CASCADE,
--     FOREIGN KEY (genre_id) REFERENCES genres (id)
--         ON DELETE CASCADE
--         ON UPDATE CASCADE,
--     CONSTRAINT unique_book UNIQUE (title, author_id)
);

--     todo: как обеспечить уникальность (название книги && авторы)?
CREATE TABLE books_authors
(
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (author_id) REFERENCES authors (id)
);


CREATE TABLE books_genres
(
    book_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
)
