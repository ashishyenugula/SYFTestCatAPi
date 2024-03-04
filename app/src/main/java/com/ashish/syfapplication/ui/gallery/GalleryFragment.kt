package com.ashish.syfapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.ashish.syfapplication.adapter.ImageAdapter
import com.ashish.syfapplication.databinding.FragmentGalleryBinding
import com.ashish.syfapplication.network.RetrofitClient
import com.ashish.syfapplication.repository.CatRepository
import com.ashish.syfapplication.utills.MyViewModelFactory
import com.ashish.syfapplication.utills.NetworkUtil

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val retrofitService = RetrofitClient.apiInterface
        val mainRepository = CatRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        )[GalleryViewModel::class.java]



        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = ImageAdapter() { imageResponse ->
            val action =
                GalleryFragmentDirections.actionNavGalleryToNavCatDetails(
                    imageResponse
                )
            findNavController().navigate(action)


        }
        binding.rvRandomCatImages.adapter = adapter



        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setImages(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                binding.idPBLoading.visibility = View.VISIBLE
            else
                binding.idPBLoading.visibility = View.GONE
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }


        if (NetworkUtil.checkConnectivity(requireContext()))
            viewModel.getAllImages()
        else
            viewModel.onError("Check Internet Connection..!!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}