CREATE TABLE IF NOT EXISTS `clanhall_functions` (
  `hall_id` int(2) NOT NULL default '0',
  `type` int(1) NOT NULL default '0',
  `lvl` int(3) NOT NULL default '0',
  `lease` int(10) NOT NULL default '0',
  `rate` decimal(20,0) NOT NULL default '0',
  `endTime` bigint(13) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`hall_id`,`type`)
) ENGINE = MYISAM DEFAULT CHARSET=utf8;