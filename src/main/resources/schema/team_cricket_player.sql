CREATE TABLE `team_cricket_player` (
  `team_id` bigint(20) NOT NULL,
  `cricket_player_id` bigint(20) NOT NULL,
  `team_role` varchar(50) NOT NULL,
  PRIMARY KEY (`team_id`,`cricket_player_id`)
);