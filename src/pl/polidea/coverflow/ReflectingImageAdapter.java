package pl.polidea.coverflow;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;

/**
 * This adapter provides reflected images from linked adapter.
 * 
 * @author potiuk
 * 
 */
public class ReflectingImageAdapter extends AbstractCoverFlowImageAdapter {
    private final AbstractCoverFlowImageAdapter linkedAdapter;
    /**
     * Gap between the image and its reflection.
     */
    private float reflectionGap;
    private float imageReflectionRatio;

    public void setWidthRatio(final float imageReflectionRatio) {
        this.imageReflectionRatio = imageReflectionRatio;
    }

    /**
     * Creates reflecting adapter.
     * 
     * @param context
     *            android context
     * @param linkedAdapter
     *            adapter that provides images to get reflections
     */
    public ReflectingImageAdapter(final Context context,
            final AbstractCoverFlowImageAdapter linkedAdapter) {
        super(context);
        this.linkedAdapter = linkedAdapter;
    }

    public void setReflectionGap(final float reflectionGap) {
        this.reflectionGap = reflectionGap;
    }

    public float getReflectionGap() {
        return reflectionGap;
    }

    @Override
    protected Bitmap createBitmap(final int position) {
        return createReflectedImages(linkedAdapter.getItem(position));
    }

    /**
     * Creates the reflected images.
     * 
     * @return true, if successful
     */
    public Bitmap createReflectedImages(final Bitmap originalImage) {
        final int width = originalImage.getWidth();
        final int height = originalImage.getHeight();
        final Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        final Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                (int) (height * imageReflectionRatio), width,
                (int) (height - height * imageReflectionRatio), matrix, false);
        final Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (int) (height + height * imageReflectionRatio),
                Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(originalImage, 0, 0, null);
        final Paint deafaultPaint = new Paint();
        deafaultPaint.setColor(color.transparent);
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
        final Paint paint = new Paint();
        final LinearGradient shader = new LinearGradient(0,
                originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
                        + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);
        return bitmapWithReflection;
    }

    @Override
    public int getCount() {
        return linkedAdapter.getCount();
    }

}
