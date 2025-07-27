package com.example.addressbook250727.data

import com.example.addressbook250727.data.Personal

class AddressBook {
    // attributes
    private var capacity: Int
    private var personals: Array<Personal?>
    private var length: Int

    // operations
    // primary constructor
    constructor(capacity: Int) {
        this.personals = arrayOfNulls(capacity)
        this.capacity = capacity
        this.length = 0
    }

    // secondary constructor
    constructor() : this(256)
    constructor(source: AddressBook) : this(source.capacity) {
        var personal: Personal?
        var i = 0
        while (i < source.length) {
            personal = source.personals[i]
            this.personals[i] = if (personal != null) Personal(personal) else null
            i++
        }
        this.length = source.length
    }

    // main operations
    fun record(name: String, address: String, telephoneNumber: String, emailAddress: String): Int {
        var index: Int
        val personal = Personal(name, address, telephoneNumber, emailAddress)
        if (this.length < this.capacity) {
            this.personals[this.length] = personal
            index = this.length
        } else {
            val personals = arrayOfNulls<Personal>(this.capacity + 1)
            var i = 0
            while (i < this.length) {
                personals[i] = this.personals[i]
                i++
            }
            personals[this.length] = personal
            this.personals = personals
            index = this.length
            this.capacity++
        }
        this.length++

        return index
    }

    fun getAt(index: Int): Personal? {
        return this.personals[index]
    }

    fun find(name: String): List<Int> {
        val indexes = mutableListOf<Int>()

        var personal: Personal?
        var i = 0
        while (i < this.length) {
            personal = this.personals[i]
            if (personal != null && personal.getName() == name) {
                indexes.add(i)
            }
            i++
        }

        return indexes
    }

    // getters
    fun getCapacity(): Int = capacity
    fun getLength(): Int = length
}

fun main() {
    // 1. create AddressBook
    val addressBook = AddressBook(2)
    // 2. record (1)
    var index = addressBook.record("HongKildong", "Seoul", "0101111", "hong@");
    var personal = addressBook.getAt(index)
    printPersonal(personal)
    // 3. record (2)
    index = addressBook.record("KoKildong", "Daejeon", "0102222", "ko@");
    personal = addressBook.getAt(index)
    printPersonal(personal)
    // copy constructor
    val newAddressBook = AddressBook(addressBook)
    var i = 0
    while (i < newAddressBook.getLength()) {
        personal = newAddressBook.getAt(i)
        printPersonal(personal) // hash Code is equal but reference is not equal
        i++;
    }
    // 4. record (3)
    index = addressBook.record("JungKildong", "Daegu", "0103333", "jung@");
    personal = addressBook.getAt(index)
    printPersonal(personal)
    // 5. record (4)
    index = addressBook.record("HongKildong", "Busan", "0104444", "hong2@");
    personal = addressBook.getAt(index)
    printPersonal(personal)
    // 6. find HongKildong
    val indexes = addressBook.find("HongKildong")
    i = 0;
    while (i < indexes.count()) {
        personal = addressBook.getAt(indexes[i]);
        printPersonal(personal)
        i++;
    }
}