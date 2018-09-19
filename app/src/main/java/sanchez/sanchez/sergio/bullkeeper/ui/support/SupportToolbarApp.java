package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Support App Bar Layout Included
 */
public class SupportToolbarApp {


    public final static int INFORMATIVE_TOOLBAR = 0;
    public final static int TOOLBAR_WITH_MENU = 1;
    public final static int RETURN_TOOLBAR = 2;
    public final static int NOT_TOOLBAR = 3;

    public final static int ENABLE_GO_TO_HOME = 1;
    public final static int DISABLE_GO_TO_HOME = 2;

    /**
     * Toolbar Type
     */
    private final int toolbarType;

    /**
     * Toolbar Layout
     */
    private final View toolbarLayout;

    /**
     * App Icon Mode
     */
    private final int appIconMode;

    private Unbinder unbinder;


    /**
     * App Icon
     */
    @BindView(R.id.appIcon)
    protected View appIcon;

    /**
     * Menu Bars
     */
    @BindView( R.id.menuBars )
    protected ImageButton menuBars;


    /**
     * Close Btn
     */
    @BindView(R.id.close)
    protected ImageButton closeBtn;


    /**
     * Question Btn
     */
    @BindView(R.id.question)
    protected ImageButton questionBtn;

    /**
     * Support Toolbar App
     * @param toolbarType
     * @param toolbarLayout
     */
    public SupportToolbarApp(int toolbarType, final View toolbarLayout, final int appIconMode) {
        this.toolbarType = toolbarType;
        this.toolbarLayout = toolbarLayout;
        this.appIconMode = appIconMode;
    }

    /**
     * Support Toolbar App
     * @param toolbarType
     * @param toolbarLayout
     */
    public SupportToolbarApp(int toolbarType, final View toolbarLayout) {
        this.toolbarType = toolbarType;
        this.toolbarLayout = toolbarLayout;
        this.appIconMode = ENABLE_GO_TO_HOME;
    }

    /**
     * Support Toolbar App
     * @param toolbarLayout
     */
    public SupportToolbarApp(final View toolbarLayout){
        this(INFORMATIVE_TOOLBAR, toolbarLayout);
    }

    /**
     * Bind
     */
    public void bind(final IBasicActivityHandler basicActivityHandler){

        unbinder = ButterKnife.bind(this, toolbarLayout);

        switch (toolbarType) {

            case INFORMATIVE_TOOLBAR:

                menuBars.setVisibility(View.GONE);
                closeBtn.setVisibility(View.GONE);
                questionBtn.setVisibility(View.VISIBLE);

                questionBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        basicActivityHandler.showAppHelpDialog();
                    }
                });

                break;

            case TOOLBAR_WITH_MENU:

                menuBars.setVisibility(View.VISIBLE);
                closeBtn.setVisibility(View.GONE);
                questionBtn.setVisibility(View.GONE);

                menuBars.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        basicActivityHandler.showAppMenu();
                    }
                });

                break;

            case RETURN_TOOLBAR:

                menuBars.setVisibility(View.GONE);
                closeBtn.setVisibility(View.VISIBLE);
                questionBtn.setVisibility(View.GONE);

                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        basicActivityHandler.safeCloseActivity();
                    }
                });

                break;

            case NOT_TOOLBAR:
                toolbarLayout.setVisibility(View.GONE);
                break;
        }

        if(appIconMode == ENABLE_GO_TO_HOME)
            appIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    basicActivityHandler.navigateToHome();
                }
            });

    }


    /**
     * Un Bind
     */
    public void unbind(){

        if(unbinder != null)
            unbinder.unbind();
    }



}
