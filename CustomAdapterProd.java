package com.example.rms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapterProd extends BaseAdapter {

    Context context1;
    ArrayList<ArrayList<String>> arrayList;

    DatabaseHandlerProduct databaseHandlerProduct;

    public CustomAdapterProd(Context ctx1, ArrayList<ArrayList<String>> array) {
        this.context1 = ctx1;
        this.arrayList = array;

        databaseHandlerProduct = new DatabaseHandlerProduct(context1);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater myinflater = (LayoutInflater) context1.getSystemService(context1.LAYOUT_INFLATER_SERVICE);
            convertView = myinflater.inflate(R.layout.listprod, parent, false);

            holder.txt_ProdCat = convertView.findViewById(R.id.txt_ProdCat);
            holder.txt1 = convertView.findViewById(R.id.txt1);
            holder.txt2 = convertView.findViewById(R.id.txt2);
            holder.txt3 = convertView.findViewById(R.id.txt3);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_ProdCat.setText("Product Catogary : "+arrayList.get(position).get(1));
        holder.txt1.setText("Product Name : "+arrayList.get(position).get(2));
        holder.txt2.setText("Product Discription : "+arrayList.get(position).get(3));
        holder.txt3.setText("Product Price : "+arrayList.get(position).get(4));


//        holder.Iv_Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseHandlerProduct.Iv_Delete(Integer.parseInt(arrayList.get(position).get(0)));
//                Toast.makeText(context1, "Product Deleted", Toast.LENGTH_SHORT).show();
//
//
//        refresh();
//
//
//            }
//        });

        return convertView;


    }

    private void refresh() {
        ArrayList<ArrayList<String>> array = databaseHandlerProduct.getAllcategoryProduct("");
        this.arrayList.clear();
        this.arrayList.addAll(array);
        notifyDataSetChanged();

    }


    private class ViewHolder {
        TextView txt_ProdCat, txt1, txt2, txt3;
        ImageView Iv_Delete;
    }
}
