fn main() {
    // Mutable
    let mut x = 5;
    println!("The value of x is: {}", x);
    x = 6;
    println!("The value of x is: {}", x);

    // Contstants
    const MATH_POINT: u32 = 100_000;

    // Shadowing
    let x = 5;
    let x = x + 1;
    let x = x * 2;
    println!("The value of x is: {}", x);  // 12

    // Ok
    let spaces = "    ";
    let spaces = spaces.len();
    println!("Spaces: {}", spaces);
    
    // Err
    let mut spaces2 = "    ";
    spaces2 = spaces2.len();
}
