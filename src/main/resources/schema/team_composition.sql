CREATE TABLE `team_composition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `batsman_count` bigint(11) NOT NULL,
  `bowler_count` bigint(11) NOT NULL,
  `wicket_keeper_count` bigint(11) NOT NULL,
  `all_rounder_count` bigint(11) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);