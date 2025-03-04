package com.example.midterm_section2

import android.os.Bundle
import android.view.*
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

    private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up posts RecyclerView with ViewModel data
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                postViewModel.posts.collect { posts ->
                    binding.rvPosts.adapter = PostsAdapter(posts)
                }
            }
        }

        // Check Firebase Auth and navigate to login screen if user is not logged in
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            goToLoginScreen()
        }

        // Floating Action Button navigation to CreateFragment
        binding.fabCreate.setOnClickListener {
            this.findNavController().navigate(R.id.navigate_to_createFragment)
        }

        // Enable options menu for toolbar
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_post, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
                goToLoginScreen()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginScreen() {
        this.findNavController().navigate(R.id.to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
