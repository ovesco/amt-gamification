# Transaction in gamification
The project is based on JPA with Hibernate as implementation. Hibernate handles database operations by itself to
allow performance optimisation (batch operations). The only moment when hibernate has to execute operations is when
we call `flush` on the entity manager. 

## When do we call flush
We call flush only when we create an entity, to make sure it exists in the database and can be included in future operations
involving it.

## How does hibernate handle exceptions
First, we do not explicitly ask hibernate to start or commit a transaction, we leave this in the ORM's hands. We tried
throwing an exception in our code but we simply saw that no entities were created in database. As stated previously, we
call flush only when we create an entity, throwing an exception before it result in no persisting in database. 

## JMeter stress test
To also check concurrency and database safety, we used JMeter to launch tests with multiple connections. The results were
quite disappointing as the application threw a lot of exceptions and couldn't handle many requests, but our database stayed
in a correct state which is a good point.