package com.ashish.syfapplication.utills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashish.syfapplication.repository.CatRepository
import com.ashish.syfapplication.ui.gallery.GalleryViewModel

class MyViewModelFactory constructor(private val repository: CatRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            GalleryViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}