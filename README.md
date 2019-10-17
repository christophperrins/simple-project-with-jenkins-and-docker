# Demo project
This project is to show you how you can build a project in an agile manner

# Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisite
You will need to download and install the following pieces of software:
* [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - version 8 is recommended
* [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)
* [Docker](https://docs.docker.com/install/)
* [Docker-compose](https://docs.docker.com/compose/install/)

## Installation
To run the application you will need to run the following lines of code
```sh
git clone https://github.com/christophperrins/simple-project
cd simple-project
mvn package -DskipTests
docker-compose build
docker-compose up -d
```

## Running the Tests
To run JUnit tests on the controller and service classes:
```sh
mvn test -Dtest=ControllerAndServiceSuite
```

To run integration tests run:
```sh
mvn test -Dtest=IntegrationSuite
```

To run end-to-end tests with selenium:
```sh
mvn test -Dtest=SeleniumSuite
``` 

## Built with
* [Apache Http server](https://httpd.apache.org/)
* Docker Containers
* [SpringBoot](https://spring.io/projects/spring-boot)

## Versioning
We use SemVer for versioning. For the versions available, see the tags on this repository.

## Authors
* Chris Perrins - Project lead

## License
This project is licensed under the GPL-v3 License - see the LICENSE file for details

# Notes (DO NOT INCLUDE in your projects)
## Devops
### Docker-compose.yml
Docker is a containerisation tool. I've thrown this in as it will be useful for you to throw your applications into a container, so that you can easily update the images, which will inturn update the containers.

The yaml file looks like the following

``` yaml
version: '3'
services:
  client:
    build: ./client/
    ports:
      - "80:80"
  server:
    build: ./server/
    ports:
      - "8081:8081"
```

It is doing the following, creating two services - one is called client and one is called server (these are names you give it, they have no special meaning here). 

First - it builds two images, one from the client folder and one from the server folder.
Second - it port forwards any requests the machine to the port in the container


How does it know what to build? This is where the Dockerfile comes in. Lets have a look at the server/Dockerfile
``` Dockerfile
FROM java:8-jdk-alpine

COPY ./target/notes-0.0.1.jar /usr/app/

ENTRYPOINT ["java","-jar","usr/app/notes-0.0.1.jar"]
```

FROM - this is the image we are going to be putting out items into. A container is an instance of an image. Containers are similar to VM's but are much more lightweight.
COPY - this will copy the jar file on the machine, and put it into the /usr/app/ folder within the container. Previously to this it is important to run mvn package or mvn install so that the jar is available in the target folder.
ENTRYPOINT - similar to a command that gets run when an the container is run.

Looking at the client dockerfile:
``` Dockerfile
FROM httpd:2.4
COPY ./public-html/* /usr/local/apache2/htdocs/
```

httpd:2.4 is the code given the the apache server. This way you don't have to muddle about with permissions on your machine, everything is containerised.


### Docker images
To build the images from these two Dockerfiles in one command, place the cwd in the base folder and run:
> docker-compose build

Next it is possible to see the images with:
> docker image ls

To run the made images, here it is possible with:
> docker-compose up -d

You can check if the containers are running with:
> docker container ls

---

### Jenkins pipeline
Jenkins pipelines offer slightly more flexibility than freestyle projects - they also allow for multibranch pipelines so that pull requests can be automatically tested.

You can configure it to run a secret script everytime, or place the Jenkinsfile in your repository.

The Jenkinsfile tests, stages and deploys your application.

---

## Client
This was done quite quickly so you might expect to see a lot of bad practice (do not replicate the bad practice - this was a quick thing to show an example).

Full CRUD achieved. 

---

## Server

### com.qa.controller
This is where the endpoints are located

### com.qa.persistence
The persistence region is broken down into two:

#### com.qa.persistence.model
These are where the models for the database lie.

#### com.qa.persitence.repository
These are interfaces which extend from JpaRepository.

Feeling a bit weird that its a respository? And later we @Autowired that repository - almost like we are creating an instance of a interface which is just not possible? Well this is due to Dependency Injection and it is all handled by springboot. It searches for something which can act as a concretion for the interface we have created - and injects it in its place. The methods to get, read, update and delete are all automatically generated from springboot. 

Can we include anything in this interface? Well yes but really only methods springboot will know how to create a method for it.
In my case we could have a method:
- public Note FindByText(String text);
And the noteRepository would be able to use that method.

Table 2.3 in the following link is quite useful: https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html

### com.qa.service
These are where our services exist - essentially where the logic takes place

### com.qa.dto
Dto stands for Data transfer object.

We should really only be interacting with the "entity" when we want to change something in the database. 

Why? Well lets look at the following:
@Entity
public class user {
    @Id
    private String username;
    private String password; 
}

If I were to return a list of users who have starred this repository I might return something like this List<User>. 
Whats going to be in that list? Well not only all the usernames of the people who have liked my page which I want to display - but also all the passwords of those users... Not realistically something we would want to be doing.

Theres many other reasons to use DTO's.

When you manipulate an entity it will try to persist those changes through to the database. However if its not part of any transaction - you might get a whole swarm of errors such as detachedEntity etc. So it is actually easier (but longer) to just turn the entities into objects and manipulate the objects instead.

On much bigger projects - you would find an automatic "mapper" which maps between the Object being received/sent (the DTO) and the entity being persisted. 