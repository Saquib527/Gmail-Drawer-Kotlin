package com.saquib.gmaildrawer2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.saquib.gmaildrawer2.Model.ResponseModel.Venue
import com.saquib.gmaildrawer2.R
import com.saquib.gmaildrawer2.SqLite.DatabaseHelper
import java.io.Serializable

class MatchAdapter(context: Context?, private var venues: List<Venue>, private var ctxName: String) : ArrayAdapter<Venue?>(context!!, R.layout.layout_matches, venues), Serializable {
    //it gets the view type of the item in the data set that will be created by the getview
    /*it returns An integer representing the type of View. Two views should share the same type if one
    can be converted to the other in getView(int, View, ViewGroup).
    Note: Integers must be in the range 0 to getViewTypeCount() - 1.
    IGNORE_ITEM_VIEW_TYPE can also be returned.*/
    override fun getItemViewType(position: Int): Int {
        return position
    }

    /*Returns the number of types of Views that will be created by getView(int, View, ViewGroup).
    Each type represents a set of views that can be converted in getView(int, View, ViewGroup).
    If the adapter always returns the same type of View for all items, this method should return 1.
    This method will only be called when the adapter is set on the AdapterView.*/
    override fun getViewTypeCount(): Int {
        return if (venues.size > 0) {
            venues.size
        } else {
            1
        }
    }

    //onbind
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val viewHolder: ViewHolder
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_matches, parent, false)
            viewHolder = ViewHolder()
            viewHolder.name = view.findViewById(R.id.name)
            viewHolder.id = view.findViewById(R.id.id)
            viewHolder.star = view.findViewById(R.id.star)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        //setting data from list
        viewHolder.id!!.text = venues[position].id
        viewHolder.name!!.text = venues[position].name

//Check Unckeck Heart
        val mDatabase = DatabaseHelper(context)

        //this code will run one time only to fetch data from database

//Favorite system
        if (mDatabase.columnExists(venues[position].id)) viewHolder.star!!.setImageResource(R.drawable.ic_action_star_filled) else viewHolder.star!!.setImageResource(R.drawable.ic_action_name)
        if (ctxName == "save") {
            viewHolder.star!!.setOnClickListener {
                if (mDatabase.columnExists(venues[position].id)) {
                    viewHolder.star!!.setImageResource(R.drawable.ic_action_name)
                    mDatabase.deleteItemByID(venues[position].id, "table_saved", "column_saved_id")
                    //removeAt not working in kotlin
                    //venues.removeAt(position)
                    notifyDataSetChanged()
                } else {
                    viewHolder.star!!.setImageResource(R.drawable.ic_action_star_filled)
                    mDatabase.addMatches(venues[position].id, venues[position].name, "table_saved",
                            "column_saved_id", "column_saved_name")
                }
            }
        } else {
            viewHolder.star!!.setOnClickListener {
                if (mDatabase.columnExists(venues[position].id)) {
                    viewHolder.star!!.setImageResource(R.drawable.ic_action_name)
                    mDatabase.deleteItemByID(venues[position].id, "table_saved", "column_saved_id")
                } else {
                    viewHolder.star!!.setImageResource(R.drawable.ic_action_star_filled)
                    mDatabase.addMatches(venues[position].id, venues[position].name, "table_saved",
                            "column_saved_id", "column_saved_name")
                }
            }
        }
        return view!!
    }

    //it improves the performance of the list view by not calling the findviewbyid often
    inner class ViewHolder {
        var name: TextView? = null
        var id: TextView? = null
        var star: ImageView? = null
    }

    //?????
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}