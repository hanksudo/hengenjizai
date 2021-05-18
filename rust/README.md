# Rust

## Installation

```bash
# install
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

# Update Rust toolchains and rustup
rustup update

# install component rustfmt
rustup component add rustfmt

# check version
rustc --version
cargo --version
rustup --version
```

## Execute

```bash
# single file
rustc hello.rs
./hello

# project
cargo new project_name
cargo build

# Execute and build if source code modified before run
cargo run

# Build only
cargo build

# Building for Release
cargo build --release

# Check binary
cargo check

# Update dependencies listed in Cargo.lock
cargo update
    
# documentation
cargo doc --open
```

## Troubleshooting

> error: failed to get `xxx` as a dependency of package

add this to ~/.cargo/config

```toml
[net]
git-fetch-with-cli = true
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
- [Rust-101: main.rs](https://www.ralfj.de/projects/rust-101/main.html)
- [awesome-rust: A curated list of Rust code and resources.](https://github.com/rust-unofficial/awesome-rust)
- [Rust Cookbook](https://rust-lang-nursery.github.io/rust-cookbook/)
- https://lib.rs
- [Rust Language Cheat Sheet](https://cheats.rs/)
- [Rust Tips and Tricks :: Jon Gjengset](https://thesquareplanet.com/blog/rust-tips-and-tricks/)
