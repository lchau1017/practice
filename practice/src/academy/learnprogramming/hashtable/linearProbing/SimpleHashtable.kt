package academy.learnprogramming.hashtable.linearProbing

import java.util.*

//Handle collision
/*
When the key is already occupied, we increment the hash value by one and then check the result in the index.
Ex: we want a value to an existing key to 5 but 5 has already a value, we increment 5 + 1 = 6
If 6 is occupied, increment and try if 7 is free and so on.... Linear fashion.
 */
class SimpleHashtable : Hashtable<Any?, Any?>() {
    private val hashtable: Array<StoredEmployee?>

    init {
        hashtable = arrayOfNulls(10)
    }

    fun put(key: String, employee: Employee) {
        var hashedKey = hashKey(key)
        if (occupied(hashedKey)) {
            //Linear probing
            //Set the very first probe
            val stopIndex = hashedKey //If array full, loop back around to 0 index
            if (hashedKey == hashtable.size - 1) {
                hashedKey = 0 //Set to zero, we loop, avoid ArrayOutOfBoundException
            } else {
                hashedKey++
            }
            while (occupied(hashedKey) && hashedKey != stopIndex) {
                hashedKey = (hashedKey + 1) % hashtable.size //10 % 10 = 0 go back to the array
            }
        }
        if (occupied(hashedKey)) { //Array is full
            println("Sorry, there's already an employee at position $hashedKey")
        } else {
            hashtable[hashedKey] = StoredEmployee(key, employee)
        }
    }

    //Linear probing
    operator fun get(key: String): Employee? {
        val hashedKey = findKey(key)
        return if (hashedKey == -1) {
            null
        } else hashtable[hashedKey]!!.employee
    }

    private fun hashKey(key: String): Int {
        return key.length % hashtable.size
    }

    private fun findKey(key: String): Int {
        var hashedKey = hashKey(key)
        if (hashtable[hashedKey] != null && hashtable[hashedKey]!!.key == key) {
            //Find our employee by comparing the hashKey and Raw key from 'StoredEmployee'
            return hashedKey
        }
        //Linear probing
        val stopIndex = hashedKey
        if (hashedKey == hashtable.size - 1) {
            hashedKey = 0
        } else {
            hashedKey++
        }
        while (hashedKey != stopIndex && hashtable[hashedKey] != null && hashtable[hashedKey]!!.key != key) {
            hashedKey = (hashedKey + 1) % hashtable.size
        }
        return if (stopIndex == hashedKey) {
            -1
        } else {
            hashedKey
        }
    }

    private fun occupied(index: Int): Boolean {
        return hashtable[index] != null
    }

    fun printHashtable() {
        for (i in hashtable.indices) {
            if (hashtable[i] == null) {
                println("empty")
            } else {
                println("Position " + i + ": " + hashtable[i]!!.employee)
            }
        }
    }
}