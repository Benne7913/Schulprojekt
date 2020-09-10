package com.example.tankapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.tankapp.model.General_Model;
import com.example.tankapp.R;
import com.example.tankapp.activitys.adapter.Result_Adapter;
import com.example.tankapp.objects.Gasstation;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Result_Activity extends AppCompatActivity {

    public static boolean isChildActiv = false;
    private RecyclerView rcView;
    private Result_Adapter m_krcAdapter;
    private General_Model m_kGeneralModel;
    private SwipeRefreshLayout m_kSwipeLayout;

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

        //refresh recycler-view
        this.m_kSwipeLayout = findViewById(R.id.swipeRefreshLayout);
        this.m_kSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Gasstation> gas =  m_kGeneralModel.getGasstaions();

                refreshList(gas);
                m_krcAdapter.notifyDataSetChanged();
                m_kSwipeLayout.setRefreshing(false);
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: //go back to Main_Activity
                Intent intent = getIntent();
                intent.putExtra("receiveToMainActivityFromResult", m_kGeneralModel);
                setResult(RESULT_OK,intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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