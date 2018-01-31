package io.moku.davide.sideproject.utils.recyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.NumberPicker;

import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.model.User;

/**
 * Created by Davide Castello on 29/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class PagerNumberPickerDialogPreference extends DialogFragment {

    public static String KEY_NUM_PAGES = "num_pages";
    public static int MIN_PAGES = 0;
    public static int MAX_PAGES;
    public static int DEFAULT_PAGES = 3;

    private NumberPicker numberPicker;
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MAX_PAGES = User.getUsers(getActivity()).size();
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dialog_pager_number_picker, null);

        bindViews(view);
        setupViews();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.pager_number_picker_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.pager_number_picker_dialog_positive_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        preferences.edit().putInt(KEY_NUM_PAGES, numberPicker.getValue()).apply();
                        ((OnPagerNumberChangeListener) getActivity()).onPagerNumberChanged();
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.pager_number_picker_dialog_negative_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .create();
    }

    private void bindViews(View view) {
        numberPicker = view.findViewById(R.id.pager_number_dialog_number_picker);
    }

    private void setupViews() {
        numberPicker.setMinValue(MIN_PAGES);
        numberPicker.setMaxValue(MAX_PAGES);
        numberPicker.setValue(preferences.getInt(KEY_NUM_PAGES, DEFAULT_PAGES));
    }
}
