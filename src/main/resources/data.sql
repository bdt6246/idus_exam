DROP DATABASE  IF EXISTS `web`;
CREATE DATABASE  IF NOT EXISTS `web` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `web`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        idx BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_name VARCHAR(255) NOT NULL,
                        nick_name VARCHAR(255),
                        password VARCHAR(255) NOT NULL,
                        phone BIGINT,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        sex VARCHAR(10),
                        role VARCHAR(50),
                        CONSTRAINT uq_email UNIQUE (email)
);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE orders (
                        idx BIGINT AUTO_INCREMENT PRIMARY KEY,
                        order_no VARCHAR(255) NOT NULL,
                        product_name VARCHAR(255) NOT NULL,
                        order_date DATETIME NOT NULL,
                        user_idx BIGINT,
                        CONSTRAINT fk_user FOREIGN KEY (user_idx) REFERENCES `user`(idx)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);

LOCK
TABLES `user` WRITE;
INSERT INTO user (idx, user_name, nick_name, password, phone, email, sex, role) VALUES
                                                                                    (1, 'john_doe', 'Johnny', '$2a$10$7Q7wJjJbFZ8tFo1T8JjTzuYzjI8EvI1YgL4L7h3Na4eLQZKqxRZey', 1234567890, 'john@example.com', 'M', 'USER'),
                                                                                    (2, 'jane_smith', 'Janey', '$2a$10$9GwYljd8P3V0RHU2iwfL3OJ2QSnBRi3dLd9cTz7KN59DFxv1I4B4K', 2345678901, 'jane@example.com', 'F', 'USER'),
                                                                                    (3, 'michael_brown', 'Mike', '$2a$10$hHV9Ad8F3Jz1aLKpzUBK6OtDRMtyIM9z8nIRm8G5TnF4/BMujWBQe', 3456789012, 'michael@example.com', 'M', 'USER'),
                                                                                    (4, 'emily_davis', 'Emmy', '$2a$10$7Q7wJjJbFZ8tFo1T8JjTzuYzjI8EvI1YgL4L7h3Na4eLQZKqxRZey', 4567890123, 'emily@example.com', 'F', 'USER'),
                                                                                    (5, 'william_jones', 'Will', '$2a$10$9GwYljd8P3V0RHU2iwfL3OJ2QSnBRi3dLd9cTz7KN59DFxv1I4B4K', 5678901234, 'william@example.com', 'M', 'USER'),
                                                                                    (6, 'olivia_miller', 'Liv', '$2a$10$hHV9Ad8F3Jz1aLKpzUBK6OtDRMtyIM9z8nIRm8G5TnF4/BMujWBQe', 6789012345, 'olivia@example.com', 'F', 'USER'),
                                                                                    (7, 'james_wilson', 'Jimmy', '$2a$10$7Q7wJjJbFZ8tFo1T8JjTzuYzjI8EvI1YgL4L7h3Na4eLQZKqxRZey', 7890123456, 'james@example.com', 'M', 'USER'),
                                                                                    (8, 'sophia_moore', 'Sophie', '$2a$10$9GwYljd8P3V0RHU2iwfL3OJ2QSnBRi3dLd9cTz7KN59DFxv1I4B4K', 8901234567, 'sophia@example.com', 'F', 'USER'),
                                                                                    (9, 'liam_taylor', 'Liam', '$2a$10$hHV9Ad8F3Jz1aLKpzUBK6OtDRMtyIM9z8nIRm8G5TnF4/BMujWBQe', 9012345678, 'liam@example.com', 'M', 'USER'),
                                                                                    (10, 'ava_anderson', 'Ava', '$2a$10$7Q7wJjJbFZ8tFo1T8JjTzuYzjI8EvI1YgL4L7h3Na4eLQZKqxRZey', 1234509876, 'ava@example.com', 'F', 'USER');
UNLOCK
TABLES;


