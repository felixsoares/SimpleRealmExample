package com.felix.simplerealmexample.repository

import com.felix.simplerealmexample.model.Person

/**
 * Created by Felix on 13/04/2018.
 */

interface IPersonRepository {
    fun saveOrUpdate(person: Person): Boolean
    fun delete(id: Int): Boolean
    fun findOne(id: Int): Person
    fun findAll(): List<Person>
}
