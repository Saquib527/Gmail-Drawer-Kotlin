package com.saquib.gmaildrawer2.fragments

import android.database.Cursor
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saquib.gmaildrawer2.Adapter.MatchAdapter
import com.saquib.gmaildrawer2.MainActivity
import com.saquib.gmaildrawer2.Model.ResponseModel.Venue
import com.saquib.gmaildrawer2.R
import com.saquib.gmaildrawer2.SqLite.DatabaseHelper
import java.util.*

class SaveFragment : Fragment() {
    var listMatch: ListView? = null
    var databaseHelper: DatabaseHelper? = null
    var venues: MutableList<Venue> = ArrayList()
    var cursor: Cursor? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_save, container, false)
        listMatch = view.findViewById(R.id.list_saved)

        //changing toolbar title
        (activity as MainActivity).supportActionBar?.title = getString(R.string.saved)

        databaseHelper = DatabaseHelper(activity)
        //load data from sqlite
        async().execute()
        return view
    }

    inner class async : AsyncTask<Void?, Void?, Void?>() {
       /* protected override fun doInBackground(vararg voids: Void): Void? { //onClick
//we are here using the DatabaseManager instance to get all employees
            cursor = database_helper!!.getAllMatches("table_saved")
            //venues.clear();
            if (cursor.moveToFirst()) {
                do {
                    venues.add(Venue(cursor.getString(0), cursor.getString(1)))
                } while (cursor.moveToNext())
            }
            return null
        }*/

        override fun onPostExecute(res: Void?) {
            val matchAdapter = MatchAdapter(activity, venues, "save")
            matchAdapter.notifyDataSetChanged()
            listMatch!!.adapter = matchAdapter
            Toast.makeText(activity, cursor!!.count.toString(), Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            //we are here using the DatabaseManager instance to get all employees
            cursor = databaseHelper!!.getAllMatches("table_saved")
            //venues.clear();



            if (cursor!!.moveToFirst()) {
                do {
                    venues.add(Venue(cursor!!.getString(0), cursor!!.getString(1)))
                } while (cursor!!.moveToNext())
            }
            return null
        }
    }
}