LOCK
TABLES `orders` WRITE;
INSERT INTO orders (idx, order_no, product_name, order_date, user_idx) VALUES
                                                                           (1, 'ORD001', 'Laptop', '2024-02-01 10:00:00', 1),
                                                                           (2, 'ORD002', 'Smartphone', '2024-02-02 11:30:00', 2),
                                                                           (3, 'ORD003', 'Headphones', '2024-02-03 09:45:00', 3),
                                                                           (4, 'ORD004', 'Monitor', '2024-02-04 14:20:00', 4),
                                                                           (5, 'ORD005', 'Keyboard', '2024-02-05 16:50:00', 5),
                                                                           (6, 'ORD006', 'Mouse', '2024-02-06 13:15:00', 6),
                                                                           (7, 'ORD007', 'Tablet', '2024-02-07 17:40:00', 7),
                                                                           (8, 'ORD008', 'Smartwatch', '2024-02-08 12:00:00', 8),
                                                                           (9, 'ORD009', 'Camera', '2024-02-09 15:30:00', 9),
                                                                           (10, 'ORD010', 'Printer', '2024-02-10 18:10:00', 10),
                                                                           (11, 'ORD011', 'Gaming Console', '2024-02-11 09:00:00', 1),
                                                                           (12, 'ORD012', 'Router', '2024-02-12 11:45:00', 2),
                                                                           (13, 'ORD013', 'External HDD', '2024-02-13 13:30:00', 3),
                                                                           (14, 'ORD014', 'Webcam', '2024-02-14 15:00:00', 4),
                                                                           (15, 'ORD015', 'Speaker', '2024-02-15 10:20:00', 5),
                                                                           (16, 'ORD016', 'Microphone', '2024-02-16 14:40:00', 6),
                                                                           (17, 'ORD017', 'Projector', '2024-02-17 13:10:00', 7),
                                                                           (18, 'ORD018', 'VR Headset', '2024-02-18 16:50:00', 8),
                                                                           (19, 'ORD019', 'USB Hub', '2024-02-19 12:00:00', 9),
                                                                           (20, 'ORD020', 'Drawing Tablet', '2024-02-20 14:30:00', 10),
                                                                           (21, 'ORD021', 'SSD', '2024-02-21 09:45:00', 1),
                                                                           (22, 'ORD022', 'Power Bank', '2024-02-22 11:20:00', 2),
                                                                           (23, 'ORD023', 'Bluetooth Speaker', '2024-02-23 14:10:00', 3),
                                                                           (24, 'ORD024', 'Graphics Card', '2024-02-24 17:30:00', 4),
                                                                           (25, 'ORD025', 'RAM 16GB', '2024-02-25 13:20:00', 5),
                                                                           (26, 'ORD026', 'CPU Cooler', '2024-02-26 15:00:00', 6),
                                                                           (27, 'ORD027', 'PC Case', '2024-02-27 16:45:00', 7),
                                                                           (28, 'ORD028', 'Motherboard', '2024-02-28 12:30:00', 8),
                                                                           (29, 'ORD029', 'Ethernet Cable', '2024-02-29 14:15:00', 9),
                                                                           (30, 'ORD030', 'Flash Drive', '2024-03-01 10:40:00', 10),
                                                                           (31, 'ORD031', 'Wireless Mouse', '2024-03-02 11:50:00', 1),
                                                                           (32, 'ORD032', 'Mechanical Keyboard', '2024-03-03 13:10:00', 2),
                                                                           (33, 'ORD033', 'Gaming Headset', '2024-03-04 15:30:00', 3),
                                                                           (34, 'ORD034', 'Drone', '2024-03-05 17:00:00', 4),
                                                                           (35, 'ORD035', 'Action Camera', '2024-03-06 12:20:00', 5),
                                                                           (36, 'ORD036', 'Tripod', '2024-03-07 14:45:00', 6),
                                                                           (37, 'ORD037', 'Smart Light', '2024-03-08 09:30:00', 7),
                                                                           (38, 'ORD038', 'Fitness Tracker', '2024-03-09 11:15:00', 8),
                                                                           (39, 'ORD039', 'E-Reader', '2024-03-10 13:50:00', 9),
                                                                           (40, 'ORD040', 'Portable Monitor', '2024-03-11 16:00:00', 10),
                                                                           (41, 'ORD041', 'NAS Storage', '2024-03-12 10:10:00', 1),
                                                                           (42, 'ORD042', '3D Printer', '2024-03-13 11:40:00', 2),
                                                                           (43, 'ORD043', 'Security Camera', '2024-03-14 14:25:00', 3),
                                                                           (44, 'ORD044', 'Tablet Pen', '2024-03-15 15:55:00', 4),
                                                                           (45, 'ORD045', 'Wireless Charger', '2024-03-16 12:10:00', 5),
                                                                           (46, 'ORD046', 'Smart Thermostat', '2024-03-17 13:35:00', 6),
                                                                           (47, 'ORD047', 'Bluetooth Earbuds', '2024-03-18 15:20:00', 7),
                                                                           (48, 'ORD048', 'Dash Cam', '2024-03-19 17:45:00', 8),
                                                                           (49, 'ORD049', 'Graphic Tablet', '2024-03-20 11:05:00', 9),
                                                                           (50, 'ORD050', 'Home Assistant', '2024-03-21 13:25:00', 10);
UNLOCK
TABLES;