package gojek.assignment.com.gojek_androidassignment.repository;

import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import gojek.assignment.com.gojek_androidassignment.R;
import gojek.assignment.com.gojek_androidassignment.models.ApiStatus;
import gojek.assignment.com.gojek_androidassignment.models.WeatherResponseModel;
import phonepe.interview.com.dunzo.network.EndpointInterface;
import phonepe.interview.com.dunzo.network.RetrofitUtils;
import phonepe.interview.com.dunzo.utils.constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

/**
 * Created by Akul Aggarwal on 26/07/19.
 */
public class MainRepository {

    private static Context mContext;
    private static MainRepository mInstance;

    private static HashSet<Character> alphabetSet = new HashSet<>();

    private EndpointInterface mApiInterface;

    private MutableLiveData<ApiStatus> apiStatus = new MutableLiveData<>();

    private MutableLiveData<WeatherResponseModel> weatherResponseModel = new MutableLiveData<>();

    public static MainRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (mInstance == null) {
            mInstance = new MainRepository();
        }
        return mInstance;
    }


    private MainRepository() {

    }

    public MutableLiveData<WeatherResponseModel> getWeatherResponseModel() {
        return weatherResponseModel;
    }

    public MutableLiveData<ApiStatus> getApiStatusModel() {
        return apiStatus;
    }


    private void initApiInterface() {
        mApiInterface = RetrofitUtils.INSTANCE.getRetrofitClient(mContext).create(EndpointInterface.class);
    }


    public void makeWeatherForecastRequest(int days, String location) {
        setApiStatus(constants.Status.LOADING, constants.ApiCodes.WEATHER_FORECAST);
        if (mApiInterface == null)
            initApiInterface();
        mApiInterface.getSearchResults(location, mContext.getString(R.string.api_key), days).enqueue(new Callback<WeatherResponseModel>() {
            @Override
            public void onResponse(Call<WeatherResponseModel> call, Response<WeatherResponseModel> response) {
                if (response.isSuccessful()) {
                    weatherResponseModel.setValue(response.body());
                    setApiStatus(constants.Status.SUCCESS, constants.ApiCodes.WEATHER_FORECAST);
                } else
                    setApiStatus(constants.Status.ERROR, constants.ApiCodes.WEATHER_FORECAST);
            }

            @Override
            public void onFailure(Call<WeatherResponseModel> call, Throwable t) {
                setApiStatus(constants.Status.ERROR, constants.ApiCodes.WEATHER_FORECAST);
            }
        });
    }

    private void setApiStatus(constants.Status status, int code) {
        apiStatus.setValue(new ApiStatus(status, code));
    }


}
