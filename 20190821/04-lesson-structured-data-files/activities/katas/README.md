# Katas

## Structure

- Add the code necessary to the `starter-code` project `U1M3L4Katas`.
- No tests provided for this module.

## Requirements

Using the provided `Car` class and code snippet below, complete the katas listed.

```java
List<Car> carList = new ArrayList<>();

carList.add(new Car(2012, "Toyota", "Camry", "Blue"));
carList.add(new Car(2001, "Honda", "Civic", "Silver"));
carList.add(new Car(2009, "Jeep", "Wrangler", "Rust"));
carList.add(new Car(2018, "Tesla", "Roadster", "Black"));
carList.add(new Car(1964, "Ford", "Mustang", "Red"));
```

1. Add OpenCSV to your POM.xml file as a dependency. Create a Java file called ```CSVWriteAndRead``` and print the contents of ```carList``` to a CSV using the OpenCSV library. Once you've written the file, read and print out the contents, again using the OpenCSV library.
1. Add Jackson Databind to your POM.xml file as a dependency. Create a Java file called ```JSONWriteAndRead``` and print the contents of ```carList``` to a JSON file. Once you've written the file, read and print out the contents.
1. Add Jackson Data Format XML to your POM.xml file as a dependency. Create a Java file called ```XMLWriteAndRead``` and print the contents of ```carList``` to an XML file. Once you've written the file, read and print out the contents.

**Computers**
1. Open ```computers.csv``` and look at the data within.  
1. Create an appropriate Java object based on the contents of the file.
1. Create a Java file called ```ComputerReader```.
1. Use OpenCSV to read the data from the file and store it in a list. 
1. Print the contents of the list.

**Televisions**
1. Open ```televisions.json``` and look at the data within.  
1. Create an appropriate Java object based on the contents of the file.
1. Create a Java file called ```TelevisionReader```.
1. Use Jackson to read the data from the file and store it in a list. 
1. Print the contents of the list.

**Phones**
1. Open `phones.xml` and look at the data within. 
1. Create an appropriate Java object based on the contents of the file.
1. Create a Java file called ```PhoneReader```.
1. Use Jackson to read the data from the file and store it in a list. 
1. Print the contents of the list.

---
© 2019 Trilogy Education Services