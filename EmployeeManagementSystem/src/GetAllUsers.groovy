//First, establish a connection to the DB
def sql = DBConnectionUtil.getDatabaseConnection()

try{

    //Write an execute our query to get all users
    //rows() executes a SQL query and returns the rows as a list of maps
    def users = sql.rows("SELECT * FROM users")

    //TODO: would be good to check if users is empty before proceeding - do this with .isEmpty()

    //Print each user
    users.each { user ->
        println "ID: ${user.user_id}, Name: ${user.name}, Username: ${user.username}, Email: ${user.email}"
    }

} catch (Exception e) {
    println "Error getting users: ${e.message}"
} finally {
    //good practice to close the DB connection once you're done with it
    sql.close()
}