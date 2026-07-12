package org.apache.pdfbox.pdmodel.font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.afm.FontMetrics;
import org.apache.fontbox.cmap.CMap;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/PDFont.class */
public abstract class PDFont implements COSObjectable, PDFontLike {
    private static final Log LOG = LogFactory.getLog(PDFont.class);
    protected static final Matrix DEFAULT_FONT_MATRIX = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
    protected final COSDictionary dict;
    private final CMap toUnicodeCMap;
    private final FontMetrics afmStandard14;
    private PDFontDescriptor fontDescriptor;
    private List<Float> widths;
    private float avgFontWidth;
    private float fontWidthOfSpace;
    private final Map<Integer, Float> codeToWidthMap;

    protected abstract float getStandard14Width(int i);

    protected abstract byte[] encode(int i) throws IOException;

    public abstract int readCode(InputStream inputStream) throws IOException;

    public abstract boolean isVertical();

    public abstract void addToSubset(int i);

    public abstract void subset() throws IOException;

    public abstract boolean willBeSubset();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont() {
        this.fontWidthOfSpace = -1.0f;
        this.dict = new COSDictionary();
        this.dict.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        this.fontDescriptor = null;
        this.afmStandard14 = null;
        this.codeToWidthMap = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont(Standard14Fonts.FontName baseFont) {
        this.fontWidthOfSpace = -1.0f;
        this.dict = new COSDictionary();
        this.dict.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        this.afmStandard14 = Standard14Fonts.getAFM(baseFont.getName());
        if (this.afmStandard14 == null) {
            throw new IllegalArgumentException("No AFM for font " + baseFont);
        }
        this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(this.afmStandard14);
        this.codeToWidthMap = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDFont(COSDictionary fontDictionary) {
        this.fontWidthOfSpace = -1.0f;
        this.dict = fontDictionary;
        this.codeToWidthMap = new HashMap();
        this.afmStandard14 = Standard14Fonts.getAFM(getName());
        this.fontDescriptor = loadFontDescriptor();
        this.toUnicodeCMap = loadUnicodeCmap();
    }

    private PDFontDescriptor loadFontDescriptor() {
        COSDictionary fd = this.dict.getCOSDictionary(COSName.FONT_DESC);
        if (fd != null) {
            return new PDFontDescriptor(fd);
        }
        if (this.afmStandard14 != null) {
            return PDType1FontEmbedder.buildFontDescriptor(this.afmStandard14);
        }
        return null;
    }

    private CMap loadUnicodeCmap() {
        COSDictionary encodingDict;
        COSBase toUnicode = this.dict.getDictionaryObject(COSName.TO_UNICODE);
        if (toUnicode == null) {
            return null;
        }
        CMap cmap = null;
        try {
            cmap = readCMap(toUnicode);
            if (cmap != null && !cmap.hasUnicodeMappings()) {
                LOG.warn("Invalid ToUnicode CMap in font " + getName());
                String cmapName = cmap.getName() != null ? cmap.getName() : "";
                String ordering = cmap.getOrdering() != null ? cmap.getOrdering() : "";
                COSName encoding = this.dict.getCOSName(COSName.ENCODING);
                if ((cmapName.contains("Identity") || ordering.contains("Identity") || COSName.IDENTITY_H.equals(encoding) || COSName.IDENTITY_V.equals(encoding)) && ((encodingDict = this.dict.getCOSDictionary(COSName.ENCODING)) == null || !encodingDict.containsKey(COSName.DIFFERENCES))) {
                    cmap = CMapManager.getPredefinedCMap(COSName.IDENTITY_H.getName());
                    LOG.warn("Using predefined identity CMap instead");
                }
            }
        } catch (IOException ex) {
            LOG.error("Could not read ToUnicode CMap in font " + getName(), ex);
        }
        return cmap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final FontMetrics getStandard14AFM() {
        return this.afmStandard14;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setFontDescriptor(PDFontDescriptor fontDescriptor) {
        this.fontDescriptor = fontDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CMap readCMap(COSBase base) throws IOException {
        if (base instanceof COSName) {
            String name = ((COSName) base).getName();
            return CMapManager.getPredefinedCMap(name);
        }
        if (base instanceof COSStream) {
            RandomAccessRead input = ((COSStream) base).createView();
            Throwable th = null;
            try {
                try {
                    CMap parseCMap = CMapManager.parseCMap(input);
                    if (input != null) {
                        if (0 != 0) {
                            try {
                                input.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            input.close();
                        }
                    }
                    return parseCMap;
                } finally {
                }
            } catch (Throwable th3) {
                if (input != null) {
                    if (th != null) {
                        try {
                            input.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        input.close();
                    }
                }
                throw th3;
            }
        }
        throw new IOException("Expected Name or Stream");
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public Vector getPositionVector(int code) {
        throw new UnsupportedOperationException("Horizontal fonts have no position vector");
    }

    public Vector getDisplacement(int code) throws IOException {
        return new Vector(getWidth(code) / 1000.0f, 0.0f);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int code) throws IOException {
        Float width = this.codeToWidthMap.get(Integer.valueOf(code));
        if (width != null) {
            return width.floatValue();
        }
        if (this.dict.getDictionaryObject(COSName.WIDTHS) != null || this.dict.containsKey(COSName.MISSING_WIDTH)) {
            int firstChar = this.dict.getInt(COSName.FIRST_CHAR, -1);
            int lastChar = this.dict.getInt(COSName.LAST_CHAR, -1);
            int siz = getWidths().size();
            int idx = code - firstChar;
            if (siz > 0 && code >= firstChar && code <= lastChar && idx < siz) {
                Float width2 = getWidths().get(idx);
                if (width2 == null) {
                    width2 = Float.valueOf(0.0f);
                }
                this.codeToWidthMap.put(Integer.valueOf(code), width2);
                return width2.floatValue();
            }
            PDFontDescriptor fd = getFontDescriptor();
            if (fd != null) {
                Float width3 = Float.valueOf(fd.getMissingWidth());
                this.codeToWidthMap.put(Integer.valueOf(code), width3);
                return width3.floatValue();
            }
        }
        if (isStandard14()) {
            Float width4 = Float.valueOf(getStandard14Width(code));
            this.codeToWidthMap.put(Integer.valueOf(code), width4);
            return width4.floatValue();
        }
        Float width5 = Float.valueOf(getWidthFromFont(code));
        this.codeToWidthMap.put(Integer.valueOf(code), width5);
        return width5.floatValue();
    }

    public final byte[] encode(String text) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int offset = i;
            if (offset < text.length()) {
                int codePoint = text.codePointAt(offset);
                byte[] bytes = encode(codePoint);
                out.write(bytes);
                i = offset + Character.charCount(codePoint);
            } else {
                return out.toByteArray();
            }
        }
    }

    public float getStringWidth(String text) throws IOException {
        byte[] bytes = encode(text);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        float f = 0.0f;
        while (true) {
            float width = f;
            if (in.available() > 0) {
                int code = readCode(in);
                f = width + getWidth(code);
            } else {
                return width;
            }
        }
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        float average;
        if (Float.compare(this.avgFontWidth, 0.0f) != 0) {
            average = this.avgFontWidth;
        } else {
            float totalWidth = 0.0f;
            float characterCount = 0.0f;
            COSArray widths = this.dict.getCOSArray(COSName.WIDTHS);
            if (widths != null) {
                for (int i = 0; i < widths.size(); i++) {
                    COSBase base = widths.getObject(i);
                    if (base instanceof COSNumber) {
                        COSNumber fontWidth = (COSNumber) base;
                        float floatValue = fontWidth.floatValue();
                        if (floatValue > 0.0f) {
                            totalWidth += floatValue;
                            characterCount += 1.0f;
                        }
                    }
                }
            }
            if (totalWidth > 0.0f) {
                average = totalWidth / characterCount;
            } else {
                average = 0.0f;
            }
            this.avgFontWidth = average;
        }
        return average;
    }

    public String toUnicode(int code, GlyphList customGlyphList) {
        return toUnicode(code);
    }

    public String toUnicode(int code) {
        COSName encoding;
        if (this.toUnicodeCMap != null) {
            if (this.toUnicodeCMap.getName() != null && this.toUnicodeCMap.getName().startsWith("Identity-") && (this.dict.getCOSName(COSName.TO_UNICODE) != null || !this.toUnicodeCMap.hasUnicodeMappings())) {
                return new String(new char[]{(char) code});
            }
            if (code < 256 && !(this instanceof PDType0Font) && (encoding = this.dict.getCOSName(COSName.ENCODING)) != null && !encoding.getName().startsWith("Identity")) {
                return this.toUnicodeCMap.toUnicode(code, 1);
            }
            return this.toUnicodeCMap.toUnicode(code);
        }
        return null;
    }

    public String getType() {
        return this.dict.getNameAsString(COSName.TYPE);
    }

    public String getSubType() {
        return this.dict.getNameAsString(COSName.SUBTYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<Float> getWidths() {
        if (this.widths == null) {
            COSArray array = this.dict.getCOSArray(COSName.WIDTHS);
            if (array != null) {
                this.widths = array.toCOSNumberFloatList();
            } else {
                this.widths = Collections.emptyList();
            }
        }
        return this.widths;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        return DEFAULT_FONT_MATRIX;
    }

    public float getSpaceWidth() {
        if (Float.compare(this.fontWidthOfSpace, -1.0f) == 0) {
            try {
                if (this.toUnicodeCMap != null && this.dict.containsKey(COSName.TO_UNICODE)) {
                    int spaceMapping = this.toUnicodeCMap.getSpaceMapping();
                    if (spaceMapping > -1) {
                        this.fontWidthOfSpace = getWidth(spaceMapping);
                    }
                } else {
                    this.fontWidthOfSpace = getWidth(32);
                }
                if (this.fontWidthOfSpace <= 0.0f) {
                    this.fontWidthOfSpace = getWidthFromFont(32);
                    if (this.fontWidthOfSpace <= 0.0f) {
                        this.fontWidthOfSpace = getAverageFontWidth();
                    }
                }
            } catch (Exception e) {
                LOG.error("Can't determine the width of the space character, assuming 250", e);
                this.fontWidthOfSpace = 250.0f;
            }
        }
        return this.fontWidthOfSpace;
    }

    public boolean isStandard14() {
        if (isEmbedded()) {
            return false;
        }
        return Standard14Fonts.containsName(getName());
    }

    public boolean equals(Object other) {
        return (other instanceof PDFont) && ((PDFont) other).getCOSObject() == getCOSObject();
    }

    public int hashCode() {
        return getCOSObject().hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CMap getToUnicodeCMap() {
        return this.toUnicodeCMap;
    }
}
