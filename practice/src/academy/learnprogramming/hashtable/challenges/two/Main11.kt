package academy.learnprogramming.hashtable.challenges.two

import java.util.*
import java.util.function.Consumer

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val employees = LinkedList<Employee>()
        employees.add(Employee("Jane", "Jones", 123))
        employees.add(Employee("John", "Doe", 5678))
        employees.add(Employee("Mike", "Wilson", 45))
        employees.add(Employee("Mary", "Smith", 5555))
        employees.add(Employee("John", "Doe", 5678))
        employees.add(Employee("Bill", "End", 3948))
        employees.add(Employee("Jane", "Jones", 123))
        employees.forEach(Consumer { e: Employee? -> println(e) })
        val employeeTable = HashMap<Int, Employee>()
        val iterator: ListIterator<Employee> = employees.listIterator()
        val remove: MutableList<Employee> = ArrayList()
        while (iterator.hasNext()) {
            val employee = iterator.next()
            if (employeeTable.containsKey(employee.id)) {
                remove.add(employee)
            } else {
                employeeTable[employee.id] = employee
            }
        }
        for (employee in remove) {
            employees.remove(employee)
        }
        println("-------------------------")
        employees.forEach(Consumer { e: Employee? -> println(e) })


//        int[] nums = new int[10];
//        int[] numsToAdd = { 59382, 43, 6894, 500, 99, -58 };
//        for (int i = 0; i < numsToAdd.length; i++) {
//            nums[hash(numsToAdd[i])] = numsToAdd[i];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
    }

    fun hash(value: Int): Int {
        return Math.abs(value % 10)
    }
}