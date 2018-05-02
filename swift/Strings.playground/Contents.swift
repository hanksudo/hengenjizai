//: Playground - noun: a place where people can play

import UIKit

let str = "Hello, playground"

str[str.startIndex]

str[str.index(before: str.endIndex)]
str[str.index(after: str.startIndex)]

let index = str.index(str.startIndex, offsetBy: 7)
str[index]


// Inserting and Removing

var welcome = "hello"
welcome.insert("!", at: welcome.endIndex)
welcome
welcome.insert(contentsOf: " there", at: welcome.index(before: welcome.endIndex))

welcome.remove(at: welcome.index(before: welcome.endIndex))

let range = welcome.index(welcome.endIndex, offsetBy: -6)..<welcome.endIndex
welcome.removeSubrange(range)


// Substrings
let greeting = "Hello, world!"
let _index = greeting.index(of: ",") ?? greeting.endIndex
let beginning = greeting[..<_index]
let newString = String(beginning)

"a" == "b"
"Swift".hasPrefix("S")
"Swift".hasPrefix("s")
"Swift".hasSuffix("Swift")
"Swift".hasSuffix("swift")
