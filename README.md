# Assuredly RESTful #

A collection of Test Framework examples using:

* Maven
* Spring 5.0.0.RC3
* Spring Boot Test Starter (2.0.0.M3)
* Rest Assured
* junit 4.12
* Hamcrest Matchers
* SLF4J Loggers
* Logback Logging Configuration
* JFairy Faker

## The Story so far ##

A small though growing collection of common approaches while developing application tests.

We have a ```public interface``` annotated with ```@FreeBuilder``` (after having setup IntelliJ )
annotation processors to generate, Person objects which are used under test.

We have a Person.FakeBuilder which uses jfairy to generate test subjects within prescribed conditions.

There is also, Ace Ventura, another Concrete example of a DAO/DTO with a different set of
preconditions which show-cases ```Optional<String>``` and FreeBuilders functional methods for Optionals.

## Test Execution Listener ##

In our simple example so far we're using the ```TestExecutionListener``` in combination with
our ```LoggerFactory``` provided ```Logger``` to ease debugging our test cases.

## Logger Output ##

The below output is a cominbation of our ```LifeCycleLoggerListener``` which

* Logs before test method
* After test method execution
* After test method
 
Also our ```PersonTest``` assertion logger leverages ```@FreeBuilders``` toString()
overloaded method so we can easily see the values of the object under assertion.

```
.13:28:20.641 [main] INFO  u.c.t.LifeCycleLoggerListener - Before test method testImposterVentura 
.13:28:20.641 [main] INFO  u.c.t.data.PersonTest - Asserting: partial FakeVentura{firstName=Bob, lastName=Ventura, gender=Male, dateOfBirth=Sun Feb 04 13:28:20 GMT 2001, job=Painter and Decorator} 
.13:28:20.641 [main] INFO  u.c.t.LifeCycleLoggerListener - After test execution uk.co.trycatchfinallysoftware.data.PersonTest@42aae04d 
.13:28:20.641 [main] INFO  u.c.t.LifeCycleLoggerListener - After test method testImposterVentura 
```