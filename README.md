### Inkdown REST API 

This is a RESTful Backend API for <i>Inkdown - `React.js` note take app</i> 
which allows us to write down, archive, and trash notes.

### General Objective 

Inkdown REST API, is a `Spring Boot` Backend Application that serves to handle resources related to `UserDetails` 
for common user attributes such as the following: 1) `username`; 2) `password` both of which are used to
**Authenticate and Authorize** a User entity for them to access the front-end application. Moreover, this API 
has **secure endpoints** that can only be accessed by an authorized user. 

A `User` is an entity which has `UserDetails`. This can be done by doing the following: 

```
public class User implements UserDetails {
  // We can now customize our User entity to have common user attributes:
  // such as, firstname, lastname, email, birthday...etc... 
}
```

By implementing a custom `User` class through `UserDetails` we can easily configure it for account management such as 
account expiration, account locking, and credentials expiration. For further information about `UserDetails` class, 
kindly see its .class source file.

Other than User Authentication and Authorization with `Spring Security 6` and `JWTs`. Inkdown REST API handles 
`Notes` related resources which contain simple attributes such as note title, note body, and note author. 
A Note can also be moved to archive or trash where the user has an option to restore it, or permanently delete it 
respectively. 

Lastly all `Notes` resources are stored at `/notes`, while archived notes are stored at `/notes-archive`, and lastly all
trashed notes are stored at `/notes-trash` endpoint.

### Personal Objectives

I want to practice more with `CrudRepository` class to handle CRUD operations. And, I want to know how to configure and 
secure APIs with `Spring Security 6` and `JWTs`.

This YouTube tutorial is very, very helpful! <a href="https://youtu.be/KxqlJblhzfI?si=1wd6ucqdoT_-UWmD">Spring Boot 3 + Spring Security 6 - JWT Authentication and Authorisation [NEW] [2023]</a>
I was able to follow this dude, and he's super cool! I added some document comments on `src` files to further understand what each class is doing.

Please, do take note that I only authored the documentation comments for `auth`, `config`, and `service`, but not the code. I also applied some revisions with some snippets, 
however I am not taking claim that I engineered the code by myself.

### Configuration 

`server.tomcat.threads.max=200` This is set in `application.properties` to handle 200 requests simultaneously. 

> Yes, Spring boot can handle simultaneously requests! If your servlet container is tomcat under the hood, it can handle 200 simultaneous requests. <br>
> Reference: https://stackoverflow.com/questions/46893237/can-spring-boot-application-handle-multiple-requests-simultaneously 

### Technologies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Windows Terminal](https://img.shields.io/badge/Windows%20Terminal-%234D4D4D.svg?style=for-the-badge&logo=windows-terminal&logoColor=white)

