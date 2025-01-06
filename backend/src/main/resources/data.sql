INSERT INTO users (name, first_name, last_name, email, phone_number, 
                  street_address, city, state, country, postal_code) 
VALUES 
('Carlos García', 'Carlos', 'García', 'carlos.garcia@example.com', '+34612345678',
    'Calle Gran Vía 25', 'Madrid', 'Madrid', 'España', '28013'),

('María López', 'María', 'López', 'maria.lopez@example.com', '+34623456789',
    'Avenida Diagonal 123', 'Barcelona', 'Barcelona', 'España', '08008'),

('Antonio Fernández', 'Antonio', 'Fernández', 'antonio.fernandez@example.com', '+34634567890',
    'Calle Sierpes 45', 'Sevilla', 'Sevilla', 'España', '41004'),

('Laura Martínez', 'Laura', 'Martínez', 'laura.martinez@example.com', '+34645678901',
    'Plaza Mayor 12', 'Salamanca', 'Salamanca', 'España', '37002'),

('Javier Rodríguez', 'Javier', 'Rodríguez', 'javier.rodriguez@example.com', '+34656789012',
    'Calle Príncipe 78', 'Vigo', 'Pontevedra', 'España', '36202'),

('Ana Sánchez', 'Ana', 'Sánchez', 'ana.sanchez@example.com', '+34667890123',
    'Avenida de la Constitución 34', 'Valencia', 'Valencia', 'España', '46002'),

('David Moreno', 'David', 'Moreno', 'david.moreno@example.com', '+34678901234',
    'Paseo de Gracia 89', 'Barcelona', 'Barcelona', 'España', '08008'),

('Carmen Ruiz', 'Carmen', 'Ruiz', 'carmen.ruiz@example.com', '+34689012345',
    'Calle Alfonso X 56', 'Murcia', 'Murcia', 'España', '30001'),

('Miguel Torres', 'Miguel', 'Torres', 'miguel.torres@example.com', '+34690123456',
    'Plaza Nueva 23', 'Bilbao', 'Vizcaya', 'España', '48001'),

('Isabel Navarro', 'Isabel', 'Navarro', 'isabel.navarro@example.com', '+34601234567',
    'Rambla Catalunya 67', 'Barcelona', 'Barcelona', 'España', '08007');

    
    
    INSERT INTO category (name, description) VALUES 
('Rings', 'Gold, silver, and diamond rings'),
('Necklaces', 'Necklaces in gold, silver, and precious stones'),
('Bracelets', 'Gold, silver, and gem-encrusted bracelets'),
('Earrings', 'Variety of earrings: studs, hoops, and dangly');
    
INSERT INTO products (name, description, price, category_id, stock) VALUES
('Gold Ring', 'A beautiful gold ring', 199.99, 1, 50),
('Diamond Ring', 'An elegant diamond ring', 999.99, 1, 25),
('Gold Necklace', 'Gold necklace with a pendant', 299.99, 2, 40),
('Silver Necklace', 'A simple silver necklace', 149.99, 2, 75),
('Silver Bracelet', 'A delicate silver bracelet', 99.99, 3, 100),
('Gem-encrusted Bracelet', 'Bracelet with rubies and diamonds', 499.99, 3, 15),
('Gold Earrings', 'Elegant gold earrings', 129.99, 4, 60),
('Diamond Earrings', 'Earrings with diamond studs', 599.99, 4, 30);


    
    INSERT INTO orders (user_id, order_date, status, total) 
VALUES
-- Orders for Carlos García (user_id = 1)
(1, '2024-01-15 10:30:00', 'COMPLETED', 150.99),
(1, '2024-02-01 15:45:00', 'PENDING', 89.99),

-- Orders for María López (user_id = 2)
(2, '2024-01-20 09:15:00', 'COMPLETED', 245.50),
(2, '2024-02-02 14:20:00', 'COMPLETED', 175.25),
(2, '2024-02-10 11:30:00', 'PENDING', 99.99),

-- Orders for Antonio Fernández (user_id = 3)
(3, '2024-01-25 16:40:00', 'COMPLETED', 325.75),
(3, '2024-02-05 13:10:00', 'CANCELLED', 199.99),

-- Orders for Laura Martínez (user_id = 4)
(4, '2024-01-28 12:20:00', 'COMPLETED', 145.50),
(4, '2024-02-08 17:30:00', 'PENDING', 289.99),

-- Orders for Javier Rodríguez (user_id = 5)
(5, '2024-01-30 11:45:00', 'COMPLETED', 199.99),
(5, '2024-02-09 10:15:00', 'PENDING', 159.99),

-- Orders for Ana Sánchez (user_id = 6)
(6, '2024-02-01 14:25:00', 'COMPLETED', 445.25),
(6, '2024-02-10 16:20:00', 'SHIPPED', 299.99),

-- Orders for David Moreno (user_id = 7)
(7, '2024-02-03 09:30:00', 'COMPLETED', 189.99),
(7, '2024-02-11 13:45:00', 'PENDING', 259.99),

-- Orders for Carmen Ruiz (user_id = 8)
(8, '2024-02-04 15:50:00', 'COMPLETED', 159.99),

-- Orders for Miguel Torres (user_id = 9)
(9, '2024-02-06 12:40:00', 'COMPLETED', 429.99),
(9, '2024-02-12 11:20:00', 'SHIPPED', 179.99),

-- Orders for Isabel Navarro (user_id = 10)
(10, '2024-02-07 10:35:00', 'COMPLETED', 269.99),
(10, '2024-02-13 15:15:00', 'PENDING', 199.99);

INSERT INTO order_details(order_id,product_id,quantity,unit_price) VALUES(1, 1, 10, 199.99);