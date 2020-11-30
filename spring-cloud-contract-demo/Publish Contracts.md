## Publish Contracts in Spring Cloud Contracts

### Generate Stubs
1. generate tests in _build/generated-test-sources_ folder
```
./gradlew clean generateContractTests
```
2. generate jar library with stubs
```
./gradlew verifierStubsJar
```


### Publish to Local Maven repo 
```
./gradlew publishToMavenLocal
```

or


### Publish to Artifactory
1. Gradle config (publications are automatically created by Spring Cloud Contract library)
```
publishing {

//    SHould be managed in Jenkins
    publications {
        repositories {
            maven {
                setUrl("http://artifactory.net/artifactory/")
                credentials {
                    username = "asdad"
//                    username = System.getenv("ARCHIVA_USR")
                    password = "asdasd"
                }
            }
        }
    }
}
```

2. publish
```
./gradlew publish
```

