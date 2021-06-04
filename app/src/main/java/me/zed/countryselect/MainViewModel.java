package me.zed.countryselect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    MainRepository repository = new MainRepository();

    private MutableLiveData<List<Result>> flags;
    public LiveData<List<Result>> getUsers() {
        if (flags == null) {
            flags = new MutableLiveData<>();
            fetchCountries();
        }
        return flags;
    }

    public void fetchCountries() {
        repository.fetchCountries(result -> {
            if (result instanceof ResultRes.Success) {
                flags.postValue(((ResultRes.Success<CountryList>) result).data.getResult());
            } else {
                // Show error in UI
            }
        });
    }
}
