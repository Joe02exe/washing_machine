# Washing machine project

This project idea is based on a real-life problem: We, the students from the "Südtirolerheim Innsbruck", have 2 washing machines in our student residence, so you might guess that it is not that easy to get one two wash your clothes. So I decided to face the problem and find a solution. But it would be too easy and basic to just use a calendar to make reservations for one of the two washing machines... So I decided I would make a Spring Boot Application with java, because we just learned about it in the previous semester.

## Installation

- To run the application you should have installed Maven and Java 19 on your machine
- Then you can simply clone the repository and open the project
- I would use "Intellij-Idea" to load the project, because it is simply the best IDE there is for Java Spring Boot
- When openened Intellij, simply open the project (you can click on opening the "pom.xml"- file so all the dependencies get added to the project instantly -> look below)
  
![setup](/src/main/resources/static/media/setup.png)

- it may happen, that your java version in Intellij does not match the version of the project, so you have to press "Strg + Alt + Shift + S" to specify the java version or maybe download the version first
#
- After the initial setup you have to add a new Database with the name "washingMachine". For this you can use whichever database system you prefer, but I used "postgres" in combination with "pgAdmin" and would recommend to also use it
- In the next step you have to modify "src/main/resources/application.properties", because you must allow the application to connect itself to your database
- You have to change the following 3 attributes: 
- "spring.datasource.url" : change this to the location of your Database with the name "washingMachine"
- "spring.datasource.username" : change this to your username in your database system (in postgres the username is "postgres" by default)
- "spring.datasource.password" : change this to your password of the user (so the password of user "postgres" in our case)
#
- If there are any problems with this setup, you can simply google "JPA postgres spring-boot setup". There are many great tutorials online
#
Now the last step is to run the application with the bottom on the top right (Intellij), or by opening the project-folder in your terminal and typing  : `mvn spring-boot:run`

## User Information

There are two users by default in the database: An admin and a user. They have the following login credentials:
- username: "admin", password:"password"
- username: "user1", password:"password"
## Homepage
In the end a homepage like this should be displayed when opening up `localhost:8080`:

 ![picture](/src/main/resources/static/media/homepage.png "")

 You can now login (as a guest or as a user) or registrate a new user which is then directly stored in your local database

## Additional Information

The project is far away from being finished. There is still a lot to do, like:

- writing JUnit Tests (so there may still be a few bugs)
- adding reservation functionality for users to actually make a reservation for one of the machines
- adding user-profile to homepage so users can see and change their attributes
- maybe a global Dashboard to show the users with the most washed minutes
#
#### Further information
My Frontend-Skills are not that advanced and I am still just trying things out. In this project I used a few templates, used Bootstrap but also styled many things by myself, so there may be a little chaos :) 
#
Copyright © Waschmaschinenprojekt 2023