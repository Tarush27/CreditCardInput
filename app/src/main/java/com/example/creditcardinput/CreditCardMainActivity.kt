package com.example.creditcardinput

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.creditcardinput.databinding.CreditCardDetailsActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import viewModel.CreditCardViewModel

class CreditCardMainActivity : AppCompatActivity() {
    /*
    created and initialized view binding object
    using lateinit keyword.

    instantiated view model object using delegation.
     */
    private lateinit var creditCardDetailsActivityMainBinding: CreditCardDetailsActivityMainBinding
    private val creditCardViewModel: CreditCardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        inflated credit card details xml file
        through view binding's inflate method.

        getting the root view from the binding
        xml file and passing into set content
        view method.
         */
        creditCardDetailsActivityMainBinding =
            CreditCardDetailsActivityMainBinding.inflate(layoutInflater)
        val creditCardRootView = creditCardDetailsActivityMainBinding.root
        setContentView(creditCardRootView)

        // setting click listener on the button
        // referred from binding xml file.
        creditCardDetailsActivityMainBinding.submitPaymentBtn.setOnClickListener {

            /*
            function to check the credit card
            details.
             */
            validateCreditCard(it)
        }
    }


    private fun validateCreditCard(view: View) {

        /*
        getting text in card number text view
        and storing in credit card number object.

        storing the value of validate card number
        function invoked through view model
        inside is card valid object.

        invoking credit card number function
        passing the boolean argument.
         */
        val creditCardNumber =
            creditCardDetailsActivityMainBinding.creditCardNumberTextView.text.toString()
        val isCardValid = creditCardViewModel.validateCardNumber(
            creditCardNumber
        )
        creditCardNumber(isCardValid)

        /*
       getting text in card expiry text view
       and storing in month and year object.

       storing the value of validate expiry month
       and year function invoked through
       view model inside is expiry date
        valid object.
        calling expirationDate function passing in
        boolean argument.
        */
        val monthAndYear: String =
            creditCardDetailsActivityMainBinding.creditCardExpiryTextView.text.toString()
        val isExpiryDateValid = creditCardViewModel.validateExpiryMonthAndYear(monthAndYear)
        expirationDate(isExpiryDateValid)

        /*
       getting text in card security code text view
       and storing in credit card cvv object.

       storing the value of validate card cvv
       function invoked through view model
       inside is card cvv valid object.

       called credit card cvv function accepting
       boolean argument.
        */
        val creditCardCvv: String =
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextView.text.toString()
        val isCardCvvValid = creditCardViewModel.validateCreditCardCvv(creditCardCvv)
        creditCardCvv(isCardCvvValid)

        /*
       getting text in first name text view
       and storing in first name object.

       storing the value of validate first name
       function invoked through view model
       inside is first name true or false object.

       invoking first name function passing in
       string as an argument.
        */
        val firstName =
            creditCardDetailsActivityMainBinding.creditCardFirstNameTextView.text.toString()
        val isFirstNameTrueOrFalse = creditCardViewModel.validateFirstName(
            firstName
        )
        firstName(isFirstNameTrueOrFalse)

        /*
       getting text in last name text view
       and storing in last name object.

       storing the value of validate last name
       function invoked through view model
       inside is last name true or false object.

       invoking last name function passing in
       string as an argument.
        */
        val lastName =
            creditCardDetailsActivityMainBinding.creditCardLastNameTextView.text.toString()
        val isLastNameTrueOrFalse = creditCardViewModel.validateLastName(
            lastName
        )
        lastName(isLastNameTrueOrFalse)

        /*
        invoking show alert dialog box
        function from view model
        which validates all the card details
        passed as an argument and displays the
        alert dialog box on successful
        validation else dialog box not shown.
         */
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

    // function to validate last name accepting a string as an argument.
    private fun lastName(isLastNameCorrect: String) {
        if (isLastNameCorrect.isEmpty()) {
            creditCardDetailsActivityMainBinding.creditCardLastNameTextInputLayout.error =
                getString(R.string.enter_last_name)

        } else {
            creditCardDetailsActivityMainBinding.creditCardLastNameTextInputLayout.error = null
        }
    }

    // function to validate first name accepting a string as an argument.
    private fun firstName(isFirstNameCorrect: String) {
        if (isFirstNameCorrect.isEmpty()) {
            creditCardDetailsActivityMainBinding.creditCardFirsNameTextInputLayout.error =
                getString(R.string.enter_first_name)
        } else {
            creditCardDetailsActivityMainBinding.creditCardFirsNameTextInputLayout.error = null
        }
    }

    // function to validate card cvv accepting a boolean as an argument.
    private fun creditCardCvv(isCardCvvValid: Boolean) {
        if (!isCardCvvValid) {
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextInputLayout.error =
                getString(R.string.invalid_cvv)
        } else {
            creditCardDetailsActivityMainBinding.creditCardSecurityCodeTextInputLayout.helperText =
                getString(R.string.valid_cvv)
        }
    }

    // function to validate expiry date accepting a boolean as an argument.
    private fun expirationDate(isExpirationDateValid: Boolean) {
        if (!isExpirationDateValid) {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.error =
                getString(R.string.incorrect_month_year)
        } else {
            creditCardDetailsActivityMainBinding.creditCardExpiryTextInputLayout.helperText =
                getString(R.string.correct_month_year)
        }
    }

    // function to validate credit card number accepting a boolean as an argument.
    private fun creditCardNumber(isCardValid: Boolean) {
        if (!isCardValid) {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.error =
                getString(R.string.invalid_card_number)

        } else {
            creditCardDetailsActivityMainBinding.creditCardTextInputLayout.helperText =
                getString(R.string.valid_card_number)
        }
    }

    // function to create material alert dialog box.
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