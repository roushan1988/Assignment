CREATE TABLE `team_player_mapping` (
  `team_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  PRIMARY KEY (`team_id`,`player_id`)
);