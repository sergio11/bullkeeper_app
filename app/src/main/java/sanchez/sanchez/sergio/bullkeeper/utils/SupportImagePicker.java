package sanchez.sanchez.sergio.bullkeeper.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import com.fernandocejas.arrow.checks.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static final int MAX_WIDTH = 400;        // min pixels
    private static final int MAX_HEIGHT = 400;
    private static final String BASE_IMAGE_NAME = "i_prefix_";

    /**
     * App Context
     */
    private final Context appContext;


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

                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePhotoIntent.putExtra("return-data", true);

                final File tempFileToCameraPhoto = getTemporalFile(String.valueOf(DEFAULT_REQUEST_CODE));
                Timber.d("Temp File To Camera Photo -> %s ", tempFileToCameraPhoto.getAbsolutePath());
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(appContext, BuildConfig.APPLICATION_ID + ".provider",
                                tempFileToCameraPhoto));

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
     * Open File Path
     * @param filePath
     * @return
     */
    private InputStream openFilePath(final Uri filePath) throws FileNotFoundException{
        Preconditions.checkNotNull(filePath, "File Path can not be null");

        return filePath.getAuthority() != null ?
                appContext.getContentResolver().openInputStream(filePath) :
                new FileInputStream(new File(filePath.toString()));

    }

    /**
     * This method is responsible for solving the rotation issue if exist. Also scale the images to
     * 1024x1024 resolution
     * @param selectedImage
     * @return
     * @throws IOException
     */
    private Bitmap handleSamplingAndRotationBitmap(final Uri selectedImage)
            throws IOException {


        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        InputStream imageStream = null;
        try {
            imageStream = openFilePath(selectedImage);
            BitmapFactory.decodeStream(imageStream, null, options);
            if(imageStream != null)
                imageStream.close();
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;

            imageStream = openFilePath(selectedImage);
            Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);
            if(imageStream != null)
                imageStream.close();
            // Rotate Image If Required
            img = rotateImageIfRequired(img, selectedImage);
            return img;
        } catch(Exception ex) {
            Timber.e(ex);
        } finally {
            if(imageStream != null)
                imageStream.close();
        }

        return null;

    }


    /**
     * Calculate In Sample Size
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    /**
     * Rotate an image if required.
     * @param img
     * @param selectedImage
     * @return
     * @throws IOException
     */
    private Bitmap rotateImageIfRequired(final Bitmap img, final Uri selectedImage) throws IOException {


        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23) {
            InputStream input = openFilePath(selectedImage);
            ei = new ExifInterface(input);
            if(input != null)
                input.close();
        } else {
            ei = new ExifInterface(selectedImage.getPath());
        }

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    /**
     * Rote Image
     * @param img
     * @param degree
     * @return
     */
    private Bitmap rotateImage(final Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
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
                selectedImage = Uri.parse(imageFile.getAbsolutePath());
            } else {
                selectedImage = imageReturnedIntent.getData();
            }
        }
        if (selectedImage == null) {
            return null;
        }

        try {
            return getFilePathFromBitmap(handleSamplingAndRotationBitmap(selectedImage),
                    String.valueOf(selectedImage.getPath().hashCode()));
        } catch (IOException e) {
            Timber.e(e);
            return null;
        }
    }

    /**
     * Get stream, save the picture to the temp file and return path.
     *
     * @param bitmap
     * @return path to the saved image.
     */
    private String getFilePathFromBitmap(final Bitmap bitmap, final String suffix) {
        return savePicture(bitmap, suffix);
    }


}
