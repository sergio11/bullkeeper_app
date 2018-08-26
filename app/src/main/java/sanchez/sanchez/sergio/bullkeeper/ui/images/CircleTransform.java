package sanchez.sanchez.sergio.bullkeeper.ui.images;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Circle Transform
 */
public final class CircleTransform implements Transformation {

    private final int borderColor;
    private final int borderRadius;

    public CircleTransform(){
        this(Color.WHITE, 7);
    }

    /**
     * Circle Transform
     * @param borderColor
     */
    public CircleTransform(int borderColor){
        this(borderColor, 7);
    }

    /**
     * Circle Transform
     * @param borderColor
     * @param borderRadius
     */
    public CircleTransform(int borderColor, int borderRadius) {
        this.borderColor = borderColor;
        this.borderRadius = borderRadius;
    }


    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;

        // Prepare the background
        Paint paintBg = new Paint();
        paintBg.setColor(borderColor);
        paintBg.setAntiAlias(true);

        // Draw the background circle
        canvas.drawCircle(r, r, r, paintBg);

        // Draw the image smaller than the background so a little border will be seen
        canvas.drawCircle(r, r, r - borderRadius, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }

}
