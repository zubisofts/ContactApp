package com.example.sqlitesample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitesample.db.DatabaseHelper;
import com.example.sqlitesample.model.Contact;
import com.google.android.material.button.MaterialButton;

public class NewContactActivity extends AppCompatActivity {


    private EditText edtName, edtPhone, edtEmail, edtAddress;
    private TextView txtError;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveContact();
                }
            }
        });

    }

    private void saveContact() {
        Contact contact = new Contact(
                edtName.getText().toString(),
                edtPhone.getText().toString(),
                edtEmail.getText().toString(),
                edtAddress.getText().toString()
        );

        boolean addContact = new DatabaseHelper(this).addContact(contact);
        if (addContact) {
            Toast.makeText(this, "Contact successfully saved...", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "There was an error saving contact...", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateFields() {

        if (TextUtils.isEmpty(edtName.getText().toString())) {
            txtError.setText("Fill in the contact fullname");
            return false;
        } else if (TextUtils.isEmpty(edtPhone.getText().toString())) {
            txtError.setText("Fill in the contact phone number");
            return false;
        } else if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            txtError.setText("Fill in the contact email address");
            return false;
        } else if (TextUtils.isEmpty(edtAddress.getText().toString())) {
            txtError.setText("Fill in the contact address");
            return false;
        } else {
            txtError.setText("");
        }

        return true;
    }

    private void initViews() {
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        txtError = findViewById(R.id.txtError);
        btnSave = findViewById(R.id.btnSave);
    }
}
