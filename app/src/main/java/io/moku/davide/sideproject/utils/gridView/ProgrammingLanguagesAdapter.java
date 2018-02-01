package io.moku.davide.sideproject.utils.gridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import io.moku.davide.sideproject.R;

/**
 * Created by Davide Castello on 01/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class ProgrammingLanguagesAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<ProgrammingLanguageUsage> languageUsages;

    public ProgrammingLanguagesAdapter(Context context, List<ProgrammingLanguageUsage> languageUsages) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.languageUsages = languageUsages;
    }

    @Override
    public int getCount() {
        return languageUsages.size();
    }

    @Override
    public Object getItem(int i) {
        return languageUsages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return languageUsages.get(i).getLanguage().getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.programming_language_cell_layout, viewGroup, false);
        }

        ProgrammingLanguageUsage languageUsage = languageUsages.get(i);
        ((TextView) view.findViewById(R.id.name)).setText(languageUsage.getLanguage().getName());
        ((TextView) view.findViewById(R.id.usage)).setText(String.format(Locale.getDefault(), context.getString(R.string.usage), languageUsage.getPeople()));

        view.setOnClickListener(null);
        return view;
    }
}
