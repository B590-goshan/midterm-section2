package com.example.midterm_section2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.midterm_section2.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


private const val TAG = "LoginFragment"


class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding?=null
    private val binding
        get() = checkNotNull(_binding){
                " Cannot access binding because it is null. "
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val auth=FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            goToPostsScreen()
        }
        binding.btnLogin.setOnClickListener {
            binding.btnLogin.isEnabled = false
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this.context, "Email/Password cannot be empty", Toast.LENGTH_SHORT)
                    .show()
                binding.btnLogin.isEnabled = true
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
                binding.btnLogin.isEnabled = true
                if(task.isSuccessful){
                    Toast.makeText(this.context,"Success!", Toast.LENGTH_SHORT).show()
                    goToPostsScreen()
                }else{
                    Log.e(TAG, "SignInWithEmail failed", task.exception)
                    Toast.makeText(this.context,"Authentication failed", Toast.LENGTH_SHORT).show()
                }

            }
        }
        return binding.root
    }
    private fun goToPostsScreen() {
        try {
            findNavController().navigate(R.id.navigateToPosts)
        } catch (e: Exception) {
            Log.e(TAG, "Navigation error: ${e.message}")
        }
    }

}