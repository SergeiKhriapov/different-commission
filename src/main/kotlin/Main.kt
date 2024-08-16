package ru.netology

fun main() {
    println(calculateCommission(cardType = "Mastercard", transferAmount = 150_000.0))
    println(calculateCommission(cardType = "Visa", transferAmount = 4666.0))
    println(calculateCommission(cardType = "Visa", transferAmount = 4667.0))
    println(calculateCommission(transferAmount = 150_000.0))

}

fun calculateCommission(
    cardType: String = "Мир",
    monthlyTransfers: Double = 0.0,
    transferAmount: Double
): Double {
    val noCommissionMastercardMonthlyLimit = 75_000.0
    val mastercardCommissionRate = 0.006
    val mastercardCommissionFlatFee = 20.0
    val visaCommissionRate = 0.0075
    val visaMinCommission = 35.0
    val maxDailyTransfer = 150_000.0
    val maxMonthlyTransfer = 600_000.0
    if (transferAmount > maxDailyTransfer) {
        println("Операция заблокирована: суточный лимит превышен.")
        return -1.0
    }
    if (monthlyTransfers + transferAmount > maxMonthlyTransfer) {
        println("Операция заблокирована: месячный лимит превышен.")
        return -1.0
    }
    return when (cardType) {
        "Mastercard" -> {
            if (monthlyTransfers + transferAmount <= noCommissionMastercardMonthlyLimit) {
                0.0
            } else {
                val excessAmount = transferAmount - noCommissionMastercardMonthlyLimit
                excessAmount * mastercardCommissionRate + mastercardCommissionFlatFee
            }
        }

        "Visa" -> {
            val commission = transferAmount * visaCommissionRate
            if (commission < visaMinCommission) visaMinCommission else commission
        }

        "Мир" -> 0.0
        else -> {
            println("Неизвестный тип карты.")
            -1.0
        }
    }
}