package io.moku.davide.sideproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.kotlin.KotlinTestActivityKt;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.myFriends.FriendsListActivity;
import io.moku.davide.sideproject.utils.Constants;
import io.moku.davide.sideproject.utils.activity.BasicActivity;
import io.moku.davide.sideproject.utils.recyclerView.LatestPostsAdapter;
import io.moku.davide.sideproject.utils.recyclerView.MyFriendsSmallCellAdapter;
import io.moku.davide.sideproject.utils.recyclerView.Post;

public class MainActivity extends BasicActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    // Friends List
    @BindView(R.id.friendsRecyclerView) RecyclerView friendsRV;
    @BindView(R.id.seeAllFriends) RelativeLayout seeAllLayout;
    private MyFriendsSmallCellAdapter friendsAdapter;
    // Latest posts
    @BindView(R.id.latestPostsRecyclerView) RecyclerView latestPostsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListeners();

        setupViews();
    }

    private void setupViews() {
        friendsAdapter = new MyFriendsSmallCellAdapter(this, User.getAllUsers().subList(0, Constants.NUMBER_OF_FRIENDS_IN_HOMEPAGE));
        friendsRV.setAdapter(friendsAdapter);
        friendsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        LatestPostsAdapter latestPostsAdapter = new LatestPostsAdapter(this, Post.Companion.getLatestPosts());
        latestPostsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        latestPostsRV.setAdapter(latestPostsAdapter);
    }

    private void setListeners() {
        seeAllLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FriendsListActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemKotlin) startActivity(KotlinTestActivityKt.KotlinActivityIntent(this, null));
        return true;
    }

}
