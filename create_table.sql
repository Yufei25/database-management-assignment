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


DROP TABLE EMPLOYEE;
DROP TABLE FAULTREPORT;
DROP TABLE OUTLET;
DROP TABLE VEHICLE;
DROP TABLE CLIENT;
DROP TABLE RECHARGEMENT;

CREATE TABLE EMPLOYEE (
  EmpNo        CHAR(4),
  FName        VARCHAR2(20),
  LName        VARCHAR2(20),
  Position     VARCHAR2(20),
  Phone        NUMBER(10),
  Email        VARCHAR2(32),
  DOB          DATE,
  Gender       CHAR(5),
  Salary       NUMBER(7),
  HireDAte     DATE,
  OutNo        CHAR(4),
  SupervisorNo CHAR(4),
  CONSTRAINT EmpNo_PK PRIMARY KEY (EmpNo)
);

CREATE TABLE FAULTREPORT (
  ReportNum   CHAR(6),
  DateChecked DATE,
  Comments    VARCHAR2(320),
  EmpNo       CHAR(4),
  LicenseNo   CHAR(6),
  RentalNo    CHAR(6),
  CONSTRAINT Faultreport_PK PRIMARY KEY (ReportNum)
);

CREATE TABLE OUTLET (
  OutNo     CHAR(4),
  Street    VARCHAR2(32),
  City      VARCHAR2(20),
  State     VARCHAR2(20),
  ZipCode   NUMBER(6),
  Phone     NUMBER(10),
  ManagerNo CHAR(4),
  CONSTRAINT Outlet_PK PRIMARY KEY (OutNo)
);

CREATE TABLE VEHICLE (
  LicenseNo      CHAR(6),
  Make           VARCHAR2(16),
  Model          VARCHAR2(16),
  Color          VARCHAR2(16),
  Year           NUMBER(4),
  NoDoors        NUMBER(1),
  Capacity       NUMBER(2),
  DailyRate      NUMBER(4),
  InspectionDate DATE,
  OutNo          CHAR(4),
  CONSTRAINT Vehicle_PK PRIMARY KEY (LicenseNo)
);

CREATE TABLE CLIENT (
  ClientNo      CHAR(6),
  ClientName    CHAR(20),
  Street        VARCHAR2(32),
  City          VARCHAR2(20),
  State         VARCHAR2(20),
  ZipCode       NUMBER(6),
  WebAddress    VARCHAR2(64),
  Contact_FName VARCHAR2(20),
  Contact_LName VARCHAR2(20),
  Phone         NUMBER(10),
  Email         VARCHAR2(32),
  CONSTRAINT Client_PK PRIMARY KEY (ClientNo)
);


CREATE TABLE RECHARGEMENT (
  RentalNo      CHAR(6),
  StartDate     DATE,
  ReturnDate    DATE,
  MileageBefore NUMBER(7),
  NileageAfter  NUMBER(7),
  InsuranceType VARCHAR2(8),
  ClientNo      CHAR(6),
  LicenseNo     CHAR(6),
  CONSTRAINT Rechargement_PK PRIMARY KEY (RentalNo)
);

----------------


ALTER TABLE OUTLET
  ADD CONSTRAINT Outlet_Manager_FK FOREIGN KEY (ManagerNo) REFERENCES EMPLOYEE (EmpNo);

ALTER TABLE VEHICLE
  ADD CONSTRAINT Vehicle_Outlet_FK FOREIGN KEY (OutNo) REFERENCES OUTLET (OutNo);

ALTER TABLE EMPLOYEE
  ADD CONSTRAINT Employee_Outlet_FK FOREIGN KEY (OutNo) REFERENCES OUTLET (OutNo);
ALTER TABLE EMPLOYEE
  ADD CONSTRAINT Supervisor_Employee_FK FOREIGN KEY (SupervisorNo) REFERENCES EMPLOYEE (EmpNo);

ALTER TABLE FAULTREPORT
  ADD CONSTRAINT Faultreport_Employee_FK FOREIGN KEY (EmpNo) REFERENCES EMPLOYEE (EmpNo);
ALTER TABLE FAULTREPORT
  ADD CONSTRAINT Faultreport_License_FK FOREIGN KEY (LicenseNo) REFERENCES VEHICLE (LicenseNo);
ALTER TABLE FAULTREPORT
  ADD CONSTRAINT Faultreport_Rental_FK FOREIGN KEY (RentalNo) REFERENCES RECHARGEMENT (RentalNo);

ALTER TABLE RECHARGEMENT
  ADD CONSTRAINT Rechargement_Client_FK FOREIGN KEY (ClientNo) REFERENCES CLIENT (ClientNo);
ALTER TABLE RECHARGEMENT
  ADD CONSTRAINT Rechargement_License_FK FOREIGN KEY (LicenseNo) REFERENCES VEHICLE (LicenseNo);







