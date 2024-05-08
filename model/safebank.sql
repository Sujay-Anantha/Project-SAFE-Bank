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
    custid BIGINT
);

ALTER TABLE ais_acct
    ADD CONSTRAINT ch_inh_ais_acct CHECK ( acct_type IN ( 'AIS_ACCT', 'C', 'L', 'S' ) );

ALTER TABLE ais_acct
    ADD CONSTRAINT chk_astatus CHECK (astatus IN ('A', 'I', 'C'));
    
ALTER TABLE ais_acct
ADD CONSTRAINT unique_cust_acct_type UNIQUE (custid, acct_type);


ALTER TABLE ais_acct ADD CONSTRAINT ais_acct_pk PRIMARY KEY ( acct_no );

CREATE TABLE ais_checking (
    acct_no        BIGINT NOT NULL COMMENT 'Account Number ',
    service_charge DECIMAL(4, 2) NOT NULL COMMENT 'Checking Service Charge'
);


ALTER TABLE ais_checking ADD CONSTRAINT ais_checking_pk PRIMARY KEY ( acct_no );

CREATE TABLE ais_cust (
    custid  BIGINT NOT NULL COMMENT 'Customer ID',
    cfname   VARCHAR(30) NOT NULL COMMENT 'Customer First Name',
    clname   VARCHAR(30) NOT NULL COMMENT 'Customer Last Name',
    cstreet  VARCHAR(30) NOT NULL COMMENT 'Customer Street Address',
    ccity    VARCHAR(30) NOT NULL COMMENT 'Customer City Address',
    cstate   VARCHAR(2) NOT NULL COMMENT 'Customer State Address',
    czipcode INT NOT NULL COMMENT 'Customer Zipcode'
);


ALTER TABLE ais_cust ADD CONSTRAINT ais_cust_pk PRIMARY KEY ( custid );

CREATE TABLE ais_edu (
    eid   INT NOT NULL COMMENT 'Educational Institution ID',
    ename VARCHAR(30) NOT NULL COMMENT 'Educational Institution Name'
);


ALTER TABLE ais_edu ADD CONSTRAINT ais_edu_pk PRIMARY KEY ( eid );

CREATE TABLE ais_home_insr (
    hid      INT NOT NULL COMMENT 'Home Insurance ID',
    hname    VARCHAR(30) NOT NULL COMMENT 'Home Insurance Name',
    hstreet  VARCHAR(30) NOT NULL COMMENT 'Home Insurance Street Address',
    hcity    VARCHAR(30) NOT NULL COMMENT 'Home Insurance City Address',
    hstate   VARCHAR(2) NOT NULL COMMENT 'Home Insurance State Address',
    hzipcode INT NOT NULL COMMENT 'Home Insurance Zipcode'
);


ALTER TABLE ais_home_insr ADD CONSTRAINT ais_home_insr_pk PRIMARY KEY ( hid );

CREATE TABLE ais_home_loan (
    acct_no           BIGINT NOT NULL COMMENT 'Account Number ',
    hacct_no          INT NOT NULL COMMENT 'Home Insurance Account Number',
    hbuilt_year       SMALLINT NOT NULL COMMENT 'House Built Year',
    yr_premium        DECIMAL(6, 2) NOT NULL COMMENT 'Yearly Premium',
    hid INT
);


ALTER TABLE ais_home_loan ADD CONSTRAINT ais_home_loan_pk PRIMARY KEY ( acct_no );

CREATE TABLE ais_loan (
    acct_no  BIGINT NOT NULL COMMENT 'Account Number ',
    lrate    DECIMAL(4, 2) NOT NULL COMMENT 'Loan Rate',
    lamount  DECIMAL(8, 2) NOT NULL COMMENT 'Loan Amount',
    lpayment DECIMAL(8, 2) NOT NULL COMMENT 'Loan Payment',
    lmonths  SMALLINT NOT NULL COMMENT 'Loan Months',
    ltype    VARCHAR(2) NOT NULL COMMENT 'Loan Type'
);

ALTER TABLE ais_loan
    ADD CONSTRAINT ch_inh_ais_loan CHECK ( ltype IN ( 'HL', 'PL', 'SL' ) );



ALTER TABLE ais_loan ADD CONSTRAINT ais_loan_pk PRIMARY KEY ( acct_no );

CREATE TABLE ais_per_loan (
    acct_no     BIGINT NOT NULL COMMENT 'Account Number ',
    early_repay DECIMAL(6, 2) COMMENT 'Loan Early Repayment Fee'
);



ALTER TABLE ais_per_loan ADD CONSTRAINT ais_per_loan_pk PRIMARY KEY ( acct_no );

CREATE TABLE ais_savings (
    acct_no       BIGINT NOT NULL COMMENT 'Account Number ',
    interest_rate DECIMAL(4, 2) NOT NULL COMMENT 'Savings Interest Rate'
);


