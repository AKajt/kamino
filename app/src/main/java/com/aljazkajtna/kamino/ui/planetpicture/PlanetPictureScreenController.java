package com.aljazkajtna.kamino.ui.planetpicture;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aljazkajtna.kamino.R;
import com.aljazkajtna.kamino.data.pojo.Planet;

class PlanetPictureScreenController {

    private PlanetPictureFragment fragment;
    private View rootView;

    private TextView name;
    private ImageView image;


    public PlanetPictureScreenController(PlanetPictureFragment fragment) {
        this.fragment = fragment;
        rootView = fragment.getView();

        name = rootView.findViewById(R.id.name);
        image = rootView.findViewById(R.id.image);
    }

    public void updateView(Planet planet) {
        name.setText(planet.getName());
    }

    public ImageView getImage() {
        return image;
    }
}
