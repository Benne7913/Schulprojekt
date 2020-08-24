package com.example.tankapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Results_Adapter extends RecyclerView.Adapter<Results_Adapter.Results_Holder> {

    private Context context;
    private ArrayList<Tankstelle> m_kTankstellen;

    public Results_Adapter(Context ct, ArrayList<Tankstelle> pkTankstellen)
    {
        this.context = ct;
        m_kTankstellen = pkTankstellen;
    }

    @NonNull
    @Override
    public Results_Adapter.Results_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.result_row, parent, false);
        return new Results_Holder(view);
    }

    //Listet alle Tankstellen auf
    @Override
    public void onBindViewHolder(@NonNull Results_Adapter.Results_Holder holder, int position) {
             holder.titleText.setText(m_kTankstellen.get(position).getName());
             holder.rangeText.setText(m_kTankstellen.get(position).getRange() + " km");
    }

    @Override
    public int getItemCount() {
        return m_kTankstellen.size();
    }


    public class Results_Holder extends RecyclerView.ViewHolder{

        private TextView titleText, rangeText;

        public Results_Holder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.textView);
            rangeText = itemView.findViewById(R.id.textRange);
        }
    }
}
