package com.example.tankapp.activitys.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tankapp.R;
import com.example.tankapp.objects.Gasstation;
import com.example.tankapp.activitys.Detail_Activity;

import java.util.ArrayList;

/*
*
*
* Quelle: Animation -> //https://www.youtube.com/watch?v=rJ-7KgMAJUo&t=249s
 */

public class Result_Adapter extends RecyclerView.Adapter<Result_Adapter.Results_Holder> {

    private Context context;
    private ArrayList<Gasstation> m_kTankstellen;

    public Result_Adapter(Context ct, ArrayList<Gasstation> pkTankstellen)
    {
        this.context = ct;
        this.m_kTankstellen = pkTankstellen;
    }

    @NonNull
    @Override
    public Result_Adapter.Results_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_card_onresult,parent,false);
        return new Results_Holder(view);
    }

    //Listet alle Tankstellen auf
    @Override
    public void onBindViewHolder(@NonNull Result_Adapter.Results_Holder holder, final int position) {

        holder.imageView.setAnimation(AnimationUtils.loadAnimation(this.context, R.anim.fade_icon_animation));
        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(this.context, R.anim.fade_card_animation));

        if (!this.m_kTankstellen.get(position).getBrand().isEmpty())
        {
            holder.titleText.setText(this.m_kTankstellen.get(position).getBrand());
            holder.rangeText.setText("Entfernung: " + String.valueOf(this.m_kTankstellen.get(position).getDist() + " km"));

            switch (this.m_kTankstellen.get(position).getBrand().toLowerCase())
            {
                case "aral":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_aral);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_aral);
                    break;
                case "total":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_total);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_total);
                    break;
                case "sprint":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_sprint);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_sprint);
                    break;
                case "shell":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_shell);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_shell);
                    break;
                case "esso":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_esso);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_esso);
                    break;
                case "star":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_star);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_star);
                    break;
                case "jet":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_jet);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_jet);
                    break;
                case "elan":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_elan);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_elan);
                    break;
                case "agip":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_agip);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_agip);
                    break;
                case "ed":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_ed);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_ed);
                    break;
                case "westfalen tankstelle":
                    holder.imageView.setImageResource(R.drawable.logo_gasstation_westfalen);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.logo_gasstation_westfalen);
                    break;
                default:
                    holder.imageView.setImageResource(R.drawable.tanklogo);
                    this.m_kTankstellen.get(position).setImageRessource(R.drawable.tanklogo);
                    break;
                }
                holder.mainLayout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Detail_Activity.class);
                        intent.putExtra("tankstelle", m_kTankstellen.get(position));
                        context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return this.m_kTankstellen.size();
    }

    //Inner Class
    public class Results_Holder extends RecyclerView.ViewHolder{

        private TextView            titleText ,rangeText;
        private ImageView           imageView;
        private ConstraintLayout    mainLayout;
        private RelativeLayout      relativeLayout;

        public Results_Holder(@NonNull View itemView) {
            super(itemView);

            //TextView
            titleText = itemView.findViewById(R.id.card_text_gasstation);
            rangeText = itemView.findViewById(R.id.card_text_range);

            //ImageView
            imageView = itemView.findViewById(R.id.card_image_Logo);

            //Layouts
            mainLayout      = itemView.findViewById(R.id.card_constraintLayout);
            relativeLayout  = itemView.findViewById(R.id.card_relativeLayout);
        }
    }
}
