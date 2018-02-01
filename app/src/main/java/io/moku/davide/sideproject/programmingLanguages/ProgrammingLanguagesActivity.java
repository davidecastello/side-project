package io.moku.davide.sideproject.programmingLanguages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.ProgrammingLanguage;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;
import io.moku.davide.sideproject.utils.gridView.ProgrammingLanguageUsage;
import io.moku.davide.sideproject.utils.gridView.ProgrammingLanguagesAdapter;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ProgrammingLanguagesActivity extends BasicSecondaryActivity {

    @BindView(R.id.programmingLanguagesGridView) GridView gridView;
    private ProgrammingLanguagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_languages);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        RealmResults<ProgrammingLanguage> results = ProgrammingLanguage.getAllLanguages();
        if (results.size() != 0) {
            List<ProgrammingLanguageUsage> languageUsages = new ArrayList<>();
            for (ProgrammingLanguage language : results) {
                languageUsages.add(new ProgrammingLanguageUsage(language, language.getUsage()));
            }
            Collections.sort(languageUsages, ProgrammingLanguageUsage.ORDER_BY_USAGE);
            adapter = new ProgrammingLanguagesAdapter(this, languageUsages);
            gridView.setAdapter(adapter);
        }
    }
}
