package io.moku.davide.sideproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.kotlin.KotlinTestActivityKt;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.myFriends.FriendsListActivity;
import io.moku.davide.sideproject.utils.Constants;
import io.moku.davide.sideproject.utils.activity.BasicActivity;
import io.moku.davide.sideproject.utils.recyclerView.MyFriendsSmallCellAdapter;

public class MainActivity extends BasicActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.seeAll) RelativeLayout seeAllLayout;
    private MyFriendsSmallCellAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListeners();

        setupViews();
    }

    private void setupViews() {
        recyclerViewAdapter = new MyFriendsSmallCellAdapter(this, User.getAllUsers().subList(0, Constants.NUMBER_OF_FRIENDS_IN_HOMEPAGE));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
