package com.freestand.ranu.fsmark2.di;

import android.app.Application;

import com.freestand.ranu.fsmark2.di.component.AppComponent;
import com.freestand.ranu.fsmark2.di.component.DaggerAppComponent;
import com.freestand.ranu.fsmark2.di.component.NetComponent;
import com.freestand.ranu.fsmark2.di.module.AppModule;
import com.freestand.ranu.fsmark2.di.module.NetModule;

/**
 * Created by prateek on 22/02/18.
 */

public class ComponentFactory {

    private static ComponentFactory instance;
    private NetComponent netComponent;
    private AppComponent appComponent;

    public static ComponentFactory getComponentFactory() {
        if(instance == null)
            instance = new ComponentFactory();
        return instance;
    }

    public NetComponent getNetComponent(){
        netComponent = appComponent.plusNetComponent(new NetModule());
        return netComponent;
    }

    public AppComponent getAppComponent(Application application) {
        if(appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    // list of modules that are part of this component need to be created here too
                    .appModule(new AppModule(application))// This also corres
                    .build();
        }
        return appComponent;
    }


}
