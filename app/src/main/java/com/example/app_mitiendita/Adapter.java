package com.example.app_mitiendita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends ArrayAdapter<BD> {

    Context context;
    List<BD>arrayalistaBD;
    public Adapter(@NonNull Context context, List<BD>arrayalistaBD) {
        super(context, R.layout.my_list_item, arrayalistaBD);

        this.context=context;
        this.arrayalistaBD=arrayalistaBD;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item,null,true);
        TextView tvid=view.findViewById(R.id.tvid);
        TextView tvnombre=view.findViewById(R.id.tvnombre);

        tvid.setText(arrayalistaBD.get(position).getId());
        tvnombre.setText(arrayalistaBD.get(position).getNombre());
        return view;
    }

}
