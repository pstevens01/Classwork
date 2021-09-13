# Paul Stevens
# Select_Queries
# 09/08/2021

# Select all rows and columns from the Exercise table. (64 rows)
select *
from exercise;

# Select all rows and columns from the Client table. (500 rows)
select *
from client;

# Select all columns from Client where the City is Metairie. (29 rows)
select *
from client
where city = 'Metairie';

# Is there a Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'? (0 rows)
select *
from client
where clientid = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';

# How many rows are in the Goal table? (17 rows)
select *
from goal;

# Select Name and LevelId from the Workout table. (26 rows)
select 
	`name`,
    levelid,
    notes
from workout;

# Select FirstName, LastName, and City from Client where City is Metairie, Kenner, or Gretna. (77 rows)
select 
	firstname,
    lastname,
    city
from client
where (city = 'Metairie' or city = 'Kenner' or city = 'Gretna');

# Select FirstName, LastName, and BirthDate from Client for Clients born in the 1980s. (72 rows)
select 
	firstname,
    lastname,
    birthdate
from client
where BirthDate between '1980-01-01' and '1989-12-31';

# Write the query above in a different way. If you used BETWEEN, you cant use it again.
select 
	firstname,
    lastname,
    birthdate
from client
where BirthDate >= '1980-01-01' and BirthDate <= '1989-12-31';

How many rows in the Login table have a .gov EmailAddress? (17 rows)
select *
from login
where EmailAddress like '%.gov';

# How many Logins do NOT have a .com EmailAddress? (122 rows)
select *
from login
where EmailAddress not like '%.com';

# Select first and last name of Clients without a BirthDate. (37 rows)
select 
	firstname,
    lastname
from client
where birthdate is null;

# Select the Name of each ExerciseCategory that has a parent (ParentCategoryId value is not null). (12 rows)
select 
	`name`
from exercisecategory
where ParentCategoryId is not null;

# Select Name and Notes of each level 3 Workout that contains the word 'you' in its Notes. (4 rows)
select 
	`name`,
    notes
from workout
where levelid = 3 and notes like '%you%';

# Select FirstName, LastName, City from Client whose LastName starts with L,M, or N and who live in LaPlace. (5 rows)
select
	firstname,
    lastname,
    city
from client
where city = 'LaPlace' and lastname between 'L%' and 'N%';

# Select InvoiceId, Description, Price, Quantity, ServiceDate and the line item total, a calculated value,
# from InvoiceLineItem, where the line item total is between 15 and 25 dollars. (667 rows)
 select
	invoiceid,
	`description`,
    price,
    quantity,
    servicedate,
    (price * quantity) as Total
from invoicelineitem
where (price * quantity) between 15 and 25;

# Does the database include an email address for the Client, Estrella Bazely? This requires two queries:
# 1.Select a Client record for Estrella Bazely. Does it exist?
# 2.If it does, select a Login record that matches its ClientId.
select *
from login
where ClientId = (
	select ClientId
    from client
    where firstname = 'Estrella' and lastname = 'Bazely');

# What are the Goals of the Workout with the Name 'This Is Parkour'? You need three queries!
# 1. Select the WorkoutId from Workout where Name equals 'This Is Parkour'. (1 row)
# 2. Select GoalId from WorkoutGoal where the WorkoutId matches the WorkoutId from your first query. (3 rows)
# 3. Select the goal name from Goal where the GoalId is one of the GoalIds from your second query. (3 rows)
select
	`name`
from goal
where GoalId = (
	select
		goalid
	from workoutgoal
	where WorkoutId = (
		select
			workoutid
		from workout
		where name = 'This is Parkour'));