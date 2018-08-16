package com.aljazkajtna.kamino.ui.residents;

import android.view.View;
import android.widget.ListView;

import com.aljazkajtna.kamino.R;
import com.aljazkajtna.kamino.data.pojo.Resident;

import java.util.List;

public class ResidentsScreenController {

    private ResidentsFragment fragment;
    private View rootView;

    private ListView residents;
    private ResidentsListAdapter adapter;

    public ResidentsScreenController(ResidentsFragment fragment) {
        this.fragment = fragment;
        rootView = fragment.getView();

        residents = rootView.findViewById(R.id.residentsList);
    }

    public void updateView(List<Resident> list) {
        adapter = new ResidentsListAdapter(fragment, list);
        residents.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
