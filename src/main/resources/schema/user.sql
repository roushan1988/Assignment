CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `starting_amount` bigint(20) NOT NULL,
  `balance_amount` bigint(20) NOT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_id` (`team_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
);