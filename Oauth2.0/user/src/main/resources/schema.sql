CREATE TABLE IF NOT EXISTS USER 
(id int NOT NULL AUTO_INCREMENT,
 username varchar(255) NOT NULL,
 password varchar(255) NOT NULL,
 roles varchar(255) NOT NULL,
 active Boolean NOT NULL, 
 PRIMARY KEY (id));
 
 CREATE TABLE IF NOT EXISTS PAYMENT 
(transactionid int NOT NULL AUTO_INCREMENT,
 amount decimal NOT NULL,
 method varchar(255) NOT NULL,
 date datetime NOT NULL,
 userid int NOT NULL, 
 PRIMARY KEY (transactionId),
 FOREIGN KEY (userid) REFERENCES USER(id) ON DELETE CASCADE);
