
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_acct (
    acct_no          BIGINT NOT NULL COMMENT 'Account Number ',
    acct_name        VARCHAR(30) NOT NULL COMMENT 'Account Name',
    astreet          VARCHAR(30) NOT NULL COMMENT 'Account Street Address',
    acity            VARCHAR(30) NOT NULL COMMENT 'Account City Address',
    astate           VARCHAR(2) NOT NULL COMMENT 'Account State Address',
    azipcode         INT NOT NULL COMMENT 'Account Zipcode',
    date_opened      DATETIME NOT NULL COMMENT 'Account Opening Date',
    astatus          VARCHAR(1) NOT NULL COMMENT 'Account Status',
    acct_type        VARCHAR(8) NOT NULL COMMENT 'Account Type',
    cust_id BIGINT
);

ALTER TABLE ais_acct
    ADD CONSTRAINT ch_inh_ais_acct CHECK ( acct_type IN ( 'AIS_ACCT', 'C', 'L', 'S' ) );

ALTER TABLE ais_acct
    ADD CONSTRAINT chk_astatus CHECK (astatus IN ('A', 'I', 'C'));
    
ALTER TABLE ais_acct
ADD CONSTRAINT unique_cust_acct_type UNIQUE (cust_id, acct_type);


ALTER TABLE ais_acct ADD CONSTRAINT ais_acct_pk PRIMARY KEY ( acct_no );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_checking (
    acct_no        BIGINT NOT NULL COMMENT 'Account Number ',
    service_charge DECIMAL(3, 2) NOT NULL COMMENT 'Checking Service Charge'
);


ALTER TABLE ais_checking ADD CONSTRAINT ais_checking_pk PRIMARY KEY ( acct_no );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_cust (
    cust_id  BIGINT NOT NULL COMMENT 'Customer ID',
    cfname   VARCHAR(30) NOT NULL COMMENT 'Customer First Name',
    clname   VARCHAR(30) NOT NULL COMMENT 'Customer Last Name',
    cstreet  VARCHAR(30) NOT NULL COMMENT 'Customer Street Address',
    ccity    VARCHAR(30) NOT NULL COMMENT 'Customer City Address',
    cstate   VARCHAR(2) NOT NULL COMMENT 'Customer State Address',
    czipcode INT NOT NULL COMMENT 'Customer Zipcode'
);


