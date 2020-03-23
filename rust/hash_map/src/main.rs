use std::collections::HashMap;

fn main() {
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);
    println!("{:?}", scores);

    // Insert twice, second one will replace first
    scores.insert(String::from("Red"), 10);
    scores.insert(String::from("Red"), 20);
    println!("{:?}", scores);

    // insert only when key not exists
    scores.entry(String::from("Blue")).or_insert(30);
    scores.entry(String::from("Brown")).or_insert(60);
    println!("{:?}", scores);

    // Creating a hash map from two vector
    let teams = vec![String::from("Blue"), String::from("Yellow")];
    let initial_scores = vec![10, 50];

    let scores: HashMap<_, _> = teams.iter().zip(initial_scores.iter()).collect();
    println!("{:?}", scores);

    let team_name = String::from("Blue");
    let score = scores.get(&team_name);
    println!("Score of Blue team: {}", score.unwrap());

    // Iterate
    for (key, value) in &scores {
        println!("{} {}", key, value);
    }

    // Update value by old value
    let text = "hello world wonderful world";
    let mut map = HashMap::new();

    for word in text.split_whitespace() {
        let count = map.entry(word).or_insert(0);
        *count += 1;
    }

    println!("{:?}", map);
}
