fn main() {
    // Tuple
    // - fixed length
    let x: (i32, f64, u8) = (500, 6.4, 1);

    let five_hundred = x.0;

    let six_point_four = x.1;

    let one = x.2;

    println!("{} {} {}", five_hundred, six_point_four, one);


    // Array
    // - fixed length
    // - every element have the same type
    let a = [3; 5]; // let a = [3, 3, 3, 3, 3]
    let b: [i32; 5] = [1, 2, 3, 4, 5];
    let first = a[0];
    let second = b[1];
    println!("{} {}", first, second);
}