package com.aljazkajtna.kamino.ui.resident;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aljazkajtna.kamino.data.pojo.Resident;
import com.aljazkajtna.kamino.data.repository.ResidentsRepository;

import java.util.List;

import javax.inject.Inject;

public class ResidentScreenModel extends ViewModel {

    private LiveData<List<Resident>> residentLiveData;
    private ResidentsRepository residentsRepository;

    @Inject
    public ResidentScreenModel(ResidentsRepository residentsRepository) {
        this.residentsRepository = residentsRepository;
    }

    public void init(String resident) {
        if (residentLiveData !=null) {
            return;
        }
        residentLiveData = residentsRepository.getSpecificResident(resident);
    }

    public LiveData<List<Resident>> getResidentLiveData() {
        return residentLiveData;
    }
}
