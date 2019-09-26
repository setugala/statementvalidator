# Customer Statement Processor

Bank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Technologies

* Springboot 2.1.8
* Java 8

### Prerequisites

* JDK 1.8 or higher
* git bash
* maven
* curl / postman

### Installing

- Open Git Bash. 
[image] 

- Go to the folder where you want to checkout the code.
```
cd git/
```

- Clone the code from git into the folder
```
git clone https://p-bitbucket.nl.eu.abnamro.com:7999/scm/pis/statementvalidator.git
```

- Go into the folder 'statementvalidator'
```
cd statementvalidator
```

- Start the application on local using maven
```
mvn spring-boot:run
```

- Call the API using curl
```
curl -X POST http://localhost:9080/customer/validate -F "file=@{path_to_file}/records.csv"
```

- Call the API using Postman
[image]
