use login;

CREATE TABLE car(
	carnum int,
	brand varchar(300),
    color varchar(300),
    price int    
);	
select * from car;
insert into car (brand,price) values ('Ferrari',65000);
select * from car where not color = "black";

truncate tb_user;
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

insert into tb_user(userid,userpw, username,useremail,usergender) 
values ('asdf1234','4444','Yonghoon','skdk@gmail.com', 'Male');
insert into tb_user(userid,userpw, username,useremail,usergender)
 values ('as12','4444','Yongbean','sk123dk@gmail.com', 'Male');
 insert into tb_user(userid,userpw, username,useremail,usergender)
 values ('as12342','444fdfg4','Yongjun','sk13r2f23dk@gmail.com', 'Male');
 insert into tb_user(userid,userpw, username,useremail,usergender)
 values ('as12snjv','4sdf342444','Park','sk123dk@naver.com', 'Female');
 insert into tb_user(userid,userpw, username,useremail,usergender)
 values ('klmav12','efdfsv','Seong','sseong@gmail.com', 'Female');
 
 SELECT username, userid FROM tb_user;
 
 CREATE TABLE tb_admin(
	userinx INT PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR(300) UNIQUE NOT NULL,  
    regidate DATETIME DEFAULT now() 
);

show tables;
select * from tb_admin;

select * from tb_user;
select userid, userpw from tb_user where username = 'Yonghoon' and useremail = 'skdk@gmail.com';

 insert into tb_user(userid,userpw, username,useremail,usergender)
 values ('sjnksd','efdfsv','asdf','asdf', 'Female');
 
 select count(*) from tb_user where username = 'Humont';
 
 truncate tb_user;
 select count(*) from tb_user where userid is null;
 
 select * from tb_user;
 select count(*)
from tb_user;

select count(*) from tb_user where userid = 'yonghoon1999';
select userid, username from tb_user where userpw='4444';
select username,usergender,useremail,userid from tb_user where userid = 'yonghoon1999';