-- Query 1
-- Write a query that returns a list of reservations that
-- end in July 2023, including the name of the guest, the
-- room number(s), and the reservation dates.
Select r.roomNumber As RoomNumber, 
g.firstName As GuestFirstName,
g.lastname As GuestLastName,
r.startDate As ReservationStartDate, 
r.endDate As ReservationEndDate
from reservation r
inner join guest g ON g.guestID = r.guestID
where endDate >= '2023-07-01' AND
endDate <= '2023-07-31'
order by r.roomNumber; 

-- Results:
-- RoomNum FirstName LastName StartDate    EndDate     
-- 204 Walter    Holaway  2023-07-13   2023-07-14
-- 205 Java      Brewers  2023-06-28   2023-07-02
-- 303 Bettyann  Seery    2023-07-28   2023-07-29
-- 401 Wilfred   Vise     2023-07-18   2023-07-21

-- Query 2
-- Write a query that returns a list of all reservations
-- for rooms with a jacuzzi, displaying the guest's name,
-- the room number, and the dates of the reservation.
Select res.roomNumber as RoomNumber,
g.firstName As GuestFirstName,
g.lastname As GuestLastName,
res.startDate As ReservationStartDate, 
res.endDate As ReservationEndDate
from reservation res
inner join guest g ON g.guestId = res.guestId
inner join room room ON room.roomNumber = res.roomNumber
inner join roomAmenity rAmen ON rAmen.roomNumber = room.roomNumber
inner join amenity amen ON amen.amenityId = rAmen.amenityId
where amen.amenityType = 'jacuzzi'
order by res.roomNumber;

-- Results:
-- RoomNum FirstName LastName StartDate    EndDate 
-- 201 Karie     Yang     2023-03-06   2023-03-07
-- 203 Bettyann  Seery    2023-02-05   2023-02-10
-- 203 Karie     Yang     2023-09-13   2023-09-15
-- 205 Java      Brewers  2023-06-28   2023-07-02
-- 207 Wilfred   Vise     2023-04-23   2023-04-24
-- 301 Walter    Holaway  2023-04-09   2023-04-13
-- 301 Mack      Simmer   2023-11-22   2023-11-25
-- 303 Bettyann  Seery    2023-07-28   2023-07-29
-- 305 Duane     Cullison 2023-02-22   2023-02-24
-- 305 Bettyann  Seery    2023-08-30   2023-09-01
-- 307 Java      Brewers  2023-03-17   2023-03-20
-- 401 Maritza   Tilton   2023-05-30   2023-06-02
-- 401 Wilfred   Vise     2023-07-18   2023-07-21
-- 401 Duane     Cullison 2023-11-22   2023-11-25 

-- Query 3
-- Write a query that returns all the rooms reserved for a
-- specific guest, including the guest's name, the room(s)
-- reserved, the starting date of the reservation, and how
-- many people were included in the reservation. (Choose a
-- guest's name from the existing data.)
select CONCAT(g.firstName, ' ', g.lastName) as Name, ro.roomNumber as 'Room Number', re.startDate as 'Start Date', (adults + children) as 'Number of People'
from room ro
inner join reservation re on ro.roomNumber = re.roomNumber
inner join guest g on re.guestId = g.guestId
where g.guestId = '3';

-- Results:
--     Name       Room Number       Start Date     Number of People
-- Bettyann Seery     203           2023-02-05             3
-- Bettyann Seery     303           2023-07-28             3
-- Bettyann Seery     305           2023-08-30             1

-- Query 4
-- Write a query that returns a list of rooms, reservation ID,
-- and per-room cost for each reservation. The results should
-- include all rooms, whether or not there is a reservation
-- associated with the room.
select ro.roomNumber as 'Room Number', re.reservationId as 'Reservation ID', re.totalRoomCost as 'Total Room Cost'
from room ro
left outer join reservation re on re.roomNumber = ro.roomNumber;

-- Results:
-- Room Number	Reservation ID	Total Room Cost
--    201            1004             199.99
--    202            1007             349.98
--    203            1002             999.95
--    203            1021             399.98
--    204            1016             184.99
--    205            1015             699.96
--    206            1012             599.96
--    206            1023             449.97
--    207            1010             174.99
--    208            1013             599.96
--    208            1020             149.99
--    301            1009             799.96
--    301            1024             599.97
--    302            1006             924.95
--    302            1025             699.96
--    303            1018             199.99
--    304            1008             874.95
--    304            1014             184.99
--    305            1003             349.98
--    305            1019             349.98
--    306            null             null
--    307            1005             524.97
--    308            1001             299.98
--    401            1011             1199.97
--    401            1017             1259.97
--    401            1022             1199.97
--    402            null             null	 

-- Query 5

-- Write a query that returns all rooms with a capacity of three or more 
-- and that are reserved on any date in April 2023.
select
	room.roomNumber,
    room.maxOccupancy
from room
inner join reservation on room.roomNumber = reservation.roomNumber
where reservation.startDate between '2023-04-01' and '2023-04-30'
	and room.maxOccupancy >= 3;
	
-- Results:
-- RoomNumber	maxOccupancy
-- 301			4

-- Query 6
-- Write a query that returns a list of all guest names and the number of 
-- reservations per guest, sorted starting with the guest with the most 
-- reservations and then by the guest's last name.
select
	guest.firstname,
    guest.lastname,
    count(reservation.guestId) NumberOfReservations
from guest
inner join reservation on guest.guestId = reservation.guestId
group by reservation.guestId
order by NumberofReservations desc, guest.lastname asc;

-- Results:

-- FirstName	LastName	NumberOfReservations
-- Mack			Simmer				4	
-- Bettyann		Seery				3
-- Java			Brewers				2
-- Duane		Cullison			2
-- Walter		Holaway				2
-- Aurore		Lipton				2
-- Maritza		Tilton				2
-- Joleen		Tison				2
-- Wilfred		Vise				2
-- Karie		Yang				2
-- Zachery		Luechtefeld			1
-- Jeremiah		Pendergrass			1

/*7. Write a query that displays the name, address,
and phone number of a guest based on their phone number.
 (Choose a phone number from the existing data.)*/

SELECT lastName, firstName, address, city, state, zip, phone
FROM guest
WHERE phone = '(446) 396-6785'

-- Results:
-- lastName	firstName	address	city	state	zip	phone
-- Holaway	Walter	7556 Arrowhead St.	Cumberland	RI	02864	(446) 396-6785
