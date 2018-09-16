/*LEC_ROOM 강의실DB (사용, 빔 만 있음)*/
truncate table LEC_ROOM;
insert into LEC_ROOM values (0,'기본',0);
insert into LEC_ROOM values (101,'빔',30);
insert into LEC_ROOM values (102,'빔',20);
insert into LEC_ROOM values (103,'빔',45);
insert into LEC_ROOM values (104,'빔',30);
insert into LEC_ROOM values (105,'빔',15);
insert into LEC_ROOM values (106,'빔',10);
insert into LEC_ROOM values (107,'빔',40);


select max_num, (select count(*) from member where lec_code = 18000 and mem_type = 0) as nowNum
from lec_room A where classno = (select classno from lec_info where lec_code = 18000);


select lec_code from LEC_INFO;
/*LEC_INFO 강의DB (강의상태는 1: 비공개 2: 공개 3: 진행중 4: 완료)*/
truncate table LEC_INFO;
insert into LEC_INFO values (11111,'빈강의','아무개',sysdate,sysdate,20,'java','http://asd.com','임시내용1',1,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명1','아무개',sysdate,sysdate,20,'java','http://asd.com','임시내용1',2,101);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명2','아무개2',sysdate,sysdate,20,'web','http://asd.com','임시내용2',1,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명3','아무개3',sysdate,sysdate,20,'db','http://asd.com','임시내용3',1,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명4','아무개4',sysdate,sysdate,20,'java','http://asd.com','임시내용4',1,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명5','아무개5',sysdate,sysdate,20,'java','http://asd.com','임시내용5',1,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명6','아무개6',sysdate,sysdate,20,'web','http://asd.com','임시내용6',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명7','아무개7',sysdate,sysdate,20,'db','http://asd.com','임시내용7',2,0);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명8','아무개8',sysdate,sysdate,20,'java','http://asd.com','임시내용8',2,0);

/*MEMBER 계정DB 학생 : 0, 교사 : 1, 취업 : 2, 영업 : 3, 행정 : 4, 관리자 : 5, 임시 : 99, 비회원 : 100 */
truncate table MEMBER;
insert into MEMBER values ('stu01', '1234', '학생1', 123456, 'stu01@naver.com', 0, 11111);
insert into MEMBER values ('stu02', '1234', '학생2', 123456, 'stu02@naver.com', 0, 11111);
insert into MEMBER values ('stu03', '1234', '학생3', 123456, 'stu03@naver.com', 0, 11111);

insert into MEMBER values ('stu04', '1234', '학생4', 123456, 'stu04@naver.com', 0, 11111);
insert into MEMBER values ('stu05', '1234', '학생5', 123456, 'stu05@naver.com', 0, 11111);
insert into MEMBER values ('stu06', '1234', '학생6', 123456, 'stu06@naver.com', 0, 11111);
insert into MEMBER values ('stu07', '1234', '학생7', 123456, 'stu07@naver.com', 0, 11111);
insert into MEMBER values ('stu08', '1234', '학생8', 123456, 'stu08@naver.com', 0, 11111);
insert into MEMBER values ('stu09', '1234', '학생9', 123456, 'stu09@naver.com', 0, 11111);
insert into MEMBER values ('stu10', '1234', '학10', 123456, 'stu11@naver.com', 0, 11111);
insert into MEMBER values ('stu11', '1234', '학11', 123456, 'stu12@naver.com', 0, 11111);
insert into MEMBER values ('stu12', '1234', '학12', 123456, 'stu13@naver.com', 0, 11111);


insert into MEMBER values ('임시id', '1234', '강사1', 123456, 'tea00@naver.com', 1, 11111);

insert into MEMBER values ('tea01', '1234', '유시민', 123456, 'tea01@naver.com', 1, 11111);
insert into MEMBER values ('tea02', '1234', '설민석', 123456, 'tea02@naver.com', 1, 11111);
insert into MEMBER values ('tea03', '1234', '최태성', 123456, 'tea03@naver.com', 1, 11111);
insert into MEMBER values ('tea04', '1234', '유1민', 123456, 'tea04@naver.com', 1, 11111);
insert into MEMBER values ('tea05', '1234', '설2석', 123456, 'tea05@naver.com', 1, 11111);
insert into MEMBER values ('tea06', '1234', '최3성', 123456, 'tea06@naver.com', 1, 11111);
insert into MEMBER values ('tea07', '1234', '유4민', 123456, 'tea07@naver.com', 1, 11111);
insert into MEMBER values ('tea08', '1234', '설5석', 123456, 'tea08@naver.com', 1, 11111);
insert into MEMBER values ('tea09', '1234', '최6성', 123456, 'tea09@naver.com', 1, 11111);



insert into MEMBER values ('exc01', '1234', '행정1', 123456, 'tea00@naver.com', 4, 11111);


insert into MEMBER values ('admin', '1234', '관리자', 123456, 'admin@naver.com', 5, 11111);


/*LEC_AF_BBS 수강후기게시판*/
truncate table Lec_AF_BBS;
insert into LEC_AF_BBS values (LEC_AF_BBS_SEQ.nextval,'수강후기1','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기2','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기3','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18000,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18001,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18001,'가나다라마바사아자차카타',0);
insert into Lec_af_bbs values (LEC_AF_BBS_SEQ.nextval,'수강후기4','khj',sysdate,18001,'가나다라마바사아자차카타',0);

select * from member where id='amor0617';

/*LEC_NOTICE_BBS 강의공지사항게시판*/
truncate table LEC_NOTICE_BBS;
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목1','아이디1',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목2','아이디2',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목3','아이디3',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목4','아이디4',sysdate,18000,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목5','아이디5',sysdate,18001,'내용내용내용내용내용',0);
insert into LEC_NOTICE_BBS values (LEC_NOTICE_BBS_SEQ.nextval,'제목6','아이디6',sysdate,18001,'내용내용내용내용내용',0);


/*JOB_INFO_BBS 취업정보게시판*/
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


/*Attend 출석DB*/
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
select count(*) from score where id = 'stu01';
select * from SCORE;
/*SCORE 성적DB*/
truncate table SCORE;
insert into SCORE values ('stu01','학생1',90,80,50,220,18000);	
insert into SCORE values ('stu03','학생3',100,100,100,300,18000);	
insert into SCORE values ('stu02','학생2',40,60,50,150,18001);	

insert into score values ('stu01', (select name from member where id = 'stu01'), 0, 0, 0, 0, (select lec_code from member where id = 'stu01'));
update score set java = 0, web= 0, db=0, tot=0, lec_code=(select lec_code from member where id = 'stu01') where id = 'stu01';

select lec_code from member where id ='stu09';



/*JOB_AF_BBS dummy 취업현황게시판*/
truncate table JOB_AF_BBS;
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title1','contents1',sysdate,'company1','user1',0,1111,'java','user1');
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title2','contents2',sysdate,'company2','user2',0,1111,'java','user2');
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title3','contents3',sysdate,'company3','user3',0,1111,'java','user3');
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title4','contents4',sysdate,'company4','user4',0,1111,'java','user4');
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title5','contents5',sysdate,'company5','user5',0,1111,'java','user5');
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title6','contents6',sysdate,'company6','user6',0,1111,'java','user6');
insert into JOB_AF_BBS values(JOB_AF_BBS_SEQ.nextval,'title7','contents7',sysdate,'company7','user7',0,1111,'java','user7');

/*emp_notice_bbs dummy 직원게시판*/
truncate table EMP_NOTICE_BBS;
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title1','contents1','user1',sysdate,0);
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title2','contents2','user2',sysdate,0);
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title3','contents3','user3',sysdate,0);
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title4','contents4','user4',sysdate,0);
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title5','contents5','user5',sysdate,0);
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title6','contents6','user6',sysdate,0);
insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,'title7','contents7','user7',sysdate,0);

/*CONSULT_BBS dummy 상담게시판
 * 강의 코드 기본값 11111 
 * 성별은 남,여로 표기
 * 수강목적은 변경가능*/
truncate table CONSULT_BBS;
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user1','title1',sysdate,'id1','남','123@gmail.com',01012344567,'job','11111','contents1','answer1',0);
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user2','title2',sysdate,'id2','여','234@gmail.com',01012344567,'job','11111','contents2','answer2',0);
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user3','title3',sysdate,'id3','여','345@gmail.com',01012355567,'job','11111','contents3','answer3',0);
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user4','title4',sysdate,'id4','남','456@gmail.com',010354567,'job','11111','contents4','answer4',0);
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user5','title5',sysdate,'id5','여','567@gmail.com',0104344567,'job','11111','contents5','answer5',0);
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user6','title6',sysdate,'id6','남','1asdf@gmail.com',08212344567,'job','11111','contents6','answer6',0);
insert into CONSULT_BBS values(CONSULT_BBS_SEQ.nextval,'user7','title7',sysdate,'id7','여','1qwer3@gmail.com',01342344567,'job','11111','contents7','answer7',0);




