package io.moku.davide.sideproject.myFriends;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;
import io.moku.davide.sideproject.utils.recyclerView.MyRecyclerAdapter;
import io.moku.davide.sideproject.utils.recyclerView.OnPagerNumberChangeListener;

public class FriendsListActivity extends BasicSecondaryActivity implements OnPagerNumberChangeListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.pagerIndicator) IndefinitePagerIndicator pagerIndicator;
    private MyRecyclerAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViews();
    }

    private void setupViews() {
        recyclerViewAdapter = new MyRecyclerAdapter(this, User.getUsers(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        pagerIndicator.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onPagerNumberChanged() {
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
