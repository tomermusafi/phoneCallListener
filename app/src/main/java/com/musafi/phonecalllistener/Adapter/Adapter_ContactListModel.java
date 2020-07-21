package com.musafi.phonecalllistener.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.musafi.phonecalllistener.Entity.Contact;
import com.musafi.phonecalllistener.R;

import java.util.ArrayList;

public class Adapter_ContactListModel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Contact> contacts;
    private OnItemClickListener mItemClickListener;

    public Adapter_ContactListModel(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    public void updateList(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_users, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final Contact user = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.user_txv_name.setText(user.getName());
            genericViewHolder.user_txv_phone_number.setText(user.getPhoneNumber());

        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    private Contact getItem(int position) {
        return contacts.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView user_txv_name, user_txv_phone_number;



        public ViewHolder(final View itemView) {
            super(itemView);
            this.user_txv_name = itemView.findViewById(R.id.user_txv_name);
            this.user_txv_phone_number = itemView.findViewById(R.id.user_txv_phone_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });
        }
    }

    public void removeAt(int position) {
        contacts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, contacts.size());
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Contact user);
    }
}
