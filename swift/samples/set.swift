// Set<Element>
var letters = Set<Character>()

// Add new item
letters.insert("a")
print("letters is of type Set<Character> With \(letters.count) items.")

// Clear Set
letters = []
print("letters is of type Set<Character> With \(letters.count) items.")

letters.insert("b")
letters.removeAll(keepingCapacity: true)
print(letters)

// Create a Set with an Array Literal
var favoriteGenres: Set<String> = ["Rock", "Classical", "Hip hop", "Hip hop"]
print(favoriteGenres)

// Iterating Over a Set
for genre in favoriteGenres {
    print(genre)
}

for genre in favoriteGenres.sorted() {
    print(genre)
}

// Infer Set<String>
var favoriteGenresInfer: Set = ["Rock", "Classical", "Hip hop", "Hip hop"]
print(favoriteGenresInfer)

print(favoriteGenresInfer.isEmpty)

print(favoriteGenres.contains("Rock"))
if let removedGenre = favoriteGenres.remove("Rock") {
    print(removedGenre)
}
print(favoriteGenres)

// performing Set operations

let oddDigits: Set = [1, 3, 5, 7, 9]
let evenDigits: Set = [0, 2, 4, 6, 8]
let singleDigitPrimeNumbers: Set = [2, 3, 5, 7]

print(oddDigits.union(evenDigits).sorted())
print(oddDigits.intersection(evenDigits).sorted())
print(oddDigits.subtracting(evenDigits).sorted())
print(oddDigits.symmetricDifference(singleDigitPrimeNumbers))

let houseAnimals: Set = ["Dog", "Cat"]
let farmAnimals: Set = ["Cow", "Chicken", "Sheep", "Dog", "Cat"]
let cityAnimals: Set = ["Bird", "Rat"]

print(houseAnimals.isSubset(of: farmAnimals))
print(farmAnimals.isSuperset(of: houseAnimals))
print(farmAnimals.isStrictSuperset(of: houseAnimals))
print(farmAnimals.isStrictSuperset(of: farmAnimals))
print(farmAnimals.isDisjoint(with: cityAnimals))
