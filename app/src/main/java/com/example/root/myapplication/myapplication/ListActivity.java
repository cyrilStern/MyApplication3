package com.example.root.myapplication.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.myapplication.DAO.Radio;
import com.example.root.myapplication.DAO.RadioDAO;
import com.example.root.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ls = (ListView) findViewById(R.id.listeViewRadio);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> Listurl = new ArrayList<String>();
        RadioDAO radioDAO = new RadioDAO(this);
        try {
            radioDAO.open();
            Listurl = radioDAO.findAllUrl();
            radioDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> aAdapteurlistRadio = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Listurl);
        ls.setAdapter(aAdapteurlistRadio);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setResult(position);
                finish();
            }
        });
    }
}
