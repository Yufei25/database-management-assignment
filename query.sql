-- 1. For clients from Pennsylvania, list the duration (in hours) of each rental along with the ID and
-- name of the client, and the cost of the rental. Sort the result by client’s name.

SELECT
  CLIENTNO,
  CLIENTNAME,
  (RETURNDATE - STARTDATE) * 24,
  (RETURNDATE - STARTDATE) * DAILYRATE
FROM CLIENT
  JOIN RECHARGEMENT USING (CLIENTNO)
  JOIN VEHICLE USING (LICENSENO)
WHERE STATE = 'Pennsylvania'
ORDER BY CLIENTNAME;

-- 2. List employee (ID & Name) and the number of fault reports he/she has “prepared”. Rank
-- each employee based on the number of fault reports prepared. The highest number of fault
-- reports should get highest rank. Include “0” for employees who did not create any fault
-- reports.

SELECT
  EMPNO,
  FNAME || ' ' || LNAME NAME,
  NUM,
  RANK
FROM (
  SELECT
    EMPNO,
    COUNT(REPORTNUM)                      NUM,
    DENSE_RANK()
    OVER (
      ORDER BY COUNT(REPORTNUM) DESC ) AS RANK
  FROM EMPLOYEE
    LEFT OUTER JOIN FAULTREPORT USING (EMPNO)
  GROUP BY EMPNO)
  JOIN EMPLOYEE USING (EMPNO)
ORDER BY RANK;

-- 3. For each outlet, find the average number of hours it takes for a fault report to be generated
-- after the “damaged” car is returned to the rental agency. Include “0” for those outlets without
-- any fault reports.

SELECT
  OUTNO,
  TO_CHAR(NVL(AVG(GAP), 0.0), '9999.99') AVERAGE_GAP
FROM (
    (SELECT
       FAULTREPORT.LICENSENO,
       DATECHECKED - RECHARGEMENT.RETURNDATE GAP
     FROM FAULTREPORT
       JOIN RECHARGEMENT USING (RENTALNO)) A
    JOIN VEHICLE ON A.LICENSENO = VEHICLE.LICENSENO
    RIGHT OUTER JOIN OUTLET USING (OUTNO))
GROUP BY OUTNO ORDER BY OUTNO;

-- 4. List clients who have rented vehicle(s) but who have never generated any fault report. List
-- the ID and name as well as the contact information for those clients.


-- 5. For each outlet, calculate the number of rental agreements and the revenue generated from the
-- rentals. Rank the outlets based on the revenue generated from the rentals. List only the top
-- two outlets.

-- 6. Generate a list of rentals (ID, Start Date, and Return Date) with the client's information
-- (client's number and name) for the outlet with the most rentals. Also include the outlet street
-- address.

-- 7. For each client from West Virginia, list client ID and name, the number of rentals, average
-- duration of a rental, and the number of fault reports associated with the rentals. Include “0s”
-- for clients without any rentals.

-- 8. A manager from one of the outlets wants to analyze data of the rentals for each “vehicle
-- make” in his outlet in order to develop strategies for future purchases of vehicles and to share
-- his findings with other outlets. Consider two different measures that would allow the
-- manager to make the recommendations. Produce an appropriate list that includes all outlets
-- and sort it by outlet number and vehicle make. Then, write a paragraph explaining how the
-- measures are to be used to help making the right decisions regarding the operations of the
-- outlets.

-- 9. We need to review fault reports of cars rented in previous month. In addition to the details on
-- each fault reports, also include information about the car on the fault report, as well as the
-- name of employee who recorded the fault report.
-- 10. For each outlet managers, provide the manager’s name, number of outlets he/she manages,
-- total number of employees working at those outlets, and the total number of vehicles at those
-- outlets.

-- 11. We are interested in what types of clients we have. Based on the “web address” information,
-- group the client by the type of client (“.edu” indicates an educational institution, “.gov” a
-- government agency, “.org” a non-for-profit organization, and “.com” a for-profit company)
-- and provide how many clients we have of each type/category. If we do not have a web
-- address, then the “type” should be ‘Not Available’. For any category that has no clients
-- currently, display a “0”.