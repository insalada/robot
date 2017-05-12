#Robot Kata

##Description

A squad of robots is to be deployed on a plateau. This plateau, which is curiously rectangular, must be navigated by the robots.

A robot position and location is represented by a combination of x and y co­ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the robot is in the bottom left corner and facing North.

In order to control a robot, we will send a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the robot spin 90 degrees left or right respectively, without moving from its current spot. 'M' means move forward one grid point, and maintains the same heading. Assume that the square directly North from (x, y) is (x, y+1).

INPUT:  The first line of input is the upper­right coordinates of the plateau, the lower­left coordinates are assumed to be 0,0. The rest of the input is information pertaining to the robots that have been deployed. Each robot has two lines of input. The first line gives the robot position, and the second line is a series of instructions telling the robot how to explore the plateau. The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co­ordinates and the robot orientation. Each robot will be finished sequentially, which means that the second robot won't start to move until the first one has finished moving.
OUTPUT:  The output for each robot should be its final co­ordinates and heading.

INPUT AND OUTPUT EXAMPLE:

|   Input    |    Output    |
|------------|--------------|
| 5 5        | 1 3 N  5 1 E |
| 1 2 N      |              |
| LMLMLMLMM  |              |
| 3 3 E      |              |
| MMRMMRMRRM |              |


##Optional

Using the implementation of yoursolution to the Robot Kata as a base, implement a mechanism whereby your solution can be used remotely. You may wish to choose to implement your own client or to use an existing widely available client such as telnet, curl or a web browser for interacting with your solution.

##How to run

Create a goal or just run into your console:

>`mvn jetty:run`


##Tests

Run the command below or just run as JUnit Tests in your IDE to see the metrics:

>`mvn test`


##How does it work

When the server is up&running, request a service as below, providing your data as a POST body.
For this purpose you can use cURL, POSTMAN or any other rest client.

Please follow the syntax below:

>`curl -d <<INPUT>> -H "Content-Type: text/plain" http://localhost:8080/robot/<<SERVICE>>`

E.g.:

>`curl -d "5 5" -H "Content-Type: text/plain" http://localhost:8080/robot/start`


##Services

| url     | method | consumes            | produces    | description                     |
|---------|--------|---------------------|-------------|---------------------------------|
| /start  | POST   | plateau bounds      | coordenates | Set a new plateau boundaries    |
| /deploy | POST   | robot deploy coords | coordenates | Deploys a robot                 |
| /move   | POST   | instructions        | coordenates | Makes a move                    |
 


##How to use it

1) Set the plateau bounds. E.g:

>`curl -d "5 5" -H "Content-Type: text/plain" http://localhost:8080/robot/start`

2) Deploy a robot into the plateau (robots queue) by giving its position. E.g:

>`curl -d "1 2 N" -H "Content-Type: text/plain" http://localhost:8080/robot/deploy`

3) Move the robot by providing a set of instructions. E.g:

>`curl -d "LMLMLMLMM" -H "Content-Type: text/plain" http://localhost:8080/robot/move`
