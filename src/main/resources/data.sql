-- Disable foreign key checks temporarily
SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM part_tran;
DELETE FROM wallet;
DELETE FROM tran_header;
DELETE FROM customer;

-- Insert customers
INSERT INTO `customer`(id, address, email, first_name, last_name, phone_number)
VALUES (1, '123 Main St, New York, NY 10001', 'john.doe@email.com', 'John', 'Doe', '+1-555-0101'),
       (2, '456 Oak Ave, Los Angeles, CA 90210', 'jane.smith@email.com', 'Jane', 'Smith', '+1-555-0102'),
       (3, '789 Pine Rd, Chicago, IL 60601', 'mike.johnson@email.com', 'Mike', 'Johnson', '+1-555-0103'),
       (4, '321 Elm St, Houston, TX 77001', 'sarah.brown@email.com', 'Sarah', 'Brown', '+1-555-0104'),
       (5, '654 Maple Dr, Phoenix, AZ 85001', 'david.wilson@email.com', 'David', 'Wilson', '+1-555-0105'),
       (6, '987 Cedar Ln, Philadelphia, PA 19101', 'lisa.garcia@email.com', 'Lisa', 'Garcia', '+1-555-0106'),
       (7, '147 Birch St, San Antonio, TX 78201', 'robert.martinez@email.com', 'Robert', 'Martinez', '+1-555-0107'),
       (8, '258 Spruce Ave, San Diego, CA 92101', 'emily.anderson@email.com', 'Emily', 'Anderson', '+1-555-0108'),
       (9, '369 Willow Rd, Dallas, TX 75201', 'james.taylor@email.com', 'James', 'Taylor', '+1-555-0109'),
       (10, '741 Aspen Dr, San Jose, CA 95101', 'maria.rodriguez@email.com', 'Maria', 'Rodriguez', '+1-555-0110');
-- Insert wallets
INSERT
INTO wallet(id, balance, wallet_name, wallet_type, customer_id)
VALUES (1, 5700, 'CASH', 'OFFICE', NULL),
       (2, 6950.5, 'Primary Savings', 'CUSTOMER', 1),
       (3, 750.25, 'Business Account', 'OFFICE', 2),
       (4, 2100.75, 'Personal Wallet', 'CUSTOMER', 3),
       (5, 500, 'Emergency Fund', 'CUSTOMER', 4),
       (6, 3250.8, 'Company Expenses', 'OFFICE', 5),
       (7, 825.4, 'Vacation Fund', 'CUSTOMER', 6),
       (8, 1875.6, 'Investment Account', 'CUSTOMER', 7),
       (9, 650.3, 'Office Petty Cash', 'OFFICE', 8),
       (10, 2950.9, 'Main Account', 'CUSTOMER', 9),
       (11, 1100.15, 'Freelance Earnings', 'CUSTOMER', 10);

-- Insert transaction headers
INSERT INTO tran_header(id, is_verified, posted_date, verified_date)
VALUES (5635, b'1', NULL, '2025-08-29 12:45:31.786000'),
       (5636, b'1', NULL, '2025-08-29 12:49:10.218000'),
       (5637, b'1', NULL, '2025-08-31 23:03:37.229000');

-- Insert part_tran records
INSERT INTO part_tran(id, amount, tran_type, wallet_id, tran_id)
VALUES (1, 300, 'C', 2, 5635),
       (2, 300, 'D', 1, 5635),
       (3, 3000, 'D', 2, 5636),
       (4, 3000, 'C', 1, 5636),
       (5, 3000, 'D', 2, 5637),
       (6, 3000, 'C', 1, 5637);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;