package io.moku.davide.sideproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.myFriends.FriendsListActivity;
import io.moku.davide.sideproject.profile.ProfileActivity;
import io.moku.davide.sideproject.utils.activity.BasicActivity;

public class MainActivity extends BasicActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.user_layout) RelativeLayout userLayout;
    @BindView(R.id.name) TextView userName;
    @BindView(R.id.personalInfo) TextView userPersonalInfo;
    @BindView(R.id.seeAll) RelativeLayout seeAllLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListeners();

        updateView();
    }

    private void updateView() {

        // TODO: RecyclerView, mostrare i primi 3 utenti
        List<User> users = User.getAllUsers();
        if (users.size() != 0) {
            final User user = users.get(0);
            userName.setText(user.getName());
            userPersonalInfo.setText(user.getPersonalInfo());
            userLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                    intent.putExtra(User.EXTRA_USER_ID, user.getId());
                    startActivity(intent);
                }
            });
        }
    }

    private void setListeners() {
        seeAllLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FriendsListActivity.class));
            }
        });
    }
}
