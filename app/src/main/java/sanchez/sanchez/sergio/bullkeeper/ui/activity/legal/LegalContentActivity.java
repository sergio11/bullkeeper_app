package sanchez.sanchez.sergio.bullkeeper.ui.activity.legal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Legal Content Activity
 */
public class LegalContentActivity extends AppCompatActivity {

    private final static String LEGAL_CONTENT_TYPE_ARG = "LEGAL_CONTENT_TYPE_ARG";

    public enum LegalTypeEnum { TERMS_OF_SERVICE, PRIVACY_POLICY }

    private LegalTypeEnum legalTypeEnum = LegalTypeEnum.TERMS_OF_SERVICE;

    /**
     * Legal Content Title
     */
    @BindView(R.id.legalContentTitle)
    protected TextView legalContentTitleTextView;

    /**
     * Text Licence Web View
     */
    @BindView(R.id.textLicenceWebView)
    protected WebView textLicenceWebView;

    /**
     * Text Licence Loading
     */
    @BindView(R.id.textLicenceLoading)
    protected ProgressBar textLicenceLoading;

    /**
     * Button Accept
     */
    @BindView(R.id.accept)
    protected Button buttonAccept;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, LegalContentActivity.class);
    }

    /**
     * Get Calling Intent
     * @param context
     * @param legalTypeEnum
     * @return
     */
    public static Intent getCallingIntent(final Context context, final LegalTypeEnum legalTypeEnum) {
        final Intent intent = new Intent(context, LegalContentActivity.class);
        intent.putExtra(LEGAL_CONTENT_TYPE_ARG, legalTypeEnum);
        return intent;
    }

    /**
     * Configure WebView
     */
    private void configureWebView(){

        // Disable JavaScript
        textLicenceWebView.getSettings().setJavaScriptEnabled(false);

        // Enable the caching for web view
        textLicenceWebView.getSettings().setAppCacheEnabled(true);
        // Specify the app cache path
        textLicenceWebView.getSettings().setAppCachePath(getCacheDir().getPath());

        // Set the cache mode
        textLicenceWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        // Configure Web View Client
        textLicenceWebView.setWebViewClient(new TextLicenceWebViewClient());

        if(legalTypeEnum.equals(LegalTypeEnum.TERMS_OF_SERVICE))
            textLicenceWebView.loadUrl(getString(R.string.terms_of_service_url));
        else
            textLicenceWebView.loadUrl(getString(R.string.privacy_policy_url));

    }

    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_content_activity);
        getWindow().setBackgroundDrawableResource(R.drawable.intro_background_cyan);

        ButterKnife.bind(this);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/HelveticaNeueLTStd-Roman.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());


        final Bundle extras = getIntent().getExtras();
        if(extras != null && !extras.isEmpty() && extras.containsKey(LEGAL_CONTENT_TYPE_ARG))
            legalTypeEnum = (LegalTypeEnum) extras.getSerializable(LEGAL_CONTENT_TYPE_ARG);

        if(legalTypeEnum.equals(LegalTypeEnum.TERMS_OF_SERVICE))
            legalContentTitleTextView.setText(R.string.terms_of_service);
        else
            legalContentTitleTextView.setText(R.string.privacy_policy);

        // Configure Web View.
        configureWebView();

    }


    @OnClick(R.id.accept)
    protected void onAccept(){
        finish();
    }


    /**
     * Text Licence Web View Client
     */
    public class TextLicenceWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            textLicenceLoading.setVisibility(View.VISIBLE);
        }

        @Override
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
            startActivity(intent);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // Hide Progress Bar
            textLicenceLoading.setVisibility(View.GONE);
            buttonAccept.setEnabled(Boolean.TRUE);

            view.setBackgroundColor(Color.TRANSPARENT);
            view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        }
    }
}
