CREATE TABLE car(
	carnum int,
	brand varchar(300),
    color varchar(300),
    price int    
);	
select * from car;
insert into car (brand,price) values ('Ferrari',65000);
select * from car where not color = "black";

truncate car;
DROP TABLE tb_user;

show tables;
CREATE TABLE tb_user(
	userinx INT PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR(300) UNIQUE NOT NULL,
    userpw VARCHAR(300) NOT NULL,
    username VARCHAR(300) NOT NULL,
    useremail VARCHAR(300) NOT NULL,
    usergender VARCHAR(10) NOT NULL,    
    regidate DATETIME DEFAULT now() 
);
show tables;

select * from tb_user;

