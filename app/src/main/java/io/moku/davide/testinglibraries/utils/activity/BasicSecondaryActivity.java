package io.moku.davide.testinglibraries.utils.activity;

public class BasicSecondaryActivity extends BasicActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
