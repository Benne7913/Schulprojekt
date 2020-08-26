package com.example.tankapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    public void onBindViewHolder(@NonNull Results_Adapter.Results_Holder holder, final int position) {
            if (!m_kTankstellen.get(position).getBrand().isEmpty())
            {
                holder.titleText.setText(m_kTankstellen.get(position).getBrand());
                holder.streetText.setText(m_kTankstellen.get(position).getStreet() + " " + m_kTankstellen.get(position).getHousnumber().toString());
                holder.rangeText.setText("Entfernung: " + String.valueOf(m_kTankstellen.get(position).getDist() + " km"));

                switch (m_kTankstellen.get(position).getBrand().toLowerCase()) {
                    case "aral":
                        holder.imageView.setImageResource(R.drawable.aral);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.aral);
                        break;
                    case "total":
                        holder.imageView.setImageResource(R.drawable.total);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.total);
                        break;
                    case "sprint":
                        holder.imageView.setImageResource(R.drawable.sprint);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.sprint);
                        break;
                    case "shell":
                        holder.imageView.setImageResource(R.drawable.shell);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.shell);
                        break;
                    case "esso":
                        holder.imageView.setImageResource(R.drawable.esso);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.esso);
                        break;
                    case "star":
                        holder.imageView.setImageResource(R.drawable.star);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.star);
                        break;
                    case "jet":
                        holder.imageView.setImageResource(R.drawable.jet);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.jet);
                        break;
                    case "elan":
                        holder.imageView.setImageResource(R.drawable.elan);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.elan);
                        break;
                    case "agip":
                        holder.imageView.setImageResource(R.drawable.agip);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.agip);
                        break;
                    case "ed":
                        holder.imageView.setImageResource(R.drawable.ed);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.ed);
                        break;
                    default:
                        holder.imageView.setImageResource(R.drawable.tanklogo);
                        m_kTankstellen.get(position).setImageRessource(R.drawable.tanklogo);
                        break;
                }
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ResultInfo_activity.class);
                        intent.putExtra("tankstelle", m_kTankstellen.get(position));
                        context.startActivity(intent);
                    }
                });
            }


    }

    @Override
    public int getItemCount() {
        return m_kTankstellen.size();
    }


    public class Results_Holder extends RecyclerView.ViewHolder{

        private TextView titleText, streetText ,rangeText;
        private ImageView imageView;
        ConstraintLayout mainLayout;

        public Results_Holder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.textView);
            streetText = itemView.findViewById(R.id.textStreet);
            rangeText = itemView.findViewById(R.id.textRange);
            imageView = itemView.findViewById(R.id.imageView);

            mainLayout= itemView.findViewById(R.id.mainLayout);
        }
    }
}
