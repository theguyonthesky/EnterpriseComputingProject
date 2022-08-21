# EnterpriseComputingProject
Description:    In  this  assignment  you  will  utilize  a  suppliers/parts/jobs/shipments  database 
(creation/population script available on Webcourses under Project 4) as the back-end database. Front-
end access to this database by end users will occur through a single page displayed in the client’s web 
browser. The schema of the backend database consists of four tables with the following schemas for 
each table: 
 
  suppliers (snum, sname, status, city)  //information about suppliers 
  parts (pnum, pname, color, weight, city)  //information about parts 
  jobs (jnum, jname, numworkers, city)  //information about jobs 
     shipments (snum, pnum, jnum, quantity)  //suppliers ship parts to jobs in specific quantities 
   
The first-tier (user-level front-end) of your application will be two JSP pages, one of which handles root-
level user clients, the other which handles non-root-level clients, to enter SQL commands into a window 
(i.e. a form) and submit them to a server application for processing.  The front-end (and only the front-
end) will utilize JSP technology.   The front-end will provide the user a simple form in which they will 
enter a SQL command (any DML, DDL, or DCL command could theoretically be entered by the user, 
however we will restrict to queries, insert, update, replace, and delete commands).  The front-end will 
provide only three buttons for the user, an “Execute Command” button that will cause the execution of 
the SQL command currently in the input window, a “Reset Form” button that simply clears any content 
currently in the form input area, and a “Clear Results” button that will erase the currently displayed data 
(user optional).  The front-end will run on any web-based browser that you would like to use.  You can 
elect to have a default query or not, it is entirely your decision.  The application will connect to the 
backend database as either a root-level or non-root-level user depending on which front-end page is 
utilized.  This connection must be handled using only one of two techniques.  Either the connection is 
established using properties read from a properties file, or the connection is established via initialization 
parameters located within the web.xml file of the Project4 webapp.  You can use whichever technique 
you prefer for this project (you can use one of each if you’d like). 
 
The second-tier servlets, ae in charge of handling the SQL command interface for the user.  The root-
level  user  servlet  will  also  implement  the  server-side  business/application  logic.    This  logic  will 
increment by 5, the status of a supplier anytime that supplier is involved in the insertion/update of a 
shipment record in which the quantity is greater than or equal to 100.  Note that any update of quantity 
>= 100 will affect any supplier involved in a shipment with a quantity >= 100.  The example screen shots 
illustrate this case.  An insert of a shipment tuple (S5, P6, J7, 400) will cause the status of every supplier 
who has a shipment with a quantity of 100 or greater to be increased by 5.  In other words, even if a 
supplier’s shipment is not directly affected by the update, their status will be affected if they have any 
shipment with quantity >= 100.  (See page 14 for a bonus problem that implements a modified 
version of this business rule.)  The business logic of the second tier will reside in the servlet on the 
CNT 4714 – Project Four – Spring 2022 
  Page 2 
Tomcat web-application server (server-side application).  This means that the business logic is not to be 
implemented in the DBMS via a trigger.   
The client-level servlet will handle the SQL command interface, just as the root-level servlet does, 
however, due to the restrictions on the client-level privileges, no business-logic will be implemented in 
this application. 
 
The third-tier (back-end) is the persistent MySQL database described above and is under control of the 
MySQL DBMS server.  You will create and maintain this database via the creation/population script.  
See the important note below concerning when/how to re-run this script for your final submission.
