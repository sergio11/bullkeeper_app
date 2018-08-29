package sanchez.sanchez.sergio.data.net.interceptors;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

/**
 * Auth Token Interceptor
 */
public final class AuthTokenInterceptor implements Interceptor {

    private final static String TOKEN_HEADER_NAME = "Authorization";

    /**
     * Auth Token Aware
     */
    private IAuthTokenAware authTokenAware;

    /**
     *
     * @param authTokenAware
     */
    public AuthTokenInterceptor(IAuthTokenAware authTokenAware) {
        super();
        this.authTokenAware = authTokenAware;
    }

    /**
     * Intercept
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (authTokenAware.getAuthToken() != null){
            request = request.newBuilder()
                    .header(TOKEN_HEADER_NAME, authTokenAware.getAuthToken())
                    .method(request.method(), request.body())
                    .build();
        }
        return chain.proceed(request);
    }

}