ALTER TABLE ais_savings ADD CONSTRAINT ais_savings_pk PRIMARY KEY ( acct_no );

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
ALTER TABLE ais_cust MODIFY COLUMN custid BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE ais_edu MODIFY COLUMN eid INT NOT NULL AUTO_INCREMENT;
ALTER TABLE ais_home_insr MODIFY COLUMN hid INT NOT NULL AUTO_INCREMENT;

ALTER TABLE ais_stud_loan ADD CONSTRAINT ais_stud_loan_pk PRIMARY KEY ( acct_no );

ALTER TABLE ais_acct
    ADD CONSTRAINT ais_acct_ais_cust_fk FOREIGN KEY ( custid )
        REFERENCES ais_cust ( custid );

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

-- Creating a stored procedure to fetch all customers
DELIMITER //
CREATE PROCEDURE GetAllCustomers()
BEGIN
    SELECT * FROM ais_cust;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE GetCustomerByID(IN inCustId BIGINT)
BEGIN
    -- Use the input parameter explicitly
    SELECT * FROM ais_cust WHERE custid = inCustId;
END //
DELIMITER ;

-- Creating a stored procedure to insert a customer
DELIMITER //
CREATE PROCEDURE CreateCustomer(
    IN cfname VARCHAR(50),
    IN clname VARCHAR(50),
    IN cstreet VARCHAR(100),
    IN ccity VARCHAR(50),
    IN cstate VARCHAR(20),
    IN czipcode INT
)
BEGIN
    INSERT INTO ais_cust (cfname, clname, cstreet, ccity, cstate, czipcode)
    VALUES (cfname, clname, cstreet, ccity, cstate, czipcode);
END //
DELIMITER ;

-- Creating a stored procedure to update a customer by ID
DELIMITER //
CREATE PROCEDURE UpdateCustomer(
    IN incustId BIGINT,
    IN cfname VARCHAR(50),
    IN clname VARCHAR(50),
    IN cstreet VARCHAR(100),
    IN ccity VARCHAR(50),
    IN cstate VARCHAR(20),
    IN czipcode INT
)
BEGIN
    UPDATE ais_cust
    SET cfname = cfname, clname = clname, cstreet = cstreet, ccity = ccity, cstate = cstate, czipcode = czipcode
    WHERE custid = incustId;
END //
DELIMITER ;

-- Creating a stored procedure to delete a customer by ID
DELIMITER //
CREATE PROCEDURE DeleteCustomer(IN incustId BIGINT)
BEGIN
    -- First, delete any records in tables referencing ais_acct
    DELETE FROM ais_checking WHERE acct_no IN (SELECT acct_no FROM ais_acct WHERE custid = incustId);
    DELETE FROM ais_home_loan WHERE acct_no IN (SELECT acct_no FROM ais_acct WHERE custid = incustId);
    DELETE FROM ais_loan WHERE acct_no IN (SELECT acct_no FROM ais_acct WHERE custid = incustId);
    DELETE FROM ais_per_loan WHERE acct_no IN (SELECT acct_no FROM ais_acct WHERE custid = incustId);
    DELETE FROM ais_savings WHERE acct_no IN (SELECT acct_no FROM ais_acct WHERE custid = incustId);
    DELETE FROM ais_stud_loan WHERE acct_no IN (SELECT acct_no FROM ais_acct WHERE custid = incustId);

    -- Then, delete the customer’s accounts from ais_acct itself
    DELETE FROM ais_acct WHERE custid = incustId;

    -- Finally, delete the customer from ais_cust
    DELETE FROM ais_cust WHERE custid = incustId;
END //
DELIMITER ;

DELIMITER //

-- Stored procedure to fetch all accounts
DELIMITER //
CREATE PROCEDURE GetAllAccounts()
BEGIN
    SELECT
        a.acct_no,
        a.acct_type,
        a.acct_name,
        a.acity,
        a.custid,
        a.date_opened,
        a.astate,
        a.astatus,
        a.astreet,
        a.azipcode,
        c.service_charge,
        hl.hid,
        hl.hbuilt_year,
        hl.yr_premium,
        l.lamount,
        l.lmonths,
        l.lpayment,
        l.lrate,
        pl.early_repay,
        s.interest_rate,
        sl.degree,
        sl.eid,
        sl.expgrad_month,
        sl.expgrad_year,
        sl.sid AS student_id
    FROM ais_acct a
    LEFT JOIN ais_checking c ON a.acct_no = c.acct_no
    LEFT JOIN ais_home_loan hl ON a.acct_no = hl.acct_no
    LEFT JOIN ais_loan l ON a.acct_no = l.acct_no
    LEFT JOIN ais_per_loan pl ON a.acct_no = pl.acct_no
    LEFT JOIN ais_savings s ON a.acct_no = s.acct_no
    LEFT JOIN ais_stud_loan sl ON a.acct_no = sl.acct_no;
END //
DELIMITER ;



