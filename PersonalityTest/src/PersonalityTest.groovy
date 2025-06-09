//Set up a Scanner (from Java.util!) to take user input
def scanner = new Scanner(System.in)

//Greet the user and gather their name
println "Welcome to the Personality Test!"
println "What's your name?"

def name = scanner.nextLine() //take in user input and make a new line

//$ == String Interpolation - this lets us use a variable's value in a string
println " Hello $name, Answer the questions with A, B, or C "

//Define an array with questions/answers that we'll loop through to make the quiz
def questions = [

        //Groovy is flexible! We can just define anonymous objects. No need to make a class
        [
            prompt: "1. What's your ideal weekend activity?",
            options: [
                    A:"Relaxing at home",
                    B:"Going to the gym",
                    C:"Going to parties"
            ]
        ],
        [
            prompt: "2. How do you handle stress?",
            options: [
                    A:"Go to sleep",
                    B:"Go on a walk",
                    C:"Talking about it with friends"
            ]
        ],
        [
            prompt: "3. What's your goal in life?",
            options: [
                    A:"Personal Growth",
                    B:"Achieving Success",
                    C:"Helping Others"
            ]
        ],
]

//Loop through the Array to show each question
questions.each { question ->
    println "\n ${question.prompt}"
    question.options.each {key, value ->
        println "$key: $value"
    }

    //Get the user's answer before moving to the next question
    def answer
    while(true) {
        answer = scanner.nextLine().toUpperCase()
        if(['A', 'B', 'C'].contains(answer)){
            break //exit the loop, valid answer given
        } else {
            println "Please enter A, B, or C"
        }
    }

    //TODO: Actually record the answers, provide a result

}