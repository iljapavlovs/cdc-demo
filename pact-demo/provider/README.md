### !!! FIRST - On Consumer Side
1. Start Pact broker
```
docker-compose up
```

1. Run Tests (Will automatically publish reports to Pack Broker if pact.verifier.publishResults = true)
```
./gradlew clean test
```

2. Can I Delpoy?
```
docker run --rm --network host \
  	-e PACT_BROKER_BASE_URL=http://localhost:9292 \
  	pactfoundation/pact-cli:latest \
  	broker can-i-deploy \
  	--pacticipant userservice \
  	--latest
```

### On Provider side
1. Run Test
  * Pact will automatically download the pact (contract in json format)
  * When test is executed, Pact will send results to Pact Broker
 
