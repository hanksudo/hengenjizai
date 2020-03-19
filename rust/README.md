# Rust

## Installation

```bash
# install
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

# Update Rust toolchains and rustup
rustup update

# install component rustfmt
rustup component add rustfmt --toolchain stable-x86_64-apple-darwin

# check version
rustc --version
cargo --version
```

## Execute

```bash
# single file
rustc hello_world.rs
./hello_world

cargo new project_name
cargo build

# Building for Release
cargo build --release

# Auto build if source code modified before run
cargo run

# Check binary
cargo check

# Update dependencies listed in Cargo.lock
cargo update
    
# documentation
cargo doc --open
```

## References

- [Rust programming language](https://www.rust-lang.org/)

- [The Rust Reference](https://doc.rust-lang.org/reference/introduction.html)

- [Rust Package Registry](https://crates.io/)

- [Docs.rs](https://docs.rs/)

- [std - Rust](https://doc.rust-lang.org/stable/std/)

- [Rust By Example](https://doc.rust-lang.org/stable/rust-by-example/)

- [Small exercises to get you used to reading and writing Rust code!](https://github.com/rust-lang/rustlings)

- [Rust Playground](https://play.rust-lang.org/)

- [Learning Rust](https://learning-rust.github.io/)

- [awesome-rust: A curated list of Rust code and resources.](https://github.com/rust-unofficial/awesome-rust)


