-- https://dbdiagram.io/d/Delivery-663311fb5b24a634d04b36ea

-- 0 -> 판매중
-- 1 -> 품절

CREATE TABLE food (
                      food_id INT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      price INT NOT NULL,
                      description VARCHAR(500),
                      status ENUM('0', '1') DEFAULT '0',
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                      PRIMARY KEY (food_id)
);

CREATE TABLE store (
                       store_id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       category_id INT,
                       phone_number VARCHAR(20),
                       operating_hours VARCHAR(100),
                       min_order_amount INT,
                       rating DECIMAL(3, 2),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                       PRIMARY KEY (store_id)
);

CREATE TABLE orders (
                        order_id INT PRIMARY KEY AUTO_INCREMENT,
                        member_id INT,
                        store_id INT,
                        delivery_request VARCHAR(50),
                        payment_method VARCHAR(50),
                        amount DECIMAL(10, 2),
                        time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status ENUM('ORDER_CONFIRMED', 'COOKING', 'DELIVERED', 'ON_DELIVERY'),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (member_id) REFERENCES members(member_id),
                        FOREIGN KEY (store_id) REFERENCES store(store_id)
);

CREATE TABLE members (
                         member_id INT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(50) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                         role ENUM('MEMBER', 'SHOP_OWNER', 'ADMIN') DEFAULT 'MEMBER',
                         phone_number VARCHAR(20),
                         address VARCHAR(255)
);

CREATE TABLE shoppingCart (
                              cart_id INT PRIMARY KEY AUTO_INCREMENT,
                              member_id INT UNIQUE,
                              menu_id INT,
                              quantity INT,
                              price INT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (member_id) REFERENCES members(member_id),
                              FOREIGN KEY (menu_id) REFERENCES food(food_id)
);


CREATE TABLE review (
                        review_id INT PRIMARY KEY AUTO_INCREMENT,
                        member_id INT,
                        store_id INT,
                        rating DECIMAL(2, 1) CHECK (rating >= 1 AND rating <= 5),
                        content VARCHAR(500),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (member_id) REFERENCES members(member_id),
                        FOREIGN KEY (store_id) REFERENCES store(store_id)
);

CREATE TABLE store_category (
                                category_id INT PRIMARY KEY AUTO_INCREMENT,
                                name VARCHAR(100) NOT NULL
);

CREATE TABLE member_address (
                                address_id INT PRIMARY KEY AUTO_INCREMENT,
                                member_id INT,
                                address VARCHAR(255) NOT NULL,
                                is_default_address BOOLEAN DEFAULT FALSE,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (member_id) REFERENCES members(member_id)
);

CREATE TABLE member_shopping_cart (
                                      cart_id INT PRIMARY KEY,
                                      member_id INT UNIQUE,
                                      FOREIGN KEY (cart_id) REFERENCES shoppingCart(cart_id),
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                      FOREIGN KEY (member_id) REFERENCES members(member_id)
);

CREATE TABLE shopping_cart_item (
                                    item_id INT PRIMARY KEY AUTO_INCREMENT,
                                    cart_id INT,
                                    menu_id INT,
                                    quantity INT,
                                    price INT,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                    FOREIGN KEY (cart_id) REFERENCES shoppingCart(cart_id),
                                    FOREIGN KEY (menu_id) REFERENCES food(food_id)
);