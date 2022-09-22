package academy.learnprogramming.linkedlists

class EmployeeNode(var employee: Employee) {
    var next: EmployeeNode? = null

    override fun toString(): String {
        return employee.toString()
    }
}