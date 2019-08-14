# Implementation UserIO Interface

## Specification

Create a new class called `Input`, which implements the provided `UserIO` interface.

This interface defines the following methods:

- `readInt`, which displays a prompt and then reads an `int` from the user
- `readLong`, which displays a prompt reading a `long` from the user
- `readDouble`, which displays a prompt reading a `double` from the user
- `readFloat`, which displays a prompt reading a `float` from the user
- `readString`, which displays a prompt reading a `string` from the user

The methods in your `Input` class must contain the logic to read the value in, convert it to the appropriate type, and return it to the user. A `main` method has been provided, which utilizes this class in the `Application` class.

Once you have implemented this class, run `Application` to test your code.

All code must be contained inside your `Input` class. You should not modify the `UserIO` interface or the `Application` class.

_**Hint**_: You'll need to use `Scanner` in your `Input` class.

### Challenge

If a user is asked to enter an integer value and instead inputs a string, Java will throw an `Exception`. This simply means that something went wrong and that Java generated some kind of error because of it.

Create a new class called `BetterInput`, which implements the `UserIO` interface but also handles exceptions thrown because of bad user input. When bad input is received, your methods should continue to prompt users for input until valid input is received. You will need to use a `try...catch` block as well as `recursion` to most effectively accomplish this.

Once completed, change `userInput` to instantiate `BetterInput` instead of `Input`, and run `Application` to test your code.

Read about [try...catch blocks](https://www.w3schools.com/java/java_try_catch.asp) and [recursion](https://www.java-samples.com/showtutorial.php?tutorialid=151) to help guide you through this.

_**Hint**_: If an error occurs, the method should call itself again with the same prompt as before.

---
© 2019 Trilogy Education Services