-- Stored procedure to fetch an account by account number
DELIMITER //
CREATE PROCEDURE GetAccountByNumber(IN inacct_no BIGINT)
BEGIN
    SELECT
        a.acct_no,
        a.acct_name,
        a.astreet,
        a.acity,
        a.astate,
        a.azipcode,
        a.date_opened,
        a.astatus,
        a.acct_type,
        a.custid,
        CASE
            WHEN a.acct_type = 'C' THEN c.service_charge
            ELSE NULL
        END AS service_charge,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'HL' THEN hl.hid
            ELSE NULL
        END AS hid,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'HL' THEN hl.hbuilt_year
            ELSE NULL
        END AS house_built_year,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'HL' THEN hl.yr_premium
            ELSE NULL
        END AS yearly_premium,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'PL' THEN pl.early_repay
            ELSE NULL
        END AS early_repayment_fee,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'SL' THEN sl.eid
            ELSE NULL
        END AS eid,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'SL' THEN sl.sid
            ELSE NULL
        END AS student_id,
        CASE
            WHEN a.acct_type = 'L' AND l.ltype = 'SL' THEN sl.degree
            ELSE NULL
        END AS degree
    FROM ais_acct a
    LEFT JOIN ais_checking c ON a.acct_no = c.acct_no AND a.acct_type = 'C'
    LEFT JOIN ais_loan l ON a.acct_no = l.acct_no AND a.acct_type = 'L'
    LEFT JOIN ais_home_loan hl ON l.acct_no = hl.acct_no AND l.ltype = 'HL'
    LEFT JOIN ais_per_loan pl ON l.acct_no = pl.acct_no AND l.ltype = 'PL'
    LEFT JOIN ais_stud_loan sl ON l.acct_no = sl.acct_no AND l.ltype = 'SL'
    WHERE a.acct_no = inacct_no;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE CreateAccount(
    IN in_acct_name VARCHAR(30),
    IN in_astreet VARCHAR(30),
    IN in_acity VARCHAR(30),
    IN in_astate VARCHAR(2),
    IN in_azipcode INT,
    IN in_date_opened DATETIME,
    IN in_astatus CHAR(1),
    IN in_acct_type VARCHAR(8),
    IN in_custid BIGINT
)
BEGIN
    INSERT INTO ais_acct (
        acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid
    )
    VALUES (in_acct_name, in_astreet, in_acity, in_astate, in_azipcode, in_date_opened, in_astatus, in_acct_type, in_custid);
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE UpdateAccount(
    IN in_acct_no BIGINT,
    IN in_acct_name VARCHAR(30),
    IN in_astreet VARCHAR(30),
    IN in_acity VARCHAR(30),
    IN in_astate VARCHAR(2),
    IN in_azipcode INT,
    IN in_date_opened DATETIME,
    IN in_astatus CHAR(1)
)
BEGIN
    UPDATE ais_acct
    SET acct_name = in_acct_name, astreet = in_astreet, acity = in_acity, astate = in_astate, azipcode = in_azipcode,
        date_opened = in_date_opened, astatus = in_astatus
    WHERE acct_no = in_acct_no;
END //
DELIMITER ;

-- Creating a stored procedure to delete an account and its related records
DELIMITER //
CREATE PROCEDURE DeleteAccount(IN in_acct_no BIGINT)
BEGIN
    -- First, delete associated records in the child tables
    DELETE FROM ais_checking WHERE acct_no = in_acct_no;
    DELETE FROM ais_home_loan WHERE acct_no = in_acct_no;
    DELETE FROM ais_loan WHERE acct_no = in_acct_no;
    DELETE FROM ais_per_loan WHERE acct_no = in_acct_no;
    DELETE FROM ais_savings WHERE acct_no = in_acct_no;
    DELETE FROM ais_stud_loan WHERE acct_no = in_acct_no;

    -- Finally, delete the account itself
    DELETE FROM ais_acct WHERE acct_no = in_acct_no;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAllEducationalInstitutions()
BEGIN
    SELECT * FROM ais_edu;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetEducationalInstitutionByID(IN ineid INT)
BEGIN
    SELECT * FROM ais_edu WHERE eid = ineid;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CreateEducationalInstitution(
    IN inename VARCHAR(100)
)
BEGIN
    INSERT INTO ais_edu (ename) VALUES (inename);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateEducationalInstitution(
    IN ineid INT,
    IN inename VARCHAR(100)
)
BEGIN
    UPDATE ais_edu
    SET ename = inename
    WHERE eid = ineid;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteEducationalInstitution(IN ineid INT)
BEGIN
    -- First, delete student loans associated with the educational institution
    DELETE FROM ais_stud_loan WHERE eid = ineid;
    
    -- Then, delete the educational institution record
    DELETE FROM ais_edu WHERE eid = ineid;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE GetAllHomeInsurance()
BEGIN
    SELECT * FROM ais_home_insr;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetHomeInsuranceByID(IN inhid INT)
