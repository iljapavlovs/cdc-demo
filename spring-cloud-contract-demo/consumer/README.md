### Process

1. On Provider side Consumer should:
    * Create a contract and make PR to Provider repo
    * When Contract is written, SCC creates the mock (Wiremock) according to the contract, the mock (Stub Runner) is then generated as a Maven artefact and can be distributed to Consumer
2. On your (Consumer) side:
    * Add SCC dependencies to your consumer codebase
    * Add a Junit test where Provider side is invoked and setup Provider URL and Port and run the test
    * verify that client code works exactly as the contract specifies, verify response, check that DTO is successfully deserialized
    * SCC will automatically download the Stub Runner artifact at test runtime
    * You can verify Stub Runner downloaded from local or remote Maven repo
