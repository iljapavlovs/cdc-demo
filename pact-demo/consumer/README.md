1. Start Pact Broker
```
docker-compose up
```

2. Run Tests
```
./gradlew clean test
```

3. Publish Pact contracts to Pact Broker
```
./gradlew pactPublish
```

4. Can I Delpoy?
```
docker run --rm --network host \
  	-e PACT_BROKER_BASE_URL=http://localhost:9292 \
  	pactfoundation/pact-cli:latest \
  	broker can-i-deploy \
  	--pacticipant userclient \
  	--latest
```
