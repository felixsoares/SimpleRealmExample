package com.felix.simplerealmexample.config

import io.realm.DynamicRealm
import io.realm.RealmMigration


class MyMigration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

        // DynamicRealm exposes an editable schema
        val schema = realm.schema

        // Migrate to version 1: Add a new class.
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     private int age;
        //     // getters and setters left out for brevity
        // }

        // Migrate to version 2: Add a primary key + object references
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     @PrimaryKey
        //     private int age;
        //     private Dog favoriteDog;
        //     private RealmList<Dog> dogs;
        //     // getters and setters left out for brevity
        // }

        /*
        when (oldVersion.toInt()) {
            0 -> {
                schema.create("Person")!!
                        .addField("name", String::class.java)
                        .addField("age", Int::class.javaPrimitiveType)
                oldVersion.plus(1)
            }
            1 -> {
                schema.get("Person")!!
                        .addField("id", Long::class.javaPrimitiveType, FieldAttribute.PRIMARY_KEY)
                        .addRealmObjectField("favoriteDog", schema.get("Dog"))
                        .addRealmListField("dogs", schema.get("Dog"))
                oldVersion.plus(1)
            }
        }
        */
    }
}