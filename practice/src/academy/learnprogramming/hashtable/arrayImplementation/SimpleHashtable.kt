package academy.learnprogramming.hashtable.arrayImplementation

class SimpleHashtable {
    private val hashtable: Array<Employee?>

    init {
        hashtable = arrayOfNulls(10)
    }

    fun put(key: String, employee: Employee?) {
        val hashedKey = hashKey(key)
        if (hashtable[hashedKey] != null) {
            println("Sorry, there's already an employee at position $hashedKey")
        } else {
            hashtable[hashedKey] = employee
        }
    }

    operator fun get(key: String): Employee? {
        val hashedKey = hashKey(key)
        return hashtable[hashedKey]
    }

    private fun hashKey(key: String): Int {
        return key.length % hashtable.size
    }

    fun printHashtable() {
        for (i in hashtable.indices) {
            println(hashtable[i])
        }
    }
}