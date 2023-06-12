package com.example.wpaccessibilityexample.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.wpaccessibilityexample.Activity.HomeActivity
import com.example.wpaccessibilityexample.R
import com.example.wpaccessibilityexample.Utils.Post

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
        try {
            val id =  arguments?.getInt("id" , -1);
            if ( id != -1 ) {
//                val dataBaseHelper = DatabaseHelper.getDatabase(requireActivity())
                val postItem: Post = HomeActivity.databaseHelper.postDao().getPost(id!!)
                view.findViewById<TextView>(R.id.tittle).setText(postItem.title)
                view.findViewById<AppCompatImageView>(R.id.backBtn).setOnClickListener { v ->
//                    dataBaseHelper.close()
                    NavHostFragment.findNavController(this).popBackStack()
                }

                view.findViewById<TextView>(R.id.postDetails).text = postItem.body
            }
            else{
                Toast.makeText( requireActivity(), " No id Found. ", Toast.LENGTH_SHORT).show()
                NavHostFragment.findNavController(this).popBackStack()
            }
    }
    catch (ex : Exception ) {
        ex.printStackTrace()
        NavHostFragment.findNavController(this).popBackStack()
    }
    }
}