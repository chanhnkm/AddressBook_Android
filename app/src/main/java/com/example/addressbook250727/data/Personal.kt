package com.example.addressbook250727.data

class Personal {
    // attributes
    private var name: String
    private var address: String
    private var telephoneNumber: String
    private var emailAddress: String

    // operations
    // primary constructor
    constructor(name: String, address: String, telephoneNumber: String, emailAddress: String) {
        this.name = name
        this.address = address
        this.telephoneNumber = telephoneNumber
        this.emailAddress = emailAddress
    }

    // secondary constructor
    constructor() : this("", "", "", "")
    constructor(source: Personal) : this(
        source.name,
        source.address,
        source.telephoneNumber,
        source.emailAddress
    )

    // main operations
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Personal) return false

        var ret = false
        if (this.name == other.name && this.address == other.address &&
            this.telephoneNumber == other.telephoneNumber && this.emailAddress == other.emailAddress
        ) {
            ret = true
        }
        return ret
    } // isNotEqual not needed

    // must defined because equals is overrided
    override fun hashCode(): Int {
        var result = this.name.hashCode()
        result = 31 * result + this.address.hashCode()
        result = 31 * result + this.telephoneNumber.hashCode()
        result = 31 * result + this.emailAddress.hashCode()
        return result
    }

    fun replace(other: Personal) {
        this.name = other.name
        this.address = other.address
        this.telephoneNumber = other.telephoneNumber
        this.emailAddress = other.emailAddress
    }

    // getters
    fun getName(): String = name
    fun getAddress(): String = address
    fun getTelephoneNumber(): String = telephoneNumber
    fun getEmailAddress(): String = emailAddress
}


/*
fun printPersonal(one: Personal?) {
    println("${one?.getName()} / ${one?.getAddress()} / ${one?.getTelephoneNumber()} / ${one?.getEmailAddress()}")
    println("hashCode: ${one.hashCode()}")
}
fun main() {
    // default constructor
    val me = Personal()
    printPersonal(me)
    // primary constructor
    val somebody = Personal("HongKildong", "Mapo, Seoul", "0101111", "hong@")
    printPersonal(somebody)
    // copy constructor
    val somesomebody = Personal(somebody)
    printPersonal(somesomebody)
    // equals
    var ret = somesomebody.equals(somebody)
    if (ret == true) {
        println("same person")
    }
    // equals operator
    if (somesomebody == somebody) {
        println("same person")
    }
    val meme = Personal("KimKildong", "Seodaemun, Seoul", "0102222", "kim@")
    printPersonal(meme)
    // replace
    me.replace(meme)
    printPersonal(me)
    // not equals
    if (me != somebody) {
        println("different person")
    }
}
*/