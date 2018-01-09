# DynamicForm

DynamicForm is a POC for generating dynamic forms.

  - Generate simple form 
  - ...

You can also:
  - Generates simple forms through the database
  
### Tech
DynamicForm uses a number of open source projects to work properly:

* [Java] - Backend
* [Spring Boot] - Framework
* [Docker] - Container (Pending)

### Installation

Requires:
> [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) v8+ .
> [Maven](https://maven.apache.org/download.cgi) 
> [Oracle Database](http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html)

Install the ojdbc dependencie in your repository:

```sh
$ mvn install:install-file -Dfile={YOUR_PATH/ojdbc7.jar} -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar
```

### Docker (Pending)
DynamicForm is very easy to install and deploy in a Docker container.

#### Kubernetes (Pending)

Pending

