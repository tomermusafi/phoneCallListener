package com.musafi.phonecalllistener.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.musafi.phonecalllistener.R;
import com.musafi.phonecalllistener.Entity.UserInfo;

import java.util.ArrayList;

public class Adapter_UserModel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<UserInfo> users;
    private OnItemClickListener mItemClickListener;

    public Adapter_UserModel(Context context, ArrayList<UserInfo> users) {
        this.context = context;
        this.users = users;
    }

    public void updateList(ArrayList<UserInfo> users) {
        this.users = users;
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
            final UserInfo user = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.user_txv_name.setText(user.getUserName());
            genericViewHolder.user_txv_phone_number.setText(user.getUserPhoneNumber());

        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private UserInfo getItem(int position) {
        return users.get(position);
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
        users.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, users.size());
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, UserInfo user);
    }
}
