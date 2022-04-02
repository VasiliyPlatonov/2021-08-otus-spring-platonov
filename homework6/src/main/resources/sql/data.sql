INSERT INTO genres (id, name)
VALUES (1, 'thriller'),
       (2, 'drama'),
       (3, 'psychological fiction'),
       (4, 'poem');


INSERT INTO authors (id, first_name, last_name)
VALUES (1, 'Stephen', 'King'),
       (2, 'Mikhail', 'Lermontov');

INSERT INTO books (id, title)
VALUES (1, 'The Green Mile'),
       (2, 'Rita Hayworth and Shawshank Redemption'),
       (3, 'A Hero of Our Time'),
       (4, 'Demon');

INSERT INTO book_comments (id, text, book_id)
VALUES (1, 'That`s awesome', 1),
       (2, 'That`s awesome', 1),
       (3, 'That`s awesome', 1),
       (4, 'That`s awesome', 2),
       (5, 'That`s awesome', 2),
       (6, 'That`s awesome', 3);



INSERT INTO books_authors (book_id, author_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2);

INSERT INTO books_genres (book_id, genre_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);