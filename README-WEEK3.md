#### Where and why you have used a @OneToMany annotation
The ***@OneToMany*** annotation is used in entities "Car" and "Member" for a list of the "Reservation" entity.
This is because a car and member can have 'many' reservations, but a reservation can only have 'one' car and member.

#### Where and why you have used a @ManyToOne annotation
The ***@ManyToOne*** annotation is used in the entity "Reservation" for an instance for both "Car" and "Member".
This is like the @ManyToOne annotation, just opposite. A reservation can only have one instance of car and member.

#### The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
- **CascadeType** is used when we perform an action on a target entity, the same action will be applied to the associated entity.
- **FetchType** defines when Hibernate gets the related entities from the database.
  - *FetchType.EAGER* fetch the data, so you will have it when you need it.
  - *FetchType.LAZY* fetch the data when you need it.
- **mappedBy** is often used in correlation with the attributed @OneToMany or @ManyToOne etc. It creates an association between 2 entities, like a join table.

#### How/where you have (if done) added user defined queries to you repositories
I have added my user defined queries in my repository interfaces. 
Naming them is pretty straight forward and Java tend to give some help so that it understands the queries too.

#### a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
Just like when I needed to connect to my local database, I set up my **application.properties** file with the following -

    spring.datasource.url=${JDBC_DATABASE_URL}
    spring.datasource.username=${JDBC_USERNAME}
    spring.datasource.password=${JDBC_PASSWORD}

After that, I put in the needed variables by editing the environment variables of **application.properties**,
making the information about my database, in a way, encrypted. Example below
****JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/(name of schema);JDBC_PASSWORD=(password);JDBC_USERNAME=(username)****

#### a few words about where you have used inheritance in your project, and how it's reflected in your database
In my member entity, It's inheriting from my security class **UserWithRoles**. Before this it used **AdminDetails**
which was the previous security in my code. The entities "Car" and "Reservation" still uses AdminDetails,
as these do not need the same kind of security to them. My database reacts by making tables or table data that makes a 
correlation between the entity classes. This is done with primary keys and foreign keys.

#### What are the pros & cons of using the Single Table Strategy for inheritance?
The pros of doing this can be things like simplicity. It's easy to manage and understand a single table.
Performance can be a pro too, as there will be less querying data processed. Reading only one table takes less memory and therefore is faster.

#### how are passwords stored in the database with the changes suggested in part-6 of the exercise
The passwords are no longer stored as a normal String with the password in plain text.
Doing that can make it way to easy for hackers and others to access any account.
Now after the changes in the exercise it is stored as an encrypted key only readable by a computer.
This is done by spring-security in spring framework. It's called as an interface ***PasswordEncoder***. See below -

    public void setPassword(String pw){
    this.password = passwordEncoder.encode(pw);
    }
