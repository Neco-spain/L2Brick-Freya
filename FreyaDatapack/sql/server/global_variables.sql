CREATE TABLE IF NOT EXISTS `global_variables` (
  `var`  VARCHAR(20) NOT NULL DEFAULT '',
  `value` VARCHAR(255) ,
  PRIMARY KEY (`var`)
) ENGINE = MYISAM DEFAULT CHARSET=utf8;