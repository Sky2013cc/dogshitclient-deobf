package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.ScaledImageIcon;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTitlePaneIcon.class */
public class FlatTitlePaneIcon extends ScaledImageIcon {
    private final List<Image> images;

    public FlatTitlePaneIcon(List<Image> images, Dimension size) {
        super(null, size.width, size.height);
        this.images = images;
    }

    @Override // com.formdev.flatlaf.util.ScaledImageIcon
    protected Image getResolutionVariant(int destImageWidth, int destImageHeight) {
        List<Image> allImages = new ArrayList<>();
        for (Image image : this.images) {
            if (MultiResolutionImageSupport.isMultiResolutionImage(image)) {
                allImages.add(MultiResolutionImageSupport.getResolutionVariant(image, destImageWidth, destImageHeight));
            } else {
                allImages.add(image);
            }
        }
        if (allImages.size() == 1) {
            return allImages.get(0);
        }
        allImages.sort((image1, image2) -> {
            return image1.getWidth((ImageObserver) null) - image2.getWidth((ImageObserver) null);
        });
        for (Image image3 : allImages) {
            if (destImageWidth <= image3.getWidth((ImageObserver) null) && destImageHeight <= image3.getHeight((ImageObserver) null)) {
                return image3;
            }
        }
        return allImages.get(allImages.size() - 1);
    }
}
