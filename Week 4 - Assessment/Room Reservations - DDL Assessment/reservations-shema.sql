drop database if exists reservations;

create database reservations;

use reservations;

create table room (
	roomNumber int,
    numberOfBeds varchar(2) not null,
    bedSize varchar(10) not null,
    constraint pk_room primary key (roomNumber)
);

create table guest (
	guestId int auto_increment,
    guestFirstName varchar(25) not null,
    guestLastName varchar(50) not null,
    guestAddress varchar(100) not null,
    guestPhoneNumber varchar(10) not null,
    guestEmail varchar(50),
    constraint pk_guest primary key (guestId)
);

create table amenity (
	amenityNumber int auto_increment,
    amenityType varchar(50) not null,
    constraint pk_amenity primary key (amenityNumber)
);

create table roomAmenity (
	roomNumber int,
    amenityNumber int,
    constraint pk_roomAmenity primary key (roomNumber, amenityNumber),
    constraint fk_roomAmenity_room foreign key (roomNumber) references room(roomNumber),
    constraint fk_roomAmenity_amenity foreign key (amenityNumber) references amenity(amenityNumber)
);

create table roomReservation (
	reservationId int auto_increment,
    roomNumber int not null,
    guestId int not null,
    startDate date not null,
    endDate date not null,
    numberOfGuests varchar(2) not null,
    constraint pk_roomReservation primary key (reservationId),
    constraint fk_roomReservation_room foreign key (roomNumber) references room(roomNumber),
    constraint fk_roomReservation_guest foreign key (guestId) references guest(guestId)
);