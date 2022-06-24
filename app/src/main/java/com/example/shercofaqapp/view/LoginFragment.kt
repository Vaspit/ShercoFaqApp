package com.example.shercofaqapp.view

import android.content.Context
import android.content.IntentSender
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private var isLoggedIn by Delegates.notNull<Boolean>()
    private lateinit var googleSignInClient: GoogleSignInClient
    private val TAG = "LOGIN_FRAGMENT"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val sharedPref = requireActivity()
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        var isLoggingIn = true

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        (activity as AppCompatActivity?)?.supportActionBar?.title = "Sherco Faq App"
        //Log in / sign up logic
        isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            findNavController()
                .navigate(R.id.action_loginFragment_to_garageFragment)
        }

        setUI(isLoggingIn)

        binding.loginRegisterTextView.setOnClickListener {
            isLoggingIn = !isLoggingIn
            setUI(isLoggingIn)
        }

        binding.signUpLogInButton.setOnClickListener {
            when {
                //Check emailTextEditText
                TextUtils.isEmpty(binding.emailTextInputEditText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(requireContext(),
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //Check passwordTextEditText
                TextUtils.isEmpty(binding.passwordTextInputEditText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(requireContext(),
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = binding.emailTextInputEditText.text.toString().trim { it <= ' ' }
                    val password: String = binding.passwordTextInputEditText.text.toString().trim { it <= ' ' }

                    if (isLoggingIn) { //when logging in
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                //If the registration is successfully done
                                if (task.isSuccessful) {
                                    isLoggedIn = true
                                    editor.putBoolean("isLoggedIn", isLoggedIn)
                                    editor.apply()
                                    Navigation.findNavController(requireView())
                                        .navigate(R.id.action_loginFragment_to_garageFragment)
                                } else {
                                    Toast.makeText(requireContext(),
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else { //when creating user
                        val confirmPassword: String = binding.confirmPasswordTextInputEditText.text.toString().trim { it <= ' ' }
                        val userName: String = binding.nameTextInputEditText.text.toString().trim { it <= ' ' }

                        //Check the password was put correctly
                        if (password == confirmPassword) {
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    //If the registration is successfully done
                                    if (task.isSuccessful) {
                                        val firebaseUser: FirebaseUser = task.result!!.user!!
                                        val database = Firebase.database

                                        createUserInDatabase(
                                            database,
                                            firebaseUser,
                                            userName,
                                            email
                                        )

                                        isLoggedIn = true

                                        editor.putBoolean("isLoggedIn", isLoggedIn)
                                        editor.apply()
                                        //Go to GarageFragment
                                        Navigation.findNavController(requireView())
                                            .navigate(R.id.action_loginFragment_to_garageFragment)
                                    } else {
                                        Toast.makeText(requireContext(),
                                            task.exception!!.message.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(requireContext(),
                                "The password and confirmed password are not equal!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }

        binding.signUpWithGoogleButton.setOnClickListener {
            Toast.makeText(requireContext(), "Is not implemented yet", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

    private fun setUI(isLoggingIn: Boolean) {
        if (isLoggingIn) {
            binding.signUpLogInTextView.setText(R.string.log_in_to_continue)
            binding.nameTextInputLayout.visibility = View.GONE
            binding.confirmPasswordTextInputLayout.visibility = View.GONE
            binding.signUpLogInButton.setText(R.string.log_in)
            binding.haveAccountTextView.setText(R.string.do_not_have_an_account)
            binding.loginRegisterTextView.setText(R.string.register)
        } else {
            binding.signUpLogInTextView.setText(R.string.sign_up_to_continue)
            binding.nameTextInputLayout.visibility = View.VISIBLE
            binding.confirmPasswordTextInputLayout.visibility = View.VISIBLE
            binding.signUpLogInButton.setText(R.string.sign_up)
            binding.haveAccountTextView.setText(R.string.already_have_an_account)
            binding.loginRegisterTextView.setText(R.string.log_in)
        }
    }

    private fun createUserInDatabase(
        database: FirebaseDatabase,
        firebaseUser: FirebaseUser,
        userName: String,
        userEmail: String
    ) {
        database.getReference("users")
            .child(firebaseUser.uid)
            .child("userName").setValue(userName)

        database.getReference("users")
            .child(firebaseUser.uid)
            .child("userEmail").setValue(userEmail)

        database.getReference("users")
            .child(firebaseUser.uid)
            .child("userProfileImage").setValue(R.drawable.default_profile_icon)

        database.getReference("users")
            .child(firebaseUser.uid)
            .child("bikes").setValue(listOf("My first bike"))
    }
}