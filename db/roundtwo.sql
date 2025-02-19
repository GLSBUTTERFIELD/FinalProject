-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema roundtwodb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `roundtwodb` ;

-- -----------------------------------------------------
-- Schema roundtwodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `roundtwodb` DEFAULT CHARACTER SET utf8 ;
USE `roundtwodb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state_abbreviation` CHAR(2) NOT NULL,
  `zip` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(60) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `role` VARCHAR(60) NULL,
  `email` VARCHAR(200) NOT NULL,
  `image_url` TEXT NULL,
  `biography` TEXT NULL,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_user_address1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gathering`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gathering` ;

CREATE TABLE IF NOT EXISTS `gathering` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `description` TEXT NULL,
  `registration_fee_usd` DOUBLE NULL,
  `min_participants` INT NOT NULL,
  `max_participants` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `end_time` TIME NULL,
  `start_time` TIME NOT NULL,
  `image_url` TEXT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_gathering_address1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gathering_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `description` TEXT NULL,
  `minimum_age` INT NULL,
  `website_url` TEXT NULL,
  `image_url` TEXT NULL,
  `min_players` INT NOT NULL,
  `max_players` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item_condition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item_condition` ;

CREATE TABLE IF NOT EXISTS `item_condition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item` ;

CREATE TABLE IF NOT EXISTS `inventory_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `notes` TEXT NULL,
  `available` TINYINT NOT NULL DEFAULT 1,
  `image_url` TEXT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `name` VARCHAR(200) NULL,
  `game_id` INT NOT NULL,
  `condition_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_inventory_item_game_idx` (`game_id` ASC) VISIBLE,
  INDEX `fk_inventory_item_condition1_idx` (`condition_id` ASC) VISIBLE,
  INDEX `fk_inventory_item_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_inventory_item_game`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_item_condition1`
    FOREIGN KEY (`condition_id`)
    REFERENCES `item_condition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_item_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_item_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item_comment` ;

CREATE TABLE IF NOT EXISTS `inventory_item_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `inventory_item_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_inventory_item_comment_inventory_item1_idx` (`inventory_item_id` ASC) VISIBLE,
  INDEX `fk_inventory_item_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_inventory_item_comment_inventory_item_comment1_idx` (`in_reply_to_id` ASC) VISIBLE,
  CONSTRAINT `fk_inventory_item_comment_inventory_item1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_item_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_item_comment_inventory_item_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `inventory_item_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gathering_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gathering_comment` ;

CREATE TABLE IF NOT EXISTS `gathering_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  `gathering_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_event_comment_event1_idx` (`gathering_id` ASC) VISIBLE,
  INDEX `fk_event_comment_event_comment1_idx` (`in_reply_to_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_comment_event1`
    FOREIGN KEY (`gathering_id`)
    REFERENCES `gathering` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_comment_event_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `gathering_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_has_category` ;

CREATE TABLE IF NOT EXISTS `game_has_category` (
  `game_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`game_id`, `category_id`),
  INDEX `fk_game_has_category_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_game_has_category_game1_idx` (`game_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_has_category_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gathering_has_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gathering_has_game` ;

CREATE TABLE IF NOT EXISTS `gathering_has_game` (
  `gathering_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`gathering_id`, `game_id`),
  INDEX `fk_event_has_game_game1_idx` (`game_id` ASC) VISIBLE,
  INDEX `fk_event_has_game_event1_idx` (`gathering_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_has_game_event1`
    FOREIGN KEY (`gathering_id`)
    REFERENCES `gathering` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gathering_participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gathering_participant` ;

CREATE TABLE IF NOT EXISTS `gathering_participant` (
  `gathering_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `participant_rating` INT NULL,
  `participant_notes` TEXT NULL,
  `host_rating` INT NULL,
  `host_notes` TEXT NULL,
  `create_date` DATETIME NULL,
  PRIMARY KEY (`gathering_id`, `user_id`),
  INDEX `fk_event_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_event_has_user_event1_idx` (`gathering_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_has_user_event1`
    FOREIGN KEY (`gathering_id`)
    REFERENCES `gathering` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_game` ;

CREATE TABLE IF NOT EXISTS `favorite_game` (
  `user_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `game_id`),
  INDEX `fk_user_has_game_game1_idx` (`game_id` ASC) VISIBLE,
  INDEX `fk_user_has_game_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_game_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `direct_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `direct_message` ;

CREATE TABLE IF NOT EXISTS `direct_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `create_date` DATETIME NULL,
  `sender_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_direct_message_user1_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_direct_message_user2_idx` (`recipient_id` ASC) VISIBLE,
  CONSTRAINT `fk_direct_message_user1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direct_message_user2`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_comment` ;

CREATE TABLE IF NOT EXISTS `game_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_event_comment_event_comment1_idx` (`in_reply_to_id` ASC) VISIBLE,
  INDEX `fk_gathering_comment_copy1_game1_idx` (`game_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_comment_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_comment_event_comment10`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `game_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gathering_comment_copy1_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_resource` ;

CREATE TABLE IF NOT EXISTS `game_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `resource_url` TEXT NULL,
  `name` TEXT NULL,
  `last_update` DATETIME NULL,
  `user_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_game_resource_game1_idx` (`game_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_comment_user11`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_resource_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS cromp;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'cromp' IDENTIFIED BY 'cromp';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'cromp';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (1, 'My Basement', '123 Colfax Ave', 'Denver', 'CO', '80014');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (2, 'Event Center', '556 Fake Street', 'Longmont', 'CO', '80501');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (3, 'Game Store', '871 Munster Rd', 'Severance', 'CO', '80546');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (4, 'Dicey Decisions', '42 Meeple Lane', 'Boulder', 'CO', '80302');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (5, 'Pawn & Pixel', '777 BoardwalkBlvd', 'Loveland', 'CO', '80538');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (6, 'Rolling Doubles Game Hub', '202 Snake Eyes Ct', 'Greeley', 'CO', '80631');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (7, 'The Tabletop Tavern', '321 Crit Hit Rd', 'Colorado Springs', 'CO', '80903');
INSERT INTO `address` (`id`, `name`, `street`, `city`, `state_abbreviation`, `zip`) VALUES (8, 'Checkmate Cafe', '14 Bishop\'s Gambit Ln', 'Golden', 'CO', '80401');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (1, 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'will', 'bill', 'chadmin', 'willbillsucks@gmail.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6XQHmnERLMxBjTPBD7AW66hNEpDJCSZyS2w&s', NULL, NULL, NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (2, 'Player2', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Ray', 'Williams', NULL, 'rayray@gmail.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (3, 'Hamilton', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Gabby', 'Blorp', NULL, 'GabbySuks@gmail.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (4, 'Wakko', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Wakko', 'Warner', NULL, 'wakko@warnerbros.org', 'https://www.jammable.com/cdn-cgi/image/width=256,quality=75,format=webp/https://imagecdn.voicify.ai/models/f78507a9-4a99-4fed-ab31-2dc79dd98af6.png', '\nWakko Warner – Board Game Dynamo, Snack Enthusiast, and Chaos Conductor\n\nHellooooooo, board game world! I’m Wakko Warner, the wacky, snacky, and slightly unhinged wildcard of any game night. If there’s a board involved, I’m on it—probably standing, spinning, or using it as a makeshift hat.\n\nI approach board games the same way I approach life: with boundless energy, a total disregard for strategy, and an unwavering commitment to fun (and snacks). Rules? Love ‘em! Break ‘em? Even better! Whether I’m launching an all-out assault in Risk, building the world’s tallest and most structurally unsound Jenga tower, or making up my own victory conditions in Monopoly (I accept pizza as currency), I guarantee an unforgettable time.\n\nPreferred Games: Hungry Hungry Hippos (a classic), Exploding Kittens (hilarious and educational), and Twister (because why not?).\n\nNot Allowed at My Table: Bored people, celery (yuck), and anyone who tells me I can’t play chess using gummy bears as pawns.', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (5, 'LeslieK', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Leslie', 'Knope', NULL, 'leslie@pawneeparks.gov', 'https://i0.wp.com/i58.photobucket.com/albums/g246/sey115/parks-game-son_zpsf91a6fa0.gif?resize=500%2C220', 'Board Game Aficionado and Teamwork Enthusiast\n\nI’m Leslie Knope, a passionate strategist who loves games that challenge my teamwork and creativity. Whether it\'s a friendly competition or a cooperative challenge, I’m all in and ready to bring my energy and focus to every move. Get ready for fun, fierce competition, and maybe a Lil Sebastian reference or two!', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (6, 'DwightS', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Dwight', 'Schrute', NULL, 'battlestargalacticaguru@dundermifflin.com', 'https://img.nbc.com/files/images/2013/11/12/dwight-500x500.jpg', 'Dwight Schrute – Board Game Enforcer, Strategic Genius, and Event Security Specialist\n\nAs Assistant (to the) Regional Manager of Dunder Mifflin Scranton and owner of Schrute Farms, I have spent my life mastering strategy, discipline, and beet cultivation. Now, I bring that same level of tactical precision to the world of board games and gatherings.\n\nI do not play games—I conquer them. Whether it’s outmaneuvering opponents in Risk, enforcing strict rule adherence in Settlers of Catan, or ensuring the structural integrity of a Jenga tower, I am an unstoppable force at the table. I believe in fair but firm gameplay—cheaters will be identified, documented, and swiftly removed (within the bounds of local law enforcement protocols).\n\nHosting a board game night? Rest assured, I will bring unmatched organizational skills, a whistle (if necessary), and an unwavering commitment to a structured, dispute-free experience. My presence alone elevates any gathering from casual fun to a battle of wits and honor.\n\nJoin me in the pursuit of true strategic dominance—because second place is just the first loser.\n\nPreferred Games: Risk, Stratego, Clue (for investigative training), and Agricola (an excellent simulation of real farming hardships).\n\nNot Allowed at My Table: Flimsy house rules, weak alliances, and anyone who calls UNO “just a casual game.”', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (7, 'DutchLF', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Dutch', 'LaFever', NULL, 'dlafeev@sdvid.com', 'https://i.pinimg.com/736x/03/0f/87/030f8739ac9aec830dd91823637d5459.jpg', 'Hey! Go by Dutch or D. I own more board games than shelf space. Will spend 45 minutes explaining the rules, then crush you in 15. Believes that Monopoly is a test of patience, not a game. Come get some!', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (8, 'BigBen', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Ben', 'Wyatt', NULL, 'bigbenWyatt@gamers.com', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.cheatsheet.com%2Fentertainment%2Fcan-you-actually-play-ben-wyatts-cones-of-dunshire-game-from-parks-and-recreation.html%2F&psig=AOvVaw2GaiCKizR8Zg53w0_gi1LW&ust=1739990876553000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIjG-aXxzYsDFQAAAAAdAAAAABAE', 'Board Game Expert and Strategy Buff\n\nI\'m Ben Wyatt, a lover of complex strategy games and a firm believer that the best plans are the ones with careful thought behind them. Whether it\'s a resource management game or a battle of wits, I’m all about the details and long-term strategy. Don’t be surprised if I’m the one keeping track of all the rules—I\'ve got a knack for figuring out the best way to win!', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (9, 'NardDogGames', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Andy', 'Bernard', NULL, 'andy.bernard@cornell.edu', 'https://img.nbc.com/files/images/2013/11/12/andy-500x500.jpg', 'Hello! I\'m Andy. I think Risk is a personality test, I\'ll only play Settlers of Catan if I can be blue, oh and I may have once threw a Scrabble board across the room because \"BANJO\" wasn\'t a triple-word score.', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gathering`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (1, 'Twister Meetup', 'We are going to play a game of twister in my basement. Be ready!', NULL, 2, 4, 1, NULL, '2025-02-15 00:00:00', NULL, '22:00:00', '', '2025-02-13', NULL, 1, 1);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (2, 'Catan Time!', 'I love playing catan and im looking for other people to play with. Any one want to join?', NULL, 2, 6, 1, NULL, '2025-02-13 00:00:00', NULL, '12:00:00', NULL, '2025-02-14', NULL, 2, 2);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (3, 'Magic The Gathering', 'Im a novice when it comes to magic, can someone come and help me out? Id want to learn as much as i can.', NULL, 2, 4, 1, NULL, '2025-01-31 00:00:00', NULL, '20:00:00', NULL, '2025-02-15', NULL, 1, 1);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (4, 'Battle Ships Championships!!!', 'Come and bring your A-Game for our finals in the battleship championships!', NULL, 5, 10, 1, NULL, '2025-02-17 00:00:00', NULL, '13:00:00', NULL, '2025-02-20', NULL, 5, 5);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (5, 'Come Solve A Mystery', 'We\'re going to be playing a game of clue, there will be prizes, snacks and a good atmpsphere', NULL, 4, 8, 1, NULL, '2025-02-18 00:00:00', NULL, '18:00:00', NULL, '2025-02-25', NULL, 8, 6);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (6, 'Help with a Puzzle?', 'Ive been having issues completing this puzzle. Maybe i mixed in other puzzle peices in with it but i cant seem to figure it out. Can someone come and help me put it together?', NULL, 2, 3, 1, NULL, '2025-02-18 00:00:00', NULL, '14:00:00', NULL, '2025-02-28', NULL, 3, 5);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (7, 'Jenga Fun Time!', 'I want to. play a game of jenga with some cool people. I want to meet others and make some new friends!', NULL, 4, 6, 1, NULL, '2025-02-18 00:00:00', NULL, '10:00:00', NULL, '2025-02-22', NULL, 7, 2);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (8, 'Scrabble and Crabble', 'I want to have a crab dinner and play dinner with a couple people but there arent many  ecentric people like me. LMK if anyone is up to have a good eats and gud funs time. ', NULL, 3, 5, 1, NULL, '2025-02-18 00:00:00', NULL, '16:00:00', NULL, '2025-02-27', NULL, 4, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (1, 'Twister Delux Edition', 'a party game where players take turns placing their hands and feet on colored circles on a mat while avoiding falling over', 6, 'https://en.wikipedia.org/wiki/Twister_(game)', 'https://upload.wikimedia.org/wikipedia/en/0/09/1966_Twister_Cover.jpg', 2, 4);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (2, 'Catan', 'Players take on the roles of settlers, each attempting to build and develop holdings while trading and acquiring resources.', 8, 'https://en.wikipedia.org/wiki/Catan#:~:text=Players%20take%20on%20the%20roles,points%2C%20typically%2010%2C%20wins.', 'https://upload.wikimedia.org/wikipedia/en/a/a3/Catan-2015-boxart.jpg', 3, 6);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (3, 'Magic the Gathering', 'a collectible trading card game where players build decks of cards representing creatures, spells, and artifacts, then take turns battling each other by summoning creatures, casting spells, and using abilities to reduce their opponent\'s life total to zero, with the goal of being the last player standing', 12, 'https://en.wikipedia.org/wiki/Magic:_The_Gathering', 'https://upload.wikimedia.org/wikipedia/en/a/aa/Magic_the_gathering-card_back.jpg', 2, 6);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (4, 'Battle Ship', 'each player secretly positions a fleet of ships on a grid, and then takes turns \"firing\" at the opponent\'s grid to try and locate and sink their ships, with the goal being to destroy all of the opponent\'s ships before they destroy yours', 8, 'https://en.wikipedia.org/wiki/Battleship_(game)#:~:text=Battleship%20(also%20known%20as%20Battleships,concealed%20from%20the%20other%20player.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Hra_n%C3%A1mo%C5%99n%C3%AD_bitva_%281%29.jpg/1280px-Hra_n%C3%A1mo%C5%99n%C3%AD_bitva_%281%29.jpg', 2, 2);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (5, 'Monopoly', 'roll dice to move around the board, aiming to buy properties that land on unowned spaces, collect rent from other players who land on your properties, and eventually build houses and hotels on those properties to increase the rent', 6, 'https://www.britannica.com/sports/Monopoly-board-game', 'https://www.maziply.com/cdn/shop/files/monopoly-board-game-main.jpg?v=1698936214', 2, 6);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (6, 'Clue', 'you take on the role of a suspect and move around a mansion board, attempting to deduce who killed Mr. Boddy, where they did it, and with what weapon by making suggestions about potential suspects, rooms, and weapons, and then gathering clues from other players who may hold the cards that disprove your suggestion', 10, 'https://guides.williamjames.edu/c.php?g=1057542&p=8491075#:~:text=Description%3A,the%20middle%20of%20the%20board.', 'https://libapps.s3.amazonaws.com/accounts/290156/images/clue.png', 3, 8);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (7, 'Jigsaw Puzzle', 'A 500 piece jigsaw puzzle to assemble ', 6, 'https://activepuzzles.com/blogs/news/benefits-of-jigsaw-puzzles?srsltid=AfmBOoqKe_IV4aqAWk2TCLL9qSl0LqHJHvrRe-kth6Ko1A1PR0wUN5fj', 'https://activepuzzles.com/cdn/shop/articles/8bc519d20344a69717d3c36c2026690c.jpg?v=1715862952&width=1100', 1, 4);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (8, 'Sorry', 'you aim to be the first player to move all four of your colored pawns from your starting position, around the board, and into your designated \"home\" space by drawing cards that dictate how many spaces you can move each turn', 6, 'https://en.wikipedia.org/wiki/Sorry!_(game)', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Sorry_diamond_edit.jpg/440px-Sorry_diamond_edit.jpg', 2, 4);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (9, 'Jenga', 'you take turns carefully removing one block at a time from a tower built of wooden blocks, then placing that removed block on top of the tower, continuing to stack blocks until the tower falls over; the last player to successfully remove a block before the tower collapses wins the game', 6, 'https://www.museumofplay.org/blog/jenga-jenga-jenga/', 'https://www.museumofplay.org/app/uploads/2009/01/Jenga-Extreme-2008-Gift-of-Pokonobe-Associates.-The-Strong-Rochester-New-York.jpg', 1, 5);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (10, 'Risk', 'control an army of pieces on a world map, aiming to conquer territories from other players by moving your troops into neighboring regions and battling them through dice rolls', 6, 'https://www.dicebreaker.com/games/risk/how-to/how-to-play-risk-board-game#:~:text=the%20distant%20future.-,What%20do%20you%20do%20in%20the%20Risk%20board%20game%3F,attack%20and%20capture%20neighbouring%20territories.', 'https://assetsio.gnwcdn.com/risk-board-game-gameplay-closeup.jpeg?width=848&quality=80&format=jpg&dpr=2&auto=webp', 2, 6);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (11, 'Scrabble', 'take turns forming words on a board using letter tiles. The goal is to score points by placing words that are valid in a standard English dictionary. ', 8, 'https://www.britannica.com/sports/Scrabble', NULL, 2, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `item_condition`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Excellent', 'The game includes all required peices and is not visually degraded. Pristine and still in its original packaging.', 'https://media.istockphoto.com/id/1362142320/vector/new-sticker-red-new-product-or-collection-badge-vectornewest-stamp.jpg?s=612x612&w=0&k=20&c=l7_rjI57JXi-xM5dAeNTa5cD8cdlmPljpip_5zgKyzo=');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Good', 'minimal wear and tear, with only minor scratches or blemishes on the packaging, and all components are present and functional, indicating it has been played a few times but is still in largely pristine condition', 'https://cdn4.iconfinder.com/data/icons/system-ui-set/512/accept-ok-complete-good-condition-ui-512.png');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Fair', 'noticeable wear and tear, including scratches, scuffs, or minor damage to the box and components, but is still fully playable with no major issues preventing gameplay', 'https://static-00.iconduck.com/assets.00/thumbs-up-icon-256x256-2f3zdnvc.png');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (4, 'Bad', 'a game where the overall experience is negatively affected due to poor gameplay mechanics, technical issues, or unbalanced design.', 'https://st3.depositphotos.com/1269954/14065/v/450/depositphotos_140653454-stock-illustration-bad-rubber-stamp.jpg');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (5, 'New', 'Game has never been opened, pristine and all peices are present with no defects.', 'https://banner2.cleanpng.com/20181209/rjk/kisspng-logo-sports-league-season-new-stamp-4-1-24x1-24-file-army-1713911913483.webp');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (1, 'No imperfections, never opened', 1, 'https://m.media-amazon.com/images/I/31lGanNsjvL.__AC_QL70_FMwebp_.jpg', 1, NULL, NULL, 'Twister', 1, 1, 1);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (2, 'I lost 12 peices', 1, 'https://m.media-amazon.com/images/I/61TMW+yg9cL.jpg', 1, NULL, NULL, 'Jigsaw Puzzle', 7, 2, 3);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (3, 'Only have half of the 100 bills', 1, 'https://www.maziply.com/cdn/shop/files/monopoly-board-game-main.jpg?v=1698936214', 1, NULL, NULL, 'Monopoly', 5, 2, 5);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (4, 'I ate one of the ships. ', 1, 'https://cdn.shoplightspeed.com/shops/638935/files/51079211/600x800x2/hasbro-battleship.jpg', 1, NULL, NULL, 'Battle Ship', 4, 3, 6);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (5, 'Perfect Condition, brand new!', 1, 'https://cdn.shoplightspeed.com/shops/638935/files/24878523/600x800x2/winning-moves-games-clue.jpg', 1, NULL, NULL, 'Clue', 6, 5, 7);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (6, 'dog chewed on a couple peices and some are lost. Still useable ', 1, 'https://store.asmodee.com/cdn/shop/files/CN32070-image3_2000_535x.jpg?v=1703861152', 1, NULL, NULL, 'Catan', 2, 4, 8);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (7, 'Perfect condition, only played once.', 1, 'https://i5.walmartimages.com/seo/Sorry-Game_e3be08d0-d8d5-45d2-a44c-1880e78fa0fd.1c0c60fd64a32dbe63ac4a7211f71d73.jpeg', 1, NULL, NULL, 'Sorry', 8, 1, 9);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (8, NULL, 1, 'https://cdn.shoplightspeed.com/shops/638935/files/24879847/600x800x2/hasbro-jenga-mini-giant.jpg', 1, NULL, NULL, 'Jenga', 9, 3, 4);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (9, NULL, 1, 'https://cdn.shoplightspeed.com/shops/638935/files/24879205/600x800x2/hasbro-risk.jpg', 1, NULL, NULL, 'Risk', 10, 2, 1);
INSERT INTO `inventory_item` (`id`, `notes`, `available`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (10, NULL, 1, 'https://cdn.shoplightspeed.com/shops/638935/files/26824468/600x800x2/winning-solutions-scrabble-luxury-editions.jpg', 1, NULL, NULL, 'Scrabble', 11, 5, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (1, 'other', 'other miscellaneous games', 'https://cdn-icons-png.flaticon.com/512/5895/5895032.png');
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (2, 'strategy', 'a game in which the players\' uncoerced, and often autonomous, decision-making skills have a high significance in determining the outcome.', 'https://e7.pngegg.com/pngimages/563/447/png-clipart-chess-computer-icons-board-game-strategy-video-game-chess-game-video-game-thumbnail.png');
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (3, 'puzzle', 'a type of game where the primary challenge is to solve puzzles, requiring players to use logic, pattern recognition, or spatial reasoning to navigate through a series of challenges, often by manipulating objects or completing sequences, to reach a solution and progress to the next level, with the main focus on mental stimulation rather than physical action', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (4, 'Mystery', 'a game where players take on the role of detectives, investigating a crime by gathering clues, interviewing suspects, and piecing together information to identify the culprit, often with a set location and scenario on a game board, with the goal to solve the mystery before the end of the game by correctly identifying the murderer, weapon, and location involved', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (5, 'Playing Card', 'a game played with playing cards, for fun or gambling', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (6, 'Bussness Sim', 'a tabletop game where players simulate running a company, making strategic decisions about managing finances, acquiring assets, marketing products, and competing against other players to achieve the ultimate goal of generating the most profit or building the most successful business', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (7, 'Roll to Move', 'a type of game where players primarily progress by rolling dice, with the result determining how many spaces they can move their piece around the board, essentially relying on chance to advance through the game', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (8, 'Jigsaw', 'puzzles made of pieces that fit together to form a picture, or a cooperative learning strategy', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (1, 'why do you have an unreleased version?', '2025-02-16', NULL, 1, 1, 2, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (2, 'wouldnt you like to know fed boy', '2025-02-16', NULL, 1, 1, 1, 1);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (3, 'did you steal it?', '2025-02-16', NULL, 1, 1, 3, 2);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (4, 'How well does this play with two players?', '2025-02-16', NULL, 1, 5, 4, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (5, 'I\'ve been looking for this game forever! What are you hoping to trade for?', '2025-02-16', NULL, 1, 3, 5, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (6, 'Are all the pieces included??', '2025-02-16', NULL, 1, 2, 2, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (7, 'I love this game! What\'s your favorite strategy when playing?', '2025-02-16', NULL, 1, 4, 3, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (8, 'Is the expansion included? Or is it just the base game?', '2025-02-16', NULL, 1, 2, 6, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (9, 'Would you be willing to trade for Settlers of Catan?', '2025-02-16', NULL, 1, 4, 1, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (10, 'I\'ve heard this game can be a bit complex for beginners. What\'s your take on that?', '2025-02-16', NULL, 1, 3, 2, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (11, 'If no one else is interested, I\'d love to work out a trade!', '2025-02-16', NULL, 1, 5, 4, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (12, 'I\'ve been wanting to try this game! How long does a typical session last?', '2025-02-16', NULL, 1, 3, 3, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (13, 'Is this the first edition or a newer reprint?', '2025-02-16', NULL, 1, 6, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gathering_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (1, 'w-why is it in your basement?', NULL, 1, 2, 1, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (2, 'ya got that funk', NULL, 1, 1, 1, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (3, 'fs, he really does', NULL, 1, 2, 1, 2);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (4, 'Looking forward to this!', '2025-02-16', 1, 3, 2, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (5, 'Do we need to bring our own snacks, or will they be provided?', '2025-02-16', 1, 4, 5, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (6, 'I may be a little late, but I\'ll definitely be there!', '2025-02-16', 1, 2, 2, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (7, 'How competitive are we playing? Should I bring my A-gam or keep it casual?', '2025-02-16', 1, 5, 3, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (8, 'Anyone need a ride to the venue? I have an extra seat.', '2025-02-16', 1, 2, 1, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (9, 'I\'m bring my lucky dice, hope you\'re all ready to lose lol jkjk', '2025-02-16', 1, 4, 2, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (10, 'I have a copy of Betrayal at House On the Hill if we need more options!', '2025-02-16', 1, 3, 1, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (11, 'Just RSVP\'d! Can\'t wait to meet some new players!', '2025-02-16', 1, 7, 4, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (12, 'Prepare to lose! I\'ve been prepping all week for this!', '2025-02-16', 1, 8, 7, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (13, 'Don\'t say I didn\'t warn you when I\'m dominating tonight!', '2025-02-16', 1, 9, 6, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (14, 'Is everyone ready to face defeat! Becuase I\'m bringing my A-game tonight!', '2025-02-16', 1, 8, 2, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (15, 'I\'ve got a secret tactic for this one, no one\'s gonna see it coming!', '2025-02-16', 1, 9, 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_has_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (1, 1);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (2, 2);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (3, 5);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (4, 2);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (5, 6);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (6, 4);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (7, 8);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (8, 7);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (9, 1);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (10, 2);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (11, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gathering_has_game`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `gathering_has_game` (`gathering_id`, `game_id`) VALUES (1, 1);
INSERT INTO `gathering_has_game` (`gathering_id`, `game_id`) VALUES (2, 2);
INSERT INTO `gathering_has_game` (`gathering_id`, `game_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gathering_participant`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `gathering_participant` (`gathering_id`, `user_id`, `participant_rating`, `participant_notes`, `host_rating`, `host_notes`, `create_date`) VALUES (1, 3, 3, 'was pretty bad at twister', 2, 'fun game but the host smelled like cheeto dust', NULL);
INSERT INTO `gathering_participant` (`gathering_id`, `user_id`, `participant_rating`, `participant_notes`, `host_rating`, `host_notes`, `create_date`) VALUES (1, 1, 4, 'actually knew how to play, super flexy', 1, 'the host was being..weird', NULL);
INSERT INTO `gathering_participant` (`gathering_id`, `user_id`, `participant_rating`, `participant_notes`, `host_rating`, `host_notes`, `create_date`) VALUES (2, 1, 3, 'these guys were pretty good. Need more of a challenge though.', 4, 'great event! Had fun!', NULL);
INSERT INTO `gathering_participant` (`gathering_id`, `user_id`, `participant_rating`, `participant_notes`, `host_rating`, `host_notes`, `create_date`) VALUES (2, 3, 4, 'It was challenging, learned a lot!', 5, 'super friendly! It was great!', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_game`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `favorite_game` (`user_id`, `game_id`) VALUES (1, 1);
INSERT INTO `favorite_game` (`user_id`, `game_id`) VALUES (2, 2);
INSERT INTO `favorite_game` (`user_id`, `game_id`) VALUES (3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `direct_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `direct_message` (`id`, `content`, `create_date`, `sender_id`, `recipient_id`) VALUES (1, 'youre bad', NULL, 1, 2);
INSERT INTO `direct_message` (`id`, `content`, `create_date`, `sender_id`, `recipient_id`) VALUES (2, 'you\'re*', NULL, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (1, 'who even plays twister anymore?', NULL, 1, 2, 1, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (2, 'me loser', NULL, 1, 1, 1, 1);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (3, 'how do you even play this game?', NULL, 1, 3, 1, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (4, 'This game is a must-have for any strategy lover! It’s challenging but so rewarding once you get the hang of it.', '2025-02-16', 1, 1, 4, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (5, 'Not my favorite, but it’s great for casual game nights with friends. Everyone can pick it up quickly.', '2025-02-16', 1, 8, 4, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (6, 'I’m totally hooked on this one. The artwork is amazing, and the gameplay is very immersive.', '2025-02-16', 1, 6, 3, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (7, 'It’s a bit too long for my taste, but if you’ve got the time, it’s a really fun challenge!', '2025-02-16', 1, 3, 5, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (8, 'Great game for family game night! The rules are easy to learn, and it’s a blast to play with all ages.', '2025-02-16', 1, 5, 7, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (9, 'Had a great time playing this, though I do wish there were more variety in the cards. Still, super fun!', '2025-02-16', 1, 2, 6, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (10, 'I love this game, but be prepared for a lot of downtime between turns. Great for patient players, though.', '2025-02-16', 1, 4, 4, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (11, 'Played this last weekend and it was awesome! It’s like a mix of strategy and luck, which makes it exciting!', '2025-02-16', 1, 6, 5, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (12, 'The game is fun, but the rulebook could use some work. Had to watch a tutorial to understand some parts.', '2025-02-16', 1, 7, 7, NULL);
INSERT INTO `game_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `game_id`, `in_reply_to_id`) VALUES (13, 'If you like cooperative games, you’ll love this one. It really forces you to work together with your team.', '2025-02-16', 1, 8, 3, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game_resource` (`id`, `description`, `create_date`, `enabled`, `resource_url`, `name`, `last_update`, `user_id`, `game_id`) VALUES (1, 'Hey guys I found a way to actually play twister. ', NULL, 1, 'https://www.math.uni-bielefeld.de/~sillke/Twister/rules/', 'Twister Rules', NULL, 3, 1);

COMMIT;

