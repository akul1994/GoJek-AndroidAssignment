package gojek.assignment.com.gojek_androidassignment.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import gojek.assignment.com.gojek_androidassignment.R;
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse;
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

    private MutableLiveData<ApiResponse> responseModel = new MutableLiveData<>();

    public static MainRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (mInstance == null) {
            mInstance = new MainRepository();
        }
        return mInstance;
    }


    private MainRepository() {

    }

    public MutableLiveData<ApiResponse> getWeatherResponseModel() {
        return responseModel;
    }

    private void initApiInterface() {
        mApiInterface = RetrofitUtils.INSTANCE.getRetrofitClient(mContext).create(EndpointInterface.class);
    }


    public void makeWeatherForecastRequest(int days, String location) {
        setResponse(constants.Status.LOADING, null);
        if (mApiInterface == null)
            initApiInterface();
        mApiInterface.getSearchResults(location, mContext.getString(R.string.api_key), days).enqueue(new Callback<WeatherResponseModel>() {
            @Override
            public void onResponse(Call<WeatherResponseModel> call, Response<WeatherResponseModel> response) {
                if (response.isSuccessful()) {
                    setResponse(constants.Status.SUCCESS, response.body());
                } else
                    setResponse(constants.Status.ERROR, null);
            }

            @Override
            public void onFailure(Call<WeatherResponseModel> call, Throwable t) {
                setResponse(constants.Status.ERROR, null);
            }
        });
    }

    private void setResponse(constants.Status status, WeatherResponseModel weatherResponseModel) {
        responseModel.setValue(new ApiResponse(status, weatherResponseModel));
    }


}
