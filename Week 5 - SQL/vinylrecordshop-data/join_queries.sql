# Paul Stevens
# Select_Queries
# 09/09/2021

# Select all columns from ExerciseCategory and Exercise. (64 rows)
# The tables should be joined on ExerciseCategoryId.
# This query returns all Exercises and their associated ExerciseCategory.
select *
from exercisecategory
inner join exercise on exercisecategory.ExerciseCategoryId = exercise.ExerciseCategoryId;

# Select ExerciseCategory.Name and Exercise.Name where the ExerciseCategory does not have a ParentCategoryId (it is null).
# Again, join the tables on their shared key (ExerciseCategoryId). (9 rows)
select
	exercisecategory.name,
    exercise.name
from exercisecategory
inner join exercise on exercisecategory.ExerciseCategoryId = exercise.ExerciseCategoryId
where ParentCategoryId is null;

# The query above is a little confusing. At first glance, its hard to tell which Name belongs to ExerciseCategory and which belongs to Exercise.
# Rewrite the query using aliases:
# Alias ExerciseCategory.Name as 'CategoryName'.
# Alias Exercise.Name as 'ExerciseName'.  (9 rows)
select
	exercisecategory.name CategoryName,
    exercise.name ExerciseName
from exercisecategory
inner join exercise on exercisecategory.ExerciseCategoryId = exercise.ExerciseCategoryId
where ParentCategoryId is null;

# Select FirstName, LastName, and BirthDate from Client and EmailAddress from Login 
# where Client.BirthDate is in the 1990s.
# Join the tables by their key relationship. (35 rows)
select
	client.FirstName,
    client.LastName,
    login.EmailAddress
from client
inner join login on client.ClientId = login.ClientId
where BirthDate between '1990-01-01' and '1999-12-31';

# Select Workout.Name, Client.FirstName, and Client.LastName for Clients with LastNames starting with 'C'?
# How are Clients and Workouts related? (25 rows)
select
	workout.name WorkoutName,
    client.FirstName,
    client.Lastname
from client
inner join clientworkout on client.clientid = clientworkout.ClientId
inner join workout on clientworkout.WorkoutId = workout.WorkoutId
where client.lastname like 'C%';

# Select Names from Workouts and their Goals.
# This is a many-to-many relationship with a bridge table.
# Use aliases appropriately to avoid ambiguous columns in the result. (78 rows)
select
	workout.Name WorkoutName,
    goal.name GoalName
from workout
inner join workoutgoal on workout.WorkoutId = workoutgoal.WorkoutId
inner join goal on workoutgoal.GoalId = goal.GoalId;

# Select client names and email addresses. (Step 1)
# Select FirstName and LastName from Client.
# Select ClientId and EmailAddress from Login. (500 rows)
Join the tables, but make Login optional.
select
	client.firstname,
    client.lastname,
    login.ClientId,
    login.EmailAddress
from client
left outer join login on client.ClientId = login.ClientId;

# Select client names and email addresses. (Step 2)
# Select FirstName and LastName from Client.
# Select ClientId and EmailAddress from Login. (200 rows)
select
	client.firstname,
    client.lastname,
    login.ClientId,
    login.EmailAddress
from client
left outer join login on client.ClientId = login.ClientId
where login.ClientId is null;

# Does the Client, Romeo Seaward, have a Login?
# Decide using a single query. (1 or 0 rows)
select
	client.firstname,
    client.lastname,
    login.ClientId
from client
left outer join login on client.ClientId = login.ClientId
where client.FirstName = 'Romeo' and client.LastName = 'Seaward';

# Select ExerciseCategory.Name and its parent ExerciseCategorys Name.
# This requires a self-join. (12 rows)
select
	parent.ExerciseCategoryId ParentCategoryId,
	child.ExerciseCategoryId ChildCategoryId,
	concat(parent.name, ': ', child.name) Name
from exercisecategory parent
inner join exercisecategory child on parent.ExerciseCategoryId = child.ParentCategoryId;

# Rewrite the query above so that every ExerciseCategory.Name 
# is included, even if it doesnt have a parent. (16 rows)
select
	parent.ExerciseCategoryId ParentCategoryId,
	child.ExerciseCategoryId ChildCategoryId,
	concat(parent.name, ': ', child.name) Name
from exercisecategory parent
right outer join exercisecategory child on parent.ExerciseCategoryId = child.ParentCategoryId;

# Are there Clients who are not signed up for a Workout? (50 rows)
select
	client.firstname,
    client.LastName
from client
left outer join clientworkout on client.ClientId = clientworkout.clientId
where clientworkout.WorkoutId is null;

#Which Beginner-Level Workouts satisfy at least one of Shell Creanes Goals?
# Goals are associated to Clients through ClientGoal.
# Goals are associated to Workouts through WorkoutGoal.
select
	workout.Name,
    workout.levelid
from workout
inner join workoutgoal on workout.WorkoutId = workoutgoal.WorkoutId
inner join goal on workoutgoal.goalid = goal.GoalId
inner join clientgoal on goal.GoalId = clientgoal.GoalId
inner join client on clientgoal.ClientId = client.ClientId
where client.firstname = 'Shell' and workout.LevelId = 1;

# Select all Workouts.
# Join to the Goal, 'Core Strength', but make it optional.
# You may have to look up the GoalId before writing the main query.
# If you filter on Goal.Name in a WHERE clause, Workouts will be excluded. Why? (26 wo
select
	workout.name,
    goal.GoalId
from workout
left outer join workoutgoal on workout.WorkoutId = workoutgoal.WorkoutId
left outer join goal on workoutgoal.GoalId = goal.GoalId
where goal.GoalId = 10;