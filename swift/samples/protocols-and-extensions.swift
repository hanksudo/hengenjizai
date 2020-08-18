protocol ExampleProtocol {
    var simpleDescription: String { get }
    mutating func adjust()
}

class SimpleClass: ExampleProtocol {
    var simpleDescription: String = "A very simple class."
    var anotherProperty: Int = 69105
    func adjust() {
        simpleDescription += " Now 100% adjusted."
    }
}

var a = SimpleClass()
a.adjust()
print(a.simpleDescription)

struct SimpleStructure: ExampleProtocol {
    var simpleDescription: String = "A simple structure"
    mutating func adjust() {
        simpleDescription += " (adjusted)"
    }
}

var b = SimpleStructure()
b.adjust()
print(b.simpleDescription)

extension Int: ExampleProtocol {
    var simpleDescription: String {
        "The number \(self)"
    }

    mutating func adjust() {
        self += 42
    }
}

var num = 7
print(num.simpleDescription)
num.adjust()
print(num.simpleDescription)

// The compiler treats it as the given type of ExampleProtocol
// This means that you can’t accidentally access methods
// or properties that the class implements in addition to its protocol conformance.
let protocolValue: ExampleProtocol = a
print(protocolValue.simpleDescription)
print(protocolValue.anotherProperty) // error
