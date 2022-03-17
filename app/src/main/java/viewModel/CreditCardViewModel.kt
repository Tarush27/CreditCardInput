package viewModel

import androidx.lifecycle.ViewModel
import java.util.*

class CreditCardViewModel : ViewModel() {

    fun validateCardNumber(cardNumber: String): Boolean {
        return cardNumber.length in 16..19
    }

    fun validateExpiryMonthAndYear(expiredMonthYear: String): Boolean {
        val expDateParts: Array<String> = expiredMonthYear.split("/").toTypedArray()
        if (expDateParts.size != 2 || expDateParts[0].isEmpty() || expDateParts[1].isEmpty()) {
            return false
        }
        val expMonth = expDateParts[0].toInt()
        val expYear = expDateParts[1].toInt()
        if (expMonth !in 1..12) {
            return false
        }
        val currentMonthSpec = Calendar.getInstance(Locale.getDefault())
        val (currentMonth, currentYear) = getCurrentMonthYear(currentMonthSpec)
        return expMonth > currentMonth && expYear > currentYear
    }

    fun validateCreditCardCvv(securityNumber: String): Boolean {
        return securityNumber.length == 3
    }

    private fun getCurrentMonthYear(currentMonthSpec: Calendar): Pair<Int, Int> {
        val currentMonth = currentMonthSpec[Calendar.MONTH] + 1
        val currentYear = currentMonthSpec[Calendar.YEAR] % 100
        return Pair(currentMonth, currentYear)
    }

    fun validateFirstName(firstName: String): String{
        return firstName.trim()
    }
    fun validateLastName(lastName: String): String{
        return lastName.trim()
    }

}