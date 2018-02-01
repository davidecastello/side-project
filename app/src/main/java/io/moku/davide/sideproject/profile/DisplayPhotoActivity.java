package io.moku.davide.sideproject.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;
import io.moku.davide.sideproject.utils.assets.AssetsUtils;
import io.moku.davide.sideproject.utils.assets.ImagesUtils;

public class DisplayPhotoActivity extends BasicSecondaryActivity {

    public static final String IS_PROFILE_PICTURE = "isProfilePicture";

    private User user;
    private boolean isProfilePicture;

    @BindView(R.id.imageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // Retrieve user
        isProfilePicture = getIntent().getBooleanExtra(IS_PROFILE_PICTURE, false);
        int userId = getIntent().getIntExtra(User.EXTRA_USER_ID, -1);
        user = User.getUser(userId);

        updateView();
    }

    private void updateView() {
        // Name
        getSupportActionBar().setTitle(user.getName());
        // Load photo
        if (isProfilePicture) {
            user.loadProfilePicture(this, imageView);
        } else {
            user.loadBackgroundCover(this, imageView);
        }
    }
}
