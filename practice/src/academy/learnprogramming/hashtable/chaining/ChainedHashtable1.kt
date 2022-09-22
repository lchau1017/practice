package academy.learnprogramming.hashtable.chaining

import java.util.*

class ChainedHashtable {
    private val hashtable: Array<LinkedList<StoredEmployee>> = arrayOf()

    init {
        for (i in hashtable.indices) {
            hashtable[i] = LinkedList()
        }
    }

    fun put(key: String, employee: Employee) {
        val hashedKey = hashKey(key)
        hashtable[hashedKey].add(StoredEmployee(key, employee))
    }

    operator fun get(key: String): Employee? {
        val hashedKey = hashKey(key)
        val iterator: ListIterator<StoredEmployee> = hashtable[hashedKey].listIterator()
        var employee: StoredEmployee? = null
        while (iterator.hasNext()) {
            employee = iterator.next()
            if (employee.key == key) {
                return employee.employee
            }
        }
        return null
    }

    fun remove(key: String): Employee? {
        val hashedKey = hashKey(key)
        val iterator: ListIterator<StoredEmployee> = hashtable[hashedKey].listIterator()
        var employee: StoredEmployee? = null
        var index = -1
        while (iterator.hasNext()) {
            employee = iterator.next()
            index++
            if (employee.key == key) {
                break
            }
        }
        return if (employee == null) {
            null
        } else {
            hashtable[hashedKey].removeAt(index)
            employee.employee
        }
    }

    private fun hashKey(key: String): Int {
        //return key.length() % hashtable.length;
        return Math.abs(key.hashCode() % hashtable.size)
    }

    fun printHashtable() {
        for (i in hashtable.indices) {
            if (hashtable[i].isEmpty()) {
                println("Position $i: empty")
            } else {
                print("Position $i: ")
                val iterator: Iterator<StoredEmployee> = hashtable[i].iterator()
                while (iterator.hasNext()) {
                    print(iterator.next().employee)
                    print("->")
                }
                println("null")
            }
        }
    }
}