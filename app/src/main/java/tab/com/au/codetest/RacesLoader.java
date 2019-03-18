package tab.com.au.codetest;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import tab.com.au.codetest.data.Races;

public interface RacesLoader {

    @GET("tab-info-service/racing/next-to-go/races?jurisdiction=NSW")
    Call<Races> getRacesCall();

    @GET("tab-info-service/racing/next-to-go/races?jurisdiction=NSW")
    Observable<Races> getRacesObservable();
}
