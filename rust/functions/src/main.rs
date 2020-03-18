fn main() {
    another_function(5);

    let five = five();
    println!("The value of five is : {}", five);

    print_sum(1, 2);

    // Function pointers
    let b = plus_one;
    let c = b(5);
    println!("c is {}", c); // 6
}

// Passing Arguments
fn another_function(x: i32) {
    println!("The value of x is: {}", x);
}

fn print_sum(a: i8, b: i8) {
    println!("sum is: {}", a + b);
}

fn five() -> i32 {
    5
}

fn plus_one(i: i64) -> i64 {
    i + 1
}