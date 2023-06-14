use myBlog;
insert into role (mean)
VALUES ('User'), ('Admin');

insert into user (first_name, last_name, avatar, login, password, role_id)
VALUES ('Oleg', 'Luchenko', 'none', 'admin', 'admin', 2),
       ('Vitek', 'Verepianko', 'none', 'vitek', '123', 1),
       ('Anna', 'Yaschenko', 'none', 'anna', '321', 1),
	   ('Alex', 'Semenyuk', 'none', 'alex', '111', 2);


insert into draft (mean)
VALUES (true), (false);

insert into post (title, published, author_id, image_path, content, draft_id)
VALUES ('Garden', '16.05.2023 11:22:38', 1, 'resources/images/garden - 1.jpg', 'My Garden', 1),
       ('My walk', '16.05.2023 11:22:38', 1, 'resources/images/my walk - 1.jpg', '<p>What do I do during my walk?</p>', 2),
       ('My hobby', '17.05.2023 11:51:51', 1, 'resources/images/hobby - 1.jpg', '<p style="text-align: left;">I play in <strong>chess</strong> when I have a <strong><em>lot of free time</em></strong></p>', 2),
	   ('My future profession', '17.05.2023 13:26:02', 1, 'resources/images/future profession - 1.jpg', '<p>What <strong>will I do</strong> then <strong><em>I became programist</em></strong>?</p>', 2),
	   ('My family', '17.05.2023 13:46:54', 1, 'resources/images/my family - 1.jpg', '<p>I spend free time with <strong>my family</strong></p>', 2),
       ('This is my family', '17.05.2023 13:53:39', 4, 'resources/images/my family - 2.jpg', '<p>Only photo</p>', 2),
       ('Spring', '22.05.2023 17:29:12', 4, 'resources/images/Spring - 1.jpg', '<p>Spring is the <strong>most beautiful</strong> season</p>', 2),
       ('Autumn', '22.05.2023 17:33:45', 4, 'resources/images/Autumn - 1.jpg', '<p>Autumn is <em><strong>the brightest</strong></em> season</p>', 2),
       ('Sunshine', '23.05.2023 22:12:01', 4, 'resources/images/Sunshine - 1.jpg', '<p>Very beautiful</p>', 2),
       ('Town In Mountain', '23.05.2023 22:18:37', 4, 'resources/images/Town In Mountain - 1.jpg', '<div><em><strong>Very beautiful place</strong></em></div>', 2),
       ('beautiful valley', '24.05.2023 21:15:16', 4, 'resources/images/beautiful valley - 1.jpg', '<p>only photo</p>', 2),
       ('General Grant Tree', '24.05.2023 21:16:41', 4, 'resources/images/General Grant Tree - 1.jpg', '<p>only photo</p>', 2),
       ('Generals Highway', '24.05.2023 21:18:19', 4, 'resources/images/Generals Highway - 1.jpg', '<p>only photo</p>', 2),
       ('Canyon', '24.05.2023 21:20:10', 4, 'resources/images/Canyon - 1.jpg', '<p>only photo</p>', 2),
       ('Narrows gorge', '24.05.2023 21:22:17', 4, 'resources/images/Narrows - 1.jpg', '<p>only photo</p>', 2);

select * from user where login='admin' and password='admin';

select * from user;
SELECT * FROM users WHERE login = 'admin' and password = 'admin';
SELECT * FROM posts order by id desc;

SELECT * FROM posts
         WHERE draft_id = 2
         ORDER BY id DESC LIMIT 2 OFFSET 2;

SELECT COUNT(*) 'count_posts' FROM posts WHERE draft_id = 2;

insert into post (title, published, author_id, image_path, content, draft_id)
VALUES ('Garden', '16.05.2023 11:22:38', 1, 'resources/images/garden - 1.jpg', 'My Garden', 1),
       ('My walk', '16.05.2023 11:22:38', 1, 'resources/images/my walk - 1.jpg', '<p>What do I do during my walk?</p>', 2),
       ('My hobby', '17.05.2023 11:51:51', 1, 'resources/images/hobby - 1.jpg', '<p style="text-align: left;">I play in <strong>chess</strong> when I have a <strong><em>lot of free time</em></strong></p>', 2),
       ('My future profession', '17.05.2023 13:26:02', 1, 'resources/images/future profession - 1.jpg', '<p>What <strong>will I do</strong> then <strong><em>I became programist</em></strong>?</p>', 2),
       ('My family', '17.05.2023 13:46:54', 1, 'resources/images/my family - 1.jpg', '<p>I spend free time with <strong>my family</strong></p>', 2),
       ('This is my family', '17.05.2023 13:53:39', 4, 'resources/images/my family - 2.jpg', '<p>Only photo</p>', 2),
       ('Spring', '22.05.2023 17:29:12', 4, 'resources/images/Spring - 1.jpg', '<p>Spring is the <strong>most beautiful</strong> season</p>', 2),
       ('Autumn', '22.05.2023 17:33:45', 4, 'resources/images/Autumn - 1.jpg', '<p>Autumn is <em><strong>the brightest</strong></em> season</p>', 2),
       ('Sunshine', '23.05.2023 22:12:01', 4, 'resources/images/Sunshine - 1.jpg', '<p>Very beautiful</p>', 2),
       ('Town In Mountain', '23.05.2023 22:18:37', 4, 'resources/images/Town In Mountain - 1.jpg', '<div><em><strong>Very beautiful place</strong></em></div>', 2),
       ('beautiful valley', '24.05.2023 21:15:16', 4, 'resources/images/beautiful valley - 1.jpg', '<p>only photo</p>', 2),
       ('General Grant Tree', '24.05.2023 21:16:41', 4, 'resources/images/General Grant Tree - 1.jpg', '<p>only photo</p>', 2),
       ('Generals Highway', '24.05.2023 21:18:19', 4, 'resources/images/Generals Highway - 1.jpg', '<p>only photo</p>', 2),
       ('Canyon', '24.05.2023 21:20:10', 4, 'resources/images/Canyon - 1.jpg', '<p>only photo</p>', 2),
       ('Narrows gorge', '24.05.2023 21:22:17', 4, 'resources/images/Narrows - 1.jpg', '<p>only photo</p>', 2);

SELECT * FROM post ORDER BY id DESC LIMIT 3 OFFSET 0;



