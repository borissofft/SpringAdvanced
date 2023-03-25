
-- Init Partners
INSERT INTO partners(id, name, description, partner_url)
VALUES (1, 'Община София', 'Област София', 'www.sofia.bg'),
       (2, 'Община Ловеч', 'Област Ловеч', 'www.lovech.bg'),
       (3, 'Община Бургас', 'Област Бургас', 'www.burgas.bg');

-- Init Blog articles
INSERT INTO articles(id, title, content, image_url)
VALUES (1, 'Тема 1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'https://res.cloudinary.com/dbhvunrvo/image/upload/v1678950459/Capture_1_sm7u7s.jpg'),
       (2, 'Тема 2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'https://res.cloudinary.com/dbhvunrvo/image/upload/v1678913649/cld-sample-2.jpg'),
       (3, 'Тема 3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'https://res.cloudinary.com/dbhvunrvo/image/upload/v1678913638/samples/food/spices.jpg'),
       (4, 'Тема 4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'https://res.cloudinary.com/dbhvunrvo/image/upload/v1678913631/samples/landscapes/girl-urban-view.jpg');



# INSERT INTO psychologists(first_name, last_name, email, description, picture_id)