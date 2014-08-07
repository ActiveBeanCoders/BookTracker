CREATE TABLE author (
	author_id MEDIUMINT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	rating SMALLINT,
	user CHAR(8) NOT NULL,
	PRIMARY KEY (author_id, user),
	UNIQUE KEY  (first_name, last_name, user)
) ENGINE=MyISAM;

