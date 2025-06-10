@GrabConfig(systemClassLoader = true) //this will help us use @Grab in a Groovy script
@Grab('org.postgresql:postgresql:42.2.5') //@Grab lets us "grab" dependencies
//Dependencies are just external libraries that give our code more functionality
//The postgres dependency lets us interact with postgresQL databases
import groovy.sql.Sql

//This method will create and return a connection to the database
//We'll invoke this method in each script that needs to interact with the DB
static def getDatabaseConnection() {

    //To connect to the database, we need the URL, username, and password to the DB
    //(Remember I made this on my own)
    def dbUrl = "jdbc:postgresql://groovydb.c38eiwk4ka29.us-east-1.rds.amazonaws.com:5432/postgres"
    def dbUsername = "postgres"
    def dbPassword = "password"

    //We can use these credentials to return a new DB connection
    return Sql.newInstance(dbUrl, dbUsername, dbPassword, "org.postgresql.Driver")

}

//Try to establish a DB connection
try {
    getDatabaseConnection()
    println "Successfully connected to the database"
} catch (Exception e) {
    println "Failed to connect to the database: ${e.message}"
}

//NOTE: In the RDS, I created a table called "users" which has some data in it already
//RDS == Relational Database Service