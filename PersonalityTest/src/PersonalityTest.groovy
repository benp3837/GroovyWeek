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

//This map will keep track of the User's answers
def score = [A:0, B:0, C:0]

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
            score[answer]++ //increment the score for the given answer
            break //exit the loop, valid answer given
        } else {
            println "Please enter A, B, or C"
        }
    }
}

//print score <-uncomment if you want to see the scores

//Now, we can use the scores to give the user their personality result
def personality = ""

//Finding the Key with the greatest value in the score map
def maxChoice = score.max{it.value}.key

if(score.A == score.B && score.A == score.C) {
    maxChoice = "Tie"
}

switch(maxChoice) {
    case "A":
        personality = "You are a calm and introspective person who values personal growth and self care"
        break
    case "B":
        personality = "You are an ambitious and driven person who values success and achievement"
        break
    case "C":
        personality = "You are a social and empathetic person who values relationships and helping others"
        break
    default:
        personality = "You have a personality that can't be defined! How unique."
}

//Tell the user their result
println "$name, based on your answers: $personality"
