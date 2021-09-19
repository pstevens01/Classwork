drop database if exists javabrewers_blogCMS;

create database javabrewers_blogCMS;

use javabrewers_blogCMS;

create table  creator (
	creatorId int auto_increment primary key,
    firstName varchar(25) NOT NULL,
    lastName varchar(50) NOT NULL,
    canPost boolean NOT NULL default 0
);

create table tag (
	tagId int auto_increment primary key,
    tagName varchar(50) NOT NULL
);

create table post (
	postId int auto_increment primary key ,
    creatorId int NOT NULL,
	postTitle varchar(200) NOT NULL,
	postText mediumtext NOT NULL,
    startDate date NOT NULL,
    endDate date NOT NULL,
    isVisible boolean NOT NULL default 0,
    constraint fk_post_creator foreign key (creatorId) references creator(creatorId)
);

create table postTag (
	postId int NOT NULL,
    tagId int NOT NULL,
    constraint fk_postTag_post foreign key (postId) references post(postId),
    constraint fk_postTag_tag foreign key (tagId) references tag(tagId)
);