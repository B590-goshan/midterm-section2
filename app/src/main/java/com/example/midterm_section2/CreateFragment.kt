package com.example.midterm_section2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.midterm_section2.databinding.FragmentCreateBinding
import com.example.midterm_section2.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.toObject


private const val TAG = "CreateFragment"
class CreateFragment: Fragment() {
    private var _binding: FragmentCreateBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
    "Cannot access binding because it is null."
        }

    private var photoUri: Uri?=null
    private var signedInUser: User?=null
    private lateinit var firestoreDb: FirebaseFirestore
    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateBinding.inflate(inflater, container,false)
        firestoreDb=FirebaseFirestore.getInstance()
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d(TAG, "Selected URI: $uri")
                photoUri=uri
                binding.imageView.setImageURI(uri)
            } else {
                Log.d(TAG, "No media selected")
            }

        }
        binding.btnPickImage.setOnClickListener {
            Log.i(TAG,"Open up image picker on device")
            pickMedia.launch(PickVisualMediaRequest((ActivityResultContracts.PickVisualMedia.ImageOnly)))
        }
        binding.btnSubmit.setOnClickListener(){
            saveThePost()
        }
        getTheCurrentUser()
        return binding.root
    }


    private fun getTheCurrentUser() {

        firestoreDb.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid as String).get()
            .addOnSuccessListener { userSnapshot ->
                signedInUser = userSnapshot.toObject(User::class.java)
                Log.i(TAG, "signed in user: $signedInUser")
            }
            .addOnFailureListener { exception ->
                Log.i(TAG, "Failure fetching signed in user", exception)
            }
    }
    private fun saveThePost() {
    }

}


