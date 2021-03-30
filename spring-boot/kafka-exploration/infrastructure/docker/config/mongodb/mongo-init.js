db.createUser(
    {
        user: "projections",
        pwd: "45aVg1",
        roles: [
            {
                role: "readWrite",
                db: "read-model-projections"
            }
        ]
    }
);