package io.moku.davide.testinglibraries;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.testinglibraries.myFriends.FriendsListActivity;
import io.moku.davide.testinglibraries.profile.ProfileActivity;
import io.moku.davide.testinglibraries.utils.activity.BasicActivity;

public class MainActivity extends BasicActivity {

    @BindView(R.id.user_layout) RelativeLayout userLayout;
    @BindView(R.id.seeAll) RelativeLayout seeAllLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListeners();
    }

    private void setListeners() {
        seeAllLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FriendsListActivity.class));
            }
        });
        userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ProfileActivity.class));
            }
        });
    }
}
