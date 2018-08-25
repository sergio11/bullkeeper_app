package sanchez.sanchez.sergio.masom_app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sanchez.sanchez.sergio.masom_app.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


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
    public ObjectMapper provideObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .build();
    }
}
