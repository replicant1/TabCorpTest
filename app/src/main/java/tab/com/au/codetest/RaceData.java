package tab.com.au.codetest;

import android.content.Context;
import android.support.annotation.RawRes;
import android.support.annotation.WorkerThread;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import tab.com.au.codetest.data.JsonSerializer;
import tab.com.au.codetest.data.Races;

public class RaceData {

    private static RaceData instance;

    private final RacesLoader racesLoader;

    private final JsonSerializer jsonSerializer;

    private RaceData() {

        jsonSerializer = new JsonSerializer();
        racesLoader = new Retrofit.Builder()
                .baseUrl("https://pre-api.beta.tab.com.au/v1/")
                .addConverterFactory(
                        JacksonConverterFactory.create(jsonSerializer.getObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(RacesLoader.class);

    }

    static public RaceData getInstance() {

        if (instance == null) {
            instance = new RaceData();
        }
        return instance;
    }

    /**
     * loads a a list of races from a local resourcef file
     *
     * @param res
     * @param context
     * @return
     * @throws IOException
     */
    @WorkerThread
    Races loadFromResource(@RawRes int res, final @Nonnull Context context) throws IOException {

        final InputStream input = context.getResources().openRawResource(res);
        return jsonSerializer.read(input, Races.class);

    }

    @Nonnull
    public RacesLoader getRacesLoader() {
        return racesLoader;
    }
}
