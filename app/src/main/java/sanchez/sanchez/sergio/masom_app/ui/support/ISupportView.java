package sanchez.sanchez.sergio.masom_app.ui.support;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

public interface ISupportView extends TiView {

    /**
     * Show Short Message
     * @param message
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showShortMessage(final String message);

    /**
     * Show Long Message
     * @param message
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showLongMessage(final String message);

}
