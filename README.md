## Skeleton to create Appium test

This repo contains a simple skeleton to start up Appium mobile tests with the JavaNG testing framework to run on the [Amazon Web Service Device Farm](https://aws.amazon.com/device-farm).

To generate a zip with Device Farm tests add apps to `tests/skeleton/apps` and execute:

```
mvn clean package -DskipTests=true
```

Then test should appear in `tests/skeleton/appszip-with-dependencies.zip`. 

We need to upload this zip to AWS Device Farm.

