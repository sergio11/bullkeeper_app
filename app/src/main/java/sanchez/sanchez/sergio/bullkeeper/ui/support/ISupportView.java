package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;

import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;

public interface ISupportView extends TiView {

    /**
     * Show Short Message
     * @param message
     */
    @CallOnMainThread
    void showShortMessage(final String message);

    /**
     * Show Short Message
     * @param messageResId
     */
    @CallOnMainThread
    void showShortMessage(@StringRes final int messageResId);

    /**
     * Show Long Message
     * @param message
     */
    @CallOnMainThread
    void showLongMessage(final String message);


    /**
     * Show Notice Dialog
     * @param title
     */
    @CallOnMainThread
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
    void showNoticeDialog(final Integer stringResId);


    /**
     * Show Notice Dialog
     * @param title
     * @param noticeDialogListener
     */
    @CallOnMainThread
    void showNoticeDialog(final String title, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);

    /**
     * Show Notice Dialog
     * @param stringResId
     * @param noticeDialogListener
     */
    @CallOnMainThread
    void showNoticeDialog(final Integer stringResId, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);

    /**
     * Show Progress Dialog
     * @param title
     */
    @CallOnMainThread
    void showProgressDialog(final String title);


    /**
     * Show Progress Dialog
     * @param stringResId
     */
    @CallOnMainThread
    void showProgressDialog(final Integer stringResId);


    /**
     * Hide Progress Dialog
     */
    @CallOnMainThread
    void hideProgressDialog();


    /**
     * Show Confirmation Dialog
     * @param title
     */
    @CallOnMainThread
    void showConfirmationDialog(final String title);

    /**
     * Show Confirmation Dialog
     * @param stringResId
     */
    @CallOnMainThread
    void showConfirmationDialog(final Integer stringResId);

    /**
     * Show Confirmation Dialog
     * @param title
     * @param confirmationDialogListener
     */
    @CallOnMainThread
    void showConfirmationDialog(final String title, final ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener);

    /**
     * Show Confirmation Dialog
     * @param stringResId
     * @param confirmationDialogListener
     */
    @CallOnMainThread
    void showConfirmationDialog(final Integer stringResId, final ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener);


    /**
     * Show Long Simple Snackbar
     * @param viewRoot
     * @param description
     * @param actionText
     * @param onClickListener
     */
    @CallOnMainThread
    void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description, final String actionText,
                                final View.OnClickListener onClickListener);

    /**
     * Show Long Simple Snackbar
     * @param viewRoot
     * @param description
     * @param actionText
     * @param onClickListener
     * @param snackbarCallback
     */
    @CallOnMainThread
    void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description,  final String actionText,
                           final View.OnClickListener onClickListener, final Snackbar.Callback snackbarCallback);


    /**
     * Get Args
     * @return
     */
    @CallOnMainThread
    Bundle getArgs();

}
