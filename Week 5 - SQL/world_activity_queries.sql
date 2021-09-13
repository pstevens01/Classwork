use world;

-- Activity 1
select *
from city
limit 10;

select *
from country
limit 10;

select *
from countrylanguage
limit 10;

-- Activity 2
select *
from city
where population < 10000
order by population desc;

-- Activity 3
select co.region AS Region, co.name AS Country, ci.name AS City
from city ci
inner join country co ON ci.countrycode = co.code
order by co.region, co.name, ci.name;

-- Activity 4
select
	country.Name,
    countrylanguage.Language,
    countrylanguage.Percentage
from country
left outer join countrylanguage on country.Code = countrylanguage.CountryCode
where Language = 'French'
order by Percentage desc;

-- Activity 5
select
	country.Name CountryName,
    country.Continent,
    country.Population
from country
where country.IndepYear is null
order by country.Name;

-- Activity 6
select
	country.Name CountryName,
    country.Continent,
    countrylanguage.Language,
    countrylanguage.Percentage
from country
left outer join countrylanguage on country.Code = countrylanguage.CountryCode
order by country.name, countrylanguage.Percentage desc;

-- Custom Query #1
select co.name AS 'Country Name', cl.percentage AS 'Official Language % Spoken', count(cl.language) AS 'Total Languages Spoken', cl.language AS 'Most Popular Language'
from countrylanguage cl
inner join country co ON co.code = cl.countrycode
where cl.isOfficial = true
group by cl.countrycode
order by cl.percentage;