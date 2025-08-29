-- Insert 10 customer records
INSERT INTO customer (address, email, first_name, last_name, phone_number)
VALUES ('123 Main St, New York, NY 10001', 'john.doe@email.com', 'John', 'Doe', '+1-555-0101'),
       ('456 Oak Ave, Los Angeles, CA 90210', 'jane.smith@email.com', 'Jane', 'Smith', '+1-555-0102'),
       ('789 Pine Rd, Chicago, IL 60601', 'mike.johnson@email.com', 'Mike', 'Johnson', '+1-555-0103'),
       ('321 Elm St, Houston, TX 77001', 'sarah.brown@email.com', 'Sarah', 'Brown', '+1-555-0104'),
       ('654 Maple Dr, Phoenix, AZ 85001', 'david.wilson@email.com', 'David', 'Wilson', '+1-555-0105'),
       ('987 Cedar Ln, Philadelphia, PA 19101', 'lisa.garcia@email.com', 'Lisa', 'Garcia', '+1-555-0106'),
       ('147 Birch St, San Antonio, TX 78201', 'robert.martinez@email.com', 'Robert', 'Martinez', '+1-555-0107'),
       ('258 Spruce Ave, San Diego, CA 92101', 'emily.anderson@email.com', 'Emily', 'Anderson', '+1-555-0108'),
       ('369 Willow Rd, Dallas, TX 75201', 'james.taylor@email.com', 'James', 'Taylor', '+1-555-0109'),
       ('741 Aspen Dr, San Jose, CA 95101', 'maria.rodriguez@email.com', 'Maria', 'Rodriguez', '+1-555-0110');

-- Insert 10 wallet records (assuming customer IDs 1-10 exist)
INSERT INTO wallet (balance, wallet_name, wallet_type, customer_id)
VALUES (1250.50, 'Primary Savings', 'customer', 1),
       (750.25, 'Business Account', 'office', 2),
       (2100.75, 'Personal Wallet', 'customer', 3),
       (500.00, 'Emergency Fund', 'customer', 4),
       (3250.80, 'Company Expenses', 'office', 5),
       (825.40, 'Vacation Fund', 'customer', 6),
       (1875.60, 'Investment Account', 'customer', 7),
       (650.30, 'Office Petty Cash', 'office', 8),
       (2950.90, 'Main Account', 'customer', 9),
       (1100.15, 'Freelance Earnings', 'customer', 10);