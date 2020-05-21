package com.example.sqlitesample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlitesample.adapters.ContactListAdapter;
import com.example.sqlitesample.db.DatabaseHelper;
import com.example.sqlitesample.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewContactActivity.class));
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        adapter=new ContactListAdapter(getContacts());
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Contact> getContacts() {

        return new DatabaseHelper(this).getContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){
            adapter.setContacts(new DatabaseHelper(this).getContacts());
        }
    }
}
