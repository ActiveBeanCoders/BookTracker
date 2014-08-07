CREATE TABLE book (
	book_id MEDIUMINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	author_id MEDIUMINT NOT NULL,
	series VARCHAR(20),
	read_status BOOLEAN NOT NULL,
	own_status BOOLEAN NOT NULL,
	year INT NOT NULL,
	user CHAR(8) NOT NULL,
	PRIMARY KEY (book_id, user),
	UNIQUE KEY (title, author_id, user)
) ENGINE=MyISAM;

