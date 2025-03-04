package com.example.midterm_section2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midterm_section2.databinding.FragmentPostsBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class PostsFragment : Fragment() {
    private var _binding: FragmentPostsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null."
        }

    private val postViewModel:PostViewModel by viewModels()
    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated (view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                postViewModel.posts.collect { posts ->
                    binding.rvPosts.adapter = PostsAdapter(posts)
                }
            }
        }
         val auth = FirebaseAuth.getInstance()
         if (auth.currentUser == null) {
             goToLoginScreen()
         }
        binding.btnLogout.setOnClickListener{
            auth.signOut()
            goToLoginScreen()
        }
        binding.fabCreate.setOnClickListener {
            this.findNavController().navigate(R.id.navigate_to_createFragment)
        }
   }
        private fun goToLoginScreen () {
            this.findNavController().navigate(R.id.to_loginFragment)
        }
   }