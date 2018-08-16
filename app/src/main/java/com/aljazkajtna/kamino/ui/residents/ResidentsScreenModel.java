package com.aljazkajtna.kamino.ui.residents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aljazkajtna.kamino.data.pojo.Resident;
import com.aljazkajtna.kamino.data.repository.ResidentsRepository;

import java.util.List;

import javax.inject.Inject;

public class ResidentsScreenModel extends ViewModel {

    private LiveData<List<Resident>> residentLiveData;
    private ResidentsRepository residentsRepository;

    @Inject
    public ResidentsScreenModel(ResidentsRepository residentsRepository) {
        this.residentsRepository = residentsRepository;
    }

    public void init(List<Integer> residents) {
        if(residentLiveData != null) {
            return;
        }
        residentLiveData = residentsRepository.getResidents(residents);
    }

    public LiveData<List<Resident>> getResidentLiveData() {
        return residentLiveData;
    }
}
