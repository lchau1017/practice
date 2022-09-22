package academy.learnprogramming.hashtable.linearProbing.removingItems

class SimpleHashtable {
    private val hashtable: Array<StoredEmployee?>

    init {
        hashtable = arrayOfNulls(10)
    }

    fun put(key: String, employee: Employee) {
        var hashedKey = hashKey(key)
        if (occupied(hashedKey)) {
            val stopIndex = hashedKey
            if (hashedKey == hashtable.size - 1) {
                hashedKey = 0
            } else {
                hashedKey++
            }
            while (occupied(hashedKey) && hashedKey != stopIndex) {
                hashedKey = (hashedKey + 1) % hashtable.size
            }
        }
        if (occupied(hashedKey)) {
            println("Sorry, there's already an employee at position $hashedKey")
        } else {
            hashtable[hashedKey] = StoredEmployee(key, employee)
        }
    }

    operator fun get(key: String): Employee? {
        val hashedKey = findKey(key)
        return if (hashedKey == -1) {
            null
        } else hashtable[hashedKey]!!.employee
    }

    fun remove(key: String): Employee? {
        val hashedKey = findKey(key)
        if (hashedKey == -1) {
            return null
        }
        val employee = hashtable[hashedKey]!!.employee
        hashtable[hashedKey] = null
        return employee
    }

    private fun hashKey(key: String): Int {
        return key.length % hashtable.size
    }

    private fun findKey(key: String): Int {
        var hashedKey = hashKey(key)
        if (hashtable[hashedKey] != null && hashtable[hashedKey]!!.key == key) {
            return hashedKey
        }
        val stopIndex = hashedKey
        if (hashedKey == hashtable.size - 1) {
            hashedKey = 0
        } else {
            hashedKey++
        }
        while (hashedKey != stopIndex && hashtable[hashedKey] != null &&
            hashtable[hashedKey]!!.key != key
        ) {
            hashedKey = (hashedKey + 1) % hashtable.size
        }
        return if (hashtable[hashedKey] != null && hashtable[hashedKey]!!.key == key) {
            hashedKey
        } else {
            -1
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