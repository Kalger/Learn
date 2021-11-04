package chapter3

class User(val id: Int, val name: String, val address: String)

fun saveUserOri(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }
    // save user to database
}

fun saveUserWithLocalMethod(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
    // save user to database
}

fun User.validateBeforeSave() {
    fun validate(value: String, filedName: String) {
        if (value.isEmpty()) throw IllegalArgumentException("Can't save user $id: empty $filedName")
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun saveUserWithExtLocalMethod(user: User) {
    user.validateBeforeSave()
    // save user to database
}
