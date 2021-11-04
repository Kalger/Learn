package constructor


fun main() {
    Primary()

    // without no-parameter constructor
//    Secondary()
}

class Primary{
}

class Secondary {
    constructor(s: String)

    constructor(num: Int)
}