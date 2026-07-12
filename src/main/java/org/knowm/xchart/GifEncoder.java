package org.knowm.xchart;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import java.awt.image.BufferedImage;
import java.util.List;
import org.knowm.xchart.internal.Utils;

/* loaded from: target.jar:org/knowm/xchart/GifEncoder.class */
public class GifEncoder {
    private static final String GIF_FILE_EXTENSION = ".gif";

    public static void saveGif(String filePath, List<BufferedImage> images) {
        saveGif(filePath, images, 0, 100);
    }

    public static void saveGif(String filePath, List<BufferedImage> images, int repeat, int delay) {
        AnimatedGifEncoder gif = new AnimatedGifEncoder();
        gif.setRepeat(repeat);
        gif.start(Utils.addFileExtension(filePath, GIF_FILE_EXTENSION));
        gif.setDelay(delay);
        for (BufferedImage image : images) {
            gif.addFrame(image);
        }
        gif.finish();
    }
}
