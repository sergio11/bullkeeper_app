package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

import android.content.Context;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;

/**
 * Sentiment Analysis Fragment Presenter
 */
public final class SentimentAnalysisFragmentPresenter extends SupportPresenter<ISentimentAnalysisFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    private final Context appContext;

    @Inject
    public SentimentAnalysisFragmentPresenter(final Context appContext){
        this.appContext = appContext;
    }

}
