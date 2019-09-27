# Customer Statement Processor

Bank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Technologies

* Spring Boot 2.1.8.RELEASE
* Java 8

### Prerequisites

* JDK 1.8 or higher
* [git bash](https://git-scm.com/downloads)
* [maven](https://maven.apache.org/download.cgi)
* [curl](https://curl.haxx.se/dlwiz/?type=bin)

### Installing

##### 1) Open Git Bash. Go to the folder where you want to checkout the code.
```
cd git/
```

##### 2) Clone the code from git into the folder
```
git clone https://github.com/setugala/statementvalidator.git
```

##### 3) Go into the folder 'statementvalidator'
```
cd statementvalidator
```

##### 4a) Start the application on local using maven
```
mvn spring-boot:run
```

#### 4b) Start the application on local using docker
```
docker image build -t customer/statementvalidator .
docker run -it customer/statementvalidator -p 9080:9080
```

##### 5) Call the API using curl
```
curl -X POST http://localhost:9080/customer/validate -F "file=@{path_to_file}/records.csv"
curl -X POST http://localhost:9080/customer/validate -F "file=@{path_to_file}/records.xml"
```