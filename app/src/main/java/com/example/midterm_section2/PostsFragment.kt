package com.example.midterm_section2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.midterm_section2.databinding.FragmentPostsBinding
import com.google.firebase.auth.FirebaseAuth

class PostsFragment : Fragment() {
    private var _binding: FragmentPostsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null."
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut() // Sign out the user
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.loginFragment) // Use the fragment ID directly
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
