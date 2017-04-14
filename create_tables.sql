ALTER TABLE OUTLET
  DROP CONSTRAINT Outlet_Manager_FK;

ALTER TABLE VEHICLE
  DROP CONSTRAINT Vehicle_Outlet_FK;

ALTER TABLE EMPLOYEE
  DROP CONSTRAINT Employee_Outlet_FK;

ALTER TABLE EMPLOYEE
  DROP CONSTRAINT Supervisor_Employee_FK;

ALTER TABLE FAULTREPORT
  DROP CONSTRAINT Faultreport_Employee_FK;

ALTER TABLE FAULTREPORT
  DROP CONSTRAINT Faultreport_License_FK;

ALTER TABLE FAULTREPORT
  DROP CONSTRAINT Faultreport_Rental_FK;

ALTER TABLE RECHARGEMENT
  DROP CONSTRAINT Rechargement_Client_FK;

ALTER TABLE RECHARGEMENT
  DROP CONSTRAINT Rechargement_License_FK;

-- Drop existing tables.

DROP TABLE EMPLOYEE;
DROP TABLE FAULTREPORT;
DROP TABLE OUTLET;
DROP TABLE VEHICLE;
DROP TABLE CLIENT;
DROP TABLE RECHARGEMENT;

CREATE TABLE EMPLOYEE (
  EmpNo        CHAR(4) NOT NULL ,
  FName        VARCHAR2(20),
  LName        VARCHAR2(20),
  Position     VARCHAR2(20),
  Phone        VARCHAR2(20),
  Email        VARCHAR2(32),
  DOB          DATE,
  Gender       CHAR(6),
  Salary       NUMBER(7),
  HireDate     DATE DEFAULT CURRENT_DATE,
  OutNo        CHAR(4),
  SupervisorNo CHAR(4),
  CONSTRAINT EmpNo_PK PRIMARY KEY (EmpNo),
  CONSTRAINT Email_format CHECK (Email LIKE '%@geekrent.com'),
  CONSTRAINT Gender_format CHECK (Gender in ('Female','Male'))
);

CREATE TABLE FAULTREPORT (
  ReportNum   CHAR(6) NOT NULL ,
  DateChecked DATE,
  Comments    VARCHAR2(320),
  EmpNo       CHAR(4),
  LicenseNo   CHAR(6),
  RentalNo    CHAR(6),
  CONSTRAINT Faultreport_PK PRIMARY KEY (ReportNum)
);

CREATE TABLE OUTLET (
  OutNo     CHAR(4) NOT NULL ,
  Street    VARCHAR2(32),
  City      VARCHAR2(20),
  State     VARCHAR2(20),
  ZipCode   NUMBER(6),
  Phone     VARCHAR2(20),
  ManagerNo CHAR(4),
  CONSTRAINT Outlet_PK PRIMARY KEY (OutNo)
);

CREATE TABLE VEHICLE (
  LicenseNo      CHAR(6) NOT NULL ,
  Make           VARCHAR2(16),
  Model          VARCHAR2(16),
  Color          VARCHAR2(16),
  Year           NUMBER(4),
  NoDoors        NUMBER(1),
  Capacity       NUMBER(2),
  DailyRate      NUMBER(4),
  InspectionDate DATE,
  OutNo          CHAR(4),
  CONSTRAINT Vehicle_PK PRIMARY KEY (LicenseNo),
  CONSTRAINT Color_enum CHECK (Color in ('BLACK', 'WHITE', 'BLUE', 'SILVER GRAY'))
);

CREATE TABLE CLIENT (
  ClientNo      CHAR(6) NOT NULL ,
  ClientName    CHAR(20),
  Street        VARCHAR2(32),
  City          VARCHAR2(20),
  State         VARCHAR2(20),
  ZipCode       NUMBER(6),
  WebAddress    VARCHAR2(64),
  Contact_FName VARCHAR2(20),
  Contact_LName VARCHAR2(20),
  Phone         VARCHAR2(20),
  Email         VARCHAR2(32),
  CONSTRAINT Client_PK PRIMARY KEY (ClientNo)
);


CREATE TABLE RECHARGEMENT (
  RentalNo      CHAR(6) NOT NULL ,
  StartDate     DATE,
  ReturnDate    DATE,
  MileageBefore NUMBER(7),
  NileageAfter  NUMBER(7),
  InsuranceType VARCHAR2(8),
  ClientNo      CHAR(6),
  LicenseNo     CHAR(6),
  CONSTRAINT Rechargement_PK PRIMARY KEY (RentalNo)
);

-- Create table complete.
