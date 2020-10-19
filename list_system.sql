create database list_system;
use list_system;


-- Enrollee List
create table `list_system`.`enrollee`
(
	`enrollee_id` int not null auto_increment,
    `name` varchar(100) not null,
    `activation_status` enum("true", "false") not null,
    `birthday` date not null,
    `phone_number` varchar(20) null,
    primary key (`enrollee_id`)
);

insert into `list_system`.`enrollee` (`enrollee_id`, `name`, `activation_status`, `birthday`, `phone_number`)
values ('1', 'thien nguyen', '09-23-1992', 'true', '4075558888');

describe enrollee;



-- Dependent List
create table `list_system`.`dependent`
(
	`dependent_id` int not null auto_increment,
    `name` varchar(100) not null,
    `birthday` date not null,
    `enrollee_id` int not null,
    primary key (`dependent_id`),
    foreign key (enrollee_id) references enrollee(enrollee_id)
);

insert into `list_system`.`dependent` (`dependent_id`, `name`, `birthday`, `enrollee_id`)
values ('1', 'hung nguyen', '01-30-1970', '1');

describe dependent;