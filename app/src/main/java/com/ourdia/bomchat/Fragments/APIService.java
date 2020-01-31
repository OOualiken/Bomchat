package com.ourdia.bomchat.Fragments;

import com.ourdia.bomchat.Notifications.MyResponse;
import com.ourdia.bomchat.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAOLKVCLY:APA91bGuweWQ0HMScBG8Y2E4LKbfVcuV6mr-dqwmBrwQkSO3_JkbGSI6zIMwNNX3X0KxSfDdgDS9fmwPuJWYYOuouGuuflio5cmFBBeq6Ef529Cv1sM6TBhLH-FYCBA_Ny6GB6BRpkmO"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
