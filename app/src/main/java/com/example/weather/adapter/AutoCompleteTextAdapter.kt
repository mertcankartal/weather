package com.example.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.finalproject.base.IBaseRecyclerViewItemClickListener
import com.example.weather.R

class AutoCompleteTextAdapter(
    context: Context,
    textViewResourceId: Int,
    searchResult: List<String>
) : ArrayAdapter<String>(context, 0, textViewResourceId, searchResult) {

    private var clickListener: IBaseRecyclerViewItemClickListener<String>? = null
    private var dataList: List<String>? = searchResult

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var adapter = convertView

        if (adapter == null) {
            adapter =
                LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        }

        val textView = adapter!!.findViewById(R.id.autoCompleteItem) as TextView
        textView.text = getItem(position)

        textView.setOnClickListener {
            clickListener?.onClick(textView.text.toString())
        }
        return adapter
    }

    override fun getCount(): Int = dataList!!.size

    override fun getItem(position: Int) = dataList!![position]

    fun setOnClickListener(listener: IBaseRecyclerViewItemClickListener<String>) {
        clickListener = listener
    }

}