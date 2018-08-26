package sanchez.sanchez.sergio.bullkeeper.utils.imagepicker;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Image Utils
 */
public final class ImageUtils {

    private static final String BASE_IMAGE_NAME = "i_prefix_";

    private ImageUtils() {
    }

    /**
     * Save Picture
     * @param context
     * @param bitmap
     * @param imageSuffix
     * @return
     */
    public static String savePicture(Context context, Bitmap bitmap, String imageSuffix) {
        File savedImage = getTemporalFile(context, imageSuffix + ".jpeg");
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
     * Get Temporal File
     * @param context
     * @param payload
     * @return
     */
    public static File getTemporalFile(final Context context, final String payload) {
        return new File(context.getExternalCacheDir(), BASE_IMAGE_NAME + payload);
    }
}
