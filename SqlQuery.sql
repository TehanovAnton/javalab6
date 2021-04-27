use java6JDBC;
create table people(
	fio varchar(30) primary key,
	birthDate date)

insert into people(fio, birthDate)
values('teh ant vict', '2002-06-06'),
	('gor ars andr', '2001-12-30'),
	('laz dmi vlad', '2001-05-31'),
	('fes den mark', '2001-05-31')
select * from people;

create table letter(
	sender varchar(30) foreign key references people(fio),
	recipient varchar(30),
	topic varchar(50),
	content varchar(50),
	sendingDate date)

insert into letter(sender, recipient, topic, content, sendingDate)
values('teh ant vict', 'gor ars andr', 'wearther', 'it is cold today', '2019-01-01'),
	('teh ant vict', 'laz dmi vlad', 'wearther', 'is it cold today', '2019-01-01'),
	('gor ars andr', 'teh ant vict', 'hobby', 'lets go to skateboard', '2019-01-02'),
	('gor ars andr', 'fes den mark', 'hobby', 'will you go to skateboard', '2019-01-02')
select * from letter;

drop table letter;
drop table people;