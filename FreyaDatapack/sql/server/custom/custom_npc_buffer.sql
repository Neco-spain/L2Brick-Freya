CREATE TABLE IF NOT EXISTS `custom_npc_buffer` (
  `npc_id` int(6) NOT NULL,
  `skill_id` int(6) NOT NULL,
  `skill_level` int(6) NOT NULL default '1',
  `skill_fee_id` int(6) NOT NULL default '0',
  `skill_fee_amount` int(6) NOT NULL default '0',
  `buff_group` int(6) NOT NULL default '0',
  PRIMARY KEY (`npc_id`,`skill_id`,`buff_group`)
) ENGINE = MYISAM DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `custom_npc_buffer` VALUES
(99999,264,1,0,0,264),
(99999,265,1,0,0,265),
(99999,266,1,0,0,266),
(99999,267,1,0,0,267),
(99999,268,1,0,0,268),
(99999,269,1,0,0,269),
(99999,270,1,0,0,270),
(99999,271,1,0,0,271),
(99999,272,1,0,0,272),
(99999,273,1,0,0,273),
(99999,274,1,0,0,274),
(99999,275,1,0,0,275),
(99999,276,1,0,0,276),
(99999,277,1,0,0,277),
(99999,304,1,0,0,304),
(99999,305,1,0,0,305),
(99999,306,1,0,0,306),
(99999,307,1,0,0,307),
(99999,308,1,0,0,308),
(99999,309,1,0,0,309),
(99999,310,1,0,0,310),
(99999,311,1,0,0,311),
(99999,349,1,0,0,349),
(99999,363,1,0,0,363),
(99999,364,1,0,0,364),
(99999,365,1,0,0,365),
(99999,366,1,0,0,366),
(99999,367,1,0,0,367),
(99999,529,1,0,0,529),
(99999,530,1,0,0,530),
(99999,825,1,0,0,825),
(99999,826,1,0,0,826),
(99999,827,1,0,0,827),
(99999,828,1,0,0,828),
(99999,829,1,0,0,829),
(99999,830,1,0,0,830),
(99999,1002,3,0,0,1002),
(99999,1003,3,0,0,1003),
(99999,1004,3,0,0,1004),
(99999,1005,3,0,0,1005),
(99999,1006,3,0,0,1006),
(99999,1007,3,0,0,1007),
(99999,1008,3,0,0,1008),
(99999,1009,3,0,0,1009),
(99999,1010,3,0,0,1010),
(99999,1032,3,0,0,1032),
(99999,1033,3,0,0,1033),
(99999,1035,4,0,0,1035),
(99999,1036,2,0,0,1036),
(99999,1040,3,0,0,1040),
(99999,1043,1,0,0,1043),
(99999,1044,3,0,0,1044),
(99999,1045,6,0,0,1045),
(99999,1048,6,0,0,1048),
(99999,1059,3,0,0,1059),
(99999,1062,2,0,0,1062),
(99999,1068,3,0,0,1068),
(99999,1073,2,0,0,1073),
(99999,1077,3,0,0,1077),
(99999,1078,6,0,0,1078),
(99999,1085,3,0,0,1085),
(99999,1086,2,0,0,1086),
(99999,1087,3,0,0,1087),
(99999,1182,3,0,0,1182),
(99999,1189,3,0,0,1189),
(99999,1191,3,0,0,1191),
(99999,1204,2,0,0,1204),
(99999,1240,3,0,0,1240),
(99999,1242,3,0,0,1242),
(99999,1243,3,0,0,1243),
(99999,1249,3,0,0,1249),
(99999,1250,3,0,0,1250),
(99999,1251,2,0,0,1251),
(99999,1252,3,0,0,1252),
(99999,1253,3,0,0,1253),
(99999,1257,3,0,0,1257),
(99999,1259,4,0,0,1259),
(99999,1260,3,0,0,1260),
(99999,1261,2,0,0,1261),
(99999,1268,4,0,0,1268),
(99999,1282,2,0,0,1282),
(99999,1284,3,0,0,1284),
(99999,1303,2,0,0,1303),
(99999,1304,3,0,0,1304),
(99999,1308,3,0,0,1308),
(99999,1309,3,0,0,1309),
(99999,1310,4,0,0,1310),
(99999,1323,1,0,0,1323),
(99999,1352,1,0,0,1352),
(99999,1353,1,0,0,1353),
(99999,1354,1,0,0,1354),
(99999,1355,1,0,0,1355),
(99999,1356,1,0,0,1356),
(99999,1357,1,0,0,1357),
(99999,1362,1,0,0,1362),
(99999,1363,1,0,0,1363),
(99999,1364,1,0,0,1364),
(99999,1365,1,0,0,1365),
(99999,1388,3,0,0,1388),
(99999,1389,3,0,0,1389),
(99999,1390,3,0,0,1390),
(99999,1391,3,0,0,1391),
(99999,1392,3,0,0,1392),
(99999,1393,3,0,0,1393),
(99999,1397,3,0,0,1397),
(99999,1413,1,0,0,1413),
(99999,1414,1,0,0,1414),
(99999,1415,1,0,0,1415),
(99999,1416,1,0,0,1416),
(99999,1460,1,0,0,1460),
(99999,1461,1,0,0,1461),
(99999,1476,3,0,0,1476),
(99999,1477,3,0,0,1477),
(99999,1478,2,0,0,1478),
(99999,1479,3,0,0,1479),
(99999,1499,1,0,0,1499),
(99999,1500,1,0,0,1500),
(99999,1501,1,0,0,1501),
(99999,1502,1,0,0,1502),
(99999,1503,1,0,0,1503),
(99999,1504,1,0,0,1504),
(99999,1517,1,0,0,1517),
(99999,1518,1,0,0,1518),
(99999,1519,1,0,0,1519),
(99999,4699,13,0,0,4699),
(99999,4700,13,0,0,4700),
(99999,4702,13,0,0,4702),
(99999,4703,13,0,0,4703),
(99999,21046,1,0,0,21046),
(99999,1307,3,0,0,21046),
(99999,1311,6,0,0,21046),
(99999,1257,3,0,0,21046),
(99999,834,3,0,0,21046),
(99999,1444,1,0,0,21046),
(99999,1442,1,0,0,21046),
(99999,1443,1,0,0,21046);