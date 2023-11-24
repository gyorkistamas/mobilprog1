package com.tamas.gyorkis.mobilprog_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends BaseAdapter {

    List<Subject> subjects = new ArrayList<>();

    Context context;

    public SubjectAdapter(List<Subject> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return subjects.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.subject_list_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.textName);
        TextView code = convertView.findViewById(R.id.textCode);
        TextView credit = convertView.findViewById(R.id.textCredit);

        name.setText(subjects.get(position).getName());
        code.setText(subjects.get(position).getCode());
        credit.setText(String.valueOf(subjects.get(position).getCredit()));

        return convertView;
    }
}