BEGIN
    SELECT * FROM ais_home_insr WHERE hid = inhid;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CreateHomeInsurance(
	IN inhname    VARCHAR(30) ,
	IN inhstreet  VARCHAR(30) ,
    IN inhcity    VARCHAR(30) ,
    IN inhstate   VARCHAR(2) ,
    IN inhzipcode INT
)
BEGIN
    INSERT INTO ais_home_insr (hname, hstreet, hcity, hstate, hzipcode)
    VALUES (inhname, inhstreet, inhcity, inhstate, inhzipcode);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateHomeInsurance(
    IN inhid INT,
    IN inhname    VARCHAR(30) ,
	IN inhstreet  VARCHAR(30) ,
    IN inhcity    VARCHAR(30) ,
    IN inhstate   VARCHAR(2) ,
    IN inhzipcode INT
)
BEGIN
    UPDATE ais_home_insr
    SET hname = inhname, hstreet = inhstreet, hcity = inhcity, hstate = inhstate, hzipcode = inhzipcode
    WHERE hid = inhid;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteHomeInsurance(IN inhid INT)
BEGIN
    -- First, delete student loans associated with the educational institution
    DELETE FROM ais_home_loan WHERE hid = inhid;
    
    -- Then, delete the educational institution record
    DELETE FROM ais_home_insr WHERE hid = inhid;
END //
DELIMITER ;




-- Deadloack Prevention
DELIMITER //
CREATE PROCEDURE GetCustomerByIDForUpdate(IN inCustId BIGINT)
BEGIN
    SELECT *
    FROM ais_cust
    WHERE custid = inCustId
    FOR UPDATE;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateAccountWithLock(
    IN in_acct_no BIGINT,
    IN in_acct_name VARCHAR(30),
    IN in_astreet VARCHAR(30),
    IN in_acity VARCHAR(30),
    IN in_astate VARCHAR(2),
    IN in_azipcode INT,
    IN in_date_opened DATETIME,
    IN in_astatus CHAR(1)
)
BEGIN
    -- Select the account to lock it for the current transaction
    SELECT acct_no
    FROM ais_acct
    WHERE acct_no = in_acct_no
    FOR UPDATE;

    -- Perform the update after the row is locked
    UPDATE ais_acct
    SET acct_name = in_acct_name, astreet = in_astreet, acity = in_acity, astate = in_astate, azipcode = in_azipcode,
        date_opened = in_date_opened, astatus = in_astatus
    WHERE acct_no = in_acct_no;
END //
DELIMITER ;

-- Set lock wait timeout to 15 seconds
SET SESSION innodb_lock_wait_timeout = 15;


-- Logging and Auditing
CREATE TABLE account_log (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    acct_no BIGINT NOT NULL,
    change_type VARCHAR(255) NOT NULL,
    change_description TEXT,
    changed_by VARCHAR(255),
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (acct_no) REFERENCES ais_acct(acct_no)
);

DELIMITER //
CREATE TRIGGER log_account_update
AFTER UPDATE ON ais_acct
FOR EACH ROW
BEGIN
    INSERT INTO account_log (acct_no, change_type, change_description, changed_by)
    VALUES (NEW.acct_no, 'Update', CONCAT('Account updated. Old Status: ', OLD.astatus, ' New Status: ', NEW.astatus), CURRENT_USER());
END;
DELIMITER ;

