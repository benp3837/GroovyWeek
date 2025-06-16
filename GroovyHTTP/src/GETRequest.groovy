import groovy.json.JsonSlurper

//randomize the pokemon id
def randomId = new Random().nextInt(1000) + 1 //Random ID between 1 and 1000

//Create the URL to be used in our request
def apiUrl = "https://pokeapi.co/api/v2/pokemon/${randomId}"

//Establish our Http Connection so we can send a GET request
def connection = new URL(apiUrl).openConnection() as HttpURLConnection
connection.requestMethod = "GET"

//Check if the response code is 200 (OK), or print an error message
if(connection.responseCode == 200){
    def response = connection.inputStream.text //save the response

    //We'll use JsonSluper to read and output the response data
    def jsonSlurper = new JsonSlurper()
    def pokemon = jsonSlurper.parseText(response) //translates the response into a string

    println "Your Pokemon is: ${pokemon.name.capitalize()} ID #${pokemon.id}"

} else {
    println "Failed to get pokemon!"
}