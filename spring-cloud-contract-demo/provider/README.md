### Process

1. Consumer creates a contract in Groovy/YML/Kotlin/Java file and provides it to Provider in `src/test/resources/contracts` folder
2. Add SCC dependencies to your provider codebase

```
id("org.springframework.cloud.contract") //Gradle plugin which creates a Stub Runner
testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:<version>")
```

3. Add Base Test class for the test setup of Contract Test

4. Add Gradle config 
```
contracts {
    packageWithBaseClasses.set("io.iljapavlovs.cdc.springcloudcontractdemo.contractbase.provider")
    setTestFramework(org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5)
    failOnInProgress.set(false)
}
```
5. _OPTIONAL_ - generate tests in _build/generated-test-sources_ folder
```
./gradlew clean generateContractTests
```


5. _OPTIONAL_ - If you do not want to use Stub Runner, you need to copy the contracts stored in src/test/resources/contracts and generate WireMock JSON stubs by using the following command:
```
./gradlew generateClientStubs
```


5. _OPTIONAL_ - pushing stubs to the Maven repository
```
./gradlew pushStubsToScm
```


6. Build project -  generate, run tests and create a Stub Runner based on a contract
```
./gradlew clean build 
```
7. Publish Stub Runner to ([Maven Publish Plugin](https://docs.gradle.org/current/userguide/publishing_maven.html))
    * Local Maven Repo
        ```
        ./gradlew publishToMavenLocal
        ```
    * Remote Maven Repo
      ```
      ./gradlew publish
      ```