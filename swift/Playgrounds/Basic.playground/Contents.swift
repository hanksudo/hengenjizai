//: Playground - noun: a place where people can play

import UIKit

// -----------------------
// Constants and Variables

var str = "Hello, playground"
var year = 2018
var pi = 3.14
var isRaining = true

let languageName = "Swift"
//languageName = "Swift++" // error

let maxmiumNumberOfLoginAttempts = 10
var currentLoginAttempt = 0

// multiple variables
var (x, y, z) = (1, 2, 3)
print(x, y, z)

// Type Annotations
var welcomeMessage: String
var red, green, blue: Double

// -----
// Print

print("The current value of languageName is \(languageName)")

let multiplier = 3
print("\(multiplier) times 2.5 is \(Double(multiplier) * 2.5)")

// --------
// Comments

// This is a comment.

/* This is also a comment
 but is written over multiple lines. */

// --------
// Numbers

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
let hexadecimalInteger = 0xFF
1.25e2
1.25e-2
0xFp2   // 15 x 2 ^ 2
0xFp-2

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

(1, "zerba") < (2, "apple")
(100, 200) > (50, 100)

// ---------
// Optionals

let possibleNumber = "123"
let convertedNumber = Int(possibleNumber)

var serverResponseCode: Int? = 400
serverResponseCode = nil

var surveyAnswer: String?

let defaultColorName = "red"
var userDefinedColorName: String?  // defaults to nil

var colorNameToUse = userDefinedColorName ?? defaultColorName

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


// --------------
// Error Handling
// ignored...


// -------------------------
// Debugging with Assertions
let age = -3
// assert(age >= 0, "A peron's age can't be less than zero.")


// --------------
// Range Operator

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