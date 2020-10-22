1. Start Pact Broker
```
docker-compose up
```

1. Run Tests
```
./gradlew clean test
```

2. Publish Pact contracts to Pact Broker
```
./gradlew pactPublish
```