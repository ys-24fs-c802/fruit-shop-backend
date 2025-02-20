CREATE TABLE payments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          payment_id VARCHAR(50) NOT NULL,
                          payer_id INT NOT NULL,
                          amount DECIMAL(10, 2) NOT NULL,
                          state VARCHAR(20) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (payer_id) REFERENCES users(id)
);
