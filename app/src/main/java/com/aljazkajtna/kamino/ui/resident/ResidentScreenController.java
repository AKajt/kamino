package com.aljazkajtna.kamino.ui.resident;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aljazkajtna.kamino.R;
import com.aljazkajtna.kamino.data.pojo.Resident;

public class ResidentScreenController {

    private ResidentFragment fragment;
    private View rootView;

    private ImageView image;
    private TextView name;
    private TextView height;
    private TextView mass;
    private TextView hairColor;
    private TextView skinColor;
    private TextView eyeColor;
    private TextView birthYear;
    private TextView gender;
    private TextView noImage;

    public ResidentScreenController(ResidentFragment fragment) {
        this.fragment = fragment;
        rootView = fragment.getView();

        image = rootView.findViewById(R.id.image);
        name = rootView.findViewById(R.id.name);
        height = rootView.findViewById(R.id.heightValue);
        mass = rootView.findViewById(R.id.massValue);
        hairColor = rootView.findViewById(R.id.hairColorValue);
        skinColor = rootView.findViewById(R.id.skinColorValue);
        eyeColor = rootView.findViewById(R.id.eyeColorValue);
        birthYear = rootView.findViewById(R.id.birthYearValue);
        gender = rootView.findViewById(R.id.genderValue);
        noImage = rootView.findViewById(R.id.noImage);
    }

    public void updateView(Resident resident) {
        name.setText(resident.getName());
        height.setText(resident.getHeight());
        mass.setText(resident.getMass());
        hairColor.setText(resident.getHairColor());
        skinColor.setText(resident.getSkinColor());
        eyeColor.setText(resident.getEyeColor());
        birthYear.setText(resident.getBirthYear());
        gender.setText(resident.getGender());
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getNoImage() {
        return noImage;
    }
}
