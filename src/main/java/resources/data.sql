USE my_db;

CREATE TABLE users (
  username varchar(15),
  password varchar(100),
  enabled tinyint(1),
  PRIMARY KEY (username)
) ;

CREATE TABLE authorities (
  username varchar(15),
  authority varchar(25),
  FOREIGN KEY (username) references users(username)
) ;

INSERT INTO my_db.users (username, password, enabled)
VALUES
	('salavat', '{bcrypt}$2a$10$9RdSKjO7BzFeoQMWVOz7GuZUfQTsRVVZtBumvA94zXJXndMvexy4O', 1),
	('elena', '{bcrypt}$2a$10$DIDGpzIyHLy4PLR8uMOM9.O1nHAzI9LeZAEJ5e1kXYF21JuhMCOI.', 1),
	('ivan', '{bcrypt}$2a$10$cucFhgjUBykTeNKuK5cU.eO8ceah/FEK.N8HQw.No9e5lTILpix9i', 1);
    
INSERT INTO my_db.authorities (username, authority)
VALUES
	('salavat', 'ROLE_EMPLOYEE'),
	('elena', 'ROLE_HR'),
    ('ivan', 'ROLE_HR'),
	('ivan', 'ROLE_MANAGER');
    
    