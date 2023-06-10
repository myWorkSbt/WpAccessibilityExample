package com.example.wpaccessibilityexample.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.wpaccessibilityexample.R
import com.example.wpaccessibilityexample.Networking.RetrofitClient.Companion.getInst
import com.example.wpaccessibilityexample.data.Model
import com.example.wpaccessibilityexample.data.ModelItem
import com.example.wpaccessibilityexample.Adapter.postAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListFragment : Fragment() {

    private var postAdapter: postAdapter? = null
    private var postsList: Model? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }


    private fun init(view: View) {
        postAdapter = postAdapter(
            ArrayList<ModelItem>(),
            object : postAdapter.OnClickListener {
                override fun onClick(position: Int) {

                    val modelItem = postsList!![position]
                    if (modelItem != null) {
                        val postBundle = Bundle()
                        postBundle.putString("title", modelItem.title)
                        postBundle.putString("desc", modelItem.body)
                        NavHostFragment.findNavController(this@PostListFragment)
                            .navigate(
                                R.id.moveFromPostListFragmentToPostFragment, postBundle,
                                NavOptions.Builder()
                                    .setEnterAnim(android.R.animator.fade_in)
                                    .setExitAnim(android.R.animator.fade_out)
                                    .build()
                            )
                    }
                }
            })


        (view.findViewById<View>(R.id.recyclerView) as RecyclerView).adapter =
            postAdapter
    }


    private fun getApiDatas() {
        getInst().posts()!!.enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                if (response.isSuccessful && response.body() != null && response.body()!!.size > 0) {
                    postsList = response.body()
                    postsList?.let { postAdapter!!.refreshLists(it) }
                } else {
                }
            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                Toast.makeText(
                    this@PostListFragment.context,
                    "" + t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    override fun onResume() {
        super.onResume()

        getApiDatas()

    }

}