# Drive

This project is a development test from http://simudyne.com/careers_backend.html

The idea of the test is be able to understand an algorithm and implement it in less than a hour. Consequently, the code works but all features are not totally finished and well implemented.

## How to run ?
To run, you can use `sbt run`.


## The project

### General idea

The steps are :

- Reading input file
    - Read CSV file
    - Fit the input data into a list of Agent
- Apply an algorithm to decide whether the Agent should be changed of breed or not
    - Apply the algorithm for 15 years
- Printing the result


### Structure

```
.
+--- main
| +--- scala
| | +--- fr.damienraymond.drive
| | | +--- Configuration.scala          -> Gather input file path, csv separator type, etc.
| | | +--- datafetcher                  -> Read data source
| | | | +--- DataFetcher.scala            -> Trait definition
| | | | +--- CSVDataFetcher.scala         -> CSV implementation of the trait
| | | +--- Main.scala                   -> Launch Simulator
| | | +--- model
| | | | +--- Agent.scala                -> Agent case class that corresponds to a row of the input file
| | | | +--- AgentBreed.scala           -> AgentBreed is a trait to define only two type of agent : C and NC
| | | +--- Simulator.scala              -> The main class that contains the algo and the prints

```

