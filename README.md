### Consumer

* Junit 5 - https://docs.pact.io/implementation_guides/jvm/consumer/junit5
* Java8 (Lambdas) - https://docs.pact.io/implementation_guides/jvm/consumer/java8

*  * https://reflectoring.io/consumer-driven-contract-feign-pact/
   * https://docs.pact.io/implementation_guides/jvm/consumer/junit5
   * https://github.com/thombergs/code-examples/tree/master/pact/pact-feign-consumer
   * https://github.com/vinogradoff/pact101/blob/master/consumer/src/test/java/PactConsumerWeatherTest.java
   * https://github.com/steam0/pact-consumer/blob/master/src/test/java/com/example/pact/consumer/ProviderPactTests.java
   * https://github.com/steam0/pact-guide
   * https://medium.com/@liran.tal/a-comprehensive-guide-to-contract-testing-apis-in-a-service-oriented-architecture-5695ccf9ac5a
   
   
*   The consumer drives the implementation of the interface by describing their expectations. The provider has to make sure that they fulfil all expectations and they're done. No gold-plating, no YAGNI and stuff.

* The provider test has to be implemented by the people providing the weather API. We're consuming a public API provided by darksky.net. In theory the darksky team would implement the provider test on their end to check that they're not breaking the contract between their application and our service.
  
  Obviously they don't care about our meager sample application and won't implement a CDC test for us. That's the big difference between a public-facing API and an organisation adopting microservices. Public-facing APIs can't consider every single consumer out there or they'd become unable to move forward. Within your own organisation, you can — and should. Your app will most likely serve a handful, maybe a couple dozen of consumers max. You'll be fine writing provider tests for these interfaces in order to keep a stable system.
  
  
 *  A request / response pair like this is called an interaction.
   


* Publish pacts from Consumer
```
./gradlew pactPublish
```
 