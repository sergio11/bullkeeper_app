package sanchez.sanchez.sergio.masom_app.ui.support;

import android.view.View;
import android.view.ViewGroup;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

import sanchez.sanchez.sergio.masom_app.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.dialog.NoticeDialogFragment;

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


    /**
     * Show Notice Dialog
     * @param title
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showNoticeDialog(final String title);

    /**
     * On Network Error
     */
    @CallOnMainThread
    void onNetworkError();

    /**
     * On Other Exception
     */
    @CallOnMainThread
    void onOtherException();

    /**
     * Show Notice Dialog
     * @param stringResId
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showNoticeDialog(final Integer stringResId);


    /**
     * Show Notice Dialog
     * @param title
     * @param noticeDialogListener
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showNoticeDialog(final String title, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);

    /**
     * Show Notice Dialog
     * @param stringResId
     * @param noticeDialogListener
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showNoticeDialog(final Integer stringResId, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);

    /**
     * Show Progress Dialog
     * @param title
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showProgressDialog(final String title);


    /**
     * Show Progress Dialog
     * @param stringResId
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showProgressDialog(final Integer stringResId);


    /**
     * Hide Progress Dialog
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void hideProgressDialog();


    /**
     * Show Confirmation Dialog
     * @param title
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showConfirmationDialog(final String title);

    /**
     * Show Confirmation Dialog
     * @param stringResId
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showConfirmationDialog(final Integer stringResId);

    /**
     * Show Confirmation Dialog
     * @param title
     * @param confirmationDialogListener
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showConfirmationDialog(final String title, final ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener);

    /**
     * Show Confirmation Dialog
     * @param stringResId
     * @param confirmationDialogListener
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showConfirmationDialog(final Integer stringResId, final ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener);


    /**
     * Show Long Simple Snackbar
     * @param viewRoot
     * @param description
     * @param actionText
     * @param onClickListener
     */
    @CallOnMainThread
    @DistinctUntilChanged
    void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description, final String actionText,
                                final View.OnClickListener onClickListener);

}
