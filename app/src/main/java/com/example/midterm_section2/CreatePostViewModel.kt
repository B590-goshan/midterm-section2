package com.example.midterm_section2

import android.media.Image
import androidx.lifecycle.ViewModel
import android.util.Base64


class CreatePostViewModel : ViewModel(){
    private val photoRepository = PhotoRepository.get()
    suspend fun uploadImageToGithub(base64Image: String,filename : String){
        photoRepository.saveImage(base64Image,filename)
    }
}