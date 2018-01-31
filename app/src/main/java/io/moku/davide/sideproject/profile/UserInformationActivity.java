package io.moku.davide.sideproject.profile;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity;

public class UserInformationActivity extends BasicSecondaryActivity {

    @BindView(R.id.constraintLayout) ConstraintLayout constraintLayout;
    @BindView(R.id.personalInfo) TextView personalInfo;
    @BindView(R.id.firstLanguageLayout) LinearLayout firstLanguageLayout;
    @BindView(R.id.firstLanguage) TextView firstLanguage;
    @BindView(R.id.firstPercentage) TextView firstPercentage;
    @BindView(R.id.secondLanguageLayout) LinearLayout secondLanguageLayout;
    @BindView(R.id.secondLanguage) TextView secondLanguage;
    @BindView(R.id.secondPercentage) TextView secondPercentage;
    @BindView(R.id.thirdLanguageLayout) LinearLayout thirdLanguageLayout;
    @BindView(R.id.thirdLanguage) TextView thirdLanguage;
    @BindView(R.id.thirdPercentage) TextView thirdPercentage;
    @BindView(R.id.fourthLanguageLayout) LinearLayout fourthLanguageLayout;
    @BindView(R.id.fourthLanguage) TextView fourthLanguage;
    @BindView(R.id.fourthPercentage) TextView fourthPercentage;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // fake retrieve user
        user = User.getAllUsers().get(0);

        updateView();
    }

    private void updateView() {
        // Personal info
        personalInfo.setText(user.getPersonalInfo());

        // Programming languages
        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);
        String testLanguage1 = "Ruby", testLanguage2 = "C", testLanguage3 = "PHP", testLanguage4 = "Java";
        float testPercentage1 = 0.4f, testPercentage2 = 0.2f, testPercentage3 = 0.2f, testPercentage4 = 0.2f;

        fixWeights(set, firstLanguage, testLanguage1, firstPercentage, testPercentage1, R.id.firstLanguageLayout);
        fixWeights(set, secondLanguage, testLanguage2, secondPercentage, testPercentage2, R.id.secondLanguageLayout);
        fixWeights(set, thirdLanguage, testLanguage3, thirdPercentage, testPercentage3, R.id.thirdLanguageLayout);
        fixWeights(set, fourthLanguage, testLanguage4, fourthPercentage, testPercentage4, R.id.fourthLanguageLayout);

        set.applyTo(constraintLayout);
    }

    private void fixWeights(ConstraintSet set, TextView language, String l, TextView percentage,
                            float p, int layoutId) {
        language.setText(l);
        int value = (int) (p * 100);
        percentage.setText(String.format(Locale.getDefault(), getString(R.string.percentage_format), value));
        set.setHorizontalWeight(layoutId, p);
    }
}
