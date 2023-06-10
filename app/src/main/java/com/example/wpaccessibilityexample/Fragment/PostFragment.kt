package com.example.wpaccessibilityexample.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.wpaccessibilityexample.R

class PostFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.fragment_post, container, false);

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init(view);
    }

    private fun init(view: View) {
        view.findViewById<TextView>(R.id.tittle).setText(arguments?.getString("title"))
        view.findViewById<AppCompatImageView>(R.id.backBtn).setOnClickListener { v ->
            NavHostFragment.findNavController(this).popBackStack()
        }


        view.findViewById<TextView>(R.id.postDetails).text = arguments?.getString("desc")
    }
}