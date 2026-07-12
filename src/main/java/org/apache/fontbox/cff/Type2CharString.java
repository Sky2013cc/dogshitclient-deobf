package org.apache.fontbox.cff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.fontbox.cff.CharStringCommand;
import org.apache.fontbox.type1.Type1CharStringReader;

/* loaded from: target.jar:org/apache/fontbox/cff/Type2CharString.class */
public class Type2CharString extends Type1CharString {
    private float defWidthX;
    private float nominalWidthX;
    private int pathCount;
    private final int gid;

    public Type2CharString(Type1CharStringReader font, String fontName, String glyphName, int gid, List<Object> sequence, int defaultWidthX, int nomWidthX) {
        super(font, fontName, glyphName);
        this.defWidthX = 0.0f;
        this.nominalWidthX = 0.0f;
        this.pathCount = 0;
        this.gid = gid;
        this.defWidthX = defaultWidthX;
        this.nominalWidthX = nomWidthX;
        convertType1ToType2(sequence);
    }

    public int getGID() {
        return this.gid;
    }

    private void convertType1ToType2(List<Object> sequence) {
        this.pathCount = 0;
        List<Number> numbers = new ArrayList<>();
        sequence.forEach(obj -> {
            if (obj instanceof CharStringCommand) {
                List<Number> results = convertType2Command(numbers, (CharStringCommand) obj);
                numbers.clear();
                numbers.addAll(results);
                return;
            }
            numbers.add((Number) obj);
        });
    }

