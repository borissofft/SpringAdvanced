-- some test users

# INSERT INTO user_roles (id, user_role)
# values
#     (1, 'ADMIN'),
#     (2, 'USER');

INSERT INTO users (id, username, first_name, last_name, image_url, is_active, password)
VALUES
    (1, 'admin@example.com', 'Admin', 'Adminov', null, 1, '57e7759fd2d59275fc3c3cd5dd2ace5013b39ee972999412f3f5f5c3382b6765c2571ef86648abe2'),
    (2, 'user@example.com', 'User', 'Userov', null, 1, '57e7759fd2d59275fc3c3cd5dd2ace5013b39ee972999412f3f5f5c3382b6765c2571ef86648abe2');

INSERT INTO brands (id, name)
VALUES (1, 'Ford'),
       (2, 'Toyota');

INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, 'Fiesta', 1, 1976, null, 1, 'https://upload.wikimedia.org/wikipedia/commons/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg'),
       (2, 'Escort', 1, 1968, 2000, 1, 'https://www.auto-data.net/images/f110/Ford-Escort-VII-Hatch-GAL-AFL.jpg'),
       (3, 'Yaris', 1, 1999, null, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg/1280px-2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg');

