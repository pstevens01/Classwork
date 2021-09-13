#Paul Stevens
#09/08/2021

drop database if exists vinylrecordshop;

create database vinylrecordshop;

use vinylrecordshop;

create table album (
	albumId int auto_increment,
    albumTitle varchar(100) not null,
    label varchar(50),
    releaseDate date,
    price decimal(5,2),
    constraint pk_album primary key (albumId)
);

create table artist (
	artistId int auto_increment,
    artistFirstName varchar(25),
    artistLastName varchar(50) not null,
    constraint pk_artist primary key (artistId)
);

create table band (
	bandId int auto_increment,
    bandName varchar(50) not null,
    constraint pk_band primary key (bandId)
);

drop table if exists song;
create table song (
	songId int auto_increment,
    songTitle varchar(100) not null,
    videoUrl varchar(100),
    bandId int not null,
    constraint pk_song primary key (songId),
    constraint fk_song_band foreign key (bandId) references band(bandId)
);

drop table if exists songAlbum;
create table songAlbum (
	songId int,
    albumId int,
    constraint pk_songAlbum primary key (songId, albumId),
    constraint fk_songAlbum_song foreign key (songId) references song(songId),
    constraint fk_songAlbum_album foreign key (albumId) references album(albumId)
);

drop table if exists bandArtist;
create table bandArtist (
	bandId int,
    artistId int,
    constraint pk_bandArtist primary key (bandId, artistId),
    constraint fk_bandArtist_bandId foreign key (bandId) references band(bandId),
    constraint fk_bandArtist_artistId foreign key (artistId) references artist(artistId)
);