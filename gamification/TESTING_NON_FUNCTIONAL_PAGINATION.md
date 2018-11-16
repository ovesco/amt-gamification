# Pagination
Pagination can be performed on three different tiers of your application, Resource, Business and Client.

## Client pagination
Let's start with client pagination. This can be really easy (for example we pull the whole dataset in memory and paginate it),
for a website we can easy use a library like datatables.js which automatically paginates our data and allow easy 
filtering and sorting. Problem occurs when the dataset grows in size, a hundred results is okay, but a thousand starts
to be a lot in memory and for the UI to handle. We tested it in a simple HTML page with 1000 and 2000 rows in an HTML table
and attached datatables on it, page was a lot laggy and operations took much more time.

We would then need to retrieve some subset of our data, but then how to perform filtering or sorting on the whole dataset?
It requires some server side pagination anyway.

**Cool** : Can be really easy to setup
**Not cool** : the more the dataset grows, the less the client can handle it and loses in responsiveness

## Business pagination
Business pagination can be really interesting, as it is done in the language used by the developers to code the app, which
means some production boost, libraries also exist to help in the creation of an effective pagination. The first problem
comes from the fact that it requires some endpoints for it. Think client, the dataset could be returned in a full HTML page
for example, not possible anymore, it must be decoupled from the "real view". This is not a problem for example in a full
API exposing server. 
Real problem comes from the fact that we let the business tier handle it (business is for logic), more efficient than 
client but not made for it. Just as the client it requires the whole dataset to be loaded before starting to work with it, 
which can quickly throw some memory error (think a one million or more dataset).

**Cool** : More efficient than client pagination
**Not Cool** : Harder to implement than client navigation, a little bit more efficient but not a lot, and if it breaks
from the load, it can be pretty harmful

## Resource pagination
Enter resource pagination. The resource tier is supposed to hold and provide data used by our application and services,
it is meant for that! As it acts as a data store, it can manipulate the whole dataset efficiently and much more quickly than
the business tier.
In business pagination we require:
1. Resource tier sends all data to business tier
2. Business tier paginates it to export a subset (a lot of the initial set is useless)

While in resource pagination, the business directly receives the subset and can efficiently work with it. It requires
some more work as interacting with the resource tier needs some special efforts (SQL queries for example) but the reward
is much more speed and less load on the server.

**Cool** : The most efficient way, resource tier is meant to deal with data
**Not cool** : Can be harder to setup sometimes, but doesnt require more endpoints than business pagination anyway

# Testing
We have a database containing 1'000'000 rows of users (first name, last name, city, npa, street) and want to access 10 results
from index 7'850 to 7'859.

## Client
Receives 1'000'000 results and has to paginate it, page stops working
## Business
Returns a result but memory consumption explodes
## Resource
Returns a result without peak memory or anything, faster than business tier