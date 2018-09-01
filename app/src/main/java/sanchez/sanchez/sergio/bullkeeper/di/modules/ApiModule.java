package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sanchez.sanchez.sergio.data.net.deserializers.BirthdayDeserializer;
import sanchez.sanchez.sergio.data.net.interceptors.AuthTokenInterceptor;
import sanchez.sanchez.sergio.data.net.utils.RxJava2ErrorHandlingCallAdapterFactory;
import sanchez.sanchez.sergio.bullkeeper.BuildConfig;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.Date;


@Module
public class ApiModule {

    /**
     * Provide Auth Token Inteceptor
     * @param authTokenAware
     * @return
     */
    @Singleton
    @Provides
    public AuthTokenInterceptor provideAuthTokenInterceptor(final IAuthTokenAware authTokenAware) {
        return new AuthTokenInterceptor(authTokenAware);
    }

    /**
     * Provide HTTP Client
     * @return
     */
    @Singleton
    @Provides
    public OkHttpClient provideHttpClient(final AuthTokenInterceptor authTokenInterceptor){
        OkHttpClient client;
        if (BuildConfig.DEBUG) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(authTokenInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }else{
            client = new OkHttpClient.Builder()
                    .addInterceptor(authTokenInterceptor)
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

    /**
     * Provide Picasso
     * @param client
     * @param context
     * @return
     */
    @Singleton
    @Provides
    public Picasso providePicasso(final OkHttpClient client, final Context context) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
    }
}
