
# Spring Cloud Contracts
### Resources:
* Gradle - https://docs.spring.io/spring-cloud-contract/docs/2.2.4.RELEASE/reference/html/gradle-project.html#gradle-add-stubs
* Contract scheme (YML, Kotlin, Java, Groovy), Messaging - https://docs.spring.io/spring-cloud-contract/docs/2.2.4.RELEASE/reference/html/project-features.html#features-http
* Provider Contract Testing with Stubs in Nexus or Artifactory (Full Flow) - https://cloud-samples.spring.io/spring-cloud-contract-samples/tutorials/contracts_on_the_producer_side.html#_consumer_flow_1

* Provider Contract Testing with Stubs in Git - https://docs.spring.io/spring-cloud-contract/docs/2.2.4.RELEASE/reference/html/using.html#flows-provider-git

* Getting Started tutorial - https://docs.spring.io/spring-cloud-contract/docs/2.2.4.RELEASE/reference/html/getting-started.html#getting-started-introducing-spring-cloud-contract
* FAQ , useful info - https://cloud.spring.io/spring-cloud-static/spring-cloud-contract/2.0.1.RELEASE/multi/multi__spring_cloud_contract_faq.html

* SCC - Rabbit MQ - https://novotnyr.github.io/scrolls/enforcing-spring-cloud-contracts-over-amqp/
 * Junit 5 - https://docs.pact.io/implementation_guides/jvm/consumer/junit5
 * Java8 (Lambdas) - https://docs.pact.io/implementation_guides/jvm/consumer/java8
 
 *  * https://reflectoring.io/consumer-driven-contract-feign-pact/
    * https://docs.pact.io/implementation_guides/jvm/consumer/junit5
    * https://github.com/thombergs/code-examples/tree/master/pact/pact-feign-consumer
    * https://github.com/vinogradoff/pact101/blob/master/consumer/src/test/java/PactConsumerWeatherTest.java
    * https://github.com/steam0/pact-consumer/blob/master/src/test/java/com/example/pact/consumer/ProviderPactTests.java
    * https://github.com/steam0/pact-guide
    * https://medium.com/@liran.tal/a-comprehensive-guide-to-contract-testing-apis-in-a-service-oriented-architecture-5695ccf9ac5a
* Matchers examples:    
 * YML - https://github.com/spring-cloud/spring-cloud-contract/tree/2.1.x/spring-cloud-contract-verifier/src/test/resources/yml
    * yml schema - https://cloud.spring.io/spring-cloud-contract/reference/html/yml-schema.html
    * https://cloud.spring.io/spring-cloud-contract/reference/html/project-features.html#yaml
 * ALL - https://cloud.spring.io/spring-cloud-contract/reference/html/project-features.html#contract-dsl-name
    * [Dynamic Properties](https://cloud.spring.io/spring-cloud-contract/reference/html/project-features.html#contract-dsl-dynamic-properties)

* Messaging
    *  https://developer.epages.com/blog/tech-stories/how-to-test-eventbased-services-using-contracts/
        * https://github.com/mduesterhoeft/testing-asynchonous-interactions        
        
* [Great Example with CI(Teamcity) and Artifactory](http://antkorwin.com/cloud/spring_cloud_contract_junit5.html) 
    * https://github.com/antkorwin/cdc-spring      
    
* [Great Example with CI (Jenkins) and Artifactory](https://piotrminkowski.com/2018/07/04/continuous-integration-with-jenkins-artifactory-and-spring-cloud-contract/)    
    * https://github.com/piomin/sample-spring-cloud-contract-ci 

* Spring Cloud Contract FAQ
 *  [What is this value(consumer(), producer()) ?](https://cloud.spring.io/spring-cloud-static/spring-cloud-contract/2.0.1.RELEASE/multi/multi__spring_cloud_contract_faq.html#_what_is_this_value_consumer_producer)
 * [How to do Stubs versioning?](https://cloud.spring.io/spring-cloud-static/spring-cloud-contract/2.0.1.RELEASE/multi/multi__spring_cloud_contract_faq.html#_how_to_do_stubs_versioning)
 * [Can I use the Pact Broker?](https://cloud.spring.io/spring-cloud-static/spring-cloud-contract/2.0.1.RELEASE/multi/multi__spring_cloud_contract_faq.html#_can_i_use_the_pact_broker)
    
    
# Pact

* CI 
    * https://solidstudio.io/blog/consumer-driven-contract-ci-cd.html    
    * https://blog.testproject.io/2020/06/09/integrating-consumer-contract-testing-in-build-pipelines/
