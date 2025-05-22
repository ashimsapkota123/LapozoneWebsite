-- init.sql with ALTER statements

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Database: `lapozone`

-- Table structure for table `carts`
CREATE TABLE `carts` (
  `product_id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `cart_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Table structure for table `inquiry`
CREATE TABLE `inquiry` (
  `Inquiry_ID` varchar(50) NOT NULL,
  `User_ID` varchar(50) DEFAULT NULL,
  `Subject` varchar(255) NOT NULL,
  `Created_at` date DEFAULT NULL,
  `Message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Table structure for table `orders`
CREATE TABLE `orders` (
  `Order_ID` varchar(50) NOT NULL,
  `User_ID` varchar(50) NOT NULL,
  `Total_Amount` decimal(10,2) NOT NULL,
  `Status` enum('Pending','Delivered','Canceled') NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `payment` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Table structure for table `order_products`
CREATE TABLE `order_products` (
  `Order_ID` varchar(50) NOT NULL,
  `Product_ID` varchar(50) NOT NULL,
  `Line_Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Table structure for table `products`
CREATE TABLE `products` (
  `Product_ID` varchar(50) NOT NULL,
  `Product_Name` varchar(255) NOT NULL,
  `Screen_Size` varchar(50) NOT NULL,
  `Processor` varchar(100) NOT NULL,
  `RAM` varchar(50) NOT NULL,
  `Features` text NOT NULL,
  `Discount` decimal(4,2) NOT NULL,
  `Storage` varchar(100) NOT NULL,
  `Warranty` varchar(100) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `Discount_Amount` decimal(8,2) NOT NULL,
  `Image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Now ALTER TABLE to add primary keys and foreign keys

ALTER TABLE `carts`
  ADD PRIMARY KEY (`cart_id`);

ALTER TABLE `inquiry`
  ADD PRIMARY KEY (`Inquiry_ID`);

ALTER TABLE `orders`
  ADD PRIMARY KEY (`Order_ID`);

ALTER TABLE `order_products`
  ADD PRIMARY KEY (`Order_ID`, `Product_ID`);

ALTER TABLE `products`
  ADD PRIMARY KEY (`Product_ID`);

-- Adding foreign keys (assuming these relations)

ALTER TABLE `carts`
  ADD CONSTRAINT `fk_carts_product` FOREIGN KEY (`product_id`) REFERENCES `products`(`Product_ID`),
  ADD CONSTRAINT `fk_carts_user` FOREIGN KEY (`user_id`) REFERENCES `orders`(`User_ID`); -- Assuming users are in orders table

ALTER TABLE `inquiry`
  ADD CONSTRAINT `fk_inquiry_user` FOREIGN KEY (`User_ID`) REFERENCES `orders`(`User_ID`); -- Assuming users are in orders table

ALTER TABLE `order_products`
  ADD CONSTRAINT `fk_order_products_order` FOREIGN KEY (`Order_ID`) REFERENCES `orders`(`Order_ID`),
  ADD CONSTRAINT `fk_order_products_product` FOREIGN KEY (`Product_ID`) REFERENCES `products`(`Product_ID`);

ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_user` FOREIGN KEY (`User_ID`) REFERENCES `orders`(`User_ID`); -- This might be a user table normally, but since missing, I used orders for demo

COMMIT;
