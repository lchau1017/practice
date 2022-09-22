package academy.learnprogramming.stacks

class Employee(var firstName: String, var lastName: String, var id: Int) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val employee = o as Employee
        if (id != employee.id) return false
        return if (firstName != employee.firstName) false else lastName == employee.lastName
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + id
        return result
    }

    override fun toString(): String {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}'
    }
}