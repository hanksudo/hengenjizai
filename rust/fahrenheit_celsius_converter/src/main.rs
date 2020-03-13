// Fahrenheit, Celsius coverter
fn main() {
    println!("68F = {}C", f_to_c(68.0));
    println!("70F = {}C", f_to_c(70.0));
    println!("25C = {}F", c_to_f(25.0));
    println!("11C = {}F", c_to_f(11.0));
}

// Fahrenheit to Celsius
fn f_to_c(temperature: f64) -> f64 {
    (temperature - 32.0) / 1.8
}

// Celsius to Fahrenheit
fn c_to_f(temperature: f64) -> f64 {
    temperature * 1.8 + 32.0
}