# Transaction in gamification
The project is based on JPA with Hibernate as implementation. Hibernate handles database operations by itself to
allow performance optimisation (batch operations). The only moment when hibernate has to execute operations is when
we call `flush` on the entity manager. 

## When do we call flush
We call flush only when we create an entity, to make sure it exists in the database and can be included in future operations
involving it. For the rest we leave all the handling in hibernate's hands.