ALTER TABLE ais_cust ADD CONSTRAINT ais_cust_pk PRIMARY KEY ( cust_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_edu (
    eid   INT NOT NULL COMMENT 'Educational Institution ID',
    ename VARCHAR(30) NOT NULL COMMENT 'Educational Institution Name'
);


ALTER TABLE ais_edu ADD CONSTRAINT ais_edu_pk PRIMARY KEY ( eid );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_home_insr (
    hid      INT NOT NULL COMMENT 'Home Insurance ID',
    hname    VARCHAR(30) NOT NULL COMMENT 'Home Insurance Name',
    hstreet  VARCHAR(30) NOT NULL COMMENT 'Home Insurance Street Address',
    hcity    VARCHAR(30) NOT NULL COMMENT 'Home Insurance City Address',
    hstate   VARCHAR(2) NOT NULL COMMENT 'Home Insurance State Address',
    hzipcode INT NOT NULL COMMENT 'Home Insurance Zipcode'
);


ALTER TABLE ais_home_insr ADD CONSTRAINT ais_home_insr_pk PRIMARY KEY ( hid );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_home_loan (
    acct_no           BIGINT NOT NULL COMMENT 'Account Number ',
    hacct_no          INT NOT NULL COMMENT 'Home Insurance Account Number',
    hbuilt_year       SMALLINT NOT NULL COMMENT 'House Built Year',
    yr_premium        DECIMAL(4, 2) NOT NULL COMMENT 'Yearly Premium',
    hid INT
);


ALTER TABLE ais_home_loan ADD CONSTRAINT ais_home_loan_pk PRIMARY KEY ( acct_no );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_loan (
    acct_no  BIGINT NOT NULL COMMENT 'Account Number ',
    lrate    DECIMAL(4, 2) NOT NULL COMMENT 'Loan Rate',
    lamount  DECIMAL(8, 2) NOT NULL COMMENT 'Loan Amount',
    lpayment DECIMAL(8, 2) NOT NULL COMMENT 'Loan Payment',
    lmonths  SMALLINT NOT NULL COMMENT 'Loan Months',
    ltype    VARCHAR(1) NOT NULL COMMENT 'Loan Type'
);

ALTER TABLE ais_loan
    ADD CONSTRAINT ch_inh_ais_loan CHECK ( ltype IN ( 'H', 'P', 'S' ) );



ALTER TABLE ais_loan ADD CONSTRAINT ais_loan_pk PRIMARY KEY ( acct_no );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_per_loan (
    acct_no     BIGINT NOT NULL COMMENT 'Account Number ',
    early_repay DECIMAL(5, 2) COMMENT 'Loan Early Repayment Fee'
);



ALTER TABLE ais_per_loan ADD CONSTRAINT ais_per_loan_pk PRIMARY KEY ( acct_no );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_savings (
    acct_no       BIGINT NOT NULL COMMENT 'Account Number ',
    interest_rate DECIMAL(4, 2) NOT NULL COMMENT 'Savings Interest Rate'
);


ALTER TABLE ais_savings ADD CONSTRAINT ais_savings_pk PRIMARY KEY ( acct_no );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ais_stud_loan (
    acct_no       BIGINT NOT NULL COMMENT 'Account Number ',
    sid           INT NOT NULL COMMENT 'Student ID',
    degree        VARCHAR(1) NOT NULL COMMENT 'Student Degree Level',
    expgrad_month TINYINT NOT NULL COMMENT 'Expected Graduation Month',
    expgrad_year  INT NOT NULL COMMENT 'Expected Graduation Year',
    eid           INT
);

-- Ensure service charge in AIS_CHECKING is non-negative
ALTER TABLE AIS_CHECKING 
ADD CONSTRAINT C_SERVICE_CHARGE CHECK (SERVICE_CHARGE >= 0);

-- Ensure interest rate in AIS_SAVINGS is non-negative
ALTER TABLE AIS_SAVINGS 
ADD CONSTRAINT C_INTEREST_RATE CHECK (INTEREST_RATE >= 0);

-- Ensure loan rate in AIS_LOAN is non-negative
ALTER TABLE AIS_LOAN 
ADD CONSTRAINT C_LOAN_RATE CHECK (LRATE >= 0);

-- Ensure loan amount in AIS_LOAN is non-negative
ALTER TABLE AIS_LOAN 
ADD CONSTRAINT C_LOAN_AMOUNT CHECK (LAMOUNT >= 0);

-- Ensure loan months in AIS_LOAN is non-negative
ALTER TABLE AIS_LOAN 
ADD CONSTRAINT C_LOAN_MONTHS CHECK (LMONTHS >= 0);

-- Ensure loan payment in AIS_LOAN is non-negative
ALTER TABLE AIS_LOAN 
ADD CONSTRAINT C_LOAN_PAYMENT CHECK (LPAYMENT >= 0);

-- Ensure valid degree values ('U' for Undergraduate, 'G' for Graduate) in AIS_STUD_LOAN
ALTER TABLE AIS_STUD_LOAN 
ADD CONSTRAINT C_STUD_DEGREE CHECK (DEGREE IN ('U', 'G'));

-- Ensure expected graduation month in AIS_STUD_LOAN is between 1 and 12
ALTER TABLE AIS_STUD_LOAN 
ADD CONSTRAINT C_EXP_GRAD_MON CHECK (EXPGRAD_MONTH BETWEEN 1 AND 12);

-- Ensure yearly premium in AIS_HOME_LOAN is non-negative
ALTER TABLE AIS_HOME_LOAN 
ADD CONSTRAINT C_YR_PREMIUM CHECK (YR_PREMIUM >= 0);


ALTER TABLE ais_acct MODIFY COLUMN acct_no BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE ais_stud_loan ADD CONSTRAINT ais_stud_loan_pk PRIMARY KEY ( acct_no );

ALTER TABLE ais_acct
    ADD CONSTRAINT ais_acct_ais_cust_fk FOREIGN KEY ( cust_id )
        REFERENCES ais_cust ( cust_id );

ALTER TABLE ais_checking
    ADD CONSTRAINT ais_checking_ais_acct_fk FOREIGN KEY ( acct_no )
        REFERENCES ais_acct ( acct_no );

ALTER TABLE ais_home_loan
    ADD CONSTRAINT ais_home_loan_ais_home_insr_fk FOREIGN KEY ( hid )
        REFERENCES ais_home_insr ( hid );

ALTER TABLE ais_home_loan
    ADD CONSTRAINT ais_home_loan_ais_loan_fk FOREIGN KEY ( acct_no )
        REFERENCES ais_loan ( acct_no );

ALTER TABLE ais_loan
    ADD CONSTRAINT ais_loan_ais_acct_fk FOREIGN KEY ( acct_no )
        REFERENCES ais_acct ( acct_no );

ALTER TABLE ais_per_loan
    ADD CONSTRAINT ais_per_loan_ais_loan_fk FOREIGN KEY ( acct_no )
        REFERENCES ais_loan ( acct_no );

ALTER TABLE ais_savings
    ADD CONSTRAINT ais_savings_ais_acct_fk FOREIGN KEY ( acct_no )
        REFERENCES ais_acct ( acct_no );

ALTER TABLE ais_stud_loan
    ADD CONSTRAINT ais_stud_loan_ais_edu_fk FOREIGN KEY ( eid )
        REFERENCES ais_edu ( eid );

ALTER TABLE ais_stud_loan
    ADD CONSTRAINT ais_stud_loan_ais_loan_fk FOREIGN KEY ( acct_no )
        REFERENCES ais_loan ( acct_no );




-- SQLINES LICENSE FOR EVALUATION USE ONLY
-- Drop existing triggers if they exist
DROP TRIGGER IF EXISTS arc_fkarc_1_ais_per_loan_ins;
DROP TRIGGER IF EXISTS arc_fkarc_1_ais_per_loan_upd;

DELIMITER //

-- Trigger for BEFORE INSERT on ais_per_loan
CREATE TRIGGER arc_fkarc_1_ais_per_loan_ins BEFORE INSERT ON ais_per_loan
FOR EACH ROW
BEGIN
    DECLARE d CHAR(1);
    SELECT a.ltype INTO d FROM ais_loan a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'P' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_PER_LOAN_AIS_LOAN_FK: Expected LTYPE ''P''';
    END IF;
END;

-- Trigger for BEFORE UPDATE on ais_per_loan
CREATE TRIGGER arc_fkarc_1_ais_per_loan_upd BEFORE UPDATE ON ais_per_loan
FOR EACH ROW
BEGIN
    DECLARE d CHAR(1);
    SELECT a.ltype INTO d FROM ais_loan a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'P' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_PER_LOAN_AIS_LOAN_FK: Expected LTYPE ''P''';
    END IF;
END;

DELIMITER ;




-- SQLINES LICENSE FOR EVALUATION USE ONLY
-- Drop existing triggers if they exist
DROP TRIGGER IF EXISTS arc_fkarc_1_ais_stud_loan_ins;
DROP TRIGGER IF EXISTS arc_fkarc_1_ais_stud_loan_upd;

DELIMITER //

-- Trigger for BEFORE INSERT on ais_stud_loan
CREATE TRIGGER arc_fkarc_1_ais_stud_loan_ins BEFORE INSERT ON ais_stud_loan
FOR EACH ROW
BEGIN
    DECLARE d CHAR(1);
    SELECT a.ltype INTO d FROM ais_loan a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'S' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_STUD_LOAN_AIS_LOAN_FK: Expected LTYPE ''S''';
    END IF;
END;

-- Trigger for BEFORE UPDATE on ais_stud_loan
CREATE TRIGGER arc_fkarc_1_ais_stud_loan_upd BEFORE UPDATE ON ais_stud_loan
FOR EACH ROW
BEGIN
    DECLARE d CHAR(1);
    SELECT a.ltype INTO d FROM ais_loan a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'S' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_STUD_LOAN_AIS_LOAN_FK: Expected LTYPE ''S''';
    END IF;
END;

DELIMITER ;




-- SQLINES LICENSE FOR EVALUATION USE ONLY
-- Drop existing triggers if they exist
DROP TRIGGER IF EXISTS arc_fkarc_1_ais_home_loan_ins;
DROP TRIGGER IF EXISTS arc_fkarc_1_ais_home_loan_upd;

DELIMITER //

-- Trigger for BEFORE INSERT on ais_home_loan
CREATE TRIGGER arc_fkarc_1_ais_home_loan_ins BEFORE INSERT ON ais_home_loan
FOR EACH ROW
BEGIN
    DECLARE d CHAR(1);
    SELECT a.ltype INTO d FROM ais_loan a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'H' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_HOME_LOAN_AIS_LOAN_FK: Expected LTYPE ''H''';
    END IF;
END;

-- Trigger for BEFORE UPDATE on ais_home_loan
CREATE TRIGGER arc_fkarc_1_ais_home_loan_upd BEFORE UPDATE ON ais_home_loan
FOR EACH ROW
BEGIN
    DECLARE d CHAR(1);
    SELECT a.ltype INTO d FROM ais_loan a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'H' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_HOME_LOAN_AIS_LOAN_FK: Expected LTYPE ''H''';
    END IF;
END;

DELIMITER ;




-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS arc_fkarc_2_ais_savings_ins;
DROP TRIGGER IF EXISTS arc_fkarc_2_ais_savings_upd;

DELIMITER //

CREATE TRIGGER arc_fkarc_2_ais_savings_ins BEFORE INSERT ON ais_savings
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(8);
    SELECT a.acct_type INTO d FROM ais_acct a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'S' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_SAVINGS_AIS_ACCT_FK: Account Type must be ''S''.';
    END IF;
END;

CREATE TRIGGER arc_fkarc_2_ais_savings_upd BEFORE UPDATE ON ais_savings
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(8);
    SELECT a.acct_type INTO d FROM ais_acct a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'S' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_SAVINGS_AIS_ACCT_FK: Account Type must be ''S''.';
    END IF;
END;

DELIMITER ;



-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS arc_fkarc_2_ais_checking_ins;
DROP TRIGGER IF EXISTS arc_fkarc_2_ais_checking_upd;

DELIMITER //

CREATE TRIGGER arc_fkarc_2_ais_checking_ins BEFORE INSERT ON ais_checking
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(8);
    SELECT a.acct_type INTO d FROM ais_acct a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'C' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_CHECKING_AIS_ACCT_FK: Account Type must be ''C''.';
    END IF;
END;

CREATE TRIGGER arc_fkarc_2_ais_checking_upd BEFORE UPDATE ON ais_checking
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(8);
    SELECT a.acct_type INTO d FROM ais_acct a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'C' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_CHECKING_AIS_ACCT_FK: Account Type must be ''C''.';
    END IF;
END;

DELIMITER ;




-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS arc_fkarc_2_ais_loan_ins;
DROP TRIGGER IF EXISTS arc_fkarc_2_ais_loan_upd;

DELIMITER //

CREATE TRIGGER arc_fkarc_2_ais_loan_ins BEFORE INSERT ON ais_loan
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(8);
    SELECT a.acct_type INTO d FROM ais_acct a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'L' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_LOAN_AIS_ACCT_FK: Account Type must be ''L''.';
    END IF;
END;

CREATE TRIGGER arc_fkarc_2_ais_loan_upd BEFORE UPDATE ON ais_loan
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(8);
    SELECT a.acct_type INTO d FROM ais_acct a WHERE a.acct_no = NEW.acct_no;
    IF d IS NULL OR d <> 'L' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Violation of FK AIS_LOAN_AIS_ACCT_FK: Account Type must be ''L''.';
    END IF;
END;

DELIMITER ;


-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_cust (cust_id, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (1, 'John', 'Doe', '123 Maple St', 'Springfield', 'IL', 62704);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_cust (cust_id, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (2, 'Jane', 'Smith', '456 Oak St', 'Riverside', 'CA', 92507);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_cust (cust_id, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (3, 'Alice', 'Johnson', '789 Pine St', 'Liberty', 'NY', 12754);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_cust (cust_id, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (4, 'Bob', 'Brown', '101 Ash Ave', 'Centerville', 'TX', 75833);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_cust (cust_id, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (5, 'Carol', 'White', '202 Birch Rd', 'Smalltown', 'FL', 32123);
COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, cust_id)
VALUES (1001, 'John Checking', '123 Maple St', 'Springfield', 'IL', 62704, STR_TO_DATE('2021-01-10', '%Y-%m-%d'), 'A', 'C', 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, cust_id)
VALUES (2001, 'Jane Savings', '456 Oak St', 'Riverside', 'CA', 92507, STR_TO_DATE('2022-05-15', '%Y-%m-%d'), 'A', 'S', 2);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, cust_id)
VALUES (3001, 'Alice Loan', '789 Pine St', 'Liberty', 'NY', 12754, STR_TO_DATE('2023-03-20', '%Y-%m-%d'), 'A', 'L', 3);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, cust_id)
VALUES (4001, 'Bob Home Loan', '101 Ash Ave', 'Centerville', 'TX', 75833, STR_TO_DATE('2021-07-05', '%Y-%m-%d'), 'A', 'L', 4);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, cust_id)
VALUES (5001, 'Carol Student Loan', '202 Birch Rd', 'Smalltown', 'FL', 32123, STR_TO_DATE('2022-08-12', '%Y-%m-%d'), 'A', 'L', 5);

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (1001, 5.00);

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (2001, 1.50);

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (3001, 3.75, 50000, 1000, 60, 'P');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (4001, 4.00, 150000, 1500, 120, 'H');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (5001, 5.00, 20000, 400, 48, 'S');

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_stud_loan (acct_no, sid, degree, expgrad_month, expgrad_year, eid)
VALUES (5001, 102, 'G', 5, 2024, 101);

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (4001, 5002, 2010, 1200.00, 501);

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode)
VALUES (501, 'Great Cover Insurance', '201 Insure Ln', 'Shieldtown', 'TX', 75839);

COMMIT;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO ais_edu (eid, ename)
VALUES (101, 'State University');

COMMIT;
