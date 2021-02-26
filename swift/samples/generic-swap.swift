// https://docs.swift.org/swift-book/LanguageGuide/Generics.html

// -- Non Generic version
func swapTwoStrings(_ a: inout String, _ b: inout String) {
    let temp = a
    a = b
    b = temp
}

func swapTwoDoubles(_ a: inout Double, _ b: inout Double) {
    let temp = a
    a = b
    b = temp
}

var a = "A"
var b = "B"
print("\(a) \(b)")
swapTwoStrings(&a, &b)
print("\(a) \(b)")

var d1 = 2.0
var d2 = 4.0
print("\(d1) \(d2)")
swapTwoDoubles(&d1, &d2)
print("\(d1) \(d2)")

// -- Generic version

func swapTwoValues<T>(_ a: inout T, _ b: inout T) {
    let temp = a
    a = b
    b = temp
}

print("\(a) \(b)")
print("\(d1) \(d2)")
swapTwoValues(&a, &b)
swapTwoValues(&d1, &d2)

print("\(a) \(b)")
print("\(d1) \(d2)")