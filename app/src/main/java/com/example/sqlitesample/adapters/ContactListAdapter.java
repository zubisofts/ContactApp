package com.example.sqlitesample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitesample.R;
import com.example.sqlitesample.model.Contact;


import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactItemHolder> {


    private ArrayList<Contact> contacts;

    public ContactListAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);
        return new ContactItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactItemHolder holder, int position) {
        Contact contact=contacts.get(position);
        if(contact!=null){
            holder.txtName.setText(contact.getName());
            holder.txtPhone.setText(contact.getPhoneNumber());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(ArrayList<Contact> cons){
        this.contacts=cons;
        notifyDataSetChanged();
    }

    class ContactItemHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        private TextView txtPhone;

        public ContactItemHolder(@NonNull View itemView) {
            super(itemView);

            txtName=(TextView)itemView.findViewById(R.id.txtName);
            txtPhone=(TextView)itemView.findViewById(R.id.txtPhone);
        }
    }
}
