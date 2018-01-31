package io.moku.davide.sideproject.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;

public class ProfileActivity extends BasicSecondaryActivity {

    private User user;

    @BindView(R.id.userName) TextView name;
    @BindView(R.id.profilePicture) ImageView profilePicture;
    @BindView(R.id.backgroundCover) ImageView backgroundCover;
    @BindView(R.id.firstActionLayout) View firstAction;
    @BindView(R.id.secondActionLayout) View secondAction;
    @BindView(R.id.thirdActionLayout) View thirdAction;
    @BindView(R.id.fourthActionLayout) View fourthAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // fake retrieve user
        user = User.getAllUsers().get(0);

        setListeners();
        updateView();
    }

    private void setListeners() {
        thirdAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), UserInformationActivity.class));
            }
        });
    }

    private void updateView() {

        // Action Bar
        getSupportActionBar().setTitle(user.getName());

        // User's info
        name.setText(user.getName());
    }
}