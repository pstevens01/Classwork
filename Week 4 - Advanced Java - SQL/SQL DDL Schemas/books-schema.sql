# Paul Stevens
# 09/08/2021

drop database if exists books;

create database books;

use books;

create table author (
	authorId int auto_increment,
    authorFirstName varchar(25) not null,
    authorMiddleName varchar(25),
    authorLastName varchar(50) not null,
    gender char(1),
    dateOfBirth date not null,
    dateOfDeath date,
    constraint pk_author primary key (authorId)
);

create table book (
	bookId int auto_increment,
    bookTitle varchar(100) not null,
    publicationDate date,
    constraint pk_book primary key (bookId)
);

create table format (
	formatId int auto_increment,
    formatName varchar(12) not null,
    constraint pk_format primary key (formatId)
);

create table genre (
	genreId int auto_increment,
    genreName varchar(25) not null,
    constraint pk_genre primary key (genreId)
);

drop table if exists authorBook;
create table authorBook (
	authorID int,
    bookId int,
    constraint pk_authorBook primary key (authorId, bookId),
    constraint fk_authorBook_author foreign key (authorId) references author(authorId),
    constraint fk_authorBook_book foreign key (bookId) references book(bookId)
);

drop table if exists bookFormat;
create table bookFormat (
	bookId int,
    formatId int,
    price decimal(5,2),
    quantityOnHand int,
    constraint pk_bookFormat primary key (bookId, formatId),
    constraint fk_bookFormat_book foreign key (bookId) references book(bookId),
    constraint fk_bookFormat_format foreign key (formatId) references format(formatId)
);

drop table if exists bookGenre;
create table bookGenre (
	bookId int,
    genreId int,
    constraint pk_bookGenre primary key (bookId, genreId),
    constraint fk_bookGenre_book foreign key (bookId) references book(bookId),
    constraint fk_bookGenre_genre foreign key (genreId) references genre(genreId)
);