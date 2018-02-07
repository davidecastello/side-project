package io.moku.davide.sideproject.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.network.asynctasks.AsyncAddFriend;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;
import io.moku.davide.sideproject.utils.assets.DisplayFullPhotoActivity;

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

        // Retrieve user
        int userId = getIntent().getIntExtra(User.EXTRA_USER_ID, -1);
        user = User.getUser(userId);

        setListeners();
        updateView();
    }

    public static Intent newIntent(Context context, int userId) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(User.EXTRA_USER_ID, userId);
        return intent;
    }

    private void setListeners() {
        firstAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFriend();
            }
        });
        thirdAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserInformationActivity.class);
                intent.putExtra(User.EXTRA_USER_ID, user.getId());
                startActivity(intent);
            }
        });
        if (user.isValidProfilePictureUrl()) {
            profilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(DisplayUserPhotoActivity.newIntent(v.getContext(), user.getId()));
                }
            });
        }
        if (user.isValidBackgroundCoverUrl()) {
            backgroundCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(DisplayFullPhotoActivity.newIntent(v.getContext(), user.getBackgroundCoverUrl(), user.getName()));
                }
            });
        }
    }

    private void addFriend() {
        new AsyncAddFriend(this).execute();
    }

    private void updateView() {

        // Action Bar
        getSupportActionBar().setTitle(user.getName());

        // User's info
        name.setText(user.getName());
        user.loadProfilePicture(this, profilePicture);
        user.loadBackgroundCover(this, backgroundCover);
    }
}
