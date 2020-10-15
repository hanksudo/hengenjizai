// Set<Element>
var letters = Set<Character>()

// add new item
letters.insert("a")
print("letters is of type Set<Character> With \(letters.count) items.")

// clear
letters = []
print("letters is of type Set<Character> With \(letters.count) items.")

letters.insert("b")
letters.removeAll(keepingCapacity: true)
print(letters)

// Create a Set with an Array Literal
var favoriteGenres: Set<String> = ["Rock", "Classical", "Hip hop", "Hip hop"]
print(favoriteGenres)

// Infer Set<String>
var favoriteGenresInfer: Set = ["Rock", "Classical", "Hip hop", "Hip hop"]
print(favoriteGenresInfer)

print(favoriteGenresInfer.isEmpty)