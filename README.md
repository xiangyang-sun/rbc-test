# rbc-test
Calculate the total cost of fruits (Banana, Orange, Apple, Lemon, Peach) in a basket.

There are two solutions in this repository. boot-basket is REST API implemented with Spring Boot. simple-basket is a simple standalone program to read data from csv files and calculate the total cost of fruits in the basket.

# boot-basket
boot-basket is a REST API implementation. It uses Spring Boot, Web, H2 embedded database and Actuator. Two entities with many-to-one relationship are created. To simplify test, initial data is populated in data.sql script. You can remove it from src/main/resources directory if you need an empty database. Unit test is based on this initial data setup. You need to adjust the test code after changing the data in the script.

There are two methods are implemented in the service classs to calculate the total cost. Method 1 is to retreive all records from database as list of entity in JAVA code, then calculate the total cost.  Method 2 uses raw query to calculate the total cost by joining tables inside database, then return the result as a double number in repository class. Other APIs are also implemented to manage data entry in fruit and fruit_quantity tables. Check controller code for the detail.

<pre>
The definition of fruit table:
id     int,       -- primary key
name   string,    -- Fruit name
price  double     -- Frut price

The definition of fruit_quantity table:
qid i       int,    -- primary key, generated in Hibernate
fruit_id    int,   -- reference ID in fruit table
quantity    int    -- quantity of specified fruit in basket
</pre>

## API endpoint:
<pre>
GET   /basket-total-cost2   return double   -- implemented usigng aggregation query in database
GET   /basket-total-cost1   return double   -- implemented using lambda in java code
</pre>


## How to compile:
<pre>
Download the code from gitbub or clone it. Change to the source home directory. Then  run the mvnw.  
mvnw clean package
</pre>

## How to run:
<pre>
In command line, cd to the source home directory boot-basket. Then run below command.
java -jar target/boot-basket-0.0.1-SNAPSHOT.jar

Several URLs to access the APIs. For defail, please see the controller code.
http://localhost:8081/basket-total-cost2
http://localhost:8081/basket-total-cost1
http://localhost:8081/fruits
http://localhost:8081/fruits/1
http://localhost:8081/fruitquantities
http://localhost:8081/fruitquantities/1000001

Add, update and delete functions can be tested using Postman. When query by id, HTTP 404 error will be thrown if entity is not found.
</pre>

## H2 console
<pre>
H2 console can be accessed with http://localhost:8081/h2-console
Driver: org.h2.Driver
URL: jdbc:h2:mem:testdb
User name: sa
Password: <blank>
</pre>

# simple-basket
This simple basket program will read two CSVs. One is called fruit.csv which saves list of fruits and their price. The other is fruite_quantity.csv that represents the basket full of various fruits. All the data will be read into memory, then calculate the total cost of fruits in the basket. OpenCSV is used to read the csv files.

<pre>
The definition of fruit csv file is as below. The first line is header.
Column 1: id    -- type int, primary key
Column 2: name -- type string
Column 3: price -- decimal

The definition of fruit_quantity csv file is as below. The first line is header
Column 1: basket id --number, reference ID in fruit.csv
Column 2: quantity  --int
</pre>

Two model classes are created to encapsulate the data in the two csv files. A service class provides the calculation of fruit cost in the basket. The main class control the process: read the csv files and populate two lists of model classes, then call the service to calculate the total cost and display. In case of any exception, simply display the stack trace.

## How to compile:
<pre>
Download the code from gitbub or clone it. Change  to the source home directory. Then  run the mvnw.  
mvnw clean package

simple-basket-0.0.1-SNAPSHOT-jar-with-dependencies.jar will be created in target subdirectory.
</pre>

## How to run:
<pre>
In command line, cd to the source home directory simple-basket. Then run below command.
java -classpath target/simple-basket-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.johnsun.simplebasket.BasketMain
</pre>


