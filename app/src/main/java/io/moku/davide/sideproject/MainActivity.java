package io.moku.davide.sideproject;

import android.content.Context;
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
import io.moku.davide.sideproject.profile.ChangeProfileActivityKt;
import io.moku.davide.sideproject.profile.ProfileActivity;
import io.moku.davide.sideproject.utils.Constants;
import io.moku.davide.sideproject.utils.activity.BasicActivity;
import io.moku.davide.sideproject.utils.preferences.PreferencesManager;
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
    private LatestPostsAdapter latestPostsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListeners();

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateView();
    }

    private void setupViews() {
        /* FRIENDS */
        // Show only the first {@link #Constants.NUMBER_OF_FRIENDS_IN_HOMEPAGE} friends of the logged user
        friendsAdapter = new MyFriendsSmallCellAdapter(this,
                User.getLoggedUserFriends(this).subList(0, Constants.NUMBER_OF_FRIENDS_IN_HOMEPAGE));
        friendsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        friendsRV.setAdapter(friendsAdapter);
        /* POSTS */
        latestPostsAdapter = new LatestPostsAdapter(this, Post.Companion.getLatestPosts());
        latestPostsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        latestPostsRV.setAdapter(latestPostsAdapter);
    }

    public void updateView() {
        /* FRIENDS */
        friendsAdapter.setUsers(User.getLoggedUserFriends(this).subList(0, Constants.NUMBER_OF_FRIENDS_IN_HOMEPAGE));
        friendsAdapter.notifyDataSetChanged();
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
        switch (item.getItemId()) {
            case R.id.itemProfile:
                startActivity(ProfileActivity.newIntent(this, PreferencesManager.getLoggedUserId(this)));
                return true;
            case R.id.itemKotlin:
                startActivity(KotlinTestActivityKt.KotlinActivityIntent(this, null));
                return true;
            case R.id.itemChangeProfile:
                startActivity(ChangeProfileActivityKt.ChangeProfileActivityIntent(this, PreferencesManager.getLoggedUserId(this)));
                return true;
            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }

}
