package io.moku.davide.sideproject.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;

public class DisplayUserPhotoActivity extends BasicSecondaryActivity {

    private User user;
    @BindView(R.id.imageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // Retrieve user
        int userId = getIntent().getIntExtra(User.EXTRA_USER_ID, -1);
        user = User.getUser(userId);

        updateView();
    }

    public static Intent newIntent(Context context, int userId) {
        Intent intent = new Intent(context, DisplayUserPhotoActivity.class);
        intent.putExtra(User.EXTRA_USER_ID, userId);
        return intent;
    }

    private void updateView() {
        // Name
        getSupportActionBar().setTitle(user.getName());
        // Load photo
        user.loadProfilePicture(this, imageView);
    }
}
