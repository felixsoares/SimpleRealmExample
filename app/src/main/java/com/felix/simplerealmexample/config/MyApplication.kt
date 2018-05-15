package com.felix.simplerealmexample.config

import android.app.Application

import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(MyMigration())
                .name("MY REALM")
                .build()

        Realm.setDefaultConfiguration(config)
    }

}