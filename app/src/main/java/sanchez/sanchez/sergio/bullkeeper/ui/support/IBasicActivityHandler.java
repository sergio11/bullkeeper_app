package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;

import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;

public interface IBasicActivityHandler {

    /**
     * Close Activity
     */
    void closeActivity();


    /**
     * Show Short Message
     * @param message
     */
    void showShortMessage(final String message);

    /**
     * Show Short Message
     * @param messageResId
     */
    void showShortMessage(@StringRes final int messageResId);

    /**
     * Show Long Message
     * @param message
     */
    void showLongMessage(final String message);

    /**
     * Show Notice Dialog
     * @param title
     */
    void showNoticeDialog(final String title);

    /**
     * Show Notice Dialog
     * @param stringResId
     */
    void showNoticeDialog(final Integer stringResId);

    /**
     * Show Notice Dialog
     * @param title
     * @param noticeDialogListener
     */
    void showNoticeDialog(final String title, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);

    /**
     * Show Notice Dialog
     * @param stringResId
     * @param noticeDialogListener
     */
    void showNoticeDialog(final Integer stringResId, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);

    /**
     * Show Progress Dialog
     * @param title
     */
    void showProgressDialog(final String title);

    /**
     * Show Progress Dialog
     * @param stringResId
     */
    void showProgressDialog(final Integer stringResId);

    /**
     * Hide Progress Dialog
     */
    void hideProgressDialog();

    /**
     * Show Confirmation Dialog
     * @param title
     */
    void showConfirmationDialog(final String title);


    /**
     * Show Confirmation Dialog
     * @param stringResId
     */
    void showConfirmationDialog(final Integer stringResId);

    /**
     * Show Confirmation Dialog
     * @param title
     * @param confirmationDialogListener
     */
    void showConfirmationDialog(final String title, final ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener);


    /**
     * Show Confirmation Dialog
     * @param stringResId
     * @param confirmationDialogListener
     */
    void showConfirmationDialog(final Integer stringResId, final ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener);


    /**
     * Show Simple Snackbar
     * @param actionText
     * @param onClickListener
     */
    void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description, final String actionText, final View.OnClickListener onClickListener);

    /**
     * Show App Menu
     */
    void showAppMenu();

    /**
     * Show App Help Dialog
     */
    void showAppHelpDialog();

    /**
     * Navigate To Home
     */
    void navigateToHome();

    /**
     * Set Support Toolbar App
     * @param supportToolbarApp
     */
    void setSupportToolbarApp(final SupportToolbarApp supportToolbarApp);

    /**
     * Open Mail App
     */
    void openMailApp();

    /**
     * Close Session
     */
    void closeSession();

}
