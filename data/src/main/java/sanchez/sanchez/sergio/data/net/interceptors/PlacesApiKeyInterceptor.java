package sanchez.sanchez.sergio.data.net.interceptors;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Places API Key Interceptor Interceptor
 */
public final class PlacesApiKeyInterceptor implements Interceptor {

    private final static String PLACE_API_ID = "Lm9XFKeBSlxlfgncRll5";
    private final static String PLACE_API_CODE = "l7S_EHXfjjGDdG170h29Ug";

    /**
     * Intercept
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("app_id", PLACE_API_ID)
                .addQueryParameter("app_code", PLACE_API_CODE)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
