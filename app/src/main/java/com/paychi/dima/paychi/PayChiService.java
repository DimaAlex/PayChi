package com.paychi.dima.paychi;

import com.paychi.dima.paychi.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PayChiService {
    @POST("user/registerVk/")
    Call<UserResponse> getUser(
        @Header(value = "token") String token,
        @Header(value = "userId") int userId,
        @Query(value = "type") int type
    );
}
