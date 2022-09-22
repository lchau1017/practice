package academy.learnprogramming.doublylinkedlists

data class EmployeeNode(var employee: Employee) {
    var next: EmployeeNode? = null
    var previous: EmployeeNode? = null

    override fun toString(): String {
        return employee.toString()
    }
}