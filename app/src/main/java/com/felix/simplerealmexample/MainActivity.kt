package com.felix.simplerealmexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.felix.simplerealmexample.model.Person
import com.felix.simplerealmexample.repository.IPersonRepository
import com.felix.simplerealmexample.repository.PersonRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val personRepository: IPersonRepository = PersonRepository()
    var persons = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = resources.getStringArray(R.array.names)
        val ages = resources.getStringArray(R.array.ages)
        val nicknames = resources.getStringArray(R.array.nicknames)

        btnAdd.setOnClickListener {
            val person = Person(
                    names[Random().nextInt(names.size)],
                    nicknames[Random().nextInt(nicknames.size)],
                    ages[Random().nextInt(ages.size)].toInt()
            )

            if (personRepository.saveOrUpdate(person)) {
                persons.add(person)
                persons.goToString()
            }
        }

        btnRemove.setOnClickListener {
            if (persons.size > 0) {
                val person = persons[Random().nextInt(persons.size)]
                if (personRepository.delete(person.id)) {
                    persons.remove(person)
                    persons.goToString()
                }
            }
        }

        btnFindAll.setOnClickListener {
            persons.addAll(personRepository.findAll())
            persons.goToString()
        }

        btnFindOne.setOnClickListener {
            if (persons.size > 0) {
                val person = persons[Random().nextInt(persons.size)]
                persons.clear()
                persons.add(person)
                persons.goToString()
            }
        }

        persons.addAll(personRepository.findAll())
        persons.goToString()
    }

    fun MutableList<Person>.goToString() {
        var str = ""

        for (i in 0..(this.size - 1)) {
            str += this[i].toString() + "\n"
        }

        txtPersons.text = str
    }
}
