package me.zed.countryselect;

import android.os.Handler;

import java.util.concurrent.Executor;

interface RepositoryCallback<T> {
    void onComplete(ResultRes<T> result);
}

public class MainRepository {

    AppSingleton as = AppSingleton.getInstance();
    CountryListApi service = as.retrofitListCountry.create(CountryListApi.class);

    private final Handler resultHandler = as.mainThreadHandler;
    private final Executor executor = as.executorService;

    public void fetchCountries(
            final RepositoryCallback<CountryList> callback
    ) {
        executor.execute(() -> {
            try {
                CountryList result = service.listCountries().execute().body();
                if(result != null){
                    ResultRes<CountryList> res = new ResultRes.Success<>(result);
                    notifyResult(res, callback);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void notifyResult(
            final ResultRes<CountryList> result,
            final RepositoryCallback<CountryList> callback
            ) {
        resultHandler.post(() -> callback.onComplete(result));
    }


}
