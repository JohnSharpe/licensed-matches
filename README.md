# Licensed Matches

This project defines a web server offering an endpoint to return all the matches
a certain customer has licensed.

## Building, testing and running

This project is built with Maven. Running `mvn clean package` is enough
to run unit tests and get a jar.

To run, use `java -jar <application.jar> server <config.yml>`

The application runs on port 3000 and has a single GET endpoint

`/licensed/{customerId}`

Your `customerId` parameter must be a UUID.

You can optionally provide a summary type query parameter

`?summaryType=<your_summary_type>`

This may be either "AvB" or "AvBTime" and is case sensitive.
In the case that no summary type is provided, or it is not recognised, "AvB" will be assumed.

The response will be
- `200` Success, with a list of matches as a JSON body, possibly empty.
- `404` The customer does not exist (see assumptions)

## Development

### Technology choices

The following choices were made at the beginning of the project.
Updates and revisions will be included below.
- Java 14
    
    It may not be production-ready, but I'm trying to stay on top of
    changes in the technology and it's almost certainly mature enough
    for this project.
    
- Maven 3

    Simple, I have more experience with it and it's a proven technology.
    
- Dropwizard

    With limited time, I'm going to use the Dropwizard framework which
    runs on Jetty. It is not a Dependency Injection container so we can,
    hopefully, whip up a solution quickly built on traditional OO
    practices. If this were a larger-scale service, possibly with
    multiple implementations per service, it would make sense to move
    to something more heavyweight, like Spring Boot.
    
    Also, it comes bundled with a few useful libraries like Jackson,
    JAX-RS and Jersey, which will come in useful.
    
- JUnit 5

    Really intuitive annotations.
    
### As We Go

The following section is roughly a chronological log of my assumptions and decisions
as I go through the project.

- 404 Not Found

In a real implementation, we might have a notion of a CustomerRepository. Here, we're
going to return a 404 if the customer is not known to the system, i.e. no licenses to
known matches exist for them.

- Best effort

The repositories will make a best-effort attempt to grab matches for a customer. Since
we're not fleshing out a database, it will be up to the person running the system to get the
data right.

- Persistence

In the interest of saving time, I will abstract away details of actually storing the
entities and some aspects of retrieving them. In particular, the project's repositories
may be backed by a relational database which could leverage techniques like `joins` to
shrink down the number of steps. Hopefully, we will hit a level of abstraction where this
could comfortably be 'dropped in' to the system, but it would be too time-consuming to
actually develop.

There are many aspects of data integrity that would have to be maintained by the Repository
implementations.

- System clock

It will be assumed that the system clock is 'good enough' for any time calculations.

### If I had more time

I would actually implement methods to get data into the system and possibly models for various
entities like Customer, Tournament and Player.