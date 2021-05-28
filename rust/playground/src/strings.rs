// Primitive str = Immutable fixed-length string in memory
// String = Growable, heap-allocated data structure - Use when you need to modify or own string data

pub fn run() {
    let (s1, s2) = ("hello", " world"); // both &str
    println!("{}{}", s1, s2);

    let s = String::from(s1) + s2; // String + &str
    println!("{}", s);

    let mut s = String::from(s1); // String
    s.push_str(s2); // &str
    println!("{}", s);

    let s = format!("{}{}", s1, s2); // &str/String + &str/String
    println!("{}", s);

    let s = [s1, s2].concat(); // &str or String array
    println!("{}", s);

    println!("Length: {}", s.len());
    println!("Capacity: {}", s.capacity());
    println!("Is empty: {}", s.is_empty());
    println!("Contains 'world': {}", s.contains("world"));
    println!("Replace: {}", s.replace("world", "Taiwan"));

    // Loop through string
    for word in s.split_whitespace() {
        println!("{}", word)
    }

    // Create string with capacity
    let mut new_s = String::with_capacity(10);
    new_s.push('a');
    new_s.push('b');

    println!("{}", new_s);
    println!("Capacity: {}", new_s.capacity());

    assert_eq!(2, new_s.len());
    assert_eq!(10, new_s.capacity());
}
