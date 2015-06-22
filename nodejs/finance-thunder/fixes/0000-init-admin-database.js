// init admin database
// run in mongo shell
// refer https://github.com/jedireza/drywall/blob/master/README.md
// $ mongo load script.js

/**
 * 1. check admingroups
 * 2. check account & admin
 * 3. check user
 */

// main program

{
    var db = connect('localhost/finance-thunder');

    // drop all
    // db.admingroups.drop();
    // db.admins.drop();
    // db.users.drop();
    // db.accounts.drop();

    // 1. check admingroups - if exists will be ignored.
    db.admingroups.save({ _id: 'root', name: 'Root' });

    // 2. check account & admin
    var usersToInit = [{
        username: 'hank',
        email: 'drapho@gmail.com',
        first: 'Hank',
        last: 'Wang',
        full: 'Hank Wang'
    },];

    for(i in usersToInit) {
        var newUser = usersToInit[i];

        var newAdmin = {
            name: {
                first: newUser.first,
                last: newUser.last,
                full: newUser.full
            }, groups: ['root']
        };

        var rootAdmin = db.admins.findAndModify({
            query: newAdmin,
            update: newAdmin,
            upsert: true,
            new: true
        });

        user = db.users.findOne({ username: newUser.username });
        if (user) {
            if (!user.roles.admin) {
                user.roles.admin = rootAdmin._id;
                db.users.save(user);

                print('username "' + user.username + '" is exists, setting admin role.');
            }
        } else {
            print('username "' + newUser.username + '" is not exists, create new user with admin role.');
            db.users.save({
                username: newUser.username,
                email: newUser.email,
                isActive: 'yes',
                // use default password: 123456
                password: "f511f36eaa184e2fb7ad63824c1056c8a25d150afe62735a26c14c58a02dc9bfb95a904d68887143f4096a6053aca28701a4ee1fd02a64323a0ebce89aed8e34",
                roles: { admin: rootAdmin._id }
            });
            user = db.users.findOne({ username: newUser.username });
        }

        // link user back to admin
        rootAdmin.user = {
            id: user._id,
            name: user.username
        };
        db.admins.save(rootAdmin);

        var newAccount = {
            search : [
                user.username
            ],
            userCreated : {
                name : ""
            },
            notes : [ ],
            statusLog : [ ],
            status : {
                userCreated : {
                    name : ""
                },
               name : ""
            },
            zip : "",
            phone : "",
            company : "",
            name : {
                full : rootAdmin.name.full,
                last : "",
                middle : "",
                first : ""
            },
            user: {
                id: user._id,
                name: user.username
            }
        };

        var rootAccount = db.accounts.findAndModify({
            query: newAccount,
            update: newAccount,
            upsert: true,
            new: true
        });

        if (!user.roles.account) {
            user.roles.account = rootAccount._id;
            db.users.save(user);
            print('username "' + user.username + '" is exists, setting account role.');
        } else {
            print('username "' + user.username + '" is exists with admin and account role.');
        }

    }
    print("Info: Init admin db finished.");
}
