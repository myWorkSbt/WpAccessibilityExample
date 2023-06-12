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
import com.example.wpaccessibilityexample.Activity.HomeActivity
import com.example.wpaccessibilityexample.Adapter.postAdapter
import com.example.wpaccessibilityexample.Networking.RetrofitClient.Companion.getInst
import com.example.wpaccessibilityexample.R
import com.example.wpaccessibilityexample.Utils.DatabaseHelper
import com.example.wpaccessibilityexample.Utils.Post
import com.example.wpaccessibilityexample.data.Model
import com.example.wpaccessibilityexample.data.ModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private var postAdapter: postAdapter? = null
    private var  isDataProcessing = false
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
                override fun onClick(post_id: Int) {
                        val postBundle = Bundle()
                        postBundle.putInt("id", post_id)
//                        databaseHelper.close()
                        NavHostFragment.findNavController(this@PostListFragment)
                            .navigate(
                                R.id.moveFromPostListFragmentToPostFragment,  postBundle,
                                NavOptions.Builder()
                                    .setEnterAnim(android.R.animator.fade_in)
                                    .setExitAnim(android.R.animator.fade_out)
                                    .build()
                            )
                }
            })


        (view.findViewById<View>(R.id.recyclerView) as RecyclerView).adapter =
            postAdapter
    }


    private fun getApiDatas() {
        if (!isDataProcessing) {
//            databaseHelper= DatabaseHelper.getDatabase(this@PostListFragment.context)
            isDataProcessing = true
            getInst().posts()!!.enqueue(object : Callback<Model?> {
                override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                    if (response.isSuccessful && response.body() != null && response.body()!!.size > 0) {
                        SaveToRoom(postsList)
                    } else {
                        isDataProcessing = false
                    }
                }

                override fun onFailure(call: Call<Model?>, t: Throwable) {
                    isDataProcessing  = false
                    Toast.makeText(
                        this@PostListFragment.context,
                        "" + t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
        else{
            Toast.makeText(requireActivity(), " Data Processing! Please wait. ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun SaveToRoom(postsList: Model?) {
            for (modelItem  in postsList!!) {
                try {
                    if (!HomeActivity.databaseHelper.postDao().isAlreadySaved(modelItem.id, modelItem.userId)) {
                        val postItem: Post =
                            Post(modelItem.body, modelItem.id, modelItem.title, modelItem.userId)
                        HomeActivity.databaseHelper.postDao().savePost(postItem)
                    }
                    }
                catch (ex :Exception) {
                    ex.printStackTrace()
                    isDataProcessing = false
                }
            }
            getAllPosts()
    }


    private fun getAllPosts() {
        val modelLists = Model()
        try {

            val postList = HomeActivity.databaseHelper.postDao().allPosts
            for (postItem in postList) {
                val modelItem =
                    ModelItem(postItem.body, postItem.id, postItem.title, postItem.userId)
                modelLists.add(modelItem)
            }
            postAdapter?.refreshLists(modelLists)
        }
        catch (exx : Exception) {
            exx.printStackTrace()
        }
        isDataProcessing = false
    }


    override fun onResume() {
        super.onResume()

        getApiDatas()

    }

}