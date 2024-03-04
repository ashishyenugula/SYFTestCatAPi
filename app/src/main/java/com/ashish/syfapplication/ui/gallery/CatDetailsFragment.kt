package com.ashish.syfapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ashish.syfapplication.databinding.FragmentCatDetailsBinding
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CatDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatDetailsFragment : Fragment() {

    private var _binding: FragmentCatDetailsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCatDetailsBinding.inflate(inflater, container, false)


        initFragment()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initFragment() {
        val data: CatDetailsFragmentArgs by navArgs()
        val imageResponse = data.ImageResponse
        binding.apply {
            Glide.with(requireContext()).load(imageResponse.url)
                .into(imageCat)
            val bread = imageResponse.breeds[0]
            nameCatText.text = bread.name
            originText.text = bread.countryCode
            weightText.text = bread.weight!!.metric
            lifeSpanText.text = bread.lifeSpan
            aboutCatText.text = bread.description
            adaptabilityRate.rating = bread.adaptability!!.toFloat()
            catTempDescText.text = bread.temperament
            childFrndRate.rating = bread.childFriendly!!.toFloat()
            energyRate.rating = bread.energyLevel!!.toFloat()
            inteligenceRate.rating = bread.intelligence!!.toFloat()
            healthRate.rating = bread.healthIssues!!.toFloat()
            sheddingRate.rating = bread.sheddingLevel!!.toFloat()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CatDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CatDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}