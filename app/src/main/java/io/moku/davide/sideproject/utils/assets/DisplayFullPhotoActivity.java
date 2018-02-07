package io.moku.davide.sideproject.utils.assets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;

public class DisplayFullPhotoActivity extends BasicSecondaryActivity {

    public static final String URL = "url";
    public static final String TITLE = "title";

    private String url;
    private String title;

    @BindView(R.id.imageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_full_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // Retrieve user
        url = getIntent().getStringExtra(URL);
        title = getIntent().getStringExtra(TITLE);

        updateView();
    }

    public static Intent newIntent(Context context, String url) {
        return newIntent(context, url, context.getString(R.string.see_photo));
    }

    public static Intent newIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, DisplayFullPhotoActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        return intent;
    }

    private void updateView() {
        // Name
        getSupportActionBar().setTitle(title);
        // Load photo
        ImagesUtils.loadUrlIntoImageView(url, this, imageView, R.drawable.no_background_cover, false);
    }
}
