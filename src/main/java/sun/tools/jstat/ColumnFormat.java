package sun.tools.jstat;

import java.util.Iterator;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDTableAttributeObject;

/* loaded from: target.jar:sun/tools/jstat/ColumnFormat.class */
public class ColumnFormat extends OptionFormat {
    private int number;
    private int width;
    private Alignment align;
    private Scale scale;
    private String format;
    private String header;
    private Expression expression;
    private Object previousValue;

    public ColumnFormat(int i) {
        super(PDTableAttributeObject.SCOPE_COLUMN + i);
        this.align = Alignment.CENTER;
        this.scale = Scale.RAW;
        this.number = i;
    }

    public void validate() throws ParserException {
        if (this.expression == null) {
            throw new ParserException("Missing data statement in column " + this.number);
        }
        if (this.header == null) {
            throw new ParserException("Missing header statement in column " + this.number);
        }
        if (this.format == null) {
            this.format = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setAlignment(Alignment alignment) {
        this.align = alignment;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getHeader() {
        return this.header;
    }

    public String getFormat() {
        return this.format;
    }

    public int getWidth() {
        return this.width;
    }

    public Alignment getAlignment() {
        return this.align;
    }

    public Scale getScale() {
        return this.scale;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setPreviousValue(Object obj) {
        this.previousValue = obj;
    }

    public Object getPreviousValue() {
        return this.previousValue;
    }

    @Override // sun.tools.jstat.OptionFormat
    public void printFormat(int i) {
        StringBuilder sb = new StringBuilder("");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        System.out.println(((Object) sb) + this.name + " {");
        System.out.println(((Object) sb) + "  name=" + this.name + ";data=" + this.expression.toString() + ";header=" + this.header + ";format=" + this.format + ";width=" + this.width + ";scale=" + this.scale.toString() + ";align=" + this.align.toString());
        Iterator<OptionFormat> it = this.children.iterator();
        while (it.hasNext()) {
            it.next().printFormat(i + 1);
        }
        System.out.println(((Object) sb) + "}");
    }

    public String getValue() {
        return null;
    }
}
