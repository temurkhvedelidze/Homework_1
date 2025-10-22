package com.example.homework1

fun main() {
    val savings = SavingsAccount("S101", "giorgi g.")
    val vip = VIPAccount("V202", "mariami a.")

    println("--- testireba ---")

    // დეპოზიტი
    savings.deposit(1000.0)
    vip.deposit(1000.0)

    // ტესტირება SavingsAccount-ის თანხის გამოტანის ლიმიტზე
    savings.withdraw(600.0) // Fails
    savings.withdraw(500.0) // Succeeds

    // ტესტირება VIPAccount-ის თანხის გამოტანააზე
    vip.withdraw(100.0) // Succeeds

    // საბოლოო ინფორმაცია
    savings.printInfo()
    vip.printInfo()
}


open class Account(val accountNumber: String, val ownerName: String) {
    private var balance: Double = 0.0

    fun getBalance(): Double {
        return balance
    }

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("depoziti: $amount$. amjamindeli balansi: $balance$.")
        }
    }

    open fun withdraw(amount: Double) {
        if (amount > 0 && balance >= amount) {
            balance -= amount
            println("gatanili tanxa: $amount$. amjamindeli balansi: $balance$.")
        } else {
            println("shecdoma: ar aris sakmarisi tanxa!")
        }
    }

    fun printInfo() {
        println("angarishi #$accountNumber, mflobeli: $ownerName, balansi: $balance$")
    }
}

class SavingsAccount(accountNumber: String, ownerName: String) : Account(accountNumber, ownerName) {
    override fun withdraw(amount: Double) {
        if (amount > 500) {
            println("shecdoma: tanxis gamotanis limiti 500$-ia!")
        } else {
            super.withdraw(amount)
        }
    }
}

class VIPAccount(accountNumber: String, ownerName: String) : Account(accountNumber, ownerName) {
    val transactionFee: Double = 2.0

    override fun withdraw(amount: Double) {
        val totalAmount = amount + transactionFee
        if (getBalance() >= totalAmount) {
            super.withdraw(totalAmount)
        } else {
            println("shecdoma: ar aris sakmarisi tanxa!")
        }
    }
}