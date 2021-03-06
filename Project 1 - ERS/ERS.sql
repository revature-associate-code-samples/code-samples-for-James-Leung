
CREATE TABLE ERS_REIMBURSEMENT (

    REIMB_ID NUMBER(10) PRIMARY KEY,
    REIMB_AMOUNT NUMBER(10) NOT NULL,
    REIMB_SUBMITTED TIMESTAMP(9),
    REIMB_RESOLVED TIMESTAMP(9),
    REIMB_DESCRIPTION VARCHAR2(250) NOT NULL,
    REIMB_AUTHOR NUMBER(10),
    REIMB_RESOLVER NUMBER(10),
    REIMB_STATUS_ID NUMBER(10) NOT NULL,
    REIMB_TYPE_ID NUMBER(10) NOT NULL,
    
    FOREIGN KEY (REIMB_AUTHOR) REFERENCES ERS_USERS(ERS_USERS_ID),
    FOREIGN KEY (REIMB_RESOLVER) REFERENCES ERS_USERS(ERS_USERS_ID),
    FOREIGN KEY (REIMB_STATUS_ID) REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID),
    FOREIGN KEY (REIMB_TYPE_ID) REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID)
    
    
);

CREATE TABLE ERS_USERS (

    ERS_USERS_ID NUMBER(10) PRIMARY KEY,
    ERS_USERNAME VARCHAR2(50) UNIQUE NOT NULL,
    ERS_PASSWORD VARCHAR2(50) NOT NULL,
    USER_FIRST_NAME VARCHAR2(100) NOT NULL,
    USER_LAST_NAME VARCHAR2(100) NOT NULL,
    USER_EMAIL VARCHAR2(150) UNIQUE NOT NULL,
    USER_ROLE_ID NUMBER(10) NOT NULL,
    
    FOREIGN KEY (USER_ROLE_ID) REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID)

);

CREATE TABLE ERS_REIMBURSEMENT_STATUS (

    REIMB_STATUS_ID NUMBER(10) PRIMARY KEY,
    REIMB_STATUS VARCHAR2(10) NOT NULL

);


CREATE TABLE ERS_REIMBURSEMENT_TYPE (

    REIMB_TYPE_ID NUMBER(10) PRIMARY KEY,
    REIMB_TYPE VARCHAR2(10) NOT NULL

);


CREATE TABLE ERS_USER_ROLES (

    ERS_USER_ROLE_ID NUMBER(10) PRIMARY KEY,
    USER_ROLE VARCHAR2(10) NOT NULL

);

CREATE SEQUENCE ERS_REIMBURSEMENT_SEQ;
 
CREATE SEQUENCE ERS_USERS_SEQ;
 
CREATE SEQUENCE ERS_REIMBURSEMENT_STATUS_SEQ;

CREATE SEQUENCE ERS_REIMBURSEMENT_TYPE_SEQ;

CREATE SEQUENCE ERS_USER_ROLES_SEQ;

CREATE OR REPLACE PROCEDURE GET_ALL_ERS_REIMBURSEMENT
(ERS_REIM_CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
OPEN
    ERS_REIM_CURSOR FOR SELECT * FROM ERS_REIMBURSEMENT ORDER BY REIMB_ID;
END;
/

CREATE OR REPLACE PROCEDURE GET_ALL_ERS_USERS
(USER_CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
OPEN
    USER_CURSOR FOR SELECT * FROM ERS_USERS;
END;
/

CREATE OR REPLACE PROCEDURE GET_ALL_ERS_STATUS
(STATUS_CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
OPEN
    STATUS_CURSOR FOR SELECT * FROM ERS_REIMBURSEMENT_STATUS;
END;
/

CREATE OR REPLACE PROCEDURE GET_ALL_ERS_TYPE
(TYPE_CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
OPEN
    TYPE_CURSOR FOR SELECT * FROM ERS_REIMBURSEMENT_TYPE;
END;
/

CREATE OR REPLACE PROCEDURE GET_ALL_ERS_ROLES
(ROLE_CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
OPEN
    ROLE_CURSOR FOR SELECT * FROM ERS_USER_ROLES;
END;
/

CREATE OR REPLACE TRIGGER ERS_REIMBURSEMENT_TRIG
BEFORE INSERT ON ERS_REIMBURSEMENT
FOR EACH ROW 
BEGIN
    SELECT ERS_REIMBURSEMENT_SEQ.NEXTVAL INTO: NEW.REIMB_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER ERS_USERS_TRIG
BEFORE INSERT ON ERS_USERS
FOR EACH ROW 
BEGIN
    SELECT ERS_USERS_SEQ.NEXTVAL INTO: NEW.ERS_USERS_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER ERS_REIMBURSEMENT_STATUS_TRIG
BEFORE INSERT ON ERS_REIMBURSEMENT_STATUS
FOR EACH ROW 
BEGIN
    SELECT ERS_REIMBURSEMENT_STATUS_SEQ.NEXTVAL INTO: NEW.REIMB_STATUS_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER ERS_REIMBURSEMENT_TYPE_TRIG
BEFORE INSERT ON ERS_REIMBURSEMENT_TYPE
FOR EACH ROW 
BEGIN
    SELECT ERS_REIMBURSEMENT_TYPE_SEQ.NEXTVAL INTO: NEW.REIMB_TYPE_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER ERS_USER_ROLES_TRIG
BEFORE INSERT ON ERS_USER_ROLES
FOR EACH ROW 
BEGIN
    SELECT ERS_USER_ROLES_SEQ.NEXTVAL INTO: NEW.ERS_USER_ROLE_ID FROM DUAL;
END;
/


commit;