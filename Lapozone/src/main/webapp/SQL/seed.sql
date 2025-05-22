-- seed.sql

START TRANSACTION;

INSERT INTO `carts` (`product_id`, `user_id`, `quantity`, `cart_id`) VALUES
('PRD009', 'ashim123', '1', 'CRD001'),
('PRD010', 'sayal0321', '2', 'CRD001'),
('PRD002', 'bihesh123', '2', 'CRD001');

INSERT INTO `orders` (`Order_ID`, `User_ID`, `Total_Amount`, `Status`, `city`, `address`, `payment`) VALUES
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'Ayub_bhatta1', 1680.00, 'Pending', 'kathmandu', 'Kamalpokhari', 'Khalti');

INSERT INTO `order_products` (`Order_ID`, `Product_ID`, `Line_Quantity`) VALUES
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'PRD002', 2),
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'PRD009', 1),
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'PRD010', 2);

INSERT INTO `products` (
    `Product_ID`, `Product_Name`, `Screen_Size`, `Processor`, `RAM`, `Features`, `Discount`, `Storage`, `Warranty`, `price`, `Image`
) VALUES
('PRD011', 'Dell Inspiron 15', '15.6 inch', 'Intel i7 11th Gen', '16GB', 'Backlit Keyboard, Fingerprint Reader', 10, '512GB SSD', '1 Year', 1200.00, 'images/dell_inspiron_15.jpg'),
('PRD012', 'HP Pavilion 14', '14 inch', 'AMD Ryzen 5', '8GB', 'Touchscreen, Fast Charging', 15, '256GB SSD', '1 Year', 850.00, 'images/hp_pavilion_14.jpg'),
('PRD013', 'Lenovo ThinkPad X1', '14 inch', 'Intel i7 12th Gen', '16GB', 'Lightweight, Fingerprint Reader', 5, '1TB SSD', '2 Years', 1500.00, 'images/lenovo_thinkpad_x1.jpg'),
('PRD014', 'Asus ZenBook 13', '13.3 inch', 'Intel i5 11th Gen', '8GB', 'OLED Display, Long Battery Life', 8, '512GB SSD', '1 Year', 1100.00, 'images/asus_zenbook_13.jpg'),
('PRD015', 'Acer Swift 3', '14 inch', 'AMD Ryzen 7', '16GB', 'Backlit Keyboard, Fingerprint Reader', 12, '512GB SSD', '1 Year', 980.00, 'images/acer_swift_3.jpg');

COMMIT;
