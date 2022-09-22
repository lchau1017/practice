package academy.learnprogramming.hashtable.hastablesAndTheJDK

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val hashMap: MutableMap<String, Employee> = HashMap()
        hashMap["Jones"] = janeJones
        hashMap["Doe"] = johnDoe
        hashMap["Smith"] = marySmith
        //Employee employee = hashMap.put("Doe", mikeWilson);
        val employee = hashMap.putIfAbsent("Doe", mikeWilson)
        println(employee)
        println(hashMap.getOrDefault("someone", mikeWilson))
        println(hashMap.remove("Jones"))

//        System.out.println(hashMap.containsKey("Doe"));
//        System.out.println(hashMap.containsValue(janeJones));

//        Iterator<Employee> iterator = hashMap.values().iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        hashMap.forEach { (k: String, v: Employee) ->
            println(
                "Key = $k, Employee = $v"
            )
        }
    }
}