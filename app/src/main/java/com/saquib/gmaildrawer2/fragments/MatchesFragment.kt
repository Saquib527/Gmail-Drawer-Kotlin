package com.saquib.gmaildrawer2.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.saquib.gmaildrawer2.R
import com.saquib.gmaildrawer2.Retrofit.RetrofitApi
import com.saquib.gmaildrawer2.Retrofit.RetrofitClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saquib.gmaildrawer2.Adapter.MatchAdapter
import com.saquib.gmaildrawer2.Model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
//import android.os.Bundleimport
import retrofit2.Response

//android.view.LayoutInflaterimport android.view.Viewimport retrofit2.Response

class MatchesFragment : Fragment() {
    var listMatch: ListView? = null
    var progressBar: ProgressBar? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_matches, container, false)
        //in this method we initialize the views
        listMatch = view.findViewById(R.id.list_match)
        progressBar = view.findViewById(R.id.progress_circular)

        //load data or api call should be made here
        val retrofitApi = RetrofitClient.retrofit?.create(RetrofitApi::class.java)
        val responseCall = retrofitApi?.getResponse("40.7484,-73.9857", "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ", "20180616")
        retrofitApi?.getResponse("40.7484,-73.9857", "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ", "20180616")?.enqueue(object : Callback<ResponseModel.Root?> {
            override fun onResponse(call: Call<ResponseModel.Root?>, response: Response<ResponseModel.Root?>) {
                //here we should fill up the list
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Toast.makeText(activity, response.body()!!.response?.venues?.size.toString(), Toast.LENGTH_SHORT).show()
                        val objAdapter = response.body()!!.response?.venues?.let { MatchAdapter(activity, it, "main") }
                        objAdapter?.notifyDataSetChanged()
                        progressBar!!.visibility = View.GONE
                        listMatch!!.adapter = objAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel.Root?>, t: Throwable) {}
        })
        return view
    }
}