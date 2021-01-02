//: Playground - noun: a place where people can play
// https://docs.swift.org/swift-book/GuidedTour/GuidedTour.html

import UIKit

// -----
// Print

print("Hello, world!")

let multiplier = 3
print("\(multiplier) times 2.5 is \(Double(multiplier) * 2.5)")

// -----------------------
// Constants and Variables

var myVariable = 42
myVariable = 52
var str = "Hello, playground"
var year = 2018
var isRaining = true

let myConstant = 54321
let PI = 3.14 // inferred to Double
let anotherPI = 3 + 0.14159  // inferred to Double

let languageName = "Swift"
//languageName = "Swift++" // reassign error
print("The current value of languageName is \(languageName)")

// multiple variables
var (x, y, z) = (1, 2, 3)
print(x, y, z)

var a = 0.0, b = 0.0, c = 0.0

// Type Annotations
var welcomeMessage: String
var red, green, blue: Double

let maximumNumberOfLoginAttempts = 10
var currentLoginAttempt = 0

let implicitInteger = 70
let implicitDouble = 70.0
let explicitDouble: Double = 70

// Multiple lines string

var apples = 3
let quotation = """
I said "I have \(apples) apples."
And then I eat one apple.
"""



// --------
// Comments

// This is a comment.

/* This is also a comment
 but is written over multiple lines. */

// --------
// Numbers

Int8.max
Int8.min
UInt8.max
UInt8.min

12 + 5
12 - 3
12 * 3
12 / 5
12 / 5.0
12 % 5

var i = 1
i += 1
i -= 1

let binaryInteger = 0b10001
let octalInteger = 0o21
let hexadecimalInteger = 0xFF
1.25e2
1.25e-2
0xFp2   // 15 x 2 ^ 2
0xFp-2  // 15 x 2 ^ -2
0xC.3p0 // (12 + 16^-1) x 2 ^ 0

let paddingDouble = 000123.456
let oneMillion = 1_000_000
let justOverOneMillion = 1_000_000.00_000_1

let three = 3
let pointOneFourOneFiveNine = 0.14159
Double(three) + pointOneFourOneFiveNine
Int(Double(three) + pointOneFourOneFiveNine)


// ------------
// Type Aliases

typealias UnsignedInteger16 = UInt16
UnsignedInteger16.max


// --------
// Booleans

true || false
true && false

if isRaining {
    print("umberlla")
} else {
    print("no need umberlla")
}

// ------
// Tuples

// http404Error is of type (Int, String)
let http404Error = (404, "Not Found")
let (statusCode, statusMessage) = http404Error
print("The status code is \(statusCode), \(http404Error.0)")
print("The status message is \(statusMessage), \(http404Error.1)")

let (justTheStatusCode, _) = http404Error
print("The status code is \(justTheStatusCode)")

let http200Status = (statusCode: 200, description: "OK")
print("The status code is \(http200Status.statusCode)")
print("The status message \(http200Status.description)")

(1, "zerba") < (2, "apple")  // true because 1 < 2; "zerba" and "apple" are not compared
(100, 100) > (50, 200)


// ---------
// Optionals

var optionalString: String? = "hello"
print(optionalString == nil)

var optionalName: String? = "Hank Wang"
if let name = optionalName {
    print("Hello, my name is \(name)")
} else {
    // if optionalName is nil
    print("Don't know who you are.")
}

let possibleNumber = "123"
let convertedNumber = Int(possibleNumber)

var serverResponseCode: Int? = 400
serverResponseCode = nil

var surveyAnswer: String?

// -----------------------
// Nil-Coalescing Operator
let defaultColorName = "red"
var userDefinedColorName: String?  // defaults to nil

// if optional value is missing, use default value instead.
var colorNameToUse = userDefinedColorName ?? defaultColorName
assert(colorNameToUse == defaultColorName)

// -----------------------------------
// If Statements and Forced Unwrapping

if convertedNumber != nil {
//    print("convertedNumber is \(convertedNumber).")
    print("convertedNumber is \(convertedNumber!).")
}

if let actualNumber = Int(possibleNumber) {
    print("\"\(possibleNumber)\" has an integer value of \(actualNumber)")
} else {
    print("\"\(possibleNumber)\" could not be converted to an integer")
}

let possibleString: String? = "An optional string."
let forcedString: String = possibleString! // require an exclamation mark

