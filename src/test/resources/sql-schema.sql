DROP TABLE IF EXISTS `coin`;

CREATE TABLE IF NOT EXISTS `coin`
(
   `id` INT (11) NOT NULL AUTO_INCREMENT,
   `coin_name` VARCHAR (100) NOT NULL,
   `year` INT (4) NOT NULL,
   `denomination` VARCHAR NOT NULL (10),
   `description` VARCHAR (250),
   `country` VARCHAR (40),
   `inCollection` BOOLEAN DEFAULT false,
   PRIMARY KEY (`id`)
);