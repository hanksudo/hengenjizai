// Vec<T>
fn main() {
    // Create
    let v: Vec<i32> = Vec::new();
    println!("{:?}", v);

    let v = vec![1, 2, 3];
    println!("{:?}", v);

    // Update
    let mut v = Vec::new();
    v.push(5);
    v.push(6);
    v.push(7);
    v.push(8);
    println!("{:?}", v);

    // Read
    let third: &i32 = &v[2];
    println!("The third element is {}", third);

    match v.get(2) {
        Some(third) => println!("The third element is {}", third),
        None => println!("There is no third element."),
    }

    // iterate
    for i in &v {
        println!("{}", i);
    }

    // update every element
    for i in &mut v {
        *i += 10;
        println!("{}", i);
    }

    // Enum
    #[derive(Debug)]
    enum SpreadsheetCell {
        Int(i32),
        Float(f64),
        Text(String),
    }

    let row = vec![
        SpreadsheetCell::Int(3),
        SpreadsheetCell::Text(String::from("blue")),
        SpreadsheetCell::Float(10.12),
    ];
    println!("{:?}", row);
}
