package com.xtorres.almacenapp.services;

import com.xtorres.almacenapp.BuildConfig;
import com.xtorres.almacenapp.models.ApiError;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
<<<<<<< HEAD
=======
import retrofit2.converter.scalars.ScalarsConverterFactory;
>>>>>>> Second commit

public class ApiServiceGenerator {

    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass) {
        if(retrofit == null) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS);

            if(BuildConfig.DEBUG) {
                httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.API_BASE_URL)
<<<<<<< HEAD
=======
                    .addConverterFactory(ScalarsConverterFactory.create())
>>>>>>> Second commit
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
        }
        return retrofit.create(serviceClass);
    }


    public static ApiError parseError(Response<?> response) {
        try {
            Converter<ResponseBody, ApiError> converter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
            return converter.convert(response.errorBody());
        } catch (Exception e) {
            return new ApiError("Error desconocido en el servicio");
        }
    }
}
