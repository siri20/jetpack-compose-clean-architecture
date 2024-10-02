package com.siri.core.data.model

data class UsersResponse(
    val limit: Int = 0,
    val skip: Int = 0,
    val total: Int = 0,
    val users: MutableList<User>?
)

data class User(
    val address: Address = Address(),
    val age: Int = 0,
    val bank: Bank = Bank(),
    val birthDate: String = "",
    val bloodGroup: String = "",
    val company: Company = Company(),
    val crypto: Crypto = Crypto(),
    val ein: String = "",
    val email: String = "",
    val eyeColor: String = "",
    val firstName: String = "",
    val gender: String = "",
    val hair: Hair = Hair(),
    val height: Double = 0.0,
    val id: Int = 0,
    val image: String = "",
    val ip: String = "",
    val lastName: String = "",
    val macAddress: String = "",
    val maidenName: String = "",
    val password: String = "",
    val phone: String = "",
    val role: String = "",
    val ssn: String = "",
    val university: String = "",
    val userAgent: String = "",
    val username: String = "",
    val weight: Double = 0.0
)

data class Address(
    val address: String = "",
    val city: String = "",
    val coordinates: Coordinates = Coordinates(),
    val country: String = "",
    val postalCode: String = "",
    val state: String = "",
    val stateCode: String = ""
)

data class Bank(
    val cardExpire: String = "",
    val cardNumber: String = "",
    val cardType: String = "",
    val currency: String = "",
    val iban: String = ""
)

data class Company(
    val address: Address = Address(),
    val department: String = "",
    val name: String = "",
    val title: String = ""
)

data class Crypto(
    val coin: String = "",
    val network: String = "",
    val wallet: String = ""
)

data class Hair(
    val color: String = "",
    val type: String = ""
)

data class Coordinates(
    val lat: Double = 0.0,
    val lng: Double = 0.0
)