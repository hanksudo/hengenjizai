var occupations = [
    "Malcolm": "Captain",
    "Kaylee": "Mechanic",
]
occupations["Jayne"] = "Public Relations"
print(occupations)
print(occupations.count)
print(occupations.isEmpty)

// Iterating over a Dictionary
for (name, role) in occupations {
    print(name, role)
}

print(occupations.keys)
// to array
let keys = [String](occupations.keys)
print(keys)
print(occupations.values)

// remove key
occupations["Jayne"] = nil
if let removedValue = occupations.removeValue(forKey: "Jayne") {
    print(removedValue)
}

// if key exists, `updateValue` return old value
// if not exists, return nil
if let oldValue = occupations.updateValue("value", forKey: "Jay") {
    print(oldValue)
}

let emptyDictionary = [String: Float]()
let anotherEmptyDictionary: [Int: String] = [:]
