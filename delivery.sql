CREATE TABLE food
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    price       INT          NOT NULL,
    description VARCHAR(500),
    PRIMARY KEY (id)
);

INSERT INTO food
VALUES (NULL, "pizza", 16900, "nice pizza");

CREATE TABLE store (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       PRIMARY KEY (id)
);
