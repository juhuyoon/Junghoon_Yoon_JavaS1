# Katas

## Structure

- Add the code necessary to the `starter-code` project `U1M2L7Katas`.
- All classes should part of the `com.company` package.
- All interfaces should be part of the `com.company.interfaces` package.
- All classes should have a default constructor.
- You can test by uncommenting the code in `AllKatasTest` and running it.

## Specifications

1. Create a `Vehicle` interface. It should define the following methods:
    - `void drive` which takes in an integer argument representing `miles`
    - `void displayMilesTraveled` which takes in no arguments.
1. Create a `Car` class. It should implement `Vehicle` and have the following properties and associated getters/setters:
    - String `make`
    - String `model`
    - int `milesTraveled`
1. Create a `Sedan` class. It should extend `Car` and have the following properties and associated getters/setters:
    - int `numDoors`
    - boolean `poweredWindows`
1. Create an `Accord` class. It should extend `Sedan` and have the following properties and associated getters/setters:
    - String `year`
    - Engine `engine`
    - Transmission `transmission`

_Hint:_ Remember that objects are often composed of other objects. If the specifications require compositional objects, you'll need to create the classes for them.

---
© 2019 Trilogy Education Services