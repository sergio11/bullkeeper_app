package sanchez.sanchez.sergio.bullkeeper.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.BuildConfig;
import sanchez.sanchez.sergio.bullkeeper.R;
import timber.log.Timber;

/**
 * Support Image Picker
 */
public final class SupportImagePickerOld {

    private static final int DEFAULT_REQUEST_CODE = 234;
    private static final int DEFAULT_MIN_WIDTH_QUALITY = 400;        // min pixels
    private static final int DEFAULT_MIN_HEIGHT_QUALITY = 400;        // min pixels

    private static final String TEMP_IMAGE_NAME = "tempImage";

    /**
     * Min Width Quality
     */
    private static int minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY;

    /**
     * Min Height Quality
     */
    private static int minHeightQuality = DEFAULT_MIN_HEIGHT_QUALITY;

    /**
     * Chooser Title
     */
    private static String mChooserTitle;

    /**
     * Pick Image Request Code
     */
    private static int mPickImageRequestCode = DEFAULT_REQUEST_CODE;

    /**
     * Gallery Only
     */
    private static boolean mGalleryOnly = false;

    private SupportImagePickerOld() {
        // not called
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps with custom request code.
     *
     * @param activity which will launch the dialog.
     * @param requestCode request code that will be returned in result.
     */
    public static void pickImage(final Activity activity, int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        pickImage(activity, activity.getString(R.string.pick_image_intent_text),
                requestCode, false);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps.
     *
     * @param activity which will launch the dialog.
     */
    public static void pickImage(final Activity activity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        pickImage(activity, activity.getString(R.string.pick_image_intent_text),
                DEFAULT_REQUEST_CODE, false);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps with custom request code.
     *
     * @param fragment which will launch the dialog.
     * @param requestCode request code that will be returned in result.
     */
    public static void pickImage(final Fragment fragment, int requestCode) {
        Preconditions.checkNotNull(fragment, "Fragment can not be null");
        pickImage(fragment, fragment.getString(R.string.pick_image_intent_text),
                requestCode, false);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps.
     *
     * @param fragment which will launch the dialog.
     */
    public static void pickImage(final Fragment fragment) {
        Preconditions.checkNotNull(fragment, "Fragment can not be null");
        pickImage(fragment, fragment.getString(R.string.pick_image_intent_text),
                DEFAULT_REQUEST_CODE, false);
    }

    /**
     * Launch a dialog to pick an image from gallery apps only with custom request code.
     *
     * @param activity which will launch the dialog.
     * @param requestCode request code that will be returned in result.
     */
    public static void pickImageGalleryOnly(Activity activity, int requestCode) {
        Preconditions.checkNotNull(activity, "Fragment can not be null");
        pickImage(activity, activity.getString(R.string.pick_image_intent_text),
                requestCode, true);

    }

    /**
     * Launch a dialog to pick an image from gallery apps only with custom request code.
     *
     * @param fragment which will launch the dialog.
     * @param requestCode request code that will be returned in result.
     */
    public static void pickImageGalleryOnly(Fragment fragment, int requestCode) {
        Preconditions.checkNotNull(fragment, "Fragment can not be null");
        pickImage(fragment, fragment.getString(R.string.pick_image_intent_text),
                requestCode, true);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps.
     *
     * @param activity     which will launch the dialog.
     * @param chooserTitle will appear on the picker dialog.
     */
    public static void pickImage(Activity activity, String chooserTitle) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(chooserTitle, "ChooserTitle can not be null");
        pickImage(activity, chooserTitle, DEFAULT_REQUEST_CODE, false);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps.
     *
     * @param fragment     which will launch the dialog and will get the result in
     *                     onActivityResult()
     * @param chooserTitle will appear on the picker dialog.
     */
    public static void pickImage(Fragment fragment, String chooserTitle) {
        Preconditions.checkNotNull(fragment, "Fragment can not be null");
        Preconditions.checkNotNull(chooserTitle, "ChooserTitle can not be null");
        pickImage(fragment, chooserTitle, DEFAULT_REQUEST_CODE, false);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps.
     *
     * @param fragment     which will launch the dialog and will get the result in
     *                     onActivityResult()
     * @param chooserTitle will appear on the picker dialog.
     * @param requestCode request code that will be returned in result.
     */
    public static void pickImage(final Fragment fragment, final String chooserTitle,
                                 int requestCode, boolean galleryOnly) {
        Preconditions.checkNotNull(fragment, "Fragment can not be null");
        Preconditions.checkNotNull(chooserTitle, "ChooserTitle can not be null");
        mGalleryOnly = galleryOnly;
        mPickImageRequestCode = requestCode;
        mChooserTitle = chooserTitle;
        startChooser(fragment);
    }

    /**
     * Launch a dialog to pick an image from camera/gallery apps.
     *
     * @param activity     which will launch the dialog and will get the result in
     *                     onActivityResult()
     * @param chooserTitle will appear on the picker dialog.
     */
    public static void pickImage(Activity activity, String chooserTitle,
                                 int requestCode, boolean galleryOnly) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(chooserTitle, "ChooserTitle can not be null");
        mGalleryOnly = galleryOnly;
        mPickImageRequestCode = requestCode;
        mChooserTitle = chooserTitle;
        startChooser(activity);
    }

    /**
     * Start Chooser
     * @param fragmentContext
     */
    private static void startChooser(final Fragment fragmentContext) {
        Preconditions.checkNotNull(fragmentContext, "Context can not be null");
        Intent chooseImageIntent = getPickImageIntent(fragmentContext.getContext(), mChooserTitle);
        fragmentContext.startActivityForResult(chooseImageIntent, mPickImageRequestCode);
    }

    /**
     * Start Chooser
     * @param activityContext
     */
    private static void startChooser(final Activity activityContext) {
        Preconditions.checkNotNull(activityContext, "Context can not be null");
        Intent chooseImageIntent = getPickImageIntent(activityContext, mChooserTitle);
        activityContext.startActivityForResult(chooseImageIntent, mPickImageRequestCode);
    }

    /**
     * Get an Intent which will launch a dialog to pick an image from camera/gallery apps.
     *
     * @param context      context.
     * @param chooserTitle will appear on the picker dialog.
     * @return intent launcher.
     */
    public static Intent getPickImageIntent(final Context context, final String chooserTitle) {

        Intent chooserIntent = null;
        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentList = addIntentsToList(context, intentList, pickIntent);

        // Check is we want gallery apps only
        if (!mGalleryOnly) {

            if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA) && !appManifestContainsPermission(context, Manifest.permission.CAMERA)
                    || hasCameraAccess(context)) {
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePhotoIntent.putExtra("return-data", true);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider",
                                SupportImageUtils.getTemporalFile(context, String.valueOf(mPickImageRequestCode))));
                //Uri.fromFile(ImageUtils.getTemporalFile(context, String.valueOf(mPickImageRequestCode))));
                intentList = addIntentsToList(context, intentList, takePhotoIntent);
            }
        }

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    intentList.toArray(new Parcelable[intentList.size()]));
        }

