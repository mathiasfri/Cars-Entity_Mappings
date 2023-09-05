#### What are the benefits of using a RESTful API
Der er massere fordele ved at bruge RESTful API. Blandt dette er det simpelt og nemt at bruge.
Hver API request er uafhængig og serveren har ikke behov for at gemme noget data mellem API requests, hvilket mindsker serverens workload.
RESTful API har også godt sikkerhed. Det gør brug af standard HTTP security mekanismer, hvilket er godt til både enkryptering og godkendelse.
REST er også nemt at teste. Dette hentydes både til Unit Testing, samt testing af HTTP metoderne ved brug af fx. Postman.
Udover det er der mange fordele. Kort og godt er RESTful API fleksibelt, sikkert, nemt og kompitabelt.

#### What is JSON, and why does JSON fit so well with REST?
JSON er en dataformat. Data i en JSON fil bliver vist som tekstbaseret. Det gør det både nemt for en computer og et menneske at læse.
Måden JSON er skrevet passer perfekt til RESTful API, da det er struktureret med key-value par, lister og indlejrede objekter.

#### How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
Jeg har oprettet 2 DTO klasser til mine 2 entities; Request og Response.
Request opretter en tom instans af sin tilhørende Entity, som så kan redigeres.
Response returnere et specifikt instans af sin tilhørende entity, som også sorteres ud fra en boolean.
Jeg gør brug af request i CRUD endpoints som ***@PutMapping***, som netop er til når der skal redigeres på en eksisterende entity.
Udover det bliver det også brugt til når metoderne skal udfylde en tom instans af sin entity ved brug af ***@PathVariable***
Response bliver brugt når der der bare skal tages fat på en eksisterende entity og fremvises eller tilføjes.

#### What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints
Vi gør dette hovedsageligt for sikkerhed. Ved at separere dem splitter vi informationen og gør det sværere 
for uvedkommende at få adgang til for meget data. Det giver også et bedre overblik for koderen.

#### Explain shortly the concept mocking in relation to software testing
Mock testing er når man tester uden at tage fat i sin rigtige dependencies/data. 
Man laver såkaldt "mock objekter", som simulerer opførslen for reelle objekter til testing.

#### How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
Jeg gjorde brug af H2, hvilket er en indlejret database. 
I min test klasse tog jeg fat i min service klasse, som tager brug af mit repository og dermed opretter en lokal database.
Jeg opsætter mock data, samt disse 2 klasse i en ***@BeforeEach*** annotation. Dette kan ses nedenfor -
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
No response yet






