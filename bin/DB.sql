select * from tabs;

select * from USERINFO;
select * from heart;

select count(r_id) as heartcount from heart where r_id = 'kjjks12';

insert into userinfo values('kjjks1','1234','¡§±§ºˆ',1,19920122,'kjjks12@naver.com',5,100);
insert into userinfo values('test1','1234','¿Â»Ò¡§',2,19940122,'jang@naver.com','img',5);

update userinfo set heart=5 where id='kjjks1';

delete USERINFO where id='kjjks12';

commit

select sysdate from dual;

select * from tab;

select * from heart;

select count(r_id)from heart where r_id = 'a';