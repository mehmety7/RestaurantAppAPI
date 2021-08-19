
INSERT INTO cities (id, name) VALUES (34, 'İstanbul');

INSERT INTO counties (id, name, city_id) VALUES (116, 'Bakırköy', 34);
INSERT INTO counties (id, name, city_id) VALUES (855, 'Şile', 34);
INSERT INTO counties (id, name, city_id) VALUES (896, 'Tuzla', 34);

INSERT INTO users (id, email, name, password) VALUES (1, 'ali@gmail.com', 'Ali Aslan', '$2y$12$61jkNQAcw2/TyKa.9OrCQ./.AkFx3HuJDjvjeFswBr3VcDf7gZWzO'); -- password = ali1212
INSERT INTO users (id, email, name, password) VALUES (2, 'veli@gmail.com', 'Veli Kaplan', '$2y$12$l8nPw/jeIWidXRK0KdpBv.gvrfj5R6yiYl3ZRuq7eVAOL1czvRdAW'); -- password = veli1212
INSERT INTO users (id, email, name, password) VALUES (3, 'ayse@gmail.com', 'Ayşe Gül', '$2y$12$1Liik9erndjj6luicv9BF.GMKShhvZdZnPGkjaRT8V7lAw7rdefIW'); -- password = ayse1212

INSERT INTO user_role (id, role) VALUES (3, 'USER');
INSERT INTO user_role (id, role) VALUES (2, 'SELLER');
INSERT INTO user_role (id, role) VALUES (1, 'ADMIN');

INSERT INTO restaurants (id, name, status, user_id) values (1, 'Kral Burger', 'APPROVED', 2);
INSERT INTO restaurants (id, name, status, user_id) values (2, 'Lezzet Evi', 'WAITING', 2);

INSERT INTO branches (id, name, restaurant_id) values (1, 'Kral Burger Şile', 1);
INSERT INTO branches (id, name, restaurant_id) values (2, 'Kral Burger Tuzla', 1);

INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable) VALUES (1, NULL, 2, 'Veli Şirket', 34, 896, 'Merkez', '100. Sokak No 15 D1', true);
INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable) VALUES (2, NULL, 3, 'Ayse Ev 1', 34, 855, 'Ağva', '151. Sokak No 20 D3', false);
INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable) VALUES (5, NULL, 3, 'Ayse Ev 2', 34, 116, 'İncirli', '160. Sokak No 18 D10', true);
INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable) VALUES (3, 1, NULL, 'Kral Burger Şile', 34, 855, 'Ağva', '100. Sokak No 1', true);
INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable) VALUES (4, 2, NULL, 'Kral Burger Tuzla', 34, 896, 'Merkez', '500. Sokak No 10', true);

INSERT INTO items (id, name, unit_type) values ( 1, 'Hamburger', 'piece' );
INSERT INTO items (id, name, unit_type) values ( 2, 'Cheeseburger', 'piece' );
INSERT INTO items (id, name, unit_type) values ( 3, 'Ayran', 'piece' );
INSERT INTO items (id, name, unit_type) values ( 4, 'Kola', 'piece' );

INSERT INTO menus (id, branch_id) values (1, 1);

INSERT INTO meals (id, name, price, menu_id) values ( 1, 'Kral Hamburger', 20.00, 1 );
INSERT INTO meals (id, name, price, menu_id) values ( 2, 'Kral Cheeseburger', 25.00, 1 );
INSERT INTO meals (id, name, price, menu_id) values ( 3, 'Kral Menu', 45.00, 1 ); -- Ayran cheeseburger hamburger

INSERT INTO meal_item (meal_id, item_id) values (1,1);
INSERT INTO meal_item (meal_id, item_id) values (2,2);
INSERT INTO meal_item (meal_id, item_id) values (3,1);
INSERT INTO meal_item (meal_id, item_id) values (3,2);
INSERT INTO meal_item (meal_id, item_id) values (3,3);





