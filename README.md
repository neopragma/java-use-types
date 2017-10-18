# Use custom types in Java

This project contains annotated examples to illustrate the point that it is useful to define custom classes to represent domain entity types. These examples are based on Java, but the same consideration applies to applications written in other programming languages. 

The existence of the Utils class is a code smell. It's a container for static methods that validate various primitive or simple types as domain entity types such as Social Security Numbers, Vehicle Identification Numbers, monetary amounts, and credit card numbers. Instead of this, domain entity types such as those ought to be defined as custom classes that take primitive types as constructor arguments and ensure the values are valid, and then instantiate immutable objects.

## 1. Social Security Number (SSN)

In many organizations, the US Social Security Number is an important domain entity, and yet it is usually defined as a simple string value. Defining SSN as a string opens the door for numerous potential errors in the application. It also increases the labor and the number of test cases necessary to provide adequate test coverage of the code. 

Information about the structure of an SSN is available from these sources:

* The SSN Numbering Scheme: https://www.ssa.gov/history/ssn/geocard.html
* Invalid or Impossible SSNs: https://www.searchbug.com/peoplefinder/invalid-social-security-numbers.aspx
* Social Security cards issued by Woolworth: https://www.ssa.gov/history/ssn/misused.html
* A free SSN lookup service: https://www.ssn-check.org/verify/
* SSN verification service (authorized users): https://www.ssa.gov/employer/ssnv.htm

To lend a note of realism to the sample code, the string implementation takes some shortcuts that are typical of developers who would be careless enough to define SSN as a simple string. It's likely they would be equally careless in other respects, and observations of code in the wild substantiates this assumption.  

* The three parts of the SSN are named prefix, infix, and suffix. This reflects a lack of care on the part of developers, who did not bother to investigate the correct names of the parts. Developers careful enough to define a custom class for SSN would likely be careful enough to learn the structure of an SSN. The correct names are used in the custom class implementation (_area number_, _group number_, and _serial number_).
* A quick lookup of Social Security Number specifications will find that area numbers in the 900 range are not valid. Further research indicates area numbers above 740 have never been issued. Therefore, the sample code for the string implementation checks for "prefixes" in the 900 range, while the custom class implementation rejects area numbers above 740.
* A careless implementation would overlook special values approved for use in advertising, and specific values that were used by mistake in advertisements and can't be issued. Therefore, the sample code for the string implementation does not consider these values. 
* Careless developers usually make up SSNs for testing, more-or-less randomly. These numbers _could_ be someone's real SSN or Taxpayer Identification Number (TIN). The custom SSN class provides a usable test SSN that will not conflict with any numbers that are issued.  

Compare the implementation of SSN as a String value in these files:

* SsnAsStringTest.java
* SsnStringClientTest.java
* SsnStringClient.java
* Utils.java

with the implementation of SSN as a custom class in these files:

* SsnTest.java
* SsnClientTest.java
* Ssn.java
* SsnClient.java
* InvalidSsnException.java

These examples illustrate the following points about using custom types:

* Even for a value that has relatively simple validation rules, defining a custom type prevents many potential errors in code that uses the type. 
* When we define a custom type that encapsulates all the validation rules for a given domain entity, we need not write test cases against every piece of client code that uses that type to guard against careless usage or data corruption in between accesses to the value. 
* We can obtain even better protection against errors by ensuring custom types like this will produce immutable objects.

## 2. Vehicle Identification Number (VIN)

VINs are often represented as simple strings in corporate applications. A VIN has even more structure and more validation rules than an SSN. The amount of code, effort, and risk that are minimized by treating a VIN as a first-class domain object is correspondingly greater. 

Information about VINs:

* ISO 3780:2009 World Manufacturer Identifiers: https://www.iso.org/standard/45844.html
* World Manufacturer Identifier summary/explanation: https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/World_Manufacturer_Identifier_(WMI)
* VIN check digit calculation: https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/Check_digit
* VIN decoding (Carfax): https://www.carfax.com/blog/vin-decoding/

The implementation that treats VIN as a string has some logical holes in it. This is meant to reflect the kind of thing we see in real-world implementations. It's so inconvenient to deal with the nuances of a VIN that people usually just touch the high points. For example, the validation method for VINs only checks the length of the string value. This is not because it's impossible to do better, but because it's generally what we see in the wild. With the value carried as a string, client code has to call a validation routine just about every time the value is used anywhere in the application. When we take the time to define a custom class for VIN, it's easier to build in more robust validation because it's all encapsulated in a single class. 

Why are developers generally careless about VINs? My guess is that they don't consider a VIN to have the same level of security risk as a Social Security Number or a credit card number. After all, a VIN is not _personally identifiable information_ (see https://www.gsa.gov/reference/gsa-privacy-program/rules-and-policies-protecting-pii-privacy-act). But that assumption overlooks the fact that vehicle theft is a major criminal business. A company that depends on VINs being correct probably has a vested interest in identifying counterfeit VINs (known as "cloned" VINs). So we should exercise due diligence when designing systems that handle VINs.

The custom VIN class implementation is not complete. It is sufficient for example purposes, but omits a few things. 

* Only a couple of WMI values are defined in enum Manufacturer.
* The Vin class doesn't deal with the structure of VIN numbers for small manufacturers.

The string implementation of VIN is in these files:

