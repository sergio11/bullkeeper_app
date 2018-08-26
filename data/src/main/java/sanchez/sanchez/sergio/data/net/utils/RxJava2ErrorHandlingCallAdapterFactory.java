package sanchez.sanchez.sergio.data.net.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 */
public final class RxJava2ErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory original;

    private RxJava2ErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new Rx2CallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    /**
     * Create Call Adapter
     * @return
     */
    public static CallAdapter.Factory create() {
        return new RxJava2ErrorHandlingCallAdapterFactory();
    }

    /**
     * Rx Call Adapter Wrapper
     */
    private static class Rx2CallAdapterWrapper implements CallAdapter<Observable<?>, Observable<?>> {

        private final Retrofit retrofit;
        private final CallAdapter<Observable<?>, Object> wrapped;

        public Rx2CallAdapterWrapper(Retrofit retrofit, CallAdapter wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @Override
        public Observable<?> adapt(Call<Observable<?>> call) {
            return ((Observable) wrapped.adapt(call)).onErrorResumeNext(throwable -> {
                return Observable.error(asRetrofitException((Throwable) throwable));
            });
        }

        /**
         * As Retrofit Exception
         * @param throwable
         * @return
         */
        private RetrofitException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable);
        }
    }
}
