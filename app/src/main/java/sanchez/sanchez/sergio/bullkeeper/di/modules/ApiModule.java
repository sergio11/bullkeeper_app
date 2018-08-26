package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sanchez.sanchez.sergio.data.net.deserializers.BirthdayDeserializer;
import sanchez.sanchez.sergio.data.net.utils.RxJava2ErrorHandlingCallAdapterFactory;
import sanchez.sanchez.sergio.bullkeeper.BuildConfig;
import sanchez.sanchez.sergio.bullkeeper.R;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;


@Module
public class ApiModule {

    /**
     * Provide HTTP Client
     * @return
     */
    @Singleton
    @Provides
    public OkHttpClient provideHttpClient(){
        OkHttpClient client;
        if (BuildConfig.DEBUG) {
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }else{
            client = new OkHttpClient.Builder()
                    .build();
        }
        return client;
    }

    /**
     * Provide Object Mapper
     * @return
     */
    @Singleton
    @Provides
    public ObjectMapper provideObjectMapper(final Context appContext){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new BirthdayDeserializer(appContext.getString(R.string.date_format_server_response)));
        mapper.registerModule(module);
        return mapper;
    }

    /**
     * Provide Retrofit
     * @param client
     * @param mapper
     * @return
     */
    @Singleton
    @Provides
    public Retrofit provideRetrofit(final OkHttpClient client, final ObjectMapper mapper){

        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2ErrorHandlingCallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .build();
    }
}
