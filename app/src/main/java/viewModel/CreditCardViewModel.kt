package viewModel

import androidx.lifecycle.ViewModel
import java.util.*

// view model class to store non ui logic
class CreditCardViewModel : ViewModel() {

    /*
    created functions to
    validate credit card details
    */

    /*
    function to check card number
    and return true if
    range is between 16 to 19
    else returns false
    */
    fun validateCardNumber(cardNumber: String): Boolean {
        return cardNumber.length in 16..19
    }

    /*
    function to check card
    validity.

    returns true if the expired
    month and expired year
    greater than current month
    and current year.
    */
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


    /*
    function to check
    card cvv.

    returns true if card
    cvv has length 3
    otherwise returns false.
    */
    fun validateCreditCardCvv(securityNumber: String): Boolean {
        return securityNumber.length == 3
    }

    /*
    function to get current
    month and year using
    calendar
    class in android.
    */
    private fun getCurrentMonthYear(currentMonthSpec: Calendar): Pair<Int, Int> {
        val currentMonth = currentMonthSpec[Calendar.MONTH] + 1
        val currentYear = currentMonthSpec[Calendar.YEAR] % 100
        return Pair(currentMonth, currentYear)
    }


    /*
      below three functions
      check last and first
      name as well as
      if all the details
      are correct
      display an alert
      dialog box.
     */

    fun validateFirstName(firstName: String): String {
        return firstName.trim()
    }

    fun validateLastName(lastName: String): String {
        return lastName.trim()
    }

    fun showAlertDialogBox(
        isCardNoCorrect: Boolean,
        isCardCvvCorrect: Boolean,
        isCardExpiryCorrect: Boolean,
        isFirstNameValid: String,
        isLastNameValid: String
    ): Boolean {
        if (!(isCardNoCorrect && isCardExpiryCorrect && isCardCvvCorrect)) {
            return false
        } else if (isFirstNameValid.isNotEmpty() && isLastNameValid.isNotEmpty()) {
            return true
        }
        return false
    }
}