package io.moku.davide.sideproject.utils.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.profile.ProfileActivity;

/**
 * Created by Davide Castello on 01/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class MyFriendsSmallCellAdapter extends RecyclerView.Adapter<MyFriendsSmallCellAdapter.SmallCellViewHolder> {

    private Context context;
    private List<User> users;

    public MyFriendsSmallCellAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public SmallCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmallCellViewHolder(LayoutInflater.from(context).inflate(R.layout.my_friends_small_cell_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SmallCellViewHolder holder, int position) {
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
        currentUser.loadProfilePicture(context, holder.imageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class SmallCellViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView name;
        @BindView(R.id.personalInfo) TextView personalInfo;
        @BindView(R.id.imageView) ImageView imageView;

        public SmallCellViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}