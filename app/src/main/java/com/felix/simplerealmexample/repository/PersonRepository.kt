package com.felix.simplerealmexample.repository

import com.felix.simplerealmexample.model.Person
import io.realm.Realm

/**
 * Created by Felix on 13/04/2018.
 */

class PersonRepository : IPersonRepository {

    private fun getId(realm: Realm): Int {
        val nextID = (realm.where(Person::class.java).max("id"))
        return if (nextID != null) {
            (nextID.toInt() + 1)
        } else {
            1
        }
    }

    override fun saveOrUpdate(person: Person): Boolean {
        val realm = Realm.getDefaultInstance()
        var isOk = false

        try {
            realm.beginTransaction()

            if (person.id == 0) {
                person.id = getId(realm)
            }

            realm.insertOrUpdate(person)
            realm.commitTransaction()
            isOk = true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            realm?.close()
        }

        return isOk
    }

    override fun delete(id: Int): Boolean {
        val realm = Realm.getDefaultInstance()
        var isOk = false

        try {
            realm.beginTransaction()
            val person = realm.where(Person::class.java).equalTo("id", id).findFirst()
            person!!.deleteFromRealm()
            realm.commitTransaction()
            isOk = true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            realm?.close()
        }

        return isOk
    }

    override fun findOne(id: Int): Person {
        val realm = Realm.getDefaultInstance()
        var person = Person()

        try {
            val personAux = realm.where(Person::class.java).equalTo("id", id).findFirst()
            if (personAux != null) {
                person = realm.copyFromRealm(personAux)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            realm?.close()
        }

        return person
    }

    override fun findAll(): List<Person> {
        val realm = Realm.getDefaultInstance()
        var persons = mutableListOf<Person>()

        try {
            val personsAux = realm.where(Person::class.java).findAll()
            if (personsAux != null) {
                persons = realm.copyFromRealm(personsAux)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            realm?.close()
        }

        return persons
    }

}
