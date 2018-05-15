package com.felix.simplerealmexample.model

import io.realm.RealmObject

/**
 * Created by Felix on 13/04/2018.
 */

open class Person(
        var name: String? = null,
        var nickName: String? = null,
        var age: Int? = null
) : RealmObject() {

    var id: Int = 0

    override fun toString(): String {
        return "Hy, my name is $name and i have $age years old, also you can call me by $nickName"
    }
}
