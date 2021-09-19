drop database if exists javabrewers_guessthenumber;

create database javabrewers_guessthenumber;

use javabrewers_guessthenumber;

create table  game (
	gameId int auto_increment primary key,
    gameAnswer int not null,
    isFinished boolean not null default 0
);

create table round (
	roundId int auto_increment primary key,
    gameId int not null,
    guess int not null,
    guessTime datetime not null,
    guessResult char(7) not null,
    constraint fk_round_game foreign key (gameId) references game(gameId)
);