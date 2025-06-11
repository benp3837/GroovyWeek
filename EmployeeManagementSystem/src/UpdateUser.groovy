//Like with insert, we'll make the update within a function so we can just call it here

def updateUser(userId, name, username, email){

    def sql = DBConnectionUtil.getDatabaseConnection()

    try {

        //check that the userId belongs to an existing user - a SELECT statement
        def userToUpdate = sql.firstRow(
                "SELECT * FROM users WHERE user_id = ?", [userId])

        //"If userToUpdate is null, then the user doesn't exist - throw an exception"
        //In Groovy, a null value is "falsy", which means it can be used in place of the boolean false
        if(!userToUpdate){ //This is the same as saying if(userToUpdate == null)
            throw new IllegalArgumentException("User with Id ${userId} does not exist!")
        }

        println("Updating user with Id ${userId}")

        sql.executeUpdate('''
            UPDATE users
            SET name = ?, username = ?, email = ?
            WHERE user_id = ?
        ''', [name, username, email, userId])

        println "User with ID ${userId} updated successfully."
    } catch (Exception e) {
        println "Error updating user: ${e.message}"
    } finally {
        sql.close()
    }
}

//Calling the updateUser function from here
updateUser(4, "updated again", "edgferewrw", "werwerewrewrewrwew@revature")