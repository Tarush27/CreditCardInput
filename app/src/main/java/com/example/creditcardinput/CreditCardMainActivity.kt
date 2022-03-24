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
    val creditCardViewModel: CreditCardViewModel by viewModels()
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
        creditCardNumber()
        expirationDate()

        creditCardCvv()

        firstName()

        lastName()
        showAlertDialogBox()

    }

    private fun lastName() {
        val lastName = creditCardViewModel.validateLastName(
            creditCardDetailsActivityMainBinding.creditCardLastNameTextView.text.toString()
        )
        if (lastName.isEmpty()) {
            creditCardDetailsActivityMainBinding.creditCardLastNameTextInputLayout.error =
                getString(R.string.enter_last_name)

        } else {
            creditCardDetailsActivityMainBinding.creditCardLastNameTextInputLayout.error = null
        }
    }

    private fun firstName() {
        val firstName = creditCardViewModel.validateFirstName(
            creditCardDetailsActivityMainBinding.creditCardFirstNameTextView.text.toString()
        )
        if (firstName.isEmpty()) {
            creditCardDetailsActivityMainBinding.creditCardFirsNameTextInputLayout.error =
                getString(R.string.enter_first_name)
        } else {
            creditCardDetailsActivityMainBinding.creditCardFirsNameTextInputLayout.error = null
        }
    }

    private fun creditCardCvv() {
        val creditCardCvv: String =
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextView.text.toString()
        if (!creditCardViewModel.validateCreditCardCvv(creditCardCvv)) {
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextInputLayout.error =
                getString(R.string.invalid_cvv)
        } else {
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextInputLayout.helperText =
                getString(R.string.valid_cvv)
        }
    }

    private fun expirationDate() {
        val monthAndYear: String =
            creditCardDetailsActivityMainBinding.creditCardExpiryTextView.text.toString()
        if (!creditCardViewModel.validateExpiryMonthAndYear(monthAndYear)) {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.error =
                getString(R.string.incorrect_month_year)
        } else {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.helperText =
                getString(R.string.correct_month_year)
        }
    }

    private fun creditCardNumber() {
        val cardNumber: String =
            creditCardDetailsActivityMainBinding.creditCardNumberTextView.text.toString()
        if (!creditCardViewModel.validateCardNumber(cardNumber)) {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.error =
                getString(R.string.invalid_card_number)

        } else {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.helperText =
                getString(R.string.valid_card_number)
        }
    }

    private fun showAlertDialogBox() {
        MaterialAlertDialogBuilder(
            this,
        )
            .setTitle(getString(R.string.payment_success))
            .setPositiveButton(
                getString(R.string.btn_ok)
            ) { p0, p1 -> p0?.dismiss() }
            .show()
    }

}