Que. Explain the aspects of the design of your program that allow lambda expressions and/or anonymous classes 
to be used to test your searchRecipes function. Your discussion should include terms 
such as the following: dependency injection, mock, interface, polymorphism, couple/decouple



Ans... One of the key aspects of our program’s design that enables effective testing of the searchRecipes 
function is the use of dependency injection. Instead of creating a concrete data source inside the method, 
we pass in an object that implements the DataService interface. This design decouples the searchRecipes 
function from any specific data source (like SQLite or CSV), making the function more flexible and testable.

By depending on the DataService interface, we’re using polymorphism—our function can accept any object 
that conforms to the interface, whether it’s a real database-backed service or a fake/mock version used 
only for testing. This makes it easy to mock the data source using lambda expressions or anonymous classes 
during testing.

For example, in MainTest.java, we use lambda expressions to create mock versions of DataService. 
These mocks return specific, controlled data to verify that searchRecipes behaves correctly under various 
scenarios. This works because the interface defines just one method (getRecipes()), which can be implemented 
concisely with a lambda.

This approach reduces tight coupling between the logic and the data layer, making our code cleaner, 
more reusable, and easier to maintain. It also allows us to isolate the logic being tested, ensuring 
that our unit tests are not affected by the external environment or actual databases.

In summary, our use of interfaces and dependency injection, along with Java's support for lambdas and anonymous
classes, enables us to write unit tests that are focused, efficient, and robust.