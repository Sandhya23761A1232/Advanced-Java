# Advanced-Java
Advanced Java Lab Work – This repository contains my daily lab exercises and implementations on Advanced Java concepts, including Servlets, JSP, JSTL, JDBC, and Spring for enterprise web application development.




✅ Day 1 – JDBC CRUD with MySQL (Statement Object)

Established JDBC connection with MySQL.

Performed CRUD operations using the Statement object:

Create → Insert records

Read → Retrieve records

Update → Modify records

Delete → Remove records



✅ Day 2 – JDBC CRUD with MySQL (PreparedStatement Object)

Implemented JDBC connectivity with MySQL using PreparedStatement.

Executed parameterized queries for secure database interaction.

Performed CRUD operations with improved efficiency and protection against SQL injection.




✅ Day 3 – JDBC with CallableStatement (Stored Procedures)

Connected Java with MySQL using CallableStatement.

Executed stored procedures for database operations.

Demonstrated usage of IN, OUT, and INOUT parameters.

Showed how CallableStatement improves reusability and efficiency in enterprise applications.




✅ Day 4 – JDBC Scrollable ResultSet Navigator

Implemented a menu-driven database navigator using Scrollable ResultSet in JDBC.

Established connection with MySQL and fetched data from the employees table.

Enabled navigation through records with operations:

Next, Previous, First, Last

Absolute (go to specific row)

Before First, After Last

Get Current Row Number

Demonstrated cursor control, row data retrieval, and resource management in JDBC.




✅ Day 5 – JDBC with Updatable ResultSet (Employee Management)

Implemented an Employee Management application using Updatable ResultSet in JDBC.

Established connection with MySQL and enabled scroll-sensitive, updatable result sets.

Features:

Display Employees → Fetch and display all employee records.

Update Salary → Search employee by name and update their salary directly in the ResultSet.

Demonstrated how changes in a ResultSet are immediately reflected in the database.

Includes menu-driven interface and proper resource handling.





✅ Day 6 – Servlet Test Application (Factorial, Addition, Languages)

This lab exercise demonstrates the power of Servlets by handling different kinds of dynamic web operations through form input and server-side processing.

🔹 Features Implemented:

Factorial Servlet

Accepts a number from the user via an HTML form.Computes the factorial using Java logic inside the servlet.

Returns the result dynamically as an HTML response.

Demonstrates mathematical computation with Servlets.

Addition Servlet

Accepts two numbers as input from the user.

Computes their sum and displays the result on the browser.

Illustrates how servlets can handle arithmetic operations and user data.

Languages Servlet

Accepts multiple language selections from the user (checkbox inputs).

Displays all languages known by the user.

Demonstrates handling of multiple input values and form processing in servlets.

🔹 Deployment Descriptor (web.xml)

Configures all three servlets with unique servlet-name and url-pattern.

Maps form actions in index.html to the respective servlets.

Ensures clean request–response flow using Servlet mappings.

🔹 Entry Page (index.html)

Acts as a welcome page for the application.

Provides links and forms for:

Finding factorial.

Performing addition of two numbers.

Selecting known languages.





✅ Day 7 – Servlet Collaboration (Login & Welcome Servlets)

A simple Java web application demonstrating servlet collaboration and session handling.

Features

Index Page (index.html) → Provides login form for user input.

Login Servlet → Authenticates user credentials and forwards the request.

Welcome Servlet → Displays a personalized welcome message after successful login.

Concepts Covered

Servlet collaboration using RequestDispatcher (forward & include)

Request/response handling with HttpServletRequest & HttpServletResponse

Basic session management and parameter passing






✅ Day 8 – User Registration & Login Authentication with HttpSession

This lab exercise demonstrates a complete user authentication system using Servlets, JDBC, and HttpSession for secure session management.

🔹 Features Implemented:

Registration Page (registration.html) → Allows new users to register by entering their details. Data is stored in the MySQL database.

Login Page (login.html) → Authenticates existing users using email and password.

Registration Servlet → Handles new user registration and database insertion.

Login Servlet →

Validates login credentials against the database.

Creates a new HttpSession for authenticated users.

Forwards to Welcome page upon successful login.

Welcome Servlet → Displays personalized content using session attributes.

Logout Servlet → Invalidates the HttpSession and redirects users back to login page.

🔹 Concepts Covered:

JDBC connectivity with MySQL for registration and authentication

HttpSession for managing user login state across multiple requests

Request forwarding and redirection using RequestDispatcher

Modular servlet design (Registration, Login, Welcome, Logout)

Session timeout and proper logout handling






✅ Day 9 – Student Management Project (HttpSession & Filters)

This lab exercise demonstrates a student management system built using Servlets, with state management via HttpSession and Servlet Filters for request preprocessing and security.

🔹 Features Implemented:

Student Registration & Login → New users can register, and existing users can log in.

HttpSession State Management → Maintains user session across multiple requests after successful login.

Welcome Page → Displays personalized content using session attributes.

Logout Servlet → Ends the session and redirects back to login page.

Servlet Filters →

Restrict direct access to protected resources without authentication.

Validate input data before processing.

Perform logging for incoming requests.

🔹 Concepts Covered:

Servlets (doGet, doPost) for handling registration, login, and logout

HttpSession for session tracking and state management

Filters for authentication, validation, and logging

Request forwarding & redirection using RequestDispatcher

Separation of concerns (Frontend → HTML/JSP, Backend → Servlets + JDBC)

🔹 Deployment Descriptor (web.xml):

Configures servlets (RegistrationServlet, LoginServlet, WelcomeServlet, LogoutServlet)

Defines Filter mappings to secure and validate requests

🔹 Entry Pages:

registration.html → Collects new student details and stores them in DB

login.html → Authenticates users and starts session






✅ Day 10  -  Multiplication Table Generator using JSP

This project is a simple JSP-based web application that dynamically generates the multiplication table for a number selected by the user. The application demonstrates JSP fundamentals such as form handling, request parameter retrieval, and server-side scripting.

🔹 Features

Entry page with a dropdown list of numbers (1–100).

User selects a number and submits through a form.

JSP processes the request and displays the multiplication table (1–10) for the chosen number.

Uses HTML, JavaScript, and JSP scripting elements.
