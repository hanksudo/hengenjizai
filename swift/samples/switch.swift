let vegetable = "red pepper"

switch vegetable {
case "celery":
    print("Add some raisins and make ants on a log.")
case "cucumber", "watercress":
    print("That would make a good tea sandwich.")
case let x where x.hasSuffix("pepper"):
    print("Is it a spicy \(x)?")
default:
    print("Everything tastes good in soup.")
}

// C-Style Fallthrough
let a = 1
switch a {
case 1:
    print("run 1")
    fallthrough
case 2:
    print("run 2")
    fallthrough
default:
    print("run default")
}

// Where
let point = (1, -1)
switch point {
case let (x, y) where x == y:
    print("(\(x), \(y)) is one the line x == y")
case let (x, y) where x == -y:
    print("(\(x), \(y)) is one the line x == -y")
case let (x, y):
    print("(\(x), \(y)) is just some arbitrary point")
}
