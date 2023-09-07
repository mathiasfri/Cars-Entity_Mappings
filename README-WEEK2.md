#### What are the benefits of using a RESTful API
There are lots of benefits in using RESTful API. Overall reasons are that it's simple and easy to use.
Each API request is independent and the server does not have the needs to save any data between API requests, which lessens the workload.
RESTful API also has good security. It uses standard HTTP security mechanisms, which are good for both encryption and confirmations.
REST is easy to test too. This is meaning Unit Testing, and testing of the HTTP requests using software like fx. Postman.
Besides that there are many benefits. In short - RESTful API is flexible, safe, easy and compatible.

#### What is JSON, and why does JSON fit so well with REST?
JSON is a data format. Data in a JSON file is shown as text-based. This makes it easy for both a computer and a human to read.
The way JSON is written fits perfectly with RESTful API, as it is structured with key-value pairs, lists and embedded objects.

#### How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
I have created 2 DTO classes for my 2 entities; Request and Response.
Request creates an empty instance of its belonging entity, so it can be used and edited.
Response returns a specific instance of its belonging entity, which is sorted with a boolean.
I use the request class in CRUD endpoints like ***@PutMapping***, which is for when an edit is needed to be done on an existing entity.
Besides that it is also used when certain methods needs to fill an empty instance of its entity using ***@PathVariable***.
Response is also used when an existing entity needs to be found and shown or a new one needs to be added.

#### What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
I do this mainly for security. By separating them I split the information and makes it harder for unwanted people to
see sensitive information. It also makes it easier for the coder to get an overview of the code.

#### Explain shortly the concept mocking in relation to software testing
Mock testing is when you test without having to use your actual dependencies/data.
You make a so-called "mock object", that simulated the behavior for the actual objects, just for testing.

#### How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
I used H2, which is a virtual database ran on your PC.
In my test class i used my service class, which uses my repository and creates a local database.
I set up mock data with these 2 classes in a ***@BeforeEach*** annotation. This can be seen below -
***
    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "email1", "pw1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "email2", "pw2", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }
***

#### Explain the concept Build Server and the role Github Actions play here
A build server automates the process of building, testing and deploying software.
It plays a big role in software development workflow, like continuous integration(CI) and continuous delivery(CD).
The way Github Actions comes into play with this, is the fact that it can automate all this.
By setting up a CI/CD pipeline, Github Actions will go through this process without you needing to do anything.
It's a very helpful tool if you have several pushes in your software and need testing without having time to do it.

#### Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
Maven is used to automate build and testing phases of a software project using a CI setup.
It is integrated into the CI workflow, executing Maven commands to build and test a project and reporting on the results.
Maven makes it a lot easier to manage your dependencies, project structure and will help you ensure consistent builds.

#### Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin
Not sure how to respond to this question, as we mostly use a local database so far.
We have a virtual database (DBaaS) set up, but we haven't really used it yet, as we are still developing the program.