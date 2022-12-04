function createAppUser(database, username, password) {

    db = db.getSiblingDB(database);
    if (db.getUser(username) == null) {
        db.createUser(
            {
                user: username,
                pwd: password,
                roles: [
                    { role: "readWrite", db: database }
                ]
            }
        );
    } else {
        print(`User ${username} already exists, nothing to do.`);
    }
}

createAppUser("hexagon-basics", "hex4566", "3553dgbaf4");