let assumedString: String! = "An implictly unwrapped optional string."
let implicitString: String = assumedString // no need exclamation mark

// -------------------
// Ternary Conditional
2 > 1 ? "yes" : "no"


/**
 Array, Dictionary
*/
var emptyArray = [String]()
var shoppingList = ["catfish", "water", "tulips"]
shoppingList[1] = "bottle of water"
shoppingList.append("blue paint")
shoppingList += ["one more item"]
shoppingList.insert("airpod", at: 2)
shoppingList.remove(at: 2)
shoppingList
shoppingList = []

var prices: [Int] = [30, 40, 50]
prices.reduce(0, +)  // sum
prices.count         // count

var emptyDictionary = [String: Float]()
var occupations = [
    "Malcolm": "Captain",
    "Kaylee": "Mechanic",
]
occupations["Jayne"] = "Public Relations"
occupations = [:]

var myDict = [String: Int]()
myDict["apple"] = 188
myDict
myDict.updateValue(190, forKey: "apple")
myDict
myDict["apple"] = 200
myDict
myDict.removeValue(forKey: "apple")
myDict
myDict["apple"] = nil
myDict
myDict.isEmpty

// ----
// Loop

// for-in
var items = ["item 1", "item 2", "item 3"]
for item in items {
    print(item)
}

// 0 ~ 3
for i in 0..<4 {
    print(i)
}

// for-in iterate over items in a dictionary
let interestingNumbers = [
    "Prime": [2, 3, 5, 7, 11, 13],
    "Fibonacci": [1, 1, 2, 3, 5, 8],
    "Square": [1, 4, 9, 16, 25],
]
var largest = 0
for (_, numbers) in interestingNumbers {
    for number in numbers {
        if number > largest {
            largest = number
        }
    }
}
print(largest)

// while
var n = 2
while n < 10 {
    n += 1
}
print(n)

n = 2
repeat {
    n += 1
} while n < 10
print(n)


// --------------
// Switch

let vegetable = "red pepper"

switch vegetable {
case "celery":
    print("Add some raisins and make ants on a log.")
case "crumber", "watercress":
    print("That would make a good tea sandwich.")
case let x where x.hasSuffix("pepper"):
    print("Is it a spicy \(x)?")
default:
    print("Everything tastes good in soup.")
}

// --------------
// Error Handling
func canThrowAnError() throws {
    // this function may or may not throw an error
}

do {
    try canThrowAnError()
    // no error was thrown
} catch let error {
    // an error was thrown
}


// -------------------------
// Debugging with Assertions
let age = -3
//assert(age >= 0, "A peron's age can't be less than zero.")
//assertionFailure("checkedStillFailed")


//--------------
// Preconditions
//let index = -1
//precondition(index > 0, "Index must be greater than zero")

// --------------
// Range Operator

// Closed range operator
1...5
for i in 1...5 {
    print("\(i) times 5 is \(i * 5)")
}

// Half-Open Range Operator
let names = ["Anna", "Alex", "Brian", "Jack"]
let count = names.count
for i in 0..<count {
    print("Person \(i + 1) is called \(names[i])")
}

for name in names[2...] {
    print(name)
}
for name in names[...2] {
    print(name)
}
for name in names[..<2] {
    print(name)
}

let range = ...5
range.contains(7)
range.contains(4)
range.contains(-1)

    
func printMoneyString(dollars :Int, cents: Int) {
    print("$\(dollars).\(cents)")
}

printMoneyString(dollars: 10, cents: 50)
printMoneyString(dollars: 19, cents: 99)


// Sets
// https://docs.swift.org/swift-book/LanguageGuide/CollectionTypes.html#ID484
// contains
// remove
// removeAll
// intersection (AND)
// union (OR)
// symmetricDifference (NAND)
//

var consonants = Set<Character>()
var vowels: Set = ["A", "E", "I", "O", "U"]
vowels.count
vowels.isEmpty
vowels.insert("A")
vowels

// Subscripts
// https://docs.swift.org/swift-book/LanguageGuide/Subscripts.html
struct Repeater {
    let times: Int
    subscript(number: Int) -> Int {
        times * number
    }
    subscript(repeatStrng: String) -> String {
        String(repeating: repeatStrng, count: times)
    }
}

let repeater = Repeater(times: 3)
repeater[3]  // 9
repeater["One"]  // OneOneOne