    private List<Number> convertType2Command(List<Number> numbers, CharStringCommand command) {
        CharStringCommand.Type2KeyWord type2KeyWord = command.getType2KeyWord();
        if (type2KeyWord == null) {
            addCommand(numbers, command);
            return Collections.emptyList();
        }
        switch (type2KeyWord) {
            case HSTEM:
            case HSTEMHM:
            case VSTEM:
            case VSTEMHM:
            case HINTMASK:
            case CNTRMASK:
                expandStemHints(clearStack(numbers, numbers.size() % 2 != 0), type2KeyWord == CharStringCommand.Type2KeyWord.HSTEM || type2KeyWord == CharStringCommand.Type2KeyWord.HSTEMHM);
                break;
            case HMOVETO:
            case VMOVETO:
                List<Number> numbers2 = clearStack(numbers, numbers.size() > 1);
                markPath();
                addCommand(numbers2, command);
                break;
            case RLINETO:
                addCommandList(split(numbers, 2), command);
                break;
            case HLINETO:
            case VLINETO:
                addAlternatingLine(numbers, type2KeyWord == CharStringCommand.Type2KeyWord.HLINETO);
                break;
            case RRCURVETO:
                addCommandList(split(numbers, 6), command);
                break;
            case ENDCHAR:
                List<Number> numbers3 = clearStack(numbers, numbers.size() == 5 || numbers.size() == 1);
                closeCharString2Path();
                if (numbers3.size() == 4) {
                    numbers3.add(0, 0);
                    addCommand(numbers3, CharStringCommand.getInstance(12, 6));
                    break;
                } else {
                    addCommand(numbers3, command);
                    break;
                }
                break;
            case RMOVETO:
                List<Number> numbers4 = clearStack(numbers, numbers.size() > 2);
                markPath();
                addCommand(numbers4, command);
                break;
            case HVCURVETO:
            case VHCURVETO:
                addAlternatingCurve(numbers, type2KeyWord == CharStringCommand.Type2KeyWord.HVCURVETO);
                break;
            case HFLEX:
                if (numbers.size() >= 7) {
                    List<Number> first = Arrays.asList(numbers.get(0), 0, numbers.get(1), numbers.get(2), numbers.get(3), 0);
                    List<Number> second = Arrays.asList(numbers.get(4), 0, numbers.get(5), Float.valueOf(-numbers.get(2).floatValue()), numbers.get(6), 0);
                    addCommandList(Arrays.asList(first, second), CharStringCommand.COMMAND_RRCURVETO);
                    break;
                }
                break;
            case FLEX:
                List<Number> first2 = numbers.subList(0, 6);
                List<Number> second2 = numbers.subList(6, 12);
                addCommandList(Arrays.asList(first2, second2), CharStringCommand.COMMAND_RRCURVETO);
                break;
            case HFLEX1:
                if (numbers.size() >= 9) {
                    List<Number> first3 = Arrays.asList(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), 0);
                    List<Number> second3 = Arrays.asList(numbers.get(5), 0, numbers.get(6), numbers.get(7), numbers.get(8), 0);
                    addCommandList(Arrays.asList(first3, second3), CharStringCommand.COMMAND_RRCURVETO);
                    break;
                }
                break;
            case FLEX1:
                int dx = 0;
                int dy = 0;
                for (int i = 0; i < 5; i++) {
                    dx += numbers.get(i * 2).intValue();
                    dy += numbers.get((i * 2) + 1).intValue();
                }
                List<Number> first4 = numbers.subList(0, 6);
                boolean dxIsBigger = Math.abs(dx) > Math.abs(dy);
                Number[] numberArr = new Number[6];
                numberArr[0] = numbers.get(6);
                numberArr[1] = numbers.get(7);
                numberArr[2] = numbers.get(8);
                numberArr[3] = numbers.get(9);
                numberArr[4] = dxIsBigger ? numbers.get(10) : Integer.valueOf(-dx);
                numberArr[5] = dxIsBigger ? Integer.valueOf(-dy) : numbers.get(10);
                List<Number> second4 = Arrays.asList(numberArr);
                addCommandList(Arrays.asList(first4, second4), CharStringCommand.COMMAND_RRCURVETO);
                break;
            case RCURVELINE:
                if (numbers.size() >= 2) {
                    addCommandList(split(numbers.subList(0, numbers.size() - 2), 6), CharStringCommand.COMMAND_RRCURVETO);
                    addCommand(numbers.subList(numbers.size() - 2, numbers.size()), CharStringCommand.COMMAND_RLINETO);
                    break;
                }
                break;
            case RLINECURVE:
                if (numbers.size() >= 6) {
                    addCommandList(split(numbers.subList(0, numbers.size() - 6), 2), CharStringCommand.COMMAND_RLINETO);
                    addCommand(numbers.subList(numbers.size() - 6, numbers.size()), CharStringCommand.COMMAND_RRCURVETO);
                    break;
                }
                break;
            case HHCURVETO:
            case VVCURVETO:
                addCurve(numbers, type2KeyWord == CharStringCommand.Type2KeyWord.HHCURVETO);
                break;
            default:
                addCommand(numbers, command);
                break;
        }
        return Collections.emptyList();
    }

    private List<Number> clearStack(List<Number> numbers, boolean flag) {
        if (isSequenceEmpty()) {
            if (flag) {
                addCommand(Arrays.asList(0, Float.valueOf(numbers.get(0).floatValue() + this.nominalWidthX)), CharStringCommand.COMMAND_HSBW);
                numbers = numbers.subList(1, numbers.size());
            } else {
                addCommand(Arrays.asList(0, Float.valueOf(this.defWidthX)), CharStringCommand.COMMAND_HSBW);
            }
        }
        return numbers;
    }

    private void expandStemHints(List<Number> numbers, boolean horizontal) {
    }

    private void markPath() {
        if (this.pathCount > 0) {
            closeCharString2Path();
        }
        this.pathCount++;
    }

    private void closeCharString2Path() {
        CharStringCommand command = this.pathCount > 0 ? (CharStringCommand) getLastSequenceEntry() : null;
        if (command != null && command.getType1KeyWord() != CharStringCommand.Type1KeyWord.CLOSEPATH) {
            addCommand(Collections.emptyList(), CharStringCommand.COMMAND_CLOSEPATH);
        }
    }

    private void addAlternatingLine(List<Number> numbers, boolean horizontal) {
        while (!numbers.isEmpty()) {
            addCommand(numbers.subList(0, 1), horizontal ? CharStringCommand.COMMAND_HLINETO : CharStringCommand.COMMAND_VLINETO);
            numbers = numbers.subList(1, numbers.size());
            horizontal = !horizontal;
        }
    }

    private void addAlternatingCurve(List<Number> numbers, boolean horizontal) {
        while (numbers.size() >= 4) {
            boolean last = numbers.size() == 5;
            if (horizontal) {
                Number[] numberArr = new Number[6];
                numberArr[0] = numbers.get(0);
                numberArr[1] = 0;
                numberArr[2] = numbers.get(1);
                numberArr[3] = numbers.get(2);
                numberArr[4] = last ? numbers.get(4) : 0;
                numberArr[5] = numbers.get(3);
                addCommand(Arrays.asList(numberArr), CharStringCommand.COMMAND_RRCURVETO);
            } else {
                Number[] numberArr2 = new Number[6];
                numberArr2[0] = 0;
                numberArr2[1] = numbers.get(0);
                numberArr2[2] = numbers.get(1);
                numberArr2[3] = numbers.get(2);
                numberArr2[4] = numbers.get(3);
                numberArr2[5] = last ? numbers.get(4) : 0;
                addCommand(Arrays.asList(numberArr2), CharStringCommand.COMMAND_RRCURVETO);
            }
            numbers = numbers.subList(last ? 5 : 4, numbers.size());
            horizontal = !horizontal;
        }
    }

    private void addCurve(List<Number> numbers, boolean horizontal) {
        while (numbers.size() >= 4) {
            boolean first = numbers.size() % 4 == 1;
            if (horizontal) {
                Number[] numberArr = new Number[6];
                numberArr[0] = numbers.get(first ? 1 : 0);
                numberArr[1] = first ? numbers.get(0) : 0;
                numberArr[2] = numbers.get(first ? 2 : 1);
                numberArr[3] = numbers.get(first ? 3 : 2);
                numberArr[4] = numbers.get(first ? 4 : 3);
                numberArr[5] = 0;
                addCommand(Arrays.asList(numberArr), CharStringCommand.COMMAND_RRCURVETO);
            } else {
                Number[] numberArr2 = new Number[6];
                numberArr2[0] = first ? numbers.get(0) : 0;
                numberArr2[1] = numbers.get(first ? 1 : 0);
                numberArr2[2] = numbers.get(first ? 2 : 1);
                numberArr2[3] = numbers.get(first ? 3 : 2);
                numberArr2[4] = 0;
                numberArr2[5] = numbers.get(first ? 4 : 3);
                addCommand(Arrays.asList(numberArr2), CharStringCommand.COMMAND_RRCURVETO);
            }
            numbers = numbers.subList(first ? 5 : 4, numbers.size());
        }
    }

    private void addCommandList(List<List<Number>> numbers, CharStringCommand command) {
        numbers.forEach(ns -> {
            addCommand(ns, command);
        });
    }

    private static <E> List<List<E>> split(List<E> list, int size) {
        int listSize = list.size() / size;
        List<List<E>> result = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            result.add(list.subList(i * size, (i + 1) * size));
        }
        return result;
    }
}
