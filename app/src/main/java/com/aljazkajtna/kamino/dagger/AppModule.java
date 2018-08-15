package com.aljazkajtna.kamino.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.aljazkajtna.kamino.data.database.SWApiDao;
import com.aljazkajtna.kamino.data.database.SWapiDatabase;
import com.aljazkajtna.kamino.data.repository.PlanetRepository;
import com.aljazkajtna.kamino.data.web.SWApiwebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    SWapiDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                SWapiDatabase.class, "SWapiDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    SWApiDao provideSWapiDao(SWapiDatabase database) { return database.swapiDao(); }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    PlanetRepository providePlanetRepository(SWApiwebService webservice, SWApiDao swapiDao, Executor executor) {
        return new PlanetRepository(webservice, swapiDao, executor);
    }

    private static String BASE_URL = "http://private-84e428-starwars2.apiary-mock.com/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    SWApiwebService provideSWapiWebservice(Retrofit restAdapter) {
        return restAdapter.create(SWApiwebService.class);
    }
}
