
truncate table LEC_ROOM;

insert into LEC_ROOM values (101,'사용',20);
insert into LEC_ROOM values (102,'빔',20);
insert into LEC_ROOM values (103,'빔',20);
insert into LEC_ROOM values (104,'빔',20);

truncate table LEC_INFO;

insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명1','아무개',sysdate,sysdate,20,'java',101);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명2','아무개2',sysdate,sysdate,20,'web',102);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명3','아무개3',sysdate,sysdate,20,'db',103);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명4','아무개4',sysdate,sysdate,20,'java',104);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명5','아무개5',sysdate,sysdate,20,'java',101);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명6','아무개6',sysdate,sysdate,20,'web',102);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명7','아무개7',sysdate,sysdate,20,'db',103);
insert into LEC_INFO values (LEC_CODE_SEQ.nextval,'테스트명8','아무개8',sysdate,sysdate,20,'java',104);

truncate table MeMBER

insert into MEMBER values ('stu01', '1234', '학생1', 123456, 'stu01@naver.com', 0, 0);
insert into MEMBER values ('stu02', '1234', '학생2', 123456, 'stu02@naver.com', 0, 0);
insert into MEMBER values ('stu03', '1234', '학생3', 123456, 'stu03@naver.com', 0, 0);

insert into MEMBER values ('tea00', '1234', '강사1', 123456, 'tea00@naver.com', 1, 0);
insert into MEMBER values ('tea01', '1234', '강사2', 123456, 'tea01@naver.com', 1, 0);

insert into MEMBER values ('admin', '1234', '관리자', 123456, 'admin@naver.com', 5, 0);


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



INSERT INTO ATTEND values(
	'test123', TO_DATE('2018/05/29', 'yyyy/mm/dd'), '111', TO_DATE('8:30:25', 'HH24:MI:SS'), 
	TO_DATE('18:30:25', 'HH24:MI:SS'), '출석', 18001  
);
	
	


SELECT NAME FROM MEMBER WHERE MEM_TYPE = 1 ORDER BY NAME DESC

SELECT * from LEC_INFO;
SELECT COUNT(*) AS TOT FROM LEC_INFO;

SELECT LEC_CODE, CLASS_NAME, TEACHER, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, CLASSNO
FROM LEC_INFO WHERE LEC_CODE= 18001;



SELECT RN, CLASS_NAME, OPEN_DATE, END_DATE, KEYWORD 
	FROM ( SELECT ROWNUM as rn, CLASS_NAME, OPEN_DATE, END_DATE, KEYWORD 
		FROM( SELECT CLASS_NAME, OPEN_DATE, END_DATE, KEYWORD FROM LEC_INFO ORDER BY LEC_CODE DESC) 
	WHERE rownum <= 5
) WHERE RN >= 2