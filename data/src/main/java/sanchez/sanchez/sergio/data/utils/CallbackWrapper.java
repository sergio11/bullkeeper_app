package sanchez.sanchez.sergio.data.utils;

import java.io.IOException;

import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.utils.RetrofitException;
import sanchez.sanchez.sergio.domain.interactor.DefaultObserver;

/**
 * Callback Wrapper
 * @param <T>
 */
public abstract class CallbackWrapper<T> extends DefaultObserver<T> {

    @Override
    public void onError(Throwable exception) {
        super.onError(exception);
        if(exception instanceof RetrofitException){
            RetrofitException error = (RetrofitException) exception;
            try {
                if(error.getKind()== RetrofitException.Kind.NETWORK){
                    onNetworkError();
                }else{
                    final APIResponse response = error.getErrorBodyAs(APIResponse.class);
                    onApiException(response);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
                onOtherException(e1);
            }
        }else{
            onOtherException(exception);
        }
    }

    /**
     * On Network Error
     */
    protected abstract void onNetworkError();

    /**
     * On Api Exception
     * @param response
     */
    protected abstract void onApiException(final APIResponse response);

    /**
     * On Other Exception
     * @param ex
     */
    protected abstract void onOtherException(final Throwable ex);

}
