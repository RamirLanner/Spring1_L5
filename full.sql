BEGIN;


DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost float);
INSERT INTO products (title, cost) VALUES
('Apple', 12.3),
('Orange', 16.4),
('Sweet Roll', 5.21),
('Moon Sugar', 66.3),
('Skooma', 77.88),
('Mace of Molag Bal', 100.3),
('Skeleton Key', 560.35),
('Wabbajack', 999.99),
('Azura''s Star', 66.6),
('Mehrune''s Razor', 95.23),
('Sanguine Rose', 54.32),
('Spellbreaker', 88.88);
COMMIT;