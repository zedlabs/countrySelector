package me.zed.countryselect;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class AppSingleton {
    private static AppSingleton single_instance = null;

    public Retrofit retrofitListCountry;
    ExecutorService executorService;
    Handler mainThreadHandler;

    private AppSingleton() {
        retrofitListCountry = new Retrofit.Builder()
                .baseUrl("https://api.printful.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        executorService = Executors.newFixedThreadPool(4);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    // static method to create instance of Singleton class
    public static AppSingleton getInstance() {
        if (single_instance == null)
            single_instance = new AppSingleton();

        return single_instance;
    }
}
