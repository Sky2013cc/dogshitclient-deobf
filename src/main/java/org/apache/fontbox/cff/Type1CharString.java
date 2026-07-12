package org.apache.fontbox.cff;

import com.sun.tools.doclint.DocLint;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.cff.CharStringCommand;
import org.apache.fontbox.encoding.StandardEncoding;
import org.apache.fontbox.type1.Type1CharStringReader;

/* loaded from: target.jar:org/apache/fontbox/cff/Type1CharString.class */
public class Type1CharString {
    private static final Log LOG = LogFactory.getLog(Type1CharString.class);
    private final Type1CharStringReader font;
    private final String fontName;
    private final String glyphName;
    private GeneralPath path;
    private int width;
    private Point2D.Float leftSideBearing;
    private Point2D.Float current;
    private boolean isFlex;
    private final List<Point2D.Float> flexPoints;
    private final List<Object> type1Sequence;
    private int commandCount;

    public Type1CharString(Type1CharStringReader font, String fontName, String glyphName, List<Object> sequence) {
        this(font, fontName, glyphName);
        this.type1Sequence.addAll(sequence);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Type1CharString(Type1CharStringReader font, String fontName, String glyphName) {
        this.path = null;
        this.width = 0;
        this.leftSideBearing = null;
        this.current = null;
        this.isFlex = false;
        this.flexPoints = new ArrayList();
        this.type1Sequence = new ArrayList();
        this.commandCount = 0;
        this.font = font;
        this.fontName = fontName;
        this.glyphName = glyphName;
        this.current = new Point2D.Float(0.0f, 0.0f);
    }

    public String getName() {
        return this.glyphName;
    }

    public Rectangle2D getBounds() {
        synchronized (LOG) {
            if (this.path == null) {
                render();
            }
        }
        return this.path.getBounds2D();
    }

    public int getWidth() {
        synchronized (LOG) {
            if (this.path == null) {
                render();
            }
        }
        return this.width;
    }

    public GeneralPath getPath() {
        synchronized (LOG) {
            if (this.path == null) {
                render();
            }
        }
        return this.path;
    }

    private void render() {
        this.path = new GeneralPath();
        this.leftSideBearing = new Point2D.Float(0.0f, 0.0f);
        this.width = 0;
        List<Number> numbers = new ArrayList<>();
        this.type1Sequence.forEach(obj -> {
            if (obj instanceof CharStringCommand) {
                List<Number> results = handleType1Command(numbers, (CharStringCommand) obj);
                numbers.clear();
                numbers.addAll(results);
                return;
            }
            numbers.add((Number) obj);
        });
    }

    private List<Number> handleType1Command(List<Number> numbers, CharStringCommand command) {
        this.commandCount++;
        CharStringCommand.Type1KeyWord type1KeyWord = command.getType1KeyWord();
        if (type1KeyWord == null) {
            LOG.warn("Unknown charstring command in glyph " + this.glyphName + " of font " + this.fontName);
            return Collections.emptyList();
        }
        switch (type1KeyWord) {
            case RMOVETO:
                if (numbers.size() >= 2) {
                    if (this.isFlex) {
                        this.flexPoints.add(new Point2D.Float(numbers.get(0).floatValue(), numbers.get(1).floatValue()));
                        break;
                    } else {
                        rmoveTo(numbers.get(0), numbers.get(1));
                        break;
                    }
                }
                break;
            case VMOVETO:
                if (!numbers.isEmpty()) {
                    if (this.isFlex) {
                        this.flexPoints.add(new Point2D.Float(0.0f, numbers.get(0).floatValue()));
                        break;
                    } else {
                        rmoveTo(0, numbers.get(0));
                        break;
                    }
                }
                break;
            case HMOVETO:
                if (!numbers.isEmpty()) {
                    if (this.isFlex) {
                        this.flexPoints.add(new Point2D.Float(numbers.get(0).floatValue(), 0.0f));
                        break;
                    } else {
                        rmoveTo(numbers.get(0), 0);
                        break;
                    }
                }
                break;
            case RLINETO:
                if (numbers.size() >= 2) {
                    rlineTo(numbers.get(0), numbers.get(1));
                    break;
                }
                break;
            case HLINETO:
                if (!numbers.isEmpty()) {
                    rlineTo(numbers.get(0), 0);
                    break;
                }
                break;
            case VLINETO:
                if (!numbers.isEmpty()) {
                    rlineTo(0, numbers.get(0));
                    break;
                }
                break;
            case RRCURVETO:
                if (numbers.size() >= 6) {
                    rrcurveTo(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), numbers.get(5));
                    break;
                }
                break;
            case CLOSEPATH:
                closeCharString1Path();
                break;
            case SBW:
                if (numbers.size() >= 3) {
                    this.leftSideBearing = new Point2D.Float(numbers.get(0).floatValue(), numbers.get(1).floatValue());
                    this.width = numbers.get(2).intValue();
                    this.current.setLocation(this.leftSideBearing);
                    break;
                }
                break;
            case HSBW:
                if (numbers.size() >= 2) {
                    this.leftSideBearing = new Point2D.Float(numbers.get(0).floatValue(), 0.0f);
                    this.width = numbers.get(1).intValue();
                    this.current.setLocation(this.leftSideBearing);
                    break;
                }
                break;
            case VHCURVETO:
                if (numbers.size() >= 4) {
                    rrcurveTo(0, numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), 0);
                    break;
                }
                break;
            case HVCURVETO:
                if (numbers.size() >= 4) {
                    rrcurveTo(numbers.get(0), 0, numbers.get(1), numbers.get(2), 0, numbers.get(3));
                    break;
                }
                break;
            case SEAC:
                if (numbers.size() >= 5) {
                    seac(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4));
                    break;
                }
                break;
            case SETCURRENTPOINT:
                if (numbers.size() >= 2) {
                    setcurrentpoint(numbers.get(0), numbers.get(1));
                    break;
                }
                break;
            case CALLOTHERSUBR:
                if (!numbers.isEmpty()) {
                    callothersubr(numbers.get(0).intValue());
                    break;
                }
                break;
            case DIV:
                if (numbers.size() >= 2) {
                    float b = numbers.get(numbers.size() - 1).floatValue();
                    float a = numbers.get(numbers.size() - 2).floatValue();
                    float result = a / b;
                    List<Number> list = new ArrayList<>(numbers);
                    list.remove(list.size() - 1);
                    list.remove(list.size() - 1);
                    list.add(Float.valueOf(result));
                    return list;
                }
                break;
            case HSTEM:
            case VSTEM:
            case HSTEM3:
            case VSTEM3:
            case DOTSECTION:
            case ENDCHAR:
                break;
            case RET:
            case CALLSUBR:
                LOG.warn("Unexpected charstring command: " + type1KeyWord + " in glyph " + this.glyphName + " of font " + this.fontName);
                break;
            default:
                throw new IllegalArgumentException("Unhandled command: " + type1KeyWord);
        }
        return Collections.emptyList();
    }

    private void setcurrentpoint(Number x, Number y) {
        this.current.setLocation(x.floatValue(), y.floatValue());
    }

    private void callothersubr(int num) {
        if (num == 0) {
            this.isFlex = false;
            if (this.flexPoints.size() < 7) {
                LOG.warn("flex without moveTo in font " + this.fontName + ", glyph " + this.glyphName + ", command " + this.commandCount);
                return;
            }
            Point2D.Float reference = this.flexPoints.get(0);
            reference.setLocation(this.current.getX() + reference.getX(), this.current.getY() + reference.getY());
            Point2D.Float first = this.flexPoints.get(1);
            first.setLocation(reference.getX() + first.getX(), reference.getY() + first.getY());
            first.setLocation(first.getX() - this.current.getX(), first.getY() - this.current.getY());
            Point2D.Float p1 = this.flexPoints.get(1);
            Point2D.Float p2 = this.flexPoints.get(2);
            Point2D.Float p3 = this.flexPoints.get(3);
            rrcurveTo(Double.valueOf(p1.getX()), Double.valueOf(p1.getY()), Double.valueOf(p2.getX()), Double.valueOf(p2.getY()), Double.valueOf(p3.getX()), Double.valueOf(p3.getY()));
            Point2D.Float p4 = this.flexPoints.get(4);
            Point2D.Float p5 = this.flexPoints.get(5);
            Point2D.Float p6 = this.flexPoints.get(6);
            rrcurveTo(Double.valueOf(p4.getX()), Double.valueOf(p4.getY()), Double.valueOf(p5.getX()), Double.valueOf(p5.getY()), Double.valueOf(p6.getX()), Double.valueOf(p6.getY()));
            this.flexPoints.clear();
            return;
        }
        if (num == 1) {
            this.isFlex = true;
        } else {
            LOG.warn("Invalid callothersubr parameter: " + num);
        }
    }

    private void rmoveTo(Number dx, Number dy) {
        float x = ((float) this.current.getX()) + dx.floatValue();
        float y = ((float) this.current.getY()) + dy.floatValue();
        this.path.moveTo(x, y);
        this.current.setLocation(x, y);
    }

    private void rlineTo(Number dx, Number dy) {
        float x = ((float) this.current.getX()) + dx.floatValue();
        float y = ((float) this.current.getY()) + dy.floatValue();
        if (this.path.getCurrentPoint() == null) {
            LOG.warn("rlineTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
            this.path.moveTo(x, y);
        } else {
            this.path.lineTo(x, y);
        }
        this.current.setLocation(x, y);
    }

    private void rrcurveTo(Number dx1, Number dy1, Number dx2, Number dy2, Number dx3, Number dy3) {
        float x1 = ((float) this.current.getX()) + dx1.floatValue();
        float y1 = ((float) this.current.getY()) + dy1.floatValue();
        float x2 = x1 + dx2.floatValue();
        float y2 = y1 + dy2.floatValue();
        float x3 = x2 + dx3.floatValue();
        float y3 = y2 + dy3.floatValue();
        if (this.path.getCurrentPoint() == null) {
            LOG.warn("rrcurveTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
            this.path.moveTo(x3, y3);
        } else {
            this.path.curveTo(x1, y1, x2, y2, x3, y3);
        }
        this.current.setLocation(x3, y3);
    }

    private void closeCharString1Path() {
        if (this.path.getCurrentPoint() == null) {
            LOG.warn("closepath without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
        } else {
            this.path.closePath();
        }
        this.path.moveTo(this.current.getX(), this.current.getY());
    }

    private void seac(Number asb, Number adx, Number ady, Number bchar, Number achar) {
        String baseName = StandardEncoding.INSTANCE.getName(bchar.intValue());
        try {
            Type1CharString base = this.font.getType1CharString(baseName);
            this.path.append(base.getPath().getPathIterator((AffineTransform) null), false);
        } catch (IOException e) {
            LOG.warn("invalid seac character in glyph " + this.glyphName + " of font " + this.fontName, e);
        }
        String accentName = StandardEncoding.INSTANCE.getName(achar.intValue());
        try {
            Type1CharString accent = this.font.getType1CharString(accentName);
            GeneralPath accentPath = accent.getPath();
            if (this.path == accentPath) {
                LOG.warn("Path for " + baseName + " and for accent " + accentName + " are same, ignored");
            } else {
                AffineTransform at = AffineTransform.getTranslateInstance((this.leftSideBearing.getX() + adx.floatValue()) - asb.floatValue(), this.leftSideBearing.getY() + ady.floatValue());
                this.path.append(accentPath.getPathIterator(at), false);
            }
        } catch (IOException e2) {
            LOG.warn("invalid seac character in glyph " + this.glyphName + " of font " + this.fontName, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addCommand(List<Number> numbers, CharStringCommand command) {
        this.type1Sequence.addAll(numbers);
        this.type1Sequence.add(command);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSequenceEmpty() {
        return this.type1Sequence.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getLastSequenceEntry() {
        if (!this.type1Sequence.isEmpty()) {
            return this.type1Sequence.get(this.type1Sequence.size() - 1);
        }
        return null;
    }

    public String toString() {
        return this.type1Sequence.toString().replace(CallSiteDescriptor.OPERATOR_DELIMITER, "\n").replace(DocLint.TAGS_SEPARATOR, " ");
    }
}
