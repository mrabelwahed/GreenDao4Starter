package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {


    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.ramadan.greendaodemo.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,  "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity user = addUser(schema);
        Entity repo = addRepo(schema);

        Property userId = repo.addLongProperty("userId").notNull().getProperty();
        user.addToMany(repo, userId, "userRepos");
    }

    private static Entity addUser(final Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("name").notNull();
        user.addShortProperty("age");

        return user;
    }

    private static Entity addRepo(final Schema schema) {
        Entity repo = schema.addEntity("Repo");
        repo.addIdProperty().primaryKey().autoincrement();
        repo.addStringProperty("title").notNull();
        repo.addStringProperty("language");
        repo.addIntProperty("watchers_count");

        return repo;
    }
}
