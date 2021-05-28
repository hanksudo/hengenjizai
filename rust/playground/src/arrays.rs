// Arrays - Fixed list where elements are the same data types
// https://doc.rust-lang.org/std/primitive.array.html

use std::mem;

pub fn run() {
    // Array
    // [T; size]
    // - fixed length
    // - every element have the same type
    let repeated = [3; 5]; // let a = [3, 3, 3, 3, 3]
    println!("{:?}", repeated);

    // Fixed size of numbers
    let numbers: [i32; 5] = [1, 2, 3, 4, 5];
    let single_number = numbers[1];
    println!("{}", single_number);
    println!("{:?}", numbers);

    // Get array length
    println!("Array Length: {}", numbers.len());

    // Arrays are stack allocated
    println!("array a occupies {} bytes", mem::size_of_val(&numbers));

    // Get Slice
    let slice: &[i32] = &numbers[0..2];
    println!("Slice {:?}", slice);
}
