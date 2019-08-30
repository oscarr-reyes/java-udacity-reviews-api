CREATE TABLE products (
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reviews (
    id          INT           NOT NULL AUTO_INCREMENT,
    text        VARCHAR(1000) NOT NULL,
    products_id INT           NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT products_reviews_fk FOREIGN KEY (products_id) REFERENCES products (id)
);

CREATE TABLE comments (
    id         INT           NOT NULL AUTO_INCREMENT,
    text       VARCHAR(1000) NOT NULL,
    reviews_id INT           NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT reviews_comments_fk FOREIGN KEY (reviews_id) REFERENCES reviews (id)
);

