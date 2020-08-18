var optionalString: String? = "Hello"
print(optionalString == nil)

var optionalName: String? = "Hank"
var greeting = "Hello!"
if let name = optionalName {
    greeting = "Hello, \(name)"
}

print(greeting)

// nil value
var nilValue: String?
print(nilValue == nil)

// default value
print(nilValue ?? "Default Name")
