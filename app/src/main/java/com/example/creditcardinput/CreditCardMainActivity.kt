package com.example.creditcardinput

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.creditcardinput.databinding.CreditCardDetailsActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import viewModel.CreditCardViewModel

class CreditCardMainActivity : AppCompatActivity() {
    private lateinit var creditCardDetailsActivityMainBinding: CreditCardDetailsActivityMainBinding
    private val creditCardViewModel: CreditCardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        creditCardDetailsActivityMainBinding =
            CreditCardDetailsActivityMainBinding.inflate(layoutInflater)
        val creditCardRootView = creditCardDetailsActivityMainBinding.root
        setContentView(creditCardRootView)
        creditCardDetailsActivityMainBinding.submitPaymentBtn.setOnClickListener {
            validateCreditCard(it)
        }
    }


    private fun validateCreditCard(view: View) {

        val creditCardNumber =
            creditCardDetailsActivityMainBinding.creditCardNumberTextView.text.toString()
        val isCardValid = creditCardViewModel.validateCardNumber(
            creditCardNumber
        )
        creditCardNumber(isCardValid)

        val monthAndYear: String =
            creditCardDetailsActivityMainBinding.creditCardExpiryTextView.text.toString()
        val isExpiryDateValid = creditCardViewModel.validateExpiryMonthAndYear(monthAndYear)
        expirationDate(isExpiryDateValid)

        val creditCardCvv: String =
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextView.text.toString()
        val isCardCvvValid = creditCardViewModel.validateCreditCardCvv(creditCardCvv)
        creditCardCvv(isCardCvvValid)

        val firstName =
            creditCardDetailsActivityMainBinding.creditCardFirstNameTextView.text.toString()
        val isFirstNameTrueOrFalse = creditCardViewModel.validateFirstName(
            firstName
        )
        firstName(isFirstNameTrueOrFalse)

        val lastName =
            creditCardDetailsActivityMainBinding.creditCardLastNameTextView.text.toString()
        val isLastNameTrueOrFalse = creditCardViewModel.validateLastName(
            lastName
        )
        lastName(isLastNameTrueOrFalse)

        if (creditCardViewModel.showAlertDialogBox(
                isCardValid,
                isCardCvvValid,
                isExpiryDateValid,
                isFirstNameTrueOrFalse,
                isLastNameTrueOrFalse
            )
        ) {
            showAlertDialogBox()
        }
    }

    private fun lastName(isLastNameCorrect: String) {
        if (isLastNameCorrect.isEmpty()) {
            creditCardDetailsActivityMainBinding.creditCardLastNameTextInputLayout.error =
                getString(R.string.enter_last_name)

        } else {
            creditCardDetailsActivityMainBinding.creditCardLastNameTextInputLayout.error = null
        }
    }

    private fun firstName(isFirstNameCorrect: String) {
        if (isFirstNameCorrect.isEmpty()) {
            creditCardDetailsActivityMainBinding.creditCardFirsNameTextInputLayout.error =
                getString(R.string.enter_first_name)
        } else {
            creditCardDetailsActivityMainBinding.creditCardFirsNameTextInputLayout.error = null
        }
    }

    private fun creditCardCvv(isCardCvvValid: Boolean) {
        if (!isCardCvvValid) {
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextInputLayout.error =
                getString(R.string.invalid_cvv)
        } else {
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextInputLayout.helperText =
                getString(R.string.valid_cvv)
        }
    }

    private fun expirationDate(isExpirationDateValid: Boolean) {
        if (!isExpirationDateValid) {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.error =
                getString(R.string.incorrect_month_year)
        } else {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.helperText =
                getString(R.string.correct_month_year)
        }
    }

    private fun creditCardNumber(isCardValid: Boolean) {
        if (!isCardValid) {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.error =
                getString(R.string.invalid_card_number)

        } else {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.helperText =
                getString(R.string.valid_card_number)
        }
    }

    private fun showAlertDialogBox() {
        MaterialAlertDialogBuilder(
            this
        )
            .setTitle(getString(R.string.payment_success))
            .setPositiveButton(
                getString(R.string.btn_ok)
            ) { p0, p1 -> p0?.dismiss() }
            .show()
    }

}