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
1. Run Test and it will send results to Pact Broker
