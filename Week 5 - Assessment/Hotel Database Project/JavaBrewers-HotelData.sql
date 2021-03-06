use JavaBrewersHotel;

INSERT INTO `amenity` VALUES (1,'Microwave'),(2,'Refrigerator'),(3,'Jacuzzi');

INSERT INTO `guest` VALUES (1,'Java','Brewers','123 Code Ln.','Seattle','WA','12345','(555) 111-7890'),
(2,'Mack','Simmer','379 Old Shore Street','Council Bluffs','IA','51501','(291) 553-0508'),
(3,'Bettyann','Seery','750 Wintergreen Dr.','Wasilla','AK','99654','(478) 277-9632'),
(4,'Duane','Cullison','9662 Foxrun Lane','Harlingen','TX','78552','(308) 494-0198'),
(5,'Karie','Yang','9378 W. Augusta Ave.','West Deptford','NJ','08096','(214) 730-0298'),
(6,'Aurore','Lipton','762 Wild Rose Street','Saginaw','MI','48601','(377) 507-0974'),
(7,'Zachery','Luechtefeld','7 Poplar Dr.','Arvada','CO','80003','(814) 485-2615'),
(8,'Jeremiah','Pendergrass','70 Oakwood St.','Zion','IL','60099','(279) 491-0960'),
(9,'Walter','Holaway','7556 Arrowhead St.','Cumberland','RI','02864','(446) 396-6785'),
(10,'Wilfred','Vise','77 West Surrey Street','Oswego','NY','13126','(834) 727-1001'),
(11,'Maritza','Tilton','939 Linda Rd.','Burke','VA','22015','(446) 351-6860'),
(12,'Joleen','Tison','87 Queen St.','Drexel Hill','PA','19026','(231) 893-2755');

INSERT INTO `room` VALUES (201,'Double',0,2,4,199.99,10),(202,'Double',1,2,4,174.99,10),(203,'Double',0,2,4,199.99,10),
(204,'Double',1,2,4,174.99,10),(205,'Single',0,2,2,174.99,0),(206,'Single',1,2,2,149.99,0),
(207,'Single',0,2,2,174.99,0),(208,'Single',1,2,2,149.99,0),(301,'Double',0,2,4,199.99,10),
(302,'Double',1,2,4,174.99,10),(303,'Double',0,2,4,199.99,10),(304,'Double',1,2,4,174.99,10),
(305,'Single',0,2,2,174.99,0),(306,'Single',1,2,2,149.99,0),(307,'Single',0,2,2,174.99,0),
(308,'Single',1,2,2,149.99,0),(401,'Suite',1,3,8,399.99,20),(402,'Suite',1,3,8,399.99,20);

INSERT INTO `reservation` VALUES (1001,'2023-02-02','2023-02-04',1,0,299.98,2,308),(1002,'2023-02-05','2023-02-10',2,1,999.95,3,203),
(1003,'2023-02-22','2023-02-24',2,0,349.98,4,305),(1004,'2023-03-06','2023-03-07',2,2,199.99,5,201),
(1005,'2023-03-17','2023-03-20',1,1,524.97,1,307),(1006,'2023-03-18','2023-03-23',3,0,924.95,6,302),
(1007,'2023-03-29','2023-03-31',2,2,349.98,7,202),(1008,'2023-03-31','2023-04-05',2,0,874.95,8,304),
(1009,'2023-04-09','2023-04-13',1,0,799.96,9,301),(1010,'2023-04-23','2023-04-24',1,1,174.99,10,207),
(1011,'2023-05-30','2023-06-02',2,4,1199.97,11,401),(1012,'2023-06-10','2023-06-14',2,0,599.96,12,206),
(1013,'2023-06-10','2023-06-14',1,0,599.96,12,208),(1014,'2023-06-17','2023-06-18',3,0,184.99,6,304),
(1015,'2023-06-28','2023-07-02',2,0,699.96,1,205),(1016,'2023-07-13','2023-07-14',3,1,184.99,9,204),
(1017,'2023-07-18','2023-07-21',4,2,1259.97,10,401),(1018,'2023-07-28','2023-07-29',2,1,199.99,3,303),
(1019,'2023-08-30','2023-09-01',1,0,349.98,3,305),(1020,'2023-09-16','2023-09-17',2,0,149.99,2,208),
(1021,'2023-09-13','2023-09-15',2,2,399.98,5,203),(1022,'2023-11-22','2023-11-25',2,2,1199.97,4,401),
(1023,'2023-11-22','2023-11-25',2,0,449.97,2,206),(1024,'2023-11-22','2023-11-25',2,2,599.97,2,301),
(1025,'2023-12-24','2023-12-28',2,0,699.96,11,302);

INSERT INTO `roomamenity` VALUES (201,1),(203,1),(205,1),(206,1),(207,1),(208,1),(301,1),(303,1),(305,1),
(306,1),(307,1),(308,1),(401,1),(402,1),(202,2),(204,2),(205,2),(206,2),(207,2),(208,2),(302,2),(304,2),
(305,2),(306,2),(307,2),(308,2),(401,2),(402,2),(201,3),(203,3),(205,3),(207,3),(301,3),(303,3),(305,3),
(307,3),(401,3),(402,3);

-- Delete Jeremiah Pendergrass from guest and reservation tables
DELETE FROM reservation
WHERE guestId = 8;

DELETE FROM guest
WHERE guestId = 8 AND lastName = 'Pendergrass' AND firstName = 'Jeremiah';
