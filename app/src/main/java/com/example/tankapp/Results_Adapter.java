package com.example.tankapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

public class Results_Adapter extends RecyclerView.Adapter<Results_Adapter.Results_Holder> {

    private Context context;
    private Tankstelle tankstellen;

    public Results_Adapter(Context ct, Tankstelle pkTankstellen)
    {
        this.context = ct;
        this.tankstellen = pkTankstellen;

    }

    @NonNull
    @Override
    public Results_Adapter.Results_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.result_row, parent, false);
        return new Results_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Results_Adapter.Results_Holder holder, int position) {
        holder.titleText.setText(tankstellen.getName());
        holder.rangeText.setText(tankstellen.getRange());
    }

    @Override
    public int getItemCount() {
        return 1; //MUSS GEÄNDERT WERDEN!°!!!!
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
