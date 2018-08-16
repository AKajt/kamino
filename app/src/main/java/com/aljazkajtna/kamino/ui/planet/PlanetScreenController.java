package com.aljazkajtna.kamino.ui.planet;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aljazkajtna.kamino.R;
import com.aljazkajtna.kamino.data.pojo.Planet;

public class PlanetScreenController {

    private PlanetFragment fragment;
    private View rootView;

    private Button likeButton;
    private Button viewResidentsButton;

    private ImageView image;
    private TextView name;
    private TextView likes;
    private TextView rotationPeriod;
    private TextView orbitalPeriod;
    private TextView diameter;
    private TextView gravity;
    private TextView climate;
    private TextView terrain;
    private TextView surfaceWater;
    private TextView population;

    public PlanetScreenController(PlanetFragment fragment) {
        this.fragment = fragment;
        rootView = fragment.getView();

        likeButton = rootView.findViewById(R.id.likeButton);
        viewResidentsButton = rootView.findViewById(R.id.residentsButton);

        image = rootView.findViewById(R.id.avatar);
        name = rootView.findViewById(R.id.planetName);
        likes = rootView.findViewById(R.id.likes);
        rotationPeriod = rootView.findViewById(R.id.rotationPeriodValue);
        orbitalPeriod = rootView.findViewById(R.id.orbitalPeriodValue);
        diameter = rootView.findViewById(R.id.diameterValue);
        gravity = rootView.findViewById(R.id.gravityValue);
        climate = rootView.findViewById(R.id.climateValue);
        terrain = rootView.findViewById(R.id.terrainValue);
        surfaceWater = rootView.findViewById(R.id.surfaceWaterValue);
        population = rootView.findViewById(R.id.populationValue);

        initListeners();
    }

    private void initListeners() {
        image.setOnClickListener(view -> {
            fragment.openImage();
        });

        likeButton.setOnClickListener(view -> {
            fragment.likePlanet();
        });

        viewResidentsButton.setOnClickListener(view -> {
            fragment.viewResidents();
        });
    }

    public void updateView(Planet planet) {
        if (planet == null) {
            return;
        }

        name.setText(planet.getName());
        likes.setText(fragment.getString(R.string.likes, planet.getLikes()));
        rotationPeriod.setText(planet.getRotationPeriod());
        orbitalPeriod.setText(planet.getOrbitalPeriod());
        diameter.setText(planet.getDiameter());
        gravity.setText(planet.getGravity());
        climate.setText(planet.getClimate());
        terrain.setText(planet.getTerrain());
        surfaceWater.setText(planet.getSurfaceWater());
        population.setText(planet.getPopulation());

        likeButton.setVisibility(planet.isLiked() ? View.GONE : View.VISIBLE);
    }

    public ImageView getImage() {
        return image;
    }
}
