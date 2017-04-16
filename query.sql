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
GROUP BY OUTNO;