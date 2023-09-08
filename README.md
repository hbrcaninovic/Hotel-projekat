# HOME - Hotel Organisation and Management Exercises
<p align="center">
<img src="https://github.com/hbrcaninovic/Hotel-projekat/blob/main/src/main/resources/img/logo.PNG" alt="Home - logo">
</p>
 
## Basic project information:
 * **Project title**: HOME - Hotel Organisation and Management Exercises
 * **Autor**: Hasan Brčaninović
 * **Project type**: Console application as a part of the Development of software solutions course
 * **Version**: 0.1
 * **Year of production**: 2022 - 2023 
<br>



 ### Project idea
The topic of the project is computer systems intended for the performance of various jobs in the hotel.
The idea is to present the principle of operation of the system, which is intended for the performance of the staff's work in the hotel, on a smaller set of basic functionalities.
The name of the project is HOME, which is an acronym for Hotel Organization and Management Exercises that further describes this topic.

### Project description
HOME is a console application intended for performing various administrative tasks that occur in hotel.
This application allows users to easily perform various tasks such as organizing rooms and scheduling reservations as well as managing the user accounts of its employees.
Given that the hotel system is complex in terms of all the roles that exist in it, from receptionists, accountants, hall managers to administrators, two roles with which the basic interaction that typically appears in hotel systems is established are singled out here. Using these roles and functionalities in the system, this application becomes an example of a basic application that enables hotel employees to achieve better and higher quality tasks during the reception and response of guests.

### Functionalities
HOME system provides the following functionalities
1. Log in
2. Creating user accounts
3. Updating and deleting a user account
4. Adding and managing hotel rooms
5. Creating and managing of reservation records

All these functionalities provide a basic set of possibilities that the system for working in the hotel should support.
In addition, this system is open for upgrading and adding new functionalities.


### Installation
To run this program, you need to perform the following steps:
1. clone the repository to your computer
2. create a database using DB dump folder in the MySQL Workrench environment
3. open the project in the IntelliJ environment
4. to view the complete documentation, enter the following command in the terminal:
```bash 
   mvn javadoc:aggregate
```
5. To start the command interface, type the following commands in the terminal:
```bash 
   mvn clean install -P cli-app
   java -jar target\Hotel-projekat-cli-jar-with-dependencies.jar
```
6. to start the graphical user interface, enter the following commands in the terminal:
```bash 
   mvn clean install -P cli-app
   mvn clean javafx:run
```