# Katas

## Structure

- Add the code necessary to the `starter-code` project `U1M3L1Katas`.
- All classes should part of the `com.company` package.
- Tests can be found in the `test/java` directory. Once you have written each class, uncomment the code in the associated test and run it.

## Specifications

For all of the below, remember to program to the interface rather than to the concrete class (i.e., declare your variables as `List` instead of `ArrayList`).

1. Refactor the following array methods found in `App` to use an `ArrayList` instead: 
    - `total`
    - `totalEven`
    - `swapFirstAndLast`
    - `reverse`
    - `lessThanFive`
    - `splitAtFive` **Challenge**
    - `evensAndOdds` **Challenge**
1. Create a new class called `LinkedListApp` and refactor the above activities to implement a `LinkedList` instead of an `ArrayList`.
1. Complete the following steps to create a `List` to hold information about your classmates.
    1. Create a `Classmate` class, which has the properties `name` and `hairColor`, along with the associated getters/setters.
    1. Create another class called `ClassmatesList` to hold `Classmate` objects.
    	- it should have a single private property which is an `ArrayList` of `Classmate` objects.
    	- do not create getters and setters for this list.
   	1. Implement the following public methods in the `ClassmatesList` class:
   		- `add` which takes in a new `Classmate` and adds it to the list of classmates.
   		- `get` which takes in an `int` and returns the classmate at that index.
   		- `getAll` which returns the entire list.
1. Create a new class called `SetIterator` and create a public method called `printSet` which does the following:
	- returns nothing.
	- takes in five integers as its arguments.
	- adds those integers into a new `HashSet`.
	- Uses an `Iterator` to print all members of the `HashSet`.

---
© 2019 Trilogy Education Services
 