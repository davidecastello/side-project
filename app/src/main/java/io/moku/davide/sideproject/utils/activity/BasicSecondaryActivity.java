package io.moku.davide.sideproject.utils.activity;

public class BasicSecondaryActivity extends BasicActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
