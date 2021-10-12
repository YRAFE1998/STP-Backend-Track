CREATE TABLE employees (
id bigint UNSIGNED auto_increment primary KEY,
firstname varchar(30) not null,
lastname varchar(30) not null,
email varchar(50) not null unique,
department varchar(50),
phoneNumber varchar(12),
nationalID varchar(14) not null unique,
employeeRole varchar(50)
);
create Table projects(
id bigint unsigned auto_increment primary KEY,
name varchar(60) not null,
startDate Date,
managerID bigint unsigned,
foreign key (managerID) references employees(id)
);


create table employee_workson_project(
employeeID bigint UNSIGNED,
projectID bigint UNSIGNED,
foreign key (employeeID) references employees(id),
foreign key (projectID) references projects(id)
);

