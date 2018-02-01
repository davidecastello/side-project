package io.moku.davide.sideproject.profile;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.UsedProgrammingLanguage;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;
import io.realm.RealmList;

public class UserInformationActivity extends BasicSecondaryActivity {

    @BindView(R.id.constraintLayout) ConstraintLayout constraintLayout;
    @BindView(R.id.personalInfo) TextView personalInfo;

    @BindViews({ R.id.firstLanguageLayout, R.id.secondLanguageLayout, R.id.thirdLanguageLayout, R.id.fourthLanguageLayout }) List<LinearLayout> languageLayouts;
    @BindViews({ R.id.firstLanguage, R.id.secondLanguage, R.id.thirdLanguage, R.id.fourthLanguage }) List<TextView> languages;
    @BindViews({ R.id.firstPercentage, R.id.secondPercentage, R.id.thirdPercentage, R.id.fourthPercentage }) List<TextView> percentages;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // Retrieve user
        int userId = getIntent().getIntExtra(User.EXTRA_USER_ID, -1);
        user = User.getUser(userId);

        updateView();
    }

    private void updateView() {
        // Personal info
        personalInfo.setText(user.getPersonalInfo());

        // Programming languages
        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);
        List<UsedProgrammingLanguage> usedProgrammingLanguages = user.getUsedProgrammingLanguages();

        int usedLanguages = usedProgrammingLanguages.size(), languagesSlots = languageLayouts.size();
        if (usedLanguages > 0 && usedLanguages <= languagesSlots) {
            RealmList<UsedProgrammingLanguage> languagesOrderedList = new RealmList<>();
            languagesOrderedList.addAll(usedProgrammingLanguages.subList(0, usedProgrammingLanguages.size()));
            Collections.sort(languagesOrderedList, UsedProgrammingLanguage.ORDER_BY_PERCENTAGE);
            int i = 0;
            while (i < usedLanguages) {
                UsedProgrammingLanguage l = languagesOrderedList.get(i);
                fixWeights(set, languages.get(i), l.getLanguage().getName(), percentages.get(i), l.getPercentage(), languageLayouts.get(i));
                i++;
            }
            while (i < languagesSlots) {
                set.setHorizontalWeight(languageLayouts.get(i).getId(), 0f);
                i++;
            }
        }

        set.applyTo(constraintLayout);
    }

    private void fixWeights(ConstraintSet set, TextView language, String l, TextView percentage,
                            float p, LinearLayout layout) {
        language.setText(l);
        int value = (int) (p * 100);
        percentage.setText(String.format(Locale.getDefault(), getString(R.string.percentage_format), value));
        set.setHorizontalWeight(layout.getId(), p);
    }
}
