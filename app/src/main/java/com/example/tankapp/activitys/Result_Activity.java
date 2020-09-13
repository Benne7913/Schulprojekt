package com.example.tankapp.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tankapp.model.General_Model;
import com.example.tankapp.R;
import com.example.tankapp.activitys.adapter.Result_Adapter;
import com.example.tankapp.objects.Gasstation;
import com.example.tankapp.utils.Filter;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Result_Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static boolean isChildActiv = false;
    private RecyclerView rcView;
    private Result_Adapter m_krcAdapter;
    private General_Model m_kGeneralModel;
    private SwipeRefreshLayout m_kSwipeLayout;

    //filter
    private Dialog filterdialog;
    private Button filterok;
    private ImageView filtercancel;
    private TextView filter_text, filterseekbar;
    private SeekBar filterseekBar;
    private Filter filter;

    private ArrayList<Gasstation> gas;
    private ArrayList<Gasstation> tmp;


//////////////////////////////////////////////////////////////
// OVERRIDE METHODS
//////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //get current data from Main_Activity
        Intent intent = getIntent();
        m_kGeneralModel = (General_Model) intent.getSerializableExtra("sendToResultActivity");

        //first time to add data inside recycler-view
        refreshList(m_kGeneralModel.getGasstaions());

        filter = m_kGeneralModel.getFilter();

        gas =  m_kGeneralModel.getGasstaions();
        tmp = new ArrayList<Gasstation>(gas);
        //Collections.copy(list,zoo);

        //refresh recycler-view
        this.m_kSwipeLayout = findViewById(R.id.swipeRefreshLayout);
        this.m_kSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshList(tmp);
                m_krcAdapter.notifyDataSetChanged();
                m_kSwipeLayout.setRefreshing(false);
            }
        });

        //filter dialog
        filterdialog = new Dialog(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                m_kSwipeLayout.setRefreshing(false);
            }
        }, 2000);
    }


    @Override
    protected void onResume() { super.onResume(); }

    @Override
    protected void onPause() { super.onPause(); }

    @Override
    protected void onDestroy(){
        if (!isChildActiv)
            super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: //go back to Main_Activity
                Intent intent = getIntent();
                intent.putExtra("receiveToMainActivityFromResult", m_kGeneralModel);
                setResult(RESULT_OK,intent);
                finish();
                return true;
            case R.id.filtermenu:
                   showfilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showfilter()
    {
        this.filterdialog.setContentView(R.layout.dialog_filter);
        this.filterok = (Button)  filterdialog.findViewById(R.id.button_filter_ok);
        this.filtercancel = (ImageView)  filterdialog.findViewById(R.id.closefilter);
        this.filter_text = (TextView) filterdialog.findViewById(R.id.filtertext);
        this.filterseekbar = (TextView) filterdialog.findViewById(R.id.seekbarValue);
        this.filterseekBar =(SeekBar) filterdialog.findViewById(R.id.filterseekBar);

        this.filterseekbar.setText(String.valueOf(filterseekBar.getMin() + " km"));

        this.filtercancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterdialog.dismiss();
            }
        });

        filterok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                m_kGeneralModel.setFilter(filter);
                filterGasList();
                filterdialog.dismiss();

            }
        });


        //set default value
        Double seekrange = new Double(filterseekBar.getMin());
        this.filter.setRange(seekrange);

        this.filterseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                filterseekbar.setText(String.valueOf(progressChangedValue) + " km");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                filter= m_kGeneralModel.getFilter();
                Double range= new Double(progressChangedValue);
                filter.setRange(range);
            }
        });


        this.filterdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.filterdialog.show();

    }

    private void filterGasList()
    {
        if (!tmp.isEmpty())
            tmp.clear();

        for (int x = 0; x < gas.size(); x++)
            if (gas.get(x).getDist() <= this.m_kGeneralModel.getFilter().getRange())
                tmp.add(gas.get(x));





    }
//////////////////////////////////////////////////////////////
// OWN METHODS
//////////////////////////////////////////////////////////////

    private void refreshList(ArrayList<Gasstation> pkGasstations)
    {
        //RecyclerView mit Klasse verbinden
        rcView = findViewById(R.id.view);
        Intent intent = getIntent();

        m_krcAdapter = new Result_Adapter(this, pkGasstations );
        rcView.setAdapter(m_krcAdapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }


}