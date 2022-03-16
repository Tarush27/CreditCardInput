package com.example.creditcardinput

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.creditcardinput.databinding.CreditCardDetailsActivityMainBinding
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
            validateCreditCard()
        }
    }

    private fun validateCreditCard() {
        val cardNumber: String =
            creditCardDetailsActivityMainBinding.creditCardNumberTextView.text.toString()
        if (!creditCardViewModel.validateCardNumber(cardNumber)) {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.error =
                getString(R.string.invalid_card_number)

        } else {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.helperText =
                getString(R.string.valid_card_number)
        }
        val monthAndYear: String =
            creditCardDetailsActivityMainBinding.creditCardExpiryTextView.text.toString()
        if (!creditCardViewModel.validateExpiryMonthAndYear(monthAndYear)) {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.error =
                getString(R.string.incorrect_month_year)
        } else {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.helperText =
                getString(R.string.correct_month_year)
        }

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

}