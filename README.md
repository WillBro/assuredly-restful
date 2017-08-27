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
.13:28:20.641 [main] INFO  u.c.t.LifeCycleLoggerListener - After test execution PersonTest@42aae04d 
.13:28:20.641 [main] INFO  u.c.t.LifeCycleLoggerListener - After test method testImposterVentura 
```

## Logging REST Assured ##

Given that REST assured uses an Apache HTTP Client from Apache HC package we have setup
two rolling log files ```http-headers.log``` and ```http-wire.log``` to hopefully aid our visibility
of the HTTP traffic that our tests Request and Responses are working with.

This logging mechanism will need improve with MDC to correlate test methods to responses in
 a way which we can then create a conversation thread to better understand the messages which were provided 
 to our test scenario.
 
 ### Logback Configuration ###
 ```xml
<!-- name attribute is our appender-ref element for the logger below --> 
<appender name="http-headers" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <File>http-headers.log</File>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <FileNamePattern>http-headers.%d{yyyy-MM-dd}.log</FileNamePattern> <!-- daily rollover -->

        <maxHistory>7</maxHistory> <!-- keep 7 days' worth of history -->
    </rollingPolicy>

    <encoder>
        <Pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg %n</Pattern>
    </encoder>
</appender>
<logger name="org.apache.http.headers" level="DEBUG">
    <appender-ref ref="http-headers"/>
</logger>
```
 
 ### Sample log output ###

```
16:13:59.599 [main] DEBUG org.apache.http.wire -  << "HTTP/1.1 302 Found[\r][\n]" 
16:13:59.601 [main] DEBUG org.apache.http.wire -  << "Cache-Control: private[\r][\n]" 
16:13:59.601 [main] DEBUG org.apache.http.wire -  << "Content-Type: text/html; charset=UTF-8[\r][\n]" 
16:13:59.601 [main] DEBUG org.apache.http.wire -  << "Referrer-Policy: no-referrer[\r][\n]" 
16:13:59.601 [main] DEBUG org.apache.http.wire -  << "Location: http://www.google.co.uk/?gfe_rd=cr&ei=u-GiWcjPL9LO8gfcq6SQDQ[\r][\n]" 
16:13:59.601 [main] DEBUG org.apache.http.wire -  << "Content-Length: 261[\r][\n]" 
```