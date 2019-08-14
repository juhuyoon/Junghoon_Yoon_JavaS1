# Katas

## Structure

- Add the code necessary to the `starter-code` project `U1M2L5Katas`.
- All classes should part of the `com.company` package.
- All classes should have a default constructor.
- You can test your classes by uncommenting the associated code in `AllKatasTest` and running it.

## Requirements

1. Create the below classes, each of which should implement the provided `Shape` interface:
    * `Square`
    * `Circle`
    * `Triangle`
1. Create a new interface called `Clock`, which has the following methods 
  - `void displayTime` which takes in no arguments
  - `void timer` which takes in an integer `hour` and `integer` minute.
1. Create a class called `AnalogClock`, which implements the `Clock` interface.
1. Create a new interface called `Alarm`, which has the following method.
  - `void sound` which takes in no arguments
1. Create a class called `Buzzer`, which implements the `Alarm` interface.
1. Create a class called `DigitalClock`. This class is both a clock and an alarm, so implement both the `Clock` and `Alarm` interfaces.

---
© 2019 Trilogy Education Services