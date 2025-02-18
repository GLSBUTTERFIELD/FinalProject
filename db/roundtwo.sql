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
  `street` VARCHAR(100) NOT NULL,
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
  `avaliable` TINYINT NOT NULL DEFAULT 1,
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

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (1, 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'will', 'bill', 'chadmin', 'willbillsucks@gmail.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6XQHmnERLMxBjTPBD7AW66hNEpDJCSZyS2w&s', NULL, NULL, NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (2, 'Player2', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Ray', 'Williams', NULL, 'rayray@gmail.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `role`, `email`, `image_url`, `biography`, `create_date`, `last_update`, `address_id`) VALUES (3, 'Hamilton', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Gabby', 'Blorp', NULL, 'GabbySuks@gmail.com', NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gathering`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (1, 'Twister Meetup', 'We are going to play a game of twister in my basement. Be ready!', NULL, 2, 4, 1, NULL, '2025-02-15 00:00:00', NULL, '22:00:00', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfDQEiXs0VOr0pRJyj4hsRLdeZYuZfLId0Ug&s', '2025-02-13', NULL, 1, 1);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (2, 'Catan Time!', 'I love playing catan and im looking for other people to play with. Any one want to join?', NULL, 2, 6, 1, NULL, '2025-02-13 00:00:00', NULL, '12:00:00', NULL, '2025-02-14', NULL, 2, 2);
INSERT INTO `gathering` (`id`, `name`, `description`, `registration_fee_usd`, `min_participants`, `max_participants`, `enabled`, `create_date`, `last_update`, `end_time`, `start_time`, `image_url`, `start_date`, `end_date`, `user_id`, `address_id`) VALUES (3, 'Magic The Gathering', 'Im a novice when it comes to magic, can someone come and help me out? Id want to learn as much as i can.', NULL, 2, 4, 1, NULL, '2025-01-31 00:00:00', NULL, '20:00:00', NULL, '2025-02-15', NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (1, 'Twister', 'a party game where players take turns placing their hands and feet on colored circles on a mat while avoiding falling over', 6, 'https://en.wikipedia.org/wiki/Twister_(game)', 'https://upload.wikimedia.org/wikipedia/en/0/09/1966_Twister_Cover.jpg', 2, 4);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (2, 'Catan', 'Players take on the roles of settlers, each attempting to build and develop holdings while trading and acquiring resources.', 8, 'https://en.wikipedia.org/wiki/Catan#:~:text=Players%20take%20on%20the%20roles,points%2C%20typically%2010%2C%20wins.', 'https://upload.wikimedia.org/wikipedia/en/a/a3/Catan-2015-boxart.jpg', 3, 6);
INSERT INTO `game` (`id`, `name`, `description`, `minimum_age`, `website_url`, `image_url`, `min_players`, `max_players`) VALUES (3, 'Magic the Gathering', 'a collectible trading card game where players build decks of cards representing creatures, spells, and artifacts, then take turns battling each other by summoning creatures, casting spells, and using abilities to reduce their opponent\'s life total to zero, with the goal of being the last player standing', 12, 'https://en.wikipedia.org/wiki/Magic:_The_Gathering', 'https://upload.wikimedia.org/wikipedia/en/a/aa/Magic_the_gathering-card_back.jpg', 2, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `item_condition`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Excellent Condition', 'The game includes all required peices and is not visually degraded. Pristine and still in its original packaging.', 'https://media.istockphoto.com/id/1362142320/vector/new-sticker-red-new-product-or-collection-badge-vectornewest-stamp.jpg?s=612x612&w=0&k=20&c=l7_rjI57JXi-xM5dAeNTa5cD8cdlmPljpip_5zgKyzo=');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Good Condition', 'minimal wear and tear, with only minor scratches or blemishes on the packaging, and all components are present and functional, indicating it has been played a few times but is still in largely pristine condition', 'https://cdn4.iconfinder.com/data/icons/system-ui-set/512/accept-ok-complete-good-condition-ui-512.png');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Fair Condition', 'noticeable wear and tear, including scratches, scuffs, or minor damage to the box and components, but is still fully playable with no major issues preventing gameplay', 'https://static-00.iconduck.com/assets.00/thumbs-up-icon-256x256-2f3zdnvc.png');
INSERT INTO `item_condition` (`id`, `name`, `description`, `image_url`) VALUES (4, 'Bad Condition', 'a game where the overall experience is negatively affected due to poor gameplay mechanics, technical issues, or unbalanced design.', 'https://st3.depositphotos.com/1269954/14065/v/450/depositphotos_140653454-stock-illustration-bad-rubber-stamp.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `inventory_item` (`id`, `notes`, `avaliable`, `image_url`, `enabled`, `create_date`, `last_update`, `name`, `game_id`, `condition_id`, `user_id`) VALUES (1, 'Delux Never Released Twister Game', 1, 'https://m.media-amazon.com/images/I/31lGanNsjvL.__AC_QL70_FMwebp_.jpg', 1, NULL, NULL, 'Twister Delux Edition', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (1, 'other', 'other miscellaneous games', 'https://cdn-icons-png.flaticon.com/512/5895/5895032.png');
INSERT INTO `category` (`id`, `name`, `description`, `image_url`) VALUES (2, 'strategy', 'a game in which the players\' uncoerced, and often autonomous, decision-making skills have a high significance in determining the outcome.', 'https://e7.pngegg.com/pngimages/563/447/png-clipart-chess-computer-icons-board-game-strategy-video-game-chess-game-video-game-thumbnail.png');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (1, 'why do you have an unreleased version?', NULL, NULL, 1, 1, 2, NULL);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (2, 'wouldnt you like to know fed boy', NULL, NULL, 1, 1, 1, 1);
INSERT INTO `inventory_item_comment` (`id`, `comment`, `create_date`, `last_update`, `enabled`, `inventory_item_id`, `user_id`, `in_reply_to_id`) VALUES (3, 'did you steal it?', NULL, NULL, 1, 1, 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gathering_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (1, 'w-why is it in your basement?', NULL, 1, 2, 1, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (2, 'ya got that funk', NULL, 1, 1, 1, NULL);
INSERT INTO `gathering_comment` (`id`, `comment`, `create_date`, `enabled`, `user_id`, `gathering_id`, `in_reply_to_id`) VALUES (3, 'fs, he really does', NULL, 1, 2, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_has_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (1, 1);
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (2, 2);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `roundtwodb`;
INSERT INTO `game_resource` (`id`, `description`, `create_date`, `enabled`, `resource_url`, `name`, `last_update`, `user_id`, `game_id`) VALUES (1, 'Hey guys I found a way to actually play twister. ', NULL, 1, 'https://www.math.uni-bielefeld.de/~sillke/Twister/rules/', 'Twister Rules', NULL, 3, 1);

COMMIT;

