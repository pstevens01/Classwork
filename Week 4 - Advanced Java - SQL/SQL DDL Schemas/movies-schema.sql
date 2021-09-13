drop database if exists movies;

create database movies;

use movies;

create table director (
	directorId int auto_increment,
    directorFirstName varchar(30) not null,
    directorLastName varchar(30) not null,
    birthdate date,
    constraint pk_director primary key (directorId)
);

create table actor (
	actorId int auto_increment,
    actorFirstName varchar(30) not null,
    actorLastName varchar(30) not null,
    actorBirthDate date,
    constraint pk_actor primary key (actorId)
);

create table  genre (
	genreId int auto_increment,
    genreName varchar(30) not null,
    constraint pk_genre primary key (genreId)
);

create table rating (
	ratingId int auto_increment,
    ratingName char(5) not null,
    constraint pk_rating primary key (ratingId)
);

drop table if exists movie;
create table movie (
	movieId int auto_increment,
    genreId int not null,
    directorId int,
    ratingId int,
    title varchar(128) not null,
    releaseDate date,
    constraint pk_movie primary key (movieId),
	constraint fk_movie_genre foreign key (genreId) references genre(genreId),
    constraint fk_movie_director foreign key (directorId) references director(directorId),
    constraint fk_movie_rating foreign key (ratingId) references rating(ratingId)
);

drop table if exists castMembers;
create table castMembers (
	castMemberId int auto_increment,
    actorId int not null,
    movieId int not null,
    role varchar(50) not null,
    constraint pk_castMembers primary key (castMemberId),
    constraint fk_castMembers_actor foreign key (actorId) references actor(actorId),
    constraint fk_castMembers_movie foreign key (movieId) references movie(movieId)
);