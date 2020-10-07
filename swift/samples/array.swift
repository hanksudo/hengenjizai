// Empty array
let emptyArray = [String]()
let anotherEmptyArray: [String] = []

print(anotherEmptyArray.count)
print(anotherEmptyArray.isEmpty)

// Create array with a default value
let threeDoubles = Array(repeating: 0.0, count: 3)
print(threeDoubles)

// Create array by adding two array together
let anotherThreeDoubles = Array(repeating: 2.5, count: 3)
let sixDoubles = threeDoubles + anotherThreeDoubles

print(sixDoubles)


// Accessing and modifiying
var shoppingList = ["catfish", "water", "tulips"]
shoppingList[1] = "bottle of water"
shoppingList.append("blue paint")
shoppingList += ["Baking Powder", "Cheese"]
print(shoppingList)

// change range of values even lenth is different
shoppingList[3...5] = ["Bananas", "Apples"]
print(shoppingList)

shoppingList.insert("Maple Syrup", at: 0)
let mapleSyrp = shoppingList.remove(at: 0)
let apples = shoppingList.removeLast()
precondition(apples == "Apples")
precondition(shoppingList.count == 4)

// Iterating Over an array
for item in shoppingList {
    print(item)
}

for (index, value) in shoppingList.enumerated() {
    print("Item \(index + 1): \(value)")
}