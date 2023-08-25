CREATE TABLE finco.profiles (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	avatar_url varchar(255) NULL,
	user_id BIGINT NOT NULL,
	CONSTRAINT profiles_FK FOREIGN KEY (user_id) REFERENCES finco.users(id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);
