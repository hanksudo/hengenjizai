//: Playground - noun: a place where people can play

import UIKit

// variables
var str = "Hello, playground"
var year = 2018
var pi = 3.14
var isRaining = true
var welcomeMessage: String
var red, green, blue: Double

// constants
let languageName = "Swift"
//immutableString = "abc" // error

let maxmiumNumberOfLoginAttempts = 10
var currentLoginAttempt = 0

var (x, y, z) = (1, 2, 3)
print(x, y, z)

12 + 5
12 - 3
12 * 3
12 / 5
12 / 5.0
12 % 5

true || false
true && false

var i = 1
i += 1
i -= 1

if isRaining {
    print("umberlla")
} else {
    print("no need umberlla")
}

var fruits = ["Apple", "Banna", "Watermelon"]
fruits[0]
fruits.append("Pear")
fruits.count

for fruit in fruits {
    print("\(fruit)\n")
}