-- Data Warehousing for Analytics
CREATE TABLE account_summary (
    acct_type VARCHAR(8),
    average_balance DECIMAL(10,2),
    total_accounts INT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //
CREATE PROCEDURE update_account_summary()
BEGIN
    -- Clear the current summary data
    DELETE FROM account_summary;

    -- Insert new summary data with fully qualified column names
    INSERT INTO account_summary (acct_type, average_balance, total_accounts)
    SELECT a.acct_type, AVG(l.lamount), COUNT(a.acct_no)
    FROM ais_acct a
    LEFT JOIN ais_loan l ON a.acct_no = l.acct_no
    GROUP BY a.acct_type;
END //
DELIMITER ;

-- Performance Metrics
CREATE INDEX idx_acct_status ON ais_acct(astatus);
CREATE INDEX idx_loan_amount ON ais_loan(lamount);
CREATE INDEX idx_loan_payment ON ais_loan(lpayment);

-- Analytic procedures
CREATE TABLE customer_metrics (
    custid BIGINT PRIMARY KEY,
    lifetime_value DECIMAL(15,2)
);

DELIMITER //
CREATE PROCEDURE calculate_lifetime_value()
BEGIN
    -- Clear existing data in the customer_metrics table
    TRUNCATE TABLE customer_metrics;

    -- Insert or update customer lifetime values into the customer_metrics table
    INSERT INTO customer_metrics (custid, lifetime_value)
    SELECT a.custid, SUM(l.lpayment * l.lmonths)
    FROM ais_loan l
    JOIN ais_acct a ON l.acct_no = a.acct_no
    GROUP BY a.custid
    ON DUPLICATE KEY UPDATE lifetime_value = VALUES(lifetime_value);
END //
DELIMITER ;



 

INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (1, 'John', 'Doe', '123 Maple St', 'Springfield', 'IL', 62704);

INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (2, 'Jane', 'Smith', '456 Oak St', 'Riverside', 'CA', 92507);

INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (3, 'Alice', 'Johnson', '789 Pine St', 'Liberty', 'NY', 12754);

INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (4, 'Bob', 'Brown', '101 Ash Ave', 'Centerville', 'TX', 75833);

INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (5, 'Carol', 'White', '202 Birch Rd', 'Smalltown', 'FL', 32123);
COMMIT;

INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (1001, 'John Checking', '123 Maple St', 'Springfield', 'IL', 62704, STR_TO_DATE('2021-01-10', '%Y-%m-%d'), 'A', 'C', 1);

INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (2001, 'Jane Savings', '456 Oak St', 'Riverside', 'CA', 92507, STR_TO_DATE('2022-05-15', '%Y-%m-%d'), 'A', 'S', 2);

INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (3001, 'Alice Loan', '789 Pine St', 'Liberty', 'NY', 12754, STR_TO_DATE('2023-03-20', '%Y-%m-%d'), 'A', 'L', 3);

INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (4001, 'Bob Home Loan', '101 Ash Ave', 'Centerville', 'TX', 75833, STR_TO_DATE('2021-07-05', '%Y-%m-%d'), 'A', 'L', 4);

INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (5001, 'Carol Student Loan', '202 Birch Rd', 'Smalltown', 'FL', 32123, STR_TO_DATE('2022-08-12', '%Y-%m-%d'), 'A', 'L', 5);

COMMIT;

INSERT INTO ais_checking (acct_no, service_charge)
VALUES (1001, 5.00);

COMMIT;

INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (2001, 1.50);

COMMIT;

INSERT INTO ais_edu (eid, ename) VALUES (101, 'State University');
INSERT INTO ais_edu (eid, ename) VALUES (102, 'Tech University');
INSERT INTO ais_edu (eid, ename) VALUES (103, 'Harbor College');
INSERT INTO ais_edu (eid, ename) VALUES (104, 'Summit University');
INSERT INTO ais_edu (eid, ename) VALUES (105, 'Pine Tree College');
INSERT INTO ais_edu (eid, ename) VALUES (106, 'Golden State University');
INSERT INTO ais_edu (eid, ename) VALUES (107, 'River University');
INSERT INTO ais_edu (eid, ename) VALUES (108, 'Lake College');
INSERT INTO ais_edu (eid, ename) VALUES (109, 'City University');
INSERT INTO ais_edu (eid, ename) VALUES (110, 'Eastwood College');
INSERT INTO ais_edu (eid, ename) VALUES (111, 'Bright Future University');
INSERT INTO ais_edu (eid, ename) VALUES (112, 'Old Town College');
INSERT INTO ais_edu (eid, ename) VALUES (113, 'New Age University');
INSERT INTO ais_edu (eid, ename) VALUES (114, 'Northern University');
INSERT INTO ais_edu (eid, ename) VALUES (115, 'Skyline College');
INSERT INTO ais_edu (eid, ename) VALUES (116, 'Horizon University');

COMMIT;

INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode)
VALUES (501, 'Great Cover Insurance', '201 Insure Ln', 'Shieldtown', 'TX', 75839);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (502, 'Secure Home Insurance', '101 Secure Blvd', 'Safety City', 'CA', 94016);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (503, 'Trusty Insurance Co.', '202 Trust Ave', 'Reliable Town', 'TX', 75840);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (504, 'Shield Plus Insurance', '303 Guard St', 'Fort City', 'FL', 32124);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (505, 'Safe Haven Insurance', '404 Haven Rd', 'Shelterville', 'IL', 62705);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (506, 'Cover All Insurance', '505 Cover Ct', 'Cover City', 'NY', 12755);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (507, 'Fortress Insurance', '606 Fort Ln', 'Castle Town', 'TX', 75841);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (508, 'Protect First Insurance', '707 Protect Pl', 'Guardian City', 'CA', 94017);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (509, 'Assure Insurance', '808 Assure Ave', 'Secure Town', 'FL', 32125);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (510, 'Home Guard Insurance', '909 Home Rd', 'Defender City', 'IL', 62706);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (511, 'Resident Shield Insurance', '110 Resident St', 'Home Town', 'NY', 12756);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (512, 'Elite Cover Insurance', '111 Elite Blvd', 'Elite City', 'TX', 75842);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (513, 'Imperial Insurance', '212 Imperial Ave', 'Empire Town', 'CA', 94018);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (514, 'Guardian Home Insurance', '313 Guardian St', 'Sentinel City', 'FL', 32126);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (515, 'Safe Property Insurance', '414 Safe Pl', 'Safety Town', 'IL', 62707);
INSERT INTO ais_home_insr (hid, hname, hstreet, hcity, hstate, hzipcode) 
VALUES (516, 'Secure Base Insurance', '515 Base Rd', 'Base City', 'NY', 12757);


COMMIT;

INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (3001, 3.75, 50000, 1000, 60, 'PL');

INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (4001, 4.00, 150000, 1500, 120, 'HL');

INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (5001, 5.00, 20000, 400, 48, 'SL');

COMMIT;

INSERT INTO ais_stud_loan (acct_no, sid, degree, expgrad_month, expgrad_year, eid)
VALUES (5001, 102, 'G', 5, 2024, 101);

COMMIT;

INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (4001, 5002, 2020, 1200.00, 501);

COMMIT;

-- Customer 6: Helen Thomas with Checking and Home Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (6, 'Helen', 'Thomas', '324 Elm St', 'Hillside', 'CA', 91001);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (6001, 'Helen Checking', '324 Elm St', 'Hillside', 'CA', 91001, STR_TO_DATE('2023-05-01', '%Y-%m-%d'), 'A', 'C', 6);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (6002, 'Helen Home Loan', '324 Elm St', 'Hillside', 'CA', 91001, STR_TO_DATE('2023-05-02', '%Y-%m-%d'), 'A', 'L', 6);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (6001, 12.00);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (6002, 4.25, 250000, 1200, 360, 'HL');
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (6002, 5003, 2021, 1200.00, 502);

-- Customer 7: Alex Johnson with Checking and Personal Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (7, 'Alex', 'Johnson', '560 Pine St', 'Greenville', 'TX', 75401);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (7001, 'Alex Checking', '560 Pine St', 'Greenville', 'TX', 75401, STR_TO_DATE('2023-04-15', '%Y-%m-%d'), 'A', 'C', 7);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (7002, 'Alex Personal Loan', '560 Pine St', 'Greenville', 'TX', 75401, STR_TO_DATE('2023-04-16', '%Y-%m-%d'), 'A', 'L', 7);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (7001, 10.00);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (7002, 5.00, 15000, 450, 36, 'PL');
INSERT INTO ais_per_loan(acct_no, early_repay)
VALUES (7002, 2000);

-- Customer 8: Diane Reed with Checking and Student Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (8, 'Diane', 'Reed', '780 Oak Ave', 'Clearwater', 'FL', 33755);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (8001, 'Diane Checking', '780 Oak Ave', 'Clearwater', 'FL', 33755, STR_TO_DATE('2023-06-10', '%Y-%m-%d'), 'A', 'C', 8);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (8002, 'Diane Student Loan', '780 Oak Ave', 'Clearwater', 'FL', 33755, STR_TO_DATE('2023-06-11', '%Y-%m-%d'), 'A', 'L', 8);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (8001, 8.00);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (8002, 6.00, 20000, 550, 48, 'SL');
INSERT INTO ais_stud_loan (acct_no, sid, degree, expgrad_month, expgrad_year, eid)
VALUES (8002, 103, 'U', 5, 2024, 102);

-- Customer 9: Mark White with Savings and Home Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (9, 'Mark', 'White', '1122 Birch Rd', 'Mapleton', 'NV', 89701);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (9001, 'Mark Savings', '1122 Birch Rd', 'Mapleton', 'NV', 89701, STR_TO_DATE('2023-07-20', '%Y-%m-%d'), 'A', 'S', 9);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (9002, 'Mark Home Loan', '1122 Birch Rd', 'Mapleton', 'NV', 89701, STR_TO_DATE('2023-07-21', '%Y-%m-%d'), 'A', 'L', 9);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (9001, 2.00);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (9002, 3.75, 300000, 1400, 360, 'HL');
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (9002, 5004, 2021, 1250.00, 503);

