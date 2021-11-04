package chapter4.part2

interface Member {
    val nickname: String
}

interface Member2 {
    val email: String
    val nickname: String
        get() = email.substringBefore('@')
}

class SpecialMember(override val email: String) : Member2 {
//    override val nickname: String = "override property with getter"
}

class PrivateMember(override val nickname: String) : Member

class SubscribingMember(val email: String) : Member {
    override val nickname: String
        get() =  email.substringBefore('@')
}

class FaceBookMember(val accountId: Int) : Member {
    override val nickname: String = getFacebookName(accountId)
}

private fun getFacebookName(accountId: Int): String {
    return "FB name"
}