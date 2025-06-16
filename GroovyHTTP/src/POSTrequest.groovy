import groovy.json.JsonOutput
import groovy.json.JsonSlurper

//Define the URL we'll send the request to
def connection = new URL("https://jsonplaceholder.typicode.com/posts").openConnection() as HttpURLConnection

//Configure the request - takes a few more configurations than a GET
connection.requestMethod = "POST"
connection.doOutput = true //make sure we get an output (response) after the POST request (for error handling, confirmation, etc)
connection.setRequestProperty("Content-Type", "application/json") //Set the data type of the request data to JSON

//Create some JSON data to be sent in the request payload (AKA the request body)
def payload = [
        pokemonName1 : "Gengar",
        pokemonName2 : "Charizard",
        pokemonName3 : "Psyduck"
]

//Use a JsonOutput object to convert our payload to JSON
def jsonPayload = JsonOutput.toJson(payload)

//Attach our payload to the request
connection.outputStream.withWriter {writer ->
    writer.write(jsonPayload)
}

if(connection.responseCode == 201){ //201 - "CREATED" status code. As in your inputted data has been created in the DB

    //Same as with GET - getting the response, using JsonSlurper to translate the response, then printing it out
    def response = connection.inputStream.text
    def jsonSlurper = new JsonSlurper()
    def jsonResponse = jsonSlurper.parseText(response)

    println jsonResponse.id //JsonPlaceholder.com includes IDs with each response

    //loop through the fields in the response to print each pokemon
    jsonResponse.each{key, value ->
        println "${key} : ${value}"
    }

} else {
    println "Failed to insert the new Pokemon"
}