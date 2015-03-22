CREATE TABLE `team_player_mapping` (
  `team_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_cricket_player_id` bigint(20) NOT NULL,
  PRIMARY KEY (`team_id`,`team_cricket_player_id`)
);