fn main() {

    // loop
    let mut counter = 0;

    let result = loop {
        counter += 1;

        if counter == 10 {
            break counter * 2;
        }
    };

    println!("The result is {}", result);

    // while
    let mut n = 0;
    while n < 5 {
        n += 1;
        println!("The value of n is: {}", n);
    }

    // for
    let a = [10, 20, 30, 40, 50];
    for element in a.iter() {
        println!("the value is: {}", element);
    }

    for number in (10..=50).step_by(10) {
        println!("the value is: {}", number);
    }
    
    for number in (1..4).rev() {
        println!("{}!", number);
    }
}
