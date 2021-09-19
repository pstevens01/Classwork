INSERT INTO creator (firstname, lastname, canPost) VALUES ("name", "test", 0);
INSERT INTO creator (firstname, lastname, canPost) VALUES ("java", "brewers", 1);
INSERT INTO creator (firstname, lastname, canPost) VALUES ("one", "two", 0);

INSERT INTO tag (tagName) VALUES ("blog update");
INSERT INTO tag (tagName) VALUES ("diamond");
INSERT INTO tag (tagName) VALUES ("gold");

INSERT INTO post (creatorId, postTitle, postText, startDate, endDate, isVisible)
VALUES (1, "Blog post 1", "This is the start of the Java Jewelers' Blog!", '2021-09-16', '2071-09-16', 1);
INSERT INTO post (creatorId, postTitle, postText, startDate, endDate, isVisible)
VALUES (3, "New diamond ring!", "We are happy to announce the introduction of a new diamond ring with a gold band...", '2021-09-16', '2022-09-16', 0);
INSERT INTO post (creatorId, postTitle, postText, startDate, endDate, isVisible)
VALUES (2, "New gold necklace!", "Be the first to buy our new gold necklace...", '2021-09-16', '2022-09-16', 0);

INSERT INTO postTag (postId, tagId) VALUES (1, 1);
INSERT INTO postTag (postId, tagId) VALUES (2, 2);
INSERT INTO postTag (postId, tagId) VALUES (2, 3);
INSERT INTO postTag (postId, tagId) VALUES (3, 3);