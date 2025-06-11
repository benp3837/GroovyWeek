//NOTE: I'm putting insert user in a method so we can call it within this script
//Realistically, this script would be called by some kind of web service or CLI application

def insertUser(name, username, email) {

    //first, as always, establish a DB connection
    def sql = DBConnectionUtil.getDatabaseConnection()

    try {

        //Validate the inputs - we'll just check that the name isn't blank
        //But realistically, we'd validate everything in this way
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank!")
        }

        //executeInsert() is the classic way to write an SQL INSERT command
        sql.executeInsert('''
            INSERT INTO users (name, username, email)
            VALUES (?, ?, ?)
        ''', [name, username, email]) //These are from the method parameters
        println "User ${username} inserted successfully."
    } catch(Exception e) {
        //There are insertion errors that can occur due to unique and not null constraints
        println "Error inserting user: ${e.message}"
    } finally {
        //closing the DB connection
        sql.close()
    }
}

//Calling the method here (instead of from some service or CLI)
insertUser("", "Bennn123", "bennnp@revature.com")