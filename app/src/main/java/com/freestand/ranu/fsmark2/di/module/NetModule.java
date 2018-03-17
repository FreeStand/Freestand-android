package com.freestand.ranu.fsmark2.di.module;

import com.freestand.ranu.fsmark2.di.NetScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.freestand.ranu.fsmark2.Constants.BASE_URL;

/**
 * Created by prateek on 22/02/18.
 */
@Module
public class NetModule {
    @Provides
    @NetScope
    Retrofit retrofit () {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