        return chooserIntent;
    }


    /**
     * Add Intents To List
     * @param context
     * @param list
     * @param intent
     * @return
     */
    private static List<Intent> addIntentsToList(final Context context, final List<Intent> list,
                                                 final Intent intent) {

        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
            Timber.d("App Package -> %s", packageName);
        }
        return list;
    }

    /**
     * Checks if the current context has permission to access the camera.
     * @param context             context.
     */
    private static boolean hasCameraAccess(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks if the androidmanifest.xml contains the given permission.
     * @param context             context.
     * @return Boolean, indicating if the permission is present.
     */
    private static boolean appManifestContainsPermission(Context context, String permission) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = null;
            if (packageInfo != null) {
                requestedPermissions = packageInfo.requestedPermissions;
            }
            if (requestedPermissions == null) {
                return false;
            }

            if (requestedPermissions.length > 0) {
                List<String> requestedPermissionsList = Arrays.asList(requestedPermissions);
                return requestedPermissionsList.contains(permission);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Called after launching the picker with the same values of Activity.getImageFromResult
     * in order to resolve the result and get the image.
     *
     * @param context             context.
     * @param requestCode         used to identify the pick image action.
     * @param resultCode          -1 means the result is OK.
     * @param imageReturnedIntent returned intent where is the image data.
     * @return image.
     */
    @Nullable
    public static Bitmap getImageFromResult(Context context, int requestCode, int resultCode,
                                            Intent imageReturnedIntent) {
        Timber.d("getImageFromResult() called with: resultCode = [%d]", requestCode);
        Bitmap bm = null;

        if (resultCode == Activity.RESULT_OK && requestCode == mPickImageRequestCode) {
            File imageFile = SupportImageUtils.getTemporalFile(context, String.valueOf(mPickImageRequestCode));
            Uri selectedImage;

            boolean isCamera = (imageReturnedIntent == null
                    || imageReturnedIntent.getData() == null
                    || imageReturnedIntent.getData().toString().contains(imageFile.toString()));

            if (isCamera) {
                selectedImage = FileProvider.getUriForFile(context,
                        BuildConfig.APPLICATION_ID + ".provider", imageFile);


            } else {            /** ALBUM **/
                selectedImage = imageReturnedIntent.getData();
            }

            bm = decodeBitmap(context, selectedImage);
            int rotation = SupportImageRotator.getRotation(context, selectedImage, isCamera);
            bm = SupportImageRotator.rotate(bm, rotation);
        }
        return bm;
    }

    /**
     * Called after launching the picker with the same values of Activity.getImageFromResult
     * in order to resolve the result and get the image path.
     *
     * @param context             context.
     * @param requestCode         used to identify the pick image action.
     * @param resultCode          -1 means the result is OK.
     * @param imageReturnedIntent returned intent where is the image data.
     * @return path to the saved image.
     */
    @Nullable
    public static String getImagePathFromResult(Context context, int requestCode, int resultCode,
                                                Intent imageReturnedIntent) {

        Uri selectedImage = null;
        if (resultCode == Activity.RESULT_OK && requestCode == mPickImageRequestCode) {
            File imageFile = SupportImageUtils.getTemporalFile(context, String.valueOf(mPickImageRequestCode));
            boolean isCamera = (imageReturnedIntent == null
                    || imageReturnedIntent.getData() == null
                    || imageReturnedIntent.getData().toString().contains(imageFile.toString()));
            if (isCamera) {
                return imageFile.getAbsolutePath();
            } else {
                selectedImage = imageReturnedIntent.getData();
            }
        }
        if (selectedImage == null) {
            return null;
        }
        return getFilePathFromUri(context, selectedImage);
    }

    /**
     * Get stream, save the picture to the temp file and return path.
     *
     * @param context context
     * @param uri uri of the incoming file
     * @return path to the saved image.
     */
    private static String getFilePathFromUri(Context context, Uri uri) {
        InputStream is = null;
        if (uri.getAuthority() != null) {
            try {
                is = context.getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);
                return SupportImageUtils.savePicture(context, bmp, String.valueOf(uri.getPath().hashCode()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Called after launching the picker with the same values of Activity.getImageFromResult
     * in order to resolve the result and get the input stream for the image.
     *
     * @param context             context.
     * @param requestCode         used to identify the pick image action.
     * @param resultCode          -1 means the result is OK.
     * @param imageReturnedIntent returned intent where is the image data.
     * @return stream.
     */
    public static InputStream getInputStreamFromResult(Context context, int requestCode, int resultCode,
                                                       Intent imageReturnedIntent) {
        if (resultCode == Activity.RESULT_OK && requestCode == mPickImageRequestCode) {
            File imageFile = SupportImageUtils.getTemporalFile(context, String.valueOf(mPickImageRequestCode));
            Uri selectedImage;
            boolean isCamera = (imageReturnedIntent == null
                    || imageReturnedIntent.getData() == null
                    || imageReturnedIntent.getData().toString().contains(imageFile.toString()));
            if (isCamera) {     /** CAMERA **/
                //selectedImage = Uri.fromFile(imageFile);
                selectedImage = FileProvider.getUriForFile(context,
                        BuildConfig.APPLICATION_ID + ".provider", imageFile);
            } else {            /** ALBUM **/
                selectedImage = imageReturnedIntent.getData();
            }

            try {
                if (isCamera) {
                    // We can just open the temporary file stream and return it
                    return new FileInputStream(imageFile);
                } else {
                    // Otherwise use the ContentResolver
                    return context.getContentResolver().openInputStream(selectedImage);
                }
            } catch (FileNotFoundException ex) {
                Timber.e( "Could not open input stream for: %s ", selectedImage.toString());
                return null;
            }
        }
        return null;
    }

    /**
     * Loads a bitmap and avoids using too much memory loading big images (e.g.: 2560*1920)
     */
    private static Bitmap decodeBitmap(Context context, Uri theUri) {
        Bitmap outputBitmap = null;
        AssetFileDescriptor fileDescriptor = null;

        try {
            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(theUri, "r");

            // Get size of bitmap file
            BitmapFactory.Options boundsOptions = new BitmapFactory.Options();
            boundsOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, boundsOptions);

            // Get desired sample size. Note that these must be powers-of-two.
            int[] sampleSizes = new int[]{8, 4, 2, 1};
            int selectedSampleSize = 1; // 1 by default (original image)

            for (int sampleSize : sampleSizes) {
                selectedSampleSize = sampleSize;
                int targetWidth = boundsOptions.outWidth / sampleSize;
                int targetHeight = boundsOptions.outHeight / sampleSize;
                if (targetWidth >= minWidthQuality && targetHeight >= minHeightQuality) {
                    break;
                }
            }

            // Decode bitmap at desired size
            BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
            decodeOptions.inSampleSize = selectedSampleSize;
            outputBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, decodeOptions);

            fileDescriptor.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputBitmap;
    }


    /*
    GETTERS AND SETTERS
     */

    public static void setMinQuality(int minWidthQuality, int minHeightQuality) {
        SupportImagePickerOld.minWidthQuality = minWidthQuality;
        SupportImagePickerOld.minHeightQuality = minHeightQuality;
    }
}
