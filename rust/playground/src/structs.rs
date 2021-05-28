struct User {
    username: String,
    email: String,
    active: bool,
    sign_in_count: u64,
}

fn add_user(email: String, username: String) -> User {
    User {
        email: email,
        username: username,
        active: true,
        sign_in_count: 1,
    }
}

fn add_user_shorthand(email: String, username: String) -> User {
    User {
        email,
        username,
        active: true,
        sign_in_count: 1,
    }
}

pub fn run() {
    let user1 = User {
        email: String::from("hank@myemail.com"),
        username: String::from("hanksudo"),
        active: true,
        sign_in_count: 1,
    };

    let user2 = add_user(String::from("hank2@myemail.com"), String::from("hank2"));

    let user3 = add_user_shorthand(String::from("hank3@myemail.com"), String::from("hank3"));
    let user_by_struct_update = User {
        email: String::from("new-email@gmail.com"),
        username: String::from("new-useranme"),
        ..user1
    };

    print_user_info(user1);
    print_user_info(user2);
    print_user_info(user3);
    print_user_info(user_by_struct_update);
}

fn print_user_info(user: User) {
    println!(
        "{username} <{email}> active: {active}, {sign_in_count}",
        username = user.username,
        email = user.email,
        active = user.active,
        sign_in_count = user.sign_in_count
    );
}
