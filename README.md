# Demo project


# Backend

## com.qa.controller
This is where the endpoints are located

## com.qa.persistence
The persistence region is broken down into two:

### com.qa.persistence.model
These are where the models for the database lie.

### com.qa.persitence.repository
These are interfaces which extend from JpaRepository.

Feeling a bit weird that its a respository? And later we @Autowired that repository - almost like we are creating an instance of a interface which is just not possible? Well this is due to Dependency Injection and it is all handled by springboot. It searches for something which can act as a concretion for the interface we have created - and injects it in its place. The methods to get, read, update and delete are all automatically generated from springboot. 

Can we include anything in this interface? Well yes but really only methods springboot will know how to create a method for it.
In my case we could have a method:
- public Note FindByText(String text);
And the noteRepository would be able to use that method.

Table 2.3 in the following link is quite useful: https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html

## com.qa.service
These are where our services exist - essentially where the logic takes place

## com.qa.dto
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