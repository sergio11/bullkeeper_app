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
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.BuildConfig;
import timber.log.Timber;

/**
 * Support Image Picker
 */
public class SupportImagePicker {

    /**
     * Default Request Code
     */
    private static final int DEFAULT_REQUEST_CODE = 213;
    private static final int DEFAULT_MIN_WIDTH_QUALITY = 400;        // min pixels
    private static final int DEFAULT_MIN_HEIGHT_QUALITY = 400;
    private static final String BASE_IMAGE_NAME = "i_prefix_";

    /**
     * App Context
     */
    private final Context appContext;

    /**
     * Min Width Quality
     */
    private static int minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY;

    /**
     * Min Height Quality
     */
    private static int minHeightQuality = DEFAULT_MIN_HEIGHT_QUALITY;

    /**
     * Support Image Picker
     * @param appContext
     */
    @Inject
    public SupportImagePicker(final Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Checks if the current context has permission to access the camera.
     */
    private boolean hasCameraAccess() {
        return ContextCompat.checkSelfPermission(appContext,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks if the androidmanifest.xml contains the given permission.
     * @param permission
     * @return
     */
    private boolean appManifestContainsPermission(final String permission) {

        final PackageManager pm = appContext.getPackageManager();

        try {

            final PackageInfo packageInfo = pm.getPackageInfo(appContext.getPackageName(),
                    PackageManager.GET_PERMISSIONS);

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
     * Add Intents To List
     * @param list
     * @param intent
     * @return
     */
    private List<Intent> addIntentsToList(final List<Intent> list,
                                                 final Intent intent) {

        List<ResolveInfo> resInfo = appContext.getPackageManager()
                .queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Timber.d("Package Name -> %s for ACTION_PICK Intent", packageName);
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }
        return list;
    }

    /**
     * Get an Intent which will launch a dialog to pick an image from camera/gallery apps.

     */
    private Intent getPickImageIntent(final String chooserTitle, final boolean galleryOnly) {

        Intent chooserIntent = null;
        // Create Intent List
        List<Intent> intentList = new ArrayList<>();
        // Action Pick Intent
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        intentList = addIntentsToList(intentList, pickIntent);

        // Check is we want gallery apps only
        if (!galleryOnly) {

            if (appContext.getPackageManager()
                    .hasSystemFeature(PackageManager.FEATURE_CAMERA)
                    && !appManifestContainsPermission(Manifest.permission.CAMERA) || hasCameraAccess()) {

                Timber.d("Take Camera Photo too!!");

                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePhotoIntent.putExtra("return-data", true);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(appContext, BuildConfig.APPLICATION_ID + ".provider",
                                SupportImageUtils.getTemporalFile(appContext, String.valueOf(DEFAULT_REQUEST_CODE))));

                intentList = addIntentsToList(intentList, takePhotoIntent);


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
     * Loads a bitmap and avoids using too much memory loading big images (e.g.: 2560*1920)
     */
    private static Bitmap decodeBitmap(final Context context, final Uri theUri) {
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


    /**
     * Start Chooser
     * @param activity
     * @param chooserTitle
     * @param galleryOnly
     */
    private void startChooser(final Activity  activity, final String chooserTitle, final boolean galleryOnly){
        Intent chooseImageIntent = getPickImageIntent(chooserTitle, galleryOnly);
        activity.startActivityForResult(chooseImageIntent, DEFAULT_REQUEST_CODE);
    }

    /**
     * Pick Image
     * @param chooserTitle
     * @param galleryOnly
     */
    public void pickImage(final Activity activity, final String chooserTitle, final boolean galleryOnly) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(chooserTitle, "Chooser Title can not be null");
        Preconditions.checkState(!chooserTitle.isEmpty(), "Chooser Title can not be empty");
        // Start Chooser
        startChooser(activity, chooserTitle, galleryOnly);
    }

    /**
     * Pick Image
     * @param chooserTitle
     */
    public void pickImage(final Activity activity, final String chooserTitle) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(chooserTitle, "Chooser Title can not be null");
        Preconditions.checkState(!chooserTitle.isEmpty(), "Chooser Title can not be empty");
        // Start Chooser
        startChooser(activity, chooserTitle, false);
    }

    /**
     * Get Temporal File
     * @param payload
     * @return
     */
    private File getTemporalFile(final String payload) {
        return new File(appContext.getExternalCacheDir(), BASE_IMAGE_NAME + payload);
    }

    /**
     * Save Pciture
     * @param bitmap
     * @param imageSuffix
     * @return
     */
    private String savePicture(Bitmap bitmap, String imageSuffix) {
        File savedImage = getTemporalFile(imageSuffix + ".jpeg");
        FileOutputStream fos = null;
        if (savedImage.exists()) {
            savedImage.delete();
        }
        try {
            fos = new FileOutputStream(savedImage.getPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return savedImage.getAbsolutePath();
    }

    /**
     * Called after launching the picker with the same values of Activity.getImageFromResult
     * in order to resolve the result and get the image path.
     * @param requestCode
     * @param resultCode
     * @param imageReturnedIntent
     * @return
     */
    @Nullable
    public String getImagePathFromResult(int requestCode, int resultCode,
                                                final Intent imageReturnedIntent) {

        Uri selectedImage = null;
        if (resultCode == Activity.RESULT_OK && requestCode == DEFAULT_REQUEST_CODE) {
            File imageFile = getTemporalFile(String.valueOf(DEFAULT_REQUEST_CODE));
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
        return getFilePathFromUri(selectedImage);
    }

    /**
     * Get stream, save the picture to the temp file and return path.
     *
     * @param uri uri of the incoming file
     * @return path to the saved image.
     */
    private String getFilePathFromUri(Uri uri) {
        InputStream is = null;
        if (uri.getAuthority() != null) {
            try {
                is = appContext.getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);
                return savePicture(bmp, String.valueOf(uri.getPath().hashCode()));
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


}
