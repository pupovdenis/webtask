insert into books (id, author, is_private, name, release_date)
values (nextval('books_seq'), 'Нушкин', true, 'Что я делал у дамы', (TIMESTAMP '2019-02-15 18:22:11.000+02:00'));

insert into books (id, author, is_private, name, release_date)
values (nextval('books_seq'), 'Ноголь', true, 'Где найти ревизора', (TIMESTAMP '2020-02-15 03:22:11.000+02:00'));

insert into books (id, author, is_private, name, release_date)
values (nextval('books_seq'), 'Нолстой', false, 'Мир в войне', (TIMESTAMP '2021-02-15 08:22:11.871+02:00'));

insert into books (id, author, is_private, name, release_date)
values (nextval('books_seq'), 'Нончаров', false, 'Счастье от ума', (TIMESTAMP '2022-02-15 10:22:11.871+02:00'));

insert into books (id, author, is_private, name, release_date)
values (nextval('books_seq'), 'Нермонтов', false, 'Пленник Кавказа', (TIMESTAMP '2018-02-15 16:22:11.871+02:00'));
