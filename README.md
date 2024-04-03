### Check out the frontend!

<a href="https://github.com/Ciezo/note-taking-react-app-client">Inkdown (React.js)</a>

### Backend Development and Mental Health

> Feeling burnout in backend development, especially when working with REST APIs using Spring Boot and Java, is completely normal. 
> Developing software can be mentally demanding, and backend work often involves intricate problem-solving, debugging, and managing complex systems.

<u>Here are some reasons why burnout might occur and some strategies to cope with it:</u>

High Expectations and Deadlines:

- Backend development often involves meeting tight deadlines and delivering high-quality code. 
- The pressure to meet expectations can lead to burnout.
  - Solution: Set realistic goals, communicate with your team, and prioritize tasks effectively. Break down large tasks into smaller, manageable pieces.

Repetitive Tasks:
- Backend work can involve repetitive tasks, such as writing boilerplate code, handling database queries, and managing APIs.
  - Solution: Automate repetitive tasks where possible. Use code generators, templates, and tools to streamline your workflow.

Complexity and Debugging:

- Debugging complex issues can be mentally exhausting. Backend developers often deal with intricate business logic, performance bottlenecks, and integration challenges.
  - Solution: Take breaks when stuck. Collaborate with colleagues or seek help from online communities. Sometimes stepping away and returning with fresh eyes can lead to breakthroughs.

Lack of Variety:
- Focusing solely on backend work can lead to monotony. Burnout can occur when you feel stuck in a routine.
  - Solution: Explore related areas (e.g., frontend development, DevOps, or cloud services). Learning new technologies or contributing to open-source projects can provide variety.

Long Hours and Work-Life Balance:
- Backend developers may work long hours, especially during critical project phases. Balancing work and personal life becomes challenging.
  - Solution: Set boundaries. Prioritize self-care, exercise, and hobbies. Disconnect from work during non-working hours.

Continuous Learning Pressure:
- Backend technologies evolve rapidly. Keeping up with the latest trends and best practices can be overwhelming.
  - Solution: Focus on fundamentals first. Learn incrementally and avoid chasing every new framework or library. Invest time in understanding core concepts.

Isolation:
- Backend work can be solitary, especially when debugging or writing code. Isolation can contribute to burnout.
  - Solution: Engage with your team, attend meetups, and participate in online communities. Collaboration and social interaction can alleviate feelings of isolation.
  
  - Remember that burnout is common in any demanding profession. It’s essential to recognize the signs early and take proactive steps to prevent or manage it. Seek support from colleagues, mentors, or mental health professionals if needed.
### Inkdown REST API 

This is a RESTful Backend API for <i>Inkdown - `React.js` note take app</i> 
which allows us to write down, archive, and trash notes.

### Integration to React.js (frontend)

To do integrate this backend application to frontend. It is necessary to allow _CORS configuration_
It is important to note that when applications are served, it is usually configured to only allow requests and response 
within its own domain. 

This means that, say, an app serving at localhost:3000 can only accept requests and responses from 
`http://localhost:3000/*/`, where `*` is any path. And so, this also works with other enterprise domains 
or domain name like, https://www.examplesecuredomain.com/ or https://www.examplesecuredomain.com:8000/

Take note that the said example domains are not allowing to accept requests or responses outside its 
namespace. To get around this, we need to allow `CORS` or _Cross-Origin-Resource Sharing_.

In layman terms, this backend is an application, my other application, Inkdown (React.js) is also an application.
And, to bridge or integrate them both is to use CORS using `@CrossOrigin` annotation.

> Cross-origin resource sharing (CORS) is a mechanism for integrating applications. 
> CORS defines a way for client web applications that are loaded in one domain to interact with resources in a different domain
> Reference: 
> https://aws.amazon.com/what-is/cross-origin-resource-sharing/#:~:text=your%20CORS%20requirements%3F-,What%20is%20Cross%2DOrigin%20Resource%20Sharing%3F,resources%20in%20a%20different%20domain.

> Before CORS became standarized there was no way to call an API endpoint under different domain for security reasons. 
> This was (and to some degree still is) blocked by the Same-Origin Policy.
> Reference: 
> https://medium.com/@baphemot/understanding-cors-18ad6b478e2b

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