* VinAsStringTest.java
* VinStringClientTest.java
* VinStringClient.java
* Utils.java

The custom VIN class implementation is in these files:

* VinTest.java
* VinClientTest.java
* Vin.java
* VinClient.java
* InvalidVinException.java

You'll notice there's substantially more source code in the implementation that uses a custom VIN class. This is because the implementation is more robust than the String version. The advantages are that client code need not worry about the validity of any VIN values it uses. No matter how much client code the solution contains, it will not have to be cluttered with repetitive validation of VIN values, and the test suite need not check and re-check VIN number handling for any code other than the Vin class. 

# 3. Monetary amounts

One of the most sobering observations about code in the wild is the carelessness with which monetary amounts are usually treated. Most Java applications running in financial institutions carry monetary amounts as doubles. This can represent a significant vulnerability to malicious code that takes advantage of the imprecision of floating-point operations to siphon off tiny amounts of money. Applications written in other languages may also expose institutions to malicious code, unless the languages include appropriate data types for money values and the programmers use those data types.

Information:

* Why not use double or float to represent currency? (StackOverflow): https://stackoverflow.com/questions/3730019/why-not-use-double-or-float-to-represent-currency
* Limitations of ISO 4217 and JSR 354: https://www.infoq.com/articles/JSR-354-Java-Money-Currency-API
* Why Bitcoin needs an ISO currency code: https://www.coindesk.com/bitcoin-needs-iso-certified-currency-code/
* List of currency codes: https://www.iban.com/currency-codes.html

With due respect to developers, one of the reasons existing Java applications tend to treat monetary amounts carelessly is that the Java language has not included particularly good support until very recently. The usual workaround has been to define custom money and currency classes that wrap a BigDecimal value. These classes have to include custom logic to deal with rounding as well as various output format patterns, input data types, and arithmetic operations. BigDecimal is a practical workaround, and yet most developers continue to use double for monetary amounts. 

JSR 354 models the concepts of "currency" and "money" based on ISO 4217. The InfoQ article cited above offers a pretty good summary and introduction. Interfaces are in ```javax.money```. These examples use the implementation found here:

```xml
<dependency>
  <groupId>org.javamoney</groupId>
  <artifactId>moneta</artifactId>
  <version>0.9</version>
</dependency>
```

The negative examples exhibit the following commonly-observed issues:

* Use of boolean or int return codes instead of informational status objects or exceptions
* Use of double to represent monetary amounts
* Use of String to represent account number
* Use of literals 

The negative example code is in the following files:

* NaughtyBankTest.java
* NaughtyBank.java
* NaughtyBankDatabase.java

The positive examples illustrate the following techniques:

* Immutable objects whose constructors throw exceptions when arguments are not valid
* Status objects that convey useful information about the results of calls
* Use of javax.money interfaces to represent money-related concepts such as currencies and amounts
* Use of a custom AccountNumber class to represent account numbers
* Use of enums and constants as appropriate

The positive example code is in the following files:

* NiceBankTest.java
* AccountTypeTest.java
* NiceBank.java
* AccountNumber.java
* AccountStatus.java
* AccountType.java
* NiceBankDatabase.java
* TransactionStatus.java

# 4. Other common examples

There are probably enough examples here to make the point. Here are a few other entity types found in many domains that ought to be represented by custom classes/types rather than strings. If you want, you're welcome to write negative and positive sample code for these or others and I'll be happy to add them to the set of examples.

### Credit card numbers 

Many existing applications carry credit card numbers as string values, and depend on validation methods/functions to ensure bad data doesn't get into the system. A credit card number is a distinct domain concept, and there are clear rules for identifying the card type based on the number as well as for valid length and check digit calculation. 

Can you design a reasonable solution for credit card numbers that provides more robust support than a simple string value?

### Personal names 

Quite a lot of software developed in the US assumes that personal names always and everywhere follow the same structure as in the US: First Name, Middle Name, Family Name. Data structures for personal names are almost always hard-coded this way. This can create confusion for international customers and for immigrants. Forcing names to fit into the US format often results in a recorded name that does not accurately match the person's real name. It leads to difficulty locating the person's records in a system, and sometimes results in refusal of business or government services because an employee is not sure they are dealing with the right person. 

In many countries, the family name is written/spoken first, and the given name after. In others, people have a given name and a patronymic (a derivative of the father's given name), and they may or may not have a family name. In some cultural regions, it is customary to keep only the first initial of the father. In Spanish-speaking areas, people usually have a first and second given name followed by a first and second family name; but they have neither a "middle" name in the American sense nor a patronymic.

Can you design a reasonable implementation that would provide more robust support for personal names than simple strings? Remember to include internationalization support. 

### Postal addresses

There is considerable variation in national conventions for postal addresses. In the US, the convention is that a postal address starts with the most local information and expands to the most global information. That is:

```
Mr. John Smith
419 Solipsism Way
Gotham City, NY 10101-0101
USA
```

In Japan, addresses are written the other way around, such that the most global information is written first, and the address narrows down to the exact location. The John Smith address might be laid out this way:

```
USA
Gotham City, NY 10101-0101
419 Solipsism Way
Mr. John Smith
```

Apart from different structural conventions, each country has its own system of postal codes. In the US, all postal codes are numeric, while in Canada they are alphanumeric. 

Can you design a reasonable way to support this that would provide a more robust implementation than simple strings? Remember to support internationalization, as well. 

