drop database if exists JavaBrewersHotel;

create database JavaBrewersHotel;

use JavaBrewersHotel;

create table room (
	roomNumber int primary key,
	roomType varchar(10) not null,
    adaAccessible boolean not null,
    standardOccupancy int not null,
    maxOccupancy int not null,
    basePrice decimal(5,2) not null,
    extraPerson decimal(2,0)
);

create table guest (
	guestId int primary key auto_increment,
    firstName varchar(25) not null,
    lastName varchar(50) not null,
    address varchar(100) not null,
    city varchar(25) not null,
    state varchar(15) not null,
    zip char(5) not null,
    phone varchar(14) not null
);

create table amenity (
	amenityId int primary key auto_increment,
    amenityType varchar(50) not null
);

create table roomAmenity (
	roomNumber int,
    amenityId int,
    constraint pk_roomAmenity primary key (roomNumber, amenityId),
    constraint fk_roomAmenity_room foreign key (roomNumber) references room(roomNumber),
    constraint fk_roomAmenity_amenity foreign key (amenityId) references amenity(amenityId)
);

create table reservation (
	reservationId int primary key auto_increment,
    startDate date not null,
    endDate date not null,
    adults tinyint not null,
    children tinyint not null,
    totalRoomCost decimal(6,2) not null,
    guestId int not null,
    roomNumber int not null,
    constraint fk_reservation_guest foreign key (guestId) references guest(guestId),
    constraint fk_reservation_room foreign key (roomNumber) references room(roomNumber)
);