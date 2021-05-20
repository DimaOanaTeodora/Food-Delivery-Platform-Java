select * from user;

DELIMITER //
create PROCEDURE insertUser(
 INOUT name varchar(30), 
 IN email varchar(30),
 IN  phoneNumber varchar(10),
 IN password varchar(30))
BEGIN
insert into user(name, email, phoneNumber, password) 
values (name, email, phoneNumber, password);
END //

DELIMITER ;

DROP PROCEDURE IF EXISTS insertUser;

DELETE from user;

UPDATE user SET email='email@nou.com' WHERE name='Dan Ionel';

CREATE TABLE IF NOT EXISTS burgers 
( bruger_id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(30), 
price double,
isVegan boolean,
ingredients varchar(100));

select * from burgers;

CREATE TABLE IF NOT EXISTS drinks 
(drink_id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(30), 
price double,
flavour varchar(50));

select * from drinks;

CREATE TABLE IF NOT EXISTS rfoods
(rfood_id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(30), 
price double,
ingredients varchar(100));

select * from rfoods;

CREATE TABLE IF NOT EXISTS sweets 
(sweet_id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(30), 
price double,
calories integer);

select * from sweets;

drop table burgers;
drop table drinks;
drop table rfoods;
drop table sweets;

delete from burgers;
delete from drinks;
delete from rfoods;
delete from sweets;

INSERT INTO burgers(name, price, isVegan, ingredients) 
VALUES('Burger Gurmand', 7.56, false , 'chiftea carne de vita, branza, ceapa, rosii');

INSERT INTO burgers(name, price, isVegan, ingredients) 
VALUES('Veggie', 10.56, true , 'chiftea din soia, branza, ceapa, rosii');

INSERT INTO drinks(name, price, flavour) 
VALUES('Cola 0 250 ml', 4.3, 'cola cu lamaie fara zahar');

INSERT INTO drinks(name, price, flavour) 
VALUES('Lipton Ice Tea Piersica 500 ml', 7.56, 'piersici');

INSERT INTO rfoods(name, price, ingredients) 
VALUES('Ciorba de vacuta', 10.56, 'carne de vita, cartofi, ceapa, rosii, smantana, patrunjel');

INSERT INTO rfoods(name, price, ingredients) 
VALUES('Snitel vienez', 8.77, 'snitel din carne de pui, pesmet, cascaval, sunca');

INSERT INTO sweets(name, price, calories) 
VALUES('Eclere cu vanilie', 3.56, 200);

INSERT INTO sweets(name, price, calories) 
VALUES('Tarte cu cocos', 4.56, 154);

DROP PROCEDURE IF EXISTS insertDrink;

DELIMITER //
create PROCEDURE insertBurger(
 INOUT name varchar(30), 
 IN price double,
 IN isVegan boolean,
 IN ingredients varchar(100))
BEGIN
insert into burgers(name, price, isVegan, ingredients) 
values (name, price, isVegan, ingredients);
END //

DELIMITER //
create PROCEDURE insertDrink(
 INOUT name varchar(30), 
 IN price double,
 IN flavour varchar(50))
BEGIN
insert into drinks(name, price, flavour) 
values (name, price, flavour);
END //;

DELIMITER //
create PROCEDURE insertRFood(
 INOUT name varchar(30), 
 IN price double,
 IN ingredients varchar(100))
BEGIN
insert into rfoods(name, price, ingredients) 
values (name, price, ingredients);
END //

DELIMITER //
create PROCEDURE insertSweet(
 INOUT name varchar(30), 
 IN price double,
 IN calories integer)
BEGIN
insert into sweets(name, price, calories) 
values (name, price, calories);
END //


