# Katas

## Structure

- Add the code necessary to the `App` class found in the `starter-code` project `U1M3L2Katas`.
- All methods should be `public`.
- Tests can be found in the `test/java` directory. Once you have written each class, uncomment the code in the associated test and run it.

## Requirements

1. Create a method called `printKeys` which takes in a `Map` with String keys and String values and iterates through and prints out all keys in the `Map`.
1. Create a method called `printValues` which takes in a `Map` with String keys and String values and iterates through and prints out allvalues in the `Map`.
1. Create a method called `printKeysAndValues` which takes in a `Map` with String keys and String values iterates through the Entry Set of the `Map` and print both the keys and values in the `Map` in this format: `key: value`.
1. Create a method called `mapFun` which does the following:
    1. Takes in a `Map` with String keys and integer values
    1. Returns a `Map` with String keys and integer values
    1. Assume the `Map` will always contain at least these entries:

        |       Key       |       Value       |
        |-----------------|-------------------|
        | Toyota Camry    |       2012        |
        | Chevy Camaro    |       1969        |
        | Hyundai Genesis |       2015        |
        | Jeep Wrangler   |       2003        |
        | Honda Civic     |       2018        |
        | Toyota Supra    |       1995        |
        | Pontiac GTO     |       1964        |

    1. Add two new entries to the `Map` - a 2012 Ford Explorer and a 2013 Smart Fortwo.
    1. Remove the Jeep Wrangler from the `Map`
    1. Return the `Map`
1. Create a method called `listCars`, which takes in a `List` of `Car` objects and complete the following:
    1. Create a `List` called `toyotaList` to hold all the `Car` variables with "Toyota" as the make and add them to the List.
    1. Create a `List` called `fordList` to hold all the `Car` variables with "Ford" as the make and add them to the List.
    1. Create a `List` called `hondaList` to hold all the `Car` variables with "Honda" as the make and add them to the List.
    1. Store all 3 `List` in a `Map` with the make as the key.
    1. Return the `Map`.

_Hint_: Your key will be a `String`. What will your value be?

---
© 2019 Trilogy Education Services
