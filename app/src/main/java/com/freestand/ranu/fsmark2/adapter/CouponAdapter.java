package com.freestand.ranu.fsmark2.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freestand.ranu.fsmark2.Activities.FeedbackScreen;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.model.CouponItem;
import com.freestand.ranu.fsmark2.data.model.CouponItemResponse;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.freestand.ranu.fsmark2.di.NetScope;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prateek on 11/02/18.
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.MyViewHolder> {
    @Inject @NetScope
    Retrofit retrofit;
    private List<CouponItem> couponItemList = new ArrayList<>();
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, couponValue, description, showCopied, clickCoupon;
        ImageView couponImage;
        LinearLayout couponCodeLayout;
        RelativeLayout couponCard;
        ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.coupon_title);
            couponValue = (TextView) view.findViewById(R.id.coupon_code_value);
            description = (TextView) view.findViewById(R.id.description);
            couponImage = (ImageView) view.findViewById(R.id.coupon_image);
            showCopied = (TextView)view.findViewById(R.id.show_copied);
            clickCoupon = (TextView) view.findViewById(R.id.click_coupon);
            couponCodeLayout = (LinearLayout)view.findViewById(R.id.coupon_code);
            couponCard = (RelativeLayout)view.findViewById(R.id.coupon_card);
            context = view.getContext();
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            attachClickListener();
        }

        private void attachClickListener() {
            couponCard.setOnClickListener(this);
        }

        private void getGeneralData(String brand, String couponID) {
            ApiInterface apiService =
                    retrofit.create(ApiInterface.class);
            Map map = new HashMap();
            map.put("uid", FirebaseAuth.getInstance().getUid());
            map.put("brand", brand);
            map.put("couponID", couponID);

            Call<CouponItemResponse> call = apiService.getGeneralCouponSurvey(map);
            call.enqueue(new Callback<CouponItemResponse>() {
                @Override
                public void onResponse(Call<CouponItemResponse>call, Response<CouponItemResponse> response) {
                    Log.e("response ", response.toString());
                    CouponItemResponse couponItemResponse = response.body();

                    couponItemResponse.setCouponCode(couponItemList.get(getAdapterPosition()).getGeneralCouponCode());
                        Intent intent = new Intent(context, FeedbackScreen.class);
                        intent.putExtra("question_list", (Serializable) couponItemResponse.getQuestions());
                        intent.putExtra("survey_id", couponItemList.get(getAdapterPosition()).getCouponID());
                        intent.putExtra("brand", couponItemList.get(getAdapterPosition()).getBrandName());
                        intent.putExtra("sender", "coupon");
                        intent.putExtra("direction", "dismiss");
                        context.startActivity(intent);
                }

                @Override
                public void onFailure(Call<CouponItemResponse>call, Throwable t) {
                    // Log error here since request failed
                    Log.e(" ", t.toString());
                }
            });

        }

        @Override
        public void onClick(View view) {
            int positon;
            CouponItem item;
            if(view.getId() == couponCard.getId()) {
                positon = getAdapterPosition();
                 item = couponItemList.get(positon);
                if (item.getRedeem() != null) {
                    copyCode();
                } else {
                    getGeneralData(item.getBrandName(), item.getCouponID());
                }
            }
        }

        private void copyCode() {
            ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText(couponValue.getText());
            Toasty.success(context, "Copied Successfully!", Toast.LENGTH_SHORT, true).show();
       }
    }


    public CouponAdapter(List<CouponItem> couponItemList) {
        this.couponItemList = couponItemList;
        ComponentFactory.getComponentFactory().getNetComponent().inject(this);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coupon, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.progressBar.setVisibility(View.GONE);
        CouponItem item = couponItemList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getSubtitle());
        Picasso.with(context).load(item.getImgURL()).into(holder.couponImage);
        if(item.getRedeem() == null) {
            holder.clickCoupon.setVisibility(View.VISIBLE);
            holder.couponCodeLayout.setVisibility(View.GONE);
        } else {
            holder.clickCoupon.setVisibility(View.GONE);
            holder.couponCodeLayout.setVisibility(View.VISIBLE);
            holder.couponValue.setText(item.getRedeem());
        }
    }

    @Override
    public int getItemCount() {
        return couponItemList.size();
    }


}
