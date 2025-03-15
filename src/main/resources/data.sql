CREATE TABLE IF NOT EXISTS currency (
    code VARCHAR(10) PRIMARY KEY,   -- Currency code (e.g., USD, EUR)
    name VARCHAR(50) NOT NULL        -- Currency name in Chinese (e.g., 美元, 歐元)
);

INSERT INTO currency (code, name) VALUES ('USD', '美元');
INSERT INTO currency (code, name) VALUES ('EUR', '歐元');
INSERT INTO currency (code, name) VALUES ('GBP', '英鎊');