-- Customer 10: Emily Black with Savings and Personal Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (10, 'Emily', 'Black', '1313 Cedar Ln', 'Brookside', 'MT', 59001);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (10001, 'Emily Savings', '1313 Cedar Ln', 'Brookside', 'MT', 59001, STR_TO_DATE('2023-08-15', '%Y-%m-%d'), 'A', 'S', 10);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (10002, 'Emily Personal Loan', '1313 Cedar Ln', 'Brookside', 'MT', 59001, STR_TO_DATE('2023-08-16', '%Y-%m-%d'), 'A', 'L', 10);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (10001, 1.75);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (10002, 4.50, 10000, 300, 24, 'PL');
INSERT INTO ais_per_loan(acct_no, early_repay)
VALUES (10002, 1000);
-- Customer 11: Sarah Miller with Checking, Savings, and Home Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (11, 'Sarah', 'Miller', '234 Oak Lane', 'Sunnydale', 'CA', 94010);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (11001, 'Sarah Checking', '234 Oak Lane', 'Sunnydale', 'CA', 94010, STR_TO_DATE('2023-09-01', '%Y-%m-%d'), 'A', 'C', 11),
       (11002, 'Sarah Savings', '234 Oak Lane', 'Sunnydale', 'CA', 94010, STR_TO_DATE('2023-09-02', '%Y-%m-%d'), 'A', 'S', 11),
       (11003, 'Sarah Home Loan', '234 Oak Lane', 'Sunnydale', 'CA', 94010, STR_TO_DATE('2023-09-03', '%Y-%m-%d'), 'A', 'L', 11);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (11001, 15.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (11002, 2.25);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (11003, 3.90, 280000, 1300, 360, 'HL');
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (11003, 5005, 2022, 1150.00, 504);

-- Customer 12: Michael Roberts with Checking, Savings, and Personal Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (12, 'Michael', 'Roberts', '678 Pine Road', 'Cliffton', 'NY', 10021);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (12001, 'Michael Checking', '678 Pine Road', 'Cliffton', 'NY', 10021, STR_TO_DATE('2023-10-01', '%Y-%m-%d'), 'A', 'C', 12),
       (12002, 'Michael Savings', '678 Pine Road', 'Cliffton', 'NY', 10021, STR_TO_DATE('2023-10-02', '%Y-%m-%d'), 'A', 'S', 12),
       (12003, 'Michael Personal Loan', '678 Pine Road', 'Cliffton', 'NY', 10021, STR_TO_DATE('2023-10-03', '%Y-%m-%d'), 'A', 'L', 12);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (12001, 15.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (12002, 1.85);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (12003, 5.25, 8000, 245, 36, 'PL');
INSERT INTO ais_per_loan(acct_no, early_repay)
VALUES (12003, 500);

-- Customer 13: Jessica Lee with Checking, Savings, and Student Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (13, 'Jessica', 'Lee', '321 Maple Avenue', 'Bridgetown', 'TX', 75001);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (13001, 'Jessica Checking', '321 Maple Avenue', 'Bridgetown', 'TX', 75001, STR_TO_DATE('2023-11-01', '%Y-%m-%d'), 'A', 'C', 13),
       (13002, 'Jessica Savings', '321 Maple Avenue', 'Bridgetown', 'TX', 75001, STR_TO_DATE('2023-11-02', '%Y-%m-%d'), 'A', 'S', 13),
       (13003, 'Jessica Student Loan', '321 Maple Avenue', 'Bridgetown', 'TX', 75001, STR_TO_DATE('2023-11-03', '%Y-%m-%d'), 'A', 'L', 13);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (13001, 12.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (13002, 2.00);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (13003, 6.50, 25000, 690, 48, 'SL');
INSERT INTO ais_stud_loan (acct_no, sid, degree, expgrad_month, expgrad_year, eid)
VALUES (13003, 104, 'U', 5, 2024, 103);

-- Customer 14: Home Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (14, 'William', 'Brown', '550 Cedar Lane', 'Greenwood', 'MO', 64030);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (14001, 'William Checking', '550 Cedar Lane', 'Greenwood', 'MO', 64030, STR_TO_DATE('2023-12-01', '%Y-%m-%d'), 'A', 'C', 14),
       (14002, 'William Savings', '550 Cedar Lane', 'Greenwood', 'MO', 64030, STR_TO_DATE('2023-12-02', '%Y-%m-%d'), 'A', 'S', 14),
       (14003, 'William Home Loan', '550 Cedar Lane', 'Greenwood', 'MO', 64030, STR_TO_DATE('2023-12-03', '%Y-%m-%d'), 'A', 'L', 14);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (14001, 10.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (14002, 1.75);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (14003, 3.65, 220000, 1100, 360, 'HL');
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (14003, 5006, 2022, 1250.00, 505);

-- Customer 15: Personal Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (15, 'Olivia', 'Taylor', '199 Spruce St', 'Fairview', 'CA', 94920);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (15001, 'Olivia Checking', '199 Spruce St', 'Fairview', 'CA', 94920, STR_TO_DATE('2024-01-01', '%Y-%m-%d'), 'A', 'C', 15),
       (15002, 'Olivia Savings', '199 Spruce St', 'Fairview', 'CA', 94920, STR_TO_DATE('2024-01-02', '%Y-%m-%d'), 'A', 'S', 15),
       (15003, 'Olivia Personal Loan', '199 Spruce St', 'Fairview', 'CA', 94920, STR_TO_DATE('2024-01-03', '%Y-%m-%d'), 'A', 'L', 15);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (15001, 14.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (15002, 2.10);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (15003, 5.00, 10000, 300, 24, 'PL');
INSERT INTO ais_per_loan(acct_no, early_repay)
VALUES (15003, 1000);

-- Customer 16: Nathan Clark with Checking, Savings, and Student Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (16, 'Nathan', 'Clark', '850 Vine Street', 'Meadow', 'NJ', 07001);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (16001, 'Nathan Checking', '850 Vine Street', 'Meadow', 'NJ', 07001, STR_TO_DATE('2024-02-01', '%Y-%m-%d'), 'A', 'C', 16),
       (16002, 'Nathan Savings', '850 Vine Street', 'Meadow', 'NJ', 07001, STR_TO_DATE('2024-02-02', '%Y-%m-%d'), 'A', 'S', 16),
       (16003, 'Nathan Student Loan', '850 Vine Street', 'Meadow', 'NJ', 07001, STR_TO_DATE('2024-02-03', '%Y-%m-%d'), 'A', 'L', 16);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (16001, 11.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (16002, 1.90);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (16003, 6.75, 30000, 850, 60, 'SL');
INSERT INTO ais_stud_loan (acct_no, sid, degree, expgrad_month, expgrad_year, eid)
VALUES (16003, 105, 'G', 5, 2024, 104);

-- Customer 17: Linda Wilson with Checking, Savings, and Home Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (17, 'Linda', 'Wilson', '132 Palm Road', 'Sunnyvale', 'CA', 94086);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (17001, 'Linda Checking', '132 Palm Road', 'Sunnyvale', 'CA', 94086, STR_TO_DATE('2024-03-01', '%Y-%m-%d'), 'A', 'C', 17),
       (17002, 'Linda Savings', '132 Palm Road', 'Sunnyvale', 'CA', 94086, STR_TO_DATE('2024-03-02', '%Y-%m-%d'), 'A', 'S', 17),
       (17003, 'Linda Home Loan', '132 Palm Road', 'Sunnyvale', 'CA', 94086, STR_TO_DATE('2024-03-03', '%Y-%m-%d'), 'A', 'L', 17);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (17001, 9.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (17002, 2.50);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (17003, 3.85, 350000, 1650, 360, 'HL');
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (17003, 5007, 2023, 1150.00, 506);
-- Customer 18: Brian Harris with Checking, Savings, and Personal Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (18, 'Brian', 'Harris', '230 Elm Dr', 'Roseville', 'MI', 48066);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (18001, 'Brian Checking', '230 Elm Dr', 'Roseville', 'MI', 48066, STR_TO_DATE('2024-04-01', '%Y-%m-%d'), 'A', 'C', 18),
       (18002, 'Brian Savings', '230 Elm Dr', 'Roseville', 'MI', 48066, STR_TO_DATE('2024-04-02', '%Y-%m-%d'), 'A', 'S', 18),
       (18003, 'Brian Personal Loan', '230 Elm Dr', 'Roseville', 'MI', 48066, STR_TO_DATE('2024-04-03', '%Y-%m-%d'), 'A', 'L', 18);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (18001, 14.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (18002, 1.65);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (18003, 5.50, 5000, 152, 36, 'PL');
INSERT INTO ais_per_loan(acct_no, early_repay)
VALUES (18003, 200);

-- Customer 19: Amy Gonzalez with Checking, Savings, and Student Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (19, 'Amy', 'Gonzalez', '880 Cedar Blvd', 'Springfield', 'IL', 62704);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (19001, 'Amy Checking', '880 Cedar Blvd', 'Springfield', 'IL', 62704, STR_TO_DATE('2024-05-01', '%Y-%m-%d'), 'A', 'C', 19),
       (19002, 'Amy Savings', '880 Cedar Blvd', 'Springfield', 'IL', 62704, STR_TO_DATE('2024-05-02', '%Y-%m-%d'), 'A', 'S', 19),
       (19003, 'Amy Student Loan', '880 Cedar Blvd', 'Springfield', 'IL', 62704, STR_TO_DATE('2024-05-03', '%Y-%m-%d'), 'A', 'L', 19);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (19001, 13.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (19002, 2.25);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (19003, 6.25, 22000, 550, 48, 'SL');
INSERT INTO ais_stud_loan (acct_no, sid, degree, expgrad_month, expgrad_year, eid)
VALUES (19003, 106, 'G', 5, 2024, 105);

-- Customer 20: Joe Walsh with Checking, Savings, and Home Loan
INSERT INTO ais_cust (custid, cfname, clname, cstreet, ccity, cstate, czipcode)
VALUES (20, 'Joe', 'Walsh', '962 Maple St', 'Ridgewood', 'NJ', 07450);
INSERT INTO ais_acct (acct_no, acct_name, astreet, acity, astate, azipcode, date_opened, astatus, acct_type, custid)
VALUES (20001, 'Joe Checking', '962 Maple St', 'Ridgewood', 'NJ', 07450, STR_TO_DATE('2024-06-01', '%Y-%m-%d'), 'A', 'C', 20),
       (20002, 'Joe Savings', '962 Maple St', 'Ridgewood', 'NJ', 07450, STR_TO_DATE('2024-06-02', '%Y-%m-%d'), 'A', 'S', 20),
       (20003, 'Joe Home Loan', '962 Maple St', 'Ridgewood', 'NJ', 07450, STR_TO_DATE('2024-06-03', '%Y-%m-%d'), 'A', 'L', 20);
INSERT INTO ais_checking (acct_no, service_charge)
VALUES (20001, 10.00);
INSERT INTO ais_savings (acct_no, interest_rate)
VALUES (20002, 2.10);
INSERT INTO ais_loan (acct_no, lrate, lamount, lpayment, lmonths, ltype)
VALUES (20003, 3.95, 400000, 1900, 360, 'HL');
INSERT INTO ais_home_loan (acct_no, hacct_no, hbuilt_year, yr_premium, hid)
VALUES (20003, 5008, 2022, 1150.00, 507);

COMMIT;
