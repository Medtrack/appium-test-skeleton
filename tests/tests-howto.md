## Testing with JavaTestNG for Appium

I created this project in order to tests the apps using testNg with Appium.

I am developing this with IntelliJ programs: WebStorm and IntelliJ for Java.
I don't know how to integrate the TestNG in Android Studio... 

I created the Skeleton folder wih IntelliJ:
> file -> new project -> maven
 
Then I added the dependencies to the pom.xml file:

```
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>3.8.1</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>6.13.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>5.0.4</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Under the `src/test folder, create a folder called "java", and create two sub folders

```
mkdir <project root>/src/test/pages <project root>/src/test/scenarios
```


El test se puede lanzar desde línea de comandos con:

```
mvn test
```
