-- seed.sql

START TRANSACTION;

INSERT INTO `carts` (`product_id`, `user_id`, `quantity`, `cart_id`) VALUES
('PRD009', 'rashmideep1', '1', 'CRD001'),
('PRD010', 'mirajbhandari1', '2', 'CRD001'),
('PRD002', 'Ayub_bhatta1', '2', 'CRD001');

INSERT INTO `orders` (`Order_ID`, `User_ID`, `Total_Amount`, `Status`, `city`, `address`, `payment`) VALUES
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'Ayub_bhatta1', 1680.00, 'Pending', 'kathmandu', 'Kamalpokhari', 'Khalti');

INSERT INTO `order_products` (`Order_ID`, `Product_ID`, `Line_Quantity`) VALUES
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'PRD002', 2),
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'PRD009', 1),
('306d4c0a-323b-440b-b878-50e18eaa5bca', 'PRD010', 2);

INSERT INTO `products` (`Product_ID`, `Product_Name`, `Screen_Size`, `Processor`, `RAM`, `Features`, `Discount`, `Storage`, `Camera`, `Warranty`, `price`, `Discount_Amount`, `Image`) VALUES
('PRD002', 'iPhone 13 Pro Max', '6.7 inches', 'A15 Bionic', '6GB', 'High-end iPhone with powerful performance, advanced camera features, and sleek design.', 12.00, '128GB', '126MP', '1 year', 7000.00, 840.00, '/ByteBazzar/images/product_images/iphone 13 max.jpg'),
('PRD003', 'OnePlus 9 Pro', '6.7 inches', 'Qualcomm Snapdragon 880', '12GB', 'Flagship phone with smooth performance, vibrant display, and impressive camera capabilities.', 8.00, '64GB', '120MP', '1.5 years', 112500.00, 9000.00, '/ByteBazzar/images/product_images/oneplus 9pro.jpeg'),
('PRD004', 'Google Pixel 6 Pro', '6.7 inches', 'Qualcomm Snapdragon 111', '12GB', 'Google\'s flagship offering with cutting-edge camera technology, stock Android experience, and premium design', 9.00, '126GB', '60MP', '1.5 years', 99500.00, 8955.00, '/ByteBazzar/images/product_images/google pixel 6 pro.jpeg'),
('PRD005', 'Xiaomi Mi 11 Ultra', '6.81 inches', 'Qualcomm Snapdragon 777', '12GB', 'Feature-packed flagship device with innovative camera features, powerful performance, and high-resolution display.', 8.00, '82GB', '52MP', '1.5 years', 105000.00, 8400.00, '/ByteBazzar/images/product_images/XiaomiMi11Ultra.jpeg'),
('PRD006', 'Samsung Galaxy Z Fold 3', '7.6 inches', 'MOTOROLLA A15', '12GB', 'Foldable smartphone with a large inner display, improved durability, and multitasking capabilities.', 20.00, '256GB', '100MP', '2 years', 180000.00, 36000.00, '/ByteBazzar/images/product_images/Samsung Galaxy Z Fold 3.jpeg'),
('PRD007', 'iPhone SE (2022)', '4.7 inches', 'A19 Bionic', '4GB', 'Compact iPhone with powerful performance, updated internals, and affordable price point.', 6.00, '302GB', '128MP', '1 year', 49500.00, 2970.00, '/ByteBazzar/images/product_images/iPhone SE (2022).jpeg'),
('PRD008', 'OnePlus Nord 2', '6.43 inches', 'MediaTek Dimensity 1200-AI', '8GB', 'Mid-range smartphone with solid performance, 5G connectivity, and clean OxygenOS experience.', 8.00, '56GB', '199MP', '3 years', 39999.00, 3199.92, '/ByteBazzar/images/product_images/OnePlus Nord 2.jpeg'),
('PRD009', 'Xiaomi Redmi Note 11 Pro', '6.67 inches', 'MediaTek Dimensity 920', '6GB', 'Budget-friendly smartphone with impressive camera capabilities, long battery life, and sleek design.', 8.00, '64GB', '74MP', '1 year', 29999.00, 2399.92, '/ByteBazzar/images/product_images/Xiaomi Redmi Note 11 Pro.jpeg'),
('PRD010', 'Realme GT 2 Pro', '6.7 inches', 'Qualcomm Snapdragon 8 Gen 1', '12GB', 'Performance-oriented flagship device with top-tier specifications, sleek design, and impressive camera features.', 9.00, '126GB', '86MP', '3 years', 74999.00, 6749.91, '/ByteBazzar/images/product_images/Realme GT 2 Pro.jpeg');

COMMIT;
