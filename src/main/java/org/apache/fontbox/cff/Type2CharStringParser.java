package org.apache.fontbox.cff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.fontbox.cff.CharStringCommand;

/* loaded from: target.jar:org/apache/fontbox/cff/Type2CharStringParser.class */
public class Type2CharStringParser {
    private static final int CALLSUBR = 10;
    private static final int CALLGSUBR = 29;
    private int hstemCount;
    private int vstemCount;
    private List<Object> sequence;
    private final String fontName;
    private String currentGlyph;

    public Type2CharStringParser(String fontName) {
        this.fontName = fontName;
    }

    public List<Object> parse(byte[] bytes, byte[][] globalSubrIndex, byte[][] localSubrIndex, String glyphName) throws IOException {
        this.hstemCount = 0;
        this.vstemCount = 0;
        this.sequence = new ArrayList();
        this.currentGlyph = glyphName;
        return parseSequence(bytes, globalSubrIndex, localSubrIndex);
    }

    private List<Object> parseSequence(byte[] bytes, byte[][] globalSubrIndex, byte[][] localSubrIndex) throws IOException {
        DataInput input = new DataInputByteArray(bytes);
        boolean localSubroutineIndexProvided = localSubrIndex != null && localSubrIndex.length > 0;
        boolean globalSubroutineIndexProvided = globalSubrIndex != null && globalSubrIndex.length > 0;
        while (input.hasRemaining()) {
            int b0 = input.readUnsignedByte();
            if (b0 == 10 && localSubroutineIndexProvided) {
                processCallSubr(globalSubrIndex, localSubrIndex);
            } else if (b0 == 29 && globalSubroutineIndexProvided) {
                processCallGSubr(globalSubrIndex, localSubrIndex);
            } else if ((b0 >= 0 && b0 <= 27) || (b0 >= 29 && b0 <= 31)) {
                this.sequence.add(readCommand(b0, input));
            } else if (b0 == 28 || (b0 >= 32 && b0 <= 255)) {
                this.sequence.add(readNumber(b0, input));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return this.sequence;
    }

    private void processCallSubr(byte[][] globalSubrIndex, byte[][] localSubrIndex) throws IOException {
        int subrNumber = calculateSubrNumber(((Integer) this.sequence.remove(this.sequence.size() - 1)).intValue(), localSubrIndex.length);
        if (subrNumber < localSubrIndex.length) {
            byte[] subrBytes = localSubrIndex[subrNumber];
            parseSequence(subrBytes, globalSubrIndex, localSubrIndex);
            Object lastItem = this.sequence.get(this.sequence.size() - 1);
            if ((lastItem instanceof CharStringCommand) && CharStringCommand.Type2KeyWord.RET == ((CharStringCommand) lastItem).getType2KeyWord()) {
                this.sequence.remove(this.sequence.size() - 1);
            }
        }
    }

    private void processCallGSubr(byte[][] globalSubrIndex, byte[][] localSubrIndex) throws IOException {
        int subrNumber = calculateSubrNumber(((Integer) this.sequence.remove(this.sequence.size() - 1)).intValue(), globalSubrIndex.length);
        if (subrNumber < globalSubrIndex.length) {
            byte[] subrBytes = globalSubrIndex[subrNumber];
            parseSequence(subrBytes, globalSubrIndex, localSubrIndex);
            Object lastItem = this.sequence.get(this.sequence.size() - 1);
            if ((lastItem instanceof CharStringCommand) && CharStringCommand.Type2KeyWord.RET == ((CharStringCommand) lastItem).getType2KeyWord()) {
                this.sequence.remove(this.sequence.size() - 1);
            }
        }
    }

    private int calculateSubrNumber(int operand, int subrIndexlength) {
        if (subrIndexlength < 1240) {
            return 107 + operand;
        }
        if (subrIndexlength < 33900) {
            return 1131 + operand;
        }
        return 32768 + operand;
    }

    private CharStringCommand readCommand(int b0, DataInput input) throws IOException {
        if (b0 == 1 || b0 == 18) {
            this.hstemCount += countNumbers() / 2;
        } else if (b0 == 3 || b0 == 19 || b0 == 20 || b0 == 23) {
            this.vstemCount += countNumbers() / 2;
        }
        if (b0 == 12) {
            int b1 = input.readUnsignedByte();
            return CharStringCommand.getInstance(b0, b1);
        }
        if (b0 == 19 || b0 == 20) {
            int[] value = new int[1 + getMaskLength()];
            value[0] = b0;
            for (int i = 1; i < value.length; i++) {
                value[i] = input.readUnsignedByte();
            }
            return CharStringCommand.getInstance(value);
        }
        return CharStringCommand.getInstance(b0);
    }

    private Number readNumber(int b0, DataInput input) throws IOException {
        if (b0 == 28) {
            return Integer.valueOf(input.readShort());
        }
        if (b0 >= 32 && b0 <= 246) {
            return Integer.valueOf(b0 - 139);
        }
        if (b0 >= 247 && b0 <= 250) {
            int b1 = input.readUnsignedByte();
            return Integer.valueOf(((b0 - 247) * 256) + b1 + 108);
        }
        if (b0 >= 251 && b0 <= 254) {
            int b12 = input.readUnsignedByte();
            return Integer.valueOf((((-(b0 - 251)) * 256) - b12) - 108);
        }
        if (b0 == 255) {
            short value = input.readShort();
            double fraction = input.readUnsignedShort() / 65535.0d;
            return Double.valueOf(value + fraction);
        }
        throw new IllegalArgumentException();
    }

    private int getMaskLength() {
        int hintCount = this.hstemCount + this.vstemCount;
        int length = hintCount / 8;
        if (hintCount % 8 > 0) {
            length++;
        }
        return length;
    }

    private int countNumbers() {
        int count = 0;
        for (int i = this.sequence.size() - 1; i > -1; i--) {
            if (!(this.sequence.get(i) instanceof Number)) {
                return count;
            }
            count++;
        }
        return count;
    }

    public String toString() {
        return this.fontName + ", current glpyh " + this.currentGlyph;
    }
}
