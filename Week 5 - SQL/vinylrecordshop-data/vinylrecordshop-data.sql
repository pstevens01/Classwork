# Paul Stevens
# 09/08/2021

use vinylrecordshop;

INSERT INTO `artist` VALUES (1,'John','Lennon',1),(2,'Paul','McCartney',1),(3,'George','Harrison',1),
(4,'Ringo','Starr',1),(5,'Denny','Zager',0),(6,'Rick','Evans',0),(10,'Van','Morrison',1),(11,'Judy','Collins',0),
(12,'Paul','Simon',1),(13,'Art','Garfunkel',0),(14,'Brian','Wilson',0),(15,'Dennis','Wilson',0),
(16,'Carl','Wilson',0),(17,'Ricky','Fataar',0),(18,'Blondie','Chaplin',0),(19,'Jimmy','Page',0),
(20,'Robert','Plant',0),(21,'John Paul','Jones',0),(22,'John','Bonham',0),(23,'Mike ','Love',0),
(24,'Al ','Jardine',0),(25,'David','Marks',0),(26,'Bruce ','Johnston',0);

INSERT INTO `album` VALUES (1,'Imagine','Apple','1971-09-09',9.99),(2,'2525 (Exordium & Terminus)','RCA','1969-07-01',25.99),
(3,'No One\'s Gonna Change Our World','Regal Starline','1969-12-12',39.95),(4,'Moondance Studio Album','Warner Bros','1969-08-01',14.99),
(5,'Clouds','Reprise','1969-05-01',9.99),(6,'Sounds of Silence Studio Album','Columbia','1966-01-17',9.99),
(7,'Abbey Road','Apple','1969-01-10',12.99),(8,'Smiley Smile','Capitol','1967-09-18',5.99);

INSERT INTO `band` VALUES (1,'The Beatles'),(2,'Zager and Evans'),(3,'Van Morrison'),(4,'Judy Collins'),
(5,'Simon and Garfunkel'),(7,'Beach Boys'),(8,'Led Zeppelin');

INSERT INTO `bandartist` VALUES (1,1),(1,2),(1,3),(1,4),(2,5),(2,6),(3,10),(4,11),(5,12),(5,13),(7,14),
(7,15),(7,16),(7,17),(7,18),(8,19),(8,20),(8,21),(8,22),(7,23),(7,24),(7,25),(7,26);

INSERT INTO `song` VALUES (1,'Imagine','https://youtu.be/DVg2EJvvlF8',1),(2,'In the Year 2525','https://youtu.be/izQB2-Kmiic',2),
(3,'Across the Universe','https://youtu.be/Tjq9LmSO1eI',1),(4,'Moondance','https://youtu.be/6lFxGBB4UG',3),
(5,'Both Sides Now','https://youtu.be/rQOuxByR5VI',4),(6,'Sounds of Silence','https://youtu.be/qn0QBXMYXsM',5),
(7,'Something','https://youtu.be/xLGe-QzCK4Q',1),(9,'Good Vibrations','https://youtu.be/d8rd53WuojE',7),
(10,'Come Together','https://youtu.be/_HONxwhwmgU',1),(11,'Something','https://youtu.be/UKAp-jRUp2o',1),
(12,'Maxwell\'s Silver Hammer','https://youtu.be/YQgsob_o1io',1);

INSERT INTO `songalbum` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(10,7),(11,7),(12,7);