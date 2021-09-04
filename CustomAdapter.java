package com.example.rms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ArrayList<String>> arrayList;

    public CustomAdapter(Context ctx, ArrayList<ArrayList<String>> array) {
        this.context = ctx;
        this.arrayList = array;


    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater myinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = myinflater.inflate(R.layout.listview, parent, false);

            holder.txt_cat = convertView.findViewById(R.id.txt_cat1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_cat.setText(arrayList.get(position).get(1));

        return convertView;

    }

    private class ViewHolder {
        TextView txt_cat;
    }
}
