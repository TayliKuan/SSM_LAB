
alter session set deferred_segment_creation=false;

DROP TABLE USERS;
DROP SEQUENCE USERS_seq;
DROP TABLE DEPT;
DROP SEQUENCE dept_seq;
CREATE TABLE DEPT (
 DEPTNO             VARCHAR2(4) NOT NULL,
 DNAME              VARCHAR2(14),
 LOC                VARCHAR2(13),
 CONSTRAINT DEPT_PRIMARY_KEY PRIMARY KEY (DEPTNO));
 
CREATE SEQUENCE dept_seq
INCREMENT BY 10
START WITH 10
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO DEPT VALUES (dept_seq.NEXTVAL,'財務部','臺灣台北');
INSERT INTO DEPT VALUES (dept_seq.NEXTVAL,'研發部','臺灣新竹');
INSERT INTO DEPT VALUES (dept_seq.NEXTVAL,'業務部','美國紐約');


CREATE TABLE USERS(
 UNO                 VARCHAR2(4) NOT NULL,
 USERNAME            VARCHAR2(30) NOT NULL,
 USERID                 VARCHAR2(10) NOT NULL,
 SEX                 VARCHAR2(3) NOT NULL,
 ADDRESS             VARCHAR2(100) NOT NULL,
 PHONE               VARCHAR2(10) NOT NULL,
 BIRTHDAY            Date NOT NULL,
 JOINDATE            Date NOT NULL,
 DEPTNO               VARCHAR2(4) NOT NULL,
 CONSTRAINT DEPT_DEPTNO_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT (DEPTNO),
 CONSTRAINT USER_UNO_PK PRIMARY KEY (UNO));
 
CREATE SEQUENCE USERS_seq
INCREMENT BY 1
START WITH 1001
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'KING'   , 'S123456781', '男' , '台北市' , '0910123456' ,TO_DATE('1981-11-17','YYYY-MM-DD') , TO_DATE('2020-08-17','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'BLAKE'  , 'S123456782', '男' , '台北市' , '0910123456' ,TO_DATE('1981-05-01','YYYY-MM-DD') , TO_DATE('2020-05-01','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'CLARK'  , 'S123456783', '男' , '台北市' , '0910123456' ,TO_DATE('1981-01-09','YYYY-MM-DD') , TO_DATE('2020-01-09','YYYY-MM-DD'),20);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'JONES'  , 'S123456784', '男' , '台北市' , '0910123456' ,TO_DATE('1981-04-02','YYYY-MM-DD') , TO_DATE('2020-04-02','YYYY-MM-DD'),30);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'MARTIN' , 'S123456785', '男' , '台北市' , '0910123456' ,TO_DATE('1981-09-28','YYYY-MM-DD') , TO_DATE('2020-09-28','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'ALLEN'  , 'S123456786', '男' , '台北市' , '0910123456' ,TO_DATE('1981-02-02','YYYY-MM-DD') , TO_DATE('2020-02-02','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'TURNER' , 'S123456787', '男' , '台北市' , '0910123456' ,TO_DATE('1981-09-28','YYYY-MM-DD') , TO_DATE('2020-09-28','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'JAMES'  , 'S123456788', '女' , '台北市' , '0910123456' ,TO_DATE('1981-12-03','YYYY-MM-DD') , TO_DATE('2020-08-03','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'WARD'   , 'S123456789', '男' , '台北市' , '0910123456' ,TO_DATE('1981-02-22','YYYY-MM-DD') , TO_DATE('2020-02-22','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'FORD'   , 'S123456790', '男' , '台北市' , '0910123456' ,TO_DATE('1981-12-03','YYYY-MM-DD') , TO_DATE('2020-08-03','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'SMITH'  , 'S123456791', '女' , '台北市' , '0910123456' ,TO_DATE('1980-12-17','YYYY-MM-DD') , TO_DATE('2020-08-17','YYYY-MM-DD'),20);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'SCOTT'  , 'S123456792', '男' , '台北市' , '0910123456' ,TO_DATE('1981-12-09','YYYY-MM-DD') , TO_DATE('2020-08-09','YYYY-MM-DD'),10);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'ADAMS'  , 'S123456793', '女' , '台北市' , '0910123456' ,TO_DATE('1983-01-12','YYYY-MM-DD') , TO_DATE('2020-01-12','YYYY-MM-DD'),30);
INSERT INTO USERS VALUES (USERS_seq.NEXTVAL , 'MILLER' , 'S123456794', '男' , '台北市' , '0910123456' ,TO_DATE('1982-01-23','YYYY-MM-DD') , TO_DATE('2020-01-23','YYYY-MM-DD'),10);

commit;