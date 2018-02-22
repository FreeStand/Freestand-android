package com.freestand.ranu.fsmark2.di.component;

import com.freestand.ranu.fsmark2.adapter.CouponAdapter;
import com.freestand.ranu.fsmark2.di.NetScope;
import com.freestand.ranu.fsmark2.di.module.NetModule;
import com.freestand.ranu.fsmark2.fragment.Alerts;
import com.freestand.ranu.fsmark2.fragment.Coupon;
import com.freestand.ranu.fsmark2.fragment.FAQ;
import com.freestand.ranu.fsmark2.fragment.QRScanner;

import dagger.Subcomponent;
import retrofit2.Retrofit;

/**
 * Created by prateek on 22/02/18.
 */
@Subcomponent(modules = NetModule.class)
@NetScope
public interface NetComponent {
    void inject(CouponAdapter couponAdapter);
    void inject (Alerts alerts);
    void inject(FAQ faq);
    void inject(Coupon coupon);
    void inject(QRScanner qrScanner);
}
