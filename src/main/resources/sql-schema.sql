drop schema coin;

CREATE SCHEMA IF NOT EXISTS `coin`;
USE `coin`;
CREATE TABLE IF NOT EXISTS `coin`.`coin`
(
 `id` BIGINT NOT NULL auto_increment,
 `coin_description` VARCHAR (255),
 `coin_name` VARCHAR (255) not null,
 `country` VARCHAR (255) not null,
 `denomination` VARCHAR (255) not null,
 `in_collection` BIT not null DEFAULT false,
 `year` INTEGER not null,
  PRIMARY KEY (`id`)
);