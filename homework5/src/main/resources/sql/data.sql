INSERT INTO genres (id, name)
VALUES (1, 'thriller'),
       (2, 'drama'),
       (3, 'psychological fiction'),
       (4, 'poem');


INSERT INTO authors (id, first_name, last_name)
VALUES (1, 'Stephen', 'King'),
       (2, 'Mikhail', 'Lermontov');

INSERT INTO books (id, title, author_id, genre_id)
VALUES (1, 'The Green Mile',  1, 1),
       (2, 'Rita Hayworth and Shawshank Redemption', 1, 2),
       (3, 'A Hero of Our Time', 2, 3),
       (4, 'Demon', 2, 4);
