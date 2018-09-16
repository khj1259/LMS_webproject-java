--LEC_ROOM
-- 사용, 빔 만 있음
truncate table LEC_ROOM;

insert into LEC_ROOM values (0,'사용',20);
insert into LEC_ROOM values (101,'빔',30);
insert into LEC_ROOM values (102,'빔',40);
insert into LEC_ROOM values (103,'빔',25);
insert into LEC_ROOM values (104,'빔',30);
insert into LEC_ROOM values (105,'빔',40);
insert into LEC_ROOM values (106,'빔',30);
insert into LEC_ROOM values (107,'빔',35);

--LEC_INFO
-- 강의상태는 1: 비공개 2: 공개 3: 진행중 4: 완료

truncate table LEC_INFO;
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명1','아무개',sysdate,sysdate,20,'java','http://asd.com','임시내용1',2,101);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명2','아무개2',sysdate,sysdate,20,'web','http://asd.com','임시내용2',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명3','아무개3',sysdate,sysdate,20,'db','http://asd.com','임시내용3',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명4','아무개4',sysdate,sysdate,20,'java','http://asd.com','임시내용4',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명5','아무개5',sysdate,sysdate,20,'java','http://asd.com','임시내용5',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명6','아무개6',sysdate,sysdate,20,'web','http://asd.com','임시내용6',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명7','아무개7',sysdate,sysdate,20,'db','http://asd.com','임시내용7',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명8','아무개8',sysdate,sysdate,20,'java','http://asd.com','임시내용8',2,0);

select * from LEC_INFO;

-- MEMBER
-- 학생 : 0, 교사 : 1, 취업 : 2, 영업 : 3, 행정 : 4, 관리자 : 5, 임시 : 99 

truncate table MEMBER
insert into MEMBER values ('stu01', '1234', '학생1', 123456, 'stu01@naver.com', 0, 18000);
insert into MEMBER values ('stu02', '1234', '학생2', 123456, 'stu02@naver.com', 0, 18000);
insert into MEMBER values ('stu03', '1234', '학생3', 123456, 'stu03@naver.com', 0, 18001);
insert into MEMBER values ('임시id', '1234', '강사1', 123456, 'tea00@naver.com', 1, 0);
insert into MEMBER values ('tea01', '1234', '강사2', 123456, 'tea01@naver.com', 1, 0);
insert into MEMBER values ('tea02', '1234', '강사3', 123456, 'tea01@naver.com', 1, 0);
insert into MEMBER values ('tea03', '1234', '강사4', 123456, 'tea00@naver.com', 1, 0);
insert into MEMBER values ('admin', '1234', '관리자', 123456, 'admin@naver.com', 5, 0);

--LEC_AF_BBS
truncate table Lec_AF_BBS;
insert into LEC_AF_BBS values (LEC_AF_BBS_SEQ.nextval,'수강후기1','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기2','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기3','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18001,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18001,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18001,'가나다라마바사아자차카타',0);

select * from LEC_AF_BBS;

--LEC_NOTICE_BBS
truncate table LEC_NOTICE_BBS;
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목1','아이디1',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목2','아이디2',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목3','아이디3',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목4','아이디4',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목5','아이디5',sysdate,18001,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목6','아이디6',sysdate,18001,'내용내용내용내용내용',0);


-- JOB_INFO_BBS


truncate table JOB_INFO_BBS;
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목1','임시',SYSDATE,'삼성',	100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목2','임시',SYSDATE,'2성', 100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용2',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목3','임시',SYSDATE,'3성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용3',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목4','임시3',SYSDATE,'4성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용4',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목5','임시3',SYSDATE,'5성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용5',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목6','임시3',SYSDATE,'6성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용6',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목7','임시3',SYSDATE,'7성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용7',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목8','임시',SYSDATE,'7성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용8',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목9','임시',SYSDATE,'7성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용9',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목10','임시',SYSDATE,'7성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용10',0);
INSERT INTO JOB_INFO_BBS VALUES (
	JOB_INFO_BBS_SEQ.NEXTVAL,'제목11','임시',SYSDATE,'7성',100,'계약직',2000,'서울','-',SYSDATE+10,'123@COM','상세모집내용11',0);


--insert 서브쿼리 test
insert into lec_notice_bbs values (LEC_NOTICE_BBS_SEQ.nextval,'제목7','임시id',sysdate,
				(select lec_code from MEMBER where id='임시id'), 'test', 0);
				
				--강의게시판 selectAll 테스트
select rownum as rn, A.num, A.title, A.id, A.nalja, A.view_num from 
				(select num, title, id, nalja, view_num, lec_code from lec_notice_bbs order by num) A 
				where A.lec_code=(select lec_code from MEMBER where id='임시id')
				order by rn desc;
				
--Attend 
truncate table Attend;
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/06', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), TO_DATE('2018/02/06 9:42:25', 'yyyy/mm/dd HH24:MI:SS'), 
   TO_DATE('2018/02/06 18:30:25', 'yyyy/mm/dd HH24:MI:SS'), '지각', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/07', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), TO_DATE('2018/02/07 9:30:25', 'yyyy/mm/dd HH24:MI:SS'), 
   TO_DATE('2018/02/07 18:30:25', 'yyyy/mm/dd HH24:MI:SS'), '출석', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/08', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), null, 
   null, '결석', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/09', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), TO_DATE('2018/02/09 9:30:25', 'yyyy/mm/dd HH24:MI:SS'), 
   TO_DATE('2018/02/09 18:30:25', 'yyyy/mm/dd HH24:MI:SS'), '출석', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/10', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), null, 
   null, '결석', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/11', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), null, 
   null, '결석', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu01', TO_DATE('2018/02/12', 'yyyy/mm/dd'), (select name from MEMBER where id='stu01'), null, 
   null, '결석', (select lec_code from MEMBER where id='stu01')  
);
INSERT INTO ATTEND values(
   'stu02', TO_DATE('2018/02/09', 'yyyy/mm/dd'), (select name from MEMBER where id='stu02'), TO_DATE('2018/02/09 9:50:25', 'yyyy/mm/dd HH24:MI:SS'), 
   TO_DATE('2018/02/09 18:30:25', 'yyyy/mm/dd HH24:MI:SS'), '지각', (select lec_code from MEMBER where id='stu02')  
);

select * from ATTEND;
--count(decode()) 테스트
select count(decode(state,'지각','1')) as late,count(decode(state,'결석','1')) as absent from ATTEND group by id having id='stu01';

--한 학생의 출결 조회 : 이름,날짜,입실,퇴실,출결상태, 지각횟수,결석횟수
select  A.name, A.nalja, to_char(A.in_date,'HH24:MI:SS') as in_date, to_char(A.out_date,'HH24:MI:SS') as out_date, A.state, 
	(select count(decode(state,'지각','1')) from ATTEND group by id having id='stu01') as late ,
	(select count(decode(state,'결석','1')) from Attend group by id having id='stu01') as absent  
	from attend A where A.id='stu01' order by nalja desc;

--SCORE
truncate table SCORE;
insert into SCORE values ('stu01','학생1',90,80,50,220,18000);	
insert into SCORE values ('stu03','학생3',100,100,100,300,18000);	
insert into SCORE values ('stu02','학생2',40,60,50,150,18001);	
