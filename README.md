# Cars (Entity Mappings)

#### The idea with, and reasons for why to use, a ORM-mapper
ORM-wrapper is good for direct communications between code and database.
I use this to not have to access my database from a database software program 
and instead be able to edit it straight from my code.

#### The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected
All 3 are concepts used in Java and other object-oriented programming languages and for forming database operations.
They are used to set up datatypes between Java and SQL. With these you can set up your SQL columns straight from your code using your Java attributes.

#### How to create simple Java entities and map them to a database via the Spring Data API
All entities are set up using the annotation JPA entity ***@Entity*** from Spring Data API.

#### How to control the mapping between individual fields in an Entity class and their matching columns in the database
The individual fields are mapped with the JPA annotation ***@Column(name = "")***.
This will provide the needed information to the database to set up the given column.

#### How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)
I define IDs using the JPA annotation ***@Id***.
I can give this annotation to any given attribute/column;
however if I want to auto generate IDs like done in "Car" entity,
I can use the JPA annotation ***@GeneratedValue(strategy = GenerationType.IDENTITY)***.
This will provide an ID taken the last used ID into account + 1.

#### How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern
How you did that in your code

#### How to write simple "integration" tests, using H2 as a mock-database instead of MySQL
How you did that in your code

#### How to add (dev) connection details for you local MySQL database
How you did that