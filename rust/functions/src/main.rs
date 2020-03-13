fn main() {
    another_function(5);

    let y = {
        let x = 3;
        x + 1 // expression - return
    };
    println!("The value of y is : {}", y);

    let five = five();
    println!("The value of five is : {}", five);
}

fn another_function(x: i32) {
    println!("The value of x is: {}", x);
}

fn five() -> i32 {
    5
}