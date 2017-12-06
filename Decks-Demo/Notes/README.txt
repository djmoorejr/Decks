Resources used:
https://dragonflytechblog.wordpress.com/2015/05/20/restful-services-using-jersey-jetty-and-gradle/

Gretty has issue with JDK v9
https://github.com/akhikhl/gretty/issues/390

Full Disclosure:
I am more up to date with C# which you might have noticed due to the naming conventions I have. (Pascal casing
for everything except for local variables and parameters.)

As of 12/5/2017 all unit tests pass.

In order to run the REST service, do the following:
1)Open a cmd
2)Change to the directory the project is located
3)run the command gradle runJettyWar
3.a) Please note the location that the service is running off of henceforth named localhost:XXXX/Decks-Demo
4) Use your favorite request program to access the REST Api's endpoints (I use Postman)
4.a) Examples:
4.a.1) GetAll: localhost:XXXX/Decks-Demo/Decks/GetAll (GET)
4.a.2) Get: localhost:XXXX/Decks-Demo/Decks/Get/{name} (GET)
4.a.3) Create: localhost:XXXX/Decks-Demo/Decks/Create/{name} (PUT)
4.a.4) Shuffle: localhost:XXXX/Decks-Demo/Decks/Shuffle/{name} (POST)
4.a.5) Delete: localhost:XXXX/Decks-Demo/Decks/Delete/{name} (DELETE)
4.b) {name} is the given value you would like to use