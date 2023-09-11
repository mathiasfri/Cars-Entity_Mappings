#### Where and why you have used a @OneToMany annotation
The ***@OneToMany*** annotation is used in entities "Car" and "Member" for a list of the "Reservation" entity.
This is because a car and member can have 'many' reservations, but a reservation can only have 'one' car and member.

#### Where and why you have used a @ManyToOne annotation
The ***@ManyToOne*** annotation is used in the entity "Reservation" for an instance for both "Car" and "Member".
This is like the @ManyToOne annotation, just opposite. A reservation can only have one instance of car and member.

#### The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many


#### How/where you have (if done) added user defined queries to you repositories
I have added my user defined queries in my repository interfaces. 
Naming them is pretty straight forward and Java tend to give some help so that it understands the queries too.

#### a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase


#### a few words about where you have used inheritance in your project, and how it's reflected in your database


#### What are the pros & cons of using the Single Table Strategy for inheritance?


#### how are passwords stored in the database with the changes suggested in part-6 of the exercise
