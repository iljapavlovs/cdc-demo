### !!! FIRST - On Consumer Side
1. Start Pact broker
```
docker-compose up
```

1. Run Tests
```
./gradlew clean test
```

2. Publish Pact when tests are run 
```
./gradlew pactPublish
```


### On Provider side
1. Run Test
  * Pact will automatically download the pact (contract in json format)
  * When test is executed, Pact will send results to Pact Broker
 
