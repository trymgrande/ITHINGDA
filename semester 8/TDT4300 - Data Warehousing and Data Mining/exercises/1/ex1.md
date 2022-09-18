# TDT4300 Datamining - Exercise 1

## 1 - Modeling
![](images/2022-02-05-17-01-09.png)


## 2 – OLAP operations
### Report 1 - "The longest duration of any flight in the air"
Use MAX() on air_time to find the longest duration of any flight in the air.

### Report 2 - "Average elapsed time for each airline company"
Drill down by moving down the concept hierarchy of the airline-dimension.

### Report 3 - "The total number of flights flown in february "
Drill down by moving down the concept hierarchy of the time-dimension.
### Report 4 - "Each month and the airport with the highest amount of arrival flights"
Drill down by moving down the concept hierarchy of the time-dimension and the location-dimension.

### Report 5 - "Descending list of all months by the amount of total distance flown each month"
Drill down by moving down the concept hierarchy of the time-dimension.


## 4 - Multi-Dimensional Expressions (MDX)
### Report 1 - "The longest duration of any flight in the air"
```SQL
SELECT [Measures].[air time] ON COLUMNS
   FROM [cube]
```
![](images/2022-02-05-17-19-26.png)

### Report 2 - "Average elapsed time for each airline company"
```SQL
SELECT [Measures].[elapsed time] FROM Columns,
   [location].[airport] FROM ROWS
   FROM [cube]
```
![](images/2022-02-05-17-22-03.png)
**See [report_2.csv](report_2.csv) for full output.**

### Report 3 - "The total number of flights flown in February"
```SQL
SELECT [Measures].[number of flights] FROM Columns,
   [time].[monthL] FROM ROWS
   FROM [cube]
```
![](images/2022-02-05-17-33-07.png)

### Report 4 - “Each Month and the airport with the highest amount of arrival flights”
**Note: we were unsure of why the distinct function did not affect the 'month' row, and could therefore not completely solve the problem. Did not manage to get in contact with the TA's in time.**
```SQL
SELECT 
   [Measures].[number of flights] FROM columns,
   TopCount(
      {Distinct([time].[monthL].members)*[location].[airport].members}, 
      [Measures].[number of flights], 
      [Measures].[number of flights]
   ) ON ROWS
FROM [cube]
```
![](images/2022-02-05-17-37-29.png)
**See [report_4.csv](report_4.csv) for full output.**

### Report 5 - "Descending list of all months by the amount of total distance flown each month"
```SQL
SELECT 
   [Measures].[total distance ] ON columns,
   TopCount(
      {[time].[monthL].members}, 
      [Measures].[total distance ], 
      [Measures].[total distance ]
   ) ON ROWS
FROM [cube]
```
![](images/2022-02-05-17-58-50.png)