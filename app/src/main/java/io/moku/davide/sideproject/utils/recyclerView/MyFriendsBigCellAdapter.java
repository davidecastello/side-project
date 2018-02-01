package io.moku.davide.sideproject.utils.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.profile.ProfileActivity;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;

/**
 * Created by Davide Castello on 29/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class MyFriendsBigCellAdapter extends RecyclerView.Adapter<MyFriendsBigCellAdapter.MyViewHolder> {

    private Context context;
    private List<User> users;

    public MyFriendsBigCellAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_friends_big_cell_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final User currentUser = users.get(position);
        holder.name.setText(currentUser.getName());
        holder.personalInfo.setText(currentUser.getPersonalInfo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                intent.putExtra(User.EXTRA_USER_ID, currentUser.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name2) TextView name;
        @BindView(R.id.personalInfo2) TextView personalInfo;
        @BindView(R.id.imageView2) ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
