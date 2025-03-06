package com.example.midterm_section2

import android.media.Image
import androidx.lifecycle.ViewModel
import android.util.Base64
import android.util.Log


class CreatePostViewModel : ViewModel() {
    private val photoRepository = PhotoRepository.get()

    // Function to upload image to GitHub
    suspend fun uploadImageToGithub(base64Image: String, filename: String) {
        // Ensure that the image is valid base64 string before uploading
        if (base64Image.isNotBlank()) {
            photoRepository.saveImage(base64Image, filename)
        } else {
            // Handle invalid base64 string
            Log.e("CreatePostViewModel", "Invalid Base64 Image String")
        }
    }
}
