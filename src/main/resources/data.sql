INSERT INTO product_stock(name, quantity) VALUES ('A', 5);
INSERT INTO product_stock(name, quantity) VALUES ('B', 8);
INSERT INTO product_stock(name, quantity) VALUES ('C', 2);
INSERT INTO product_stock(name, quantity) VALUES ('D', 0);
INSERT INTO product_stock(name, quantity) VALUES ('E', 1);

INSERT INTO product_rule(name, min_qty, blocked, min_order) VALUES ('A', 4, false, 1);
INSERT INTO product_rule(name, min_qty, blocked, min_order) VALUES ('B', 4, false, 1);
INSERT INTO product_rule(name, min_qty, blocked, min_order) VALUES ('C', 4, true, 1);
INSERT INTO product_rule(name, min_qty, blocked, min_order) VALUES ('D', 8, false, 15);
INSERT INTO product_rule(name, min_qty, blocked, min_order) VALUES ('E', 4, false, 1);