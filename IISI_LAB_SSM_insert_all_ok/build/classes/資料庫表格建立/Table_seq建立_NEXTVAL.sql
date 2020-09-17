
                                          --�إߪ��: EMP2 �M DEPT2--
----------------------------------------------------------------------------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP TABLE EMP2;
DROP TABLE DEPT2;
DROP SEQUENCE emp2_seq;
DROP SEQUENCE dept2_seq;


CREATE TABLE DEPT2 (
 DEPTNO             NUMBER(2) NOT NULL,
 DNAME              VARCHAR2(14),
 LOC                VARCHAR2(13),
 CONSTRAINT DEPT2_PRIMARY_KEY PRIMARY KEY (DEPTNO));
 
CREATE SEQUENCE dept2_seq
INCREMENT BY 10
START WITH 10
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO DEPT2 VALUES (dept2_seq.NEXTVAL,'�]�ȳ�','�O�W�x�_');
INSERT INTO DEPT2 VALUES (dept2_seq.NEXTVAL,'��o��','�O�W�s��');
INSERT INTO DEPT2 VALUES (dept2_seq.NEXTVAL,'�~�ȳ�','����ì�');
INSERT INTO DEPT2 VALUES (dept2_seq.NEXTVAL,'�ͺ޳�','����W��');



CREATE TABLE EMP2 (
 EMPNO               NUMBER(4) NOT NULL,
 ENAME               VARCHAR2(10),
 JOB                 VARCHAR2(9),
 HIREDATE            DATE,
 SAL                 NUMBER(7,2),
 COMM                NUMBER(7,2),
 DEPTNO              NUMBER(2) NOT NULL,
 CONSTRAINT EMP2_DEPTNO_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT2 (DEPTNO),
 CONSTRAINT EMP2_EMPNO_PK PRIMARY KEY (EMPNO));
 
CREATE SEQUENCE emp2_seq
INCREMENT BY 1
START WITH 7001
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'KING'   , 'PRESIDENT', TO_DATE('1981-11-17','YYYY-MM-DD') , 5000.5 , 0.0  ,10);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'BLAKE'  , 'MANAGER'  , TO_DATE('1981-05-01','YYYY-MM-DD') , 2850   , 0.0  ,30);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'CLARK'  , 'MANAGER'  , TO_DATE('1981-01-09','YYYY-MM-DD') , 2450   , 0.0  ,10);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'JONES'  , 'MANAGER'  , TO_DATE('1981-04-02','YYYY-MM-DD') , 2975   , 0.0  ,20);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'MARTIN' , 'SALESMAN' , TO_DATE('1981-09-28','YYYY-MM-DD') , 1250   , 1400 ,30);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'ALLEN'  , 'SALESMAN' , TO_DATE('1981-02-02','YYYY-MM-DD') , 1600   , 300  ,30);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'TURNER' , 'SALESMAN' , TO_DATE('1981-09-28','YYYY-MM-DD') , 1500   , 0.0  ,30);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'JAMES'  , 'CLERK'    , TO_DATE('1981-12-03','YYYY-MM-DD') , 950    , 0.0  ,30);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'WARD'   , 'SALESMAN' , TO_DATE('1981-02-22','YYYY-MM-DD') , 1250   , 500  ,30);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'FORD'   , 'ANALYST'  , TO_DATE('1981-12-03','YYYY-MM-DD') , 3000   , 0.0  ,20);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'SMITH'  , 'CLERK'    , TO_DATE('1980-12-17','YYYY-MM-DD') , 800    , 0.0  ,20);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'SCOTT'  , 'ANALYST'  , TO_DATE('1981-12-09','YYYY-MM-DD') , 3000   , 0.0  ,20);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'ADAMS'  , 'CLERK'    , TO_DATE('1983-01-12','YYYY-MM-DD') , 1100   , 0.0  ,20);
INSERT INTO EMP2 VALUES (emp2_seq.NEXTVAL , 'MILLER' , 'CLERK'    , TO_DATE('1982-01-23','YYYY-MM-DD') , 1300   , 0.0  ,10);

commit;
----------------------------------------------------------------------------------------------------------------------------