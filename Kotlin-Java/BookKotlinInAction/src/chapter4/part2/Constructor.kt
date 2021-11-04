package chapter4.part2

import javax.naming.Context


// secondary constructor
open class View {
    constructor(ctx: Context) {}

    constructor(ctx: Context, attr: String) {}
}

class MyButton : View {
    constructor(ctx: Context) : super (ctx)

    constructor(ctx: Context, attr: String) : super(ctx, attr)
}

class MyButton2 : View {
    constructor(ctx: Context) : this(ctx, "2")

    constructor(ctx: Context, attr: String) : super(ctx, attr)
}

// private
class Secretive private constructor() {}

// parent
open class User6(val nickname: String)
class TwitterUser(nickname: String) : User6(nickname)

open class Button
class RadioButton : Button()

/**
 * default
 */
class User4(val nickname: String, val isSubscribed: Boolean = true)
class User5(val nickname: String= "", val isSubscribed: Boolean = true)

/**
 * User = User2 = User3
 */
class User constructor(_nickname: String) {
    private val nickname: String
    init {
        nickname = _nickname
    }
}

class User2(_nickname: String) {
    val nickname = _nickname
}

class User3(val nickName: String)



