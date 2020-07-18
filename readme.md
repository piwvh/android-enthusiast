# Android Enthusiast

This is an experimental project created as part of an interview process. The goal of this application is to gather android-related
questions from [StackOverflow](https://stackoverflow.com) website.

### Technology Stack
- Java 11 / Maven
- Spring Boot 2
- HTML 5 / CSS 3 (SCSS) / JavaScript (jQuery) 
- Docker / Docker-Compose

### Run
The application is accessible using this url: [http://88.99.37.77:8585](http://88.99.37.77:8585).

However, if you wish to install it on your local machine, there are multiple ways for this purpose:

- This application can be run using Docker. If you have Docker and Docker-Compose installed, please go to
`cd src/main/docker` directory and simply run: `docker-compose up`. After downloading required images, 
you should be able to access the website using [http://localhost:8585](http://localhost:8585) url.

- If you have Java 11 (or later) and Maven installed, please run `mvn clean spring-boot:run` in the root
 directory to start the application.
 
 
 ### Arbitrary Feature
 I was asked to provide an arbitrary feature as part of the interview task. I implemented an advanced search functionality
 to fulfil this requirement. This search feature is able to fetch and filter StackOverflow questions based multiple criteria,
 including full text search, creation date and subject (tags). The results can be sorted by relevance, creation date, votes
 and activity, based on your ideal order (ascending or descending).
 
 Also, I made the website responsive and mobile friendly :)
 
 