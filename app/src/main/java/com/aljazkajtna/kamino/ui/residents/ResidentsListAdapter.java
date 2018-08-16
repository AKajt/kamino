package com.aljazkajtna.kamino.ui.residents;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aljazkajtna.kamino.R;
import com.aljazkajtna.kamino.data.pojo.Resident;

import java.util.List;

public class ResidentsListAdapter extends BaseAdapter {

    private static final String Tag = "ResidentsListAdapter";

    private final Fragment fragment;
    private final List<Resident> residentList;

    public ResidentsListAdapter(Fragment fragment, List<Resident> residentList) {
        this.fragment = fragment;
        this.residentList = residentList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return residentList.size();
    }

    @Override
    public Object getItem(int i) {
        return residentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Resident data = (Resident) getItem(i);

        View elementView = (View) view;
        if (elementView == null) {
            final LayoutInflater inflater = LayoutInflater.from(fragment.getContext());
            elementView = inflater.inflate(R.layout.resident_list_element, null);
        }
        TextView name = elementView.findViewById(R.id.residentName);
        name.setText(data.getName());

        elementView.setOnClickListener(view1 -> {
            ((ResidentsFragment) fragment).openResident(data);
        });

        return elementView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
