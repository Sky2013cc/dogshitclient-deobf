package org.apache.fontbox.cff;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/* loaded from: target.jar:org/apache/fontbox/cff/CharStringCommand.class */
public class CharStringCommand {
    private final Type1KeyWord type1KeyWord;
    private final Type2KeyWord type2KeyWord;
    private static final int KEY_UNKNOWN = 99;
    private static final Map<Integer, CharStringCommand> CHAR_STRING_COMMANDS = createMap();
    public static final CharStringCommand COMMAND_CLOSEPATH = getInstance(Key.CLOSEPATH.hashValue);
    public static final CharStringCommand COMMAND_RLINETO = getInstance(Key.RLINETO.hashValue);
    public static final CharStringCommand COMMAND_HLINETO = getInstance(Key.HLINETO.hashValue);
    public static final CharStringCommand COMMAND_VLINETO = getInstance(Key.VLINETO.hashValue);
    public static final CharStringCommand COMMAND_RRCURVETO = getInstance(Key.RRCURVETO.hashValue);
    public static final CharStringCommand COMMAND_HSBW = getInstance(Key.HSBW.hashValue);
    public static final CharStringCommand COMMAND_CALLOTHERSUBR = getInstance(Key.CALLOTHERSUBR.hashValue);
    private static final CharStringCommand COMMAND_UNKNOWN = new CharStringCommand(99, 0);

    private static Map<Integer, CharStringCommand> createMap() {
        Map<Integer, CharStringCommand> charStringCommandMap = new HashMap<>();
        charStringCommandMap.put(Integer.valueOf(Key.HSTEM.hashValue), new CharStringCommand(Key.HSTEM));
        charStringCommandMap.put(Integer.valueOf(Key.VSTEM.hashValue), new CharStringCommand(Key.VSTEM));
        charStringCommandMap.put(Integer.valueOf(Key.VMOVETO.hashValue), new CharStringCommand(Key.VMOVETO));
        charStringCommandMap.put(Integer.valueOf(Key.RLINETO.hashValue), new CharStringCommand(Key.RLINETO));
        charStringCommandMap.put(Integer.valueOf(Key.HLINETO.hashValue), new CharStringCommand(Key.HLINETO));
        charStringCommandMap.put(Integer.valueOf(Key.VLINETO.hashValue), new CharStringCommand(Key.VLINETO));
        charStringCommandMap.put(Integer.valueOf(Key.RRCURVETO.hashValue), new CharStringCommand(Key.RRCURVETO));
        charStringCommandMap.put(Integer.valueOf(Key.CLOSEPATH.hashValue), new CharStringCommand(Key.CLOSEPATH));
        charStringCommandMap.put(Integer.valueOf(Key.CALLSUBR.hashValue), new CharStringCommand(Key.CALLSUBR));
        charStringCommandMap.put(Integer.valueOf(Key.RET.hashValue), new CharStringCommand(Key.RET));
        charStringCommandMap.put(Integer.valueOf(Key.ESCAPE.hashValue), new CharStringCommand(Key.ESCAPE));
        charStringCommandMap.put(Integer.valueOf(Key.HSBW.hashValue), new CharStringCommand(Key.HSBW));
        charStringCommandMap.put(Integer.valueOf(Key.ENDCHAR.hashValue), new CharStringCommand(Key.ENDCHAR));
        charStringCommandMap.put(Integer.valueOf(Key.HSTEMHM.hashValue), new CharStringCommand(Key.HSTEMHM));
        charStringCommandMap.put(Integer.valueOf(Key.HINTMASK.hashValue), new CharStringCommand(Key.HINTMASK));
        charStringCommandMap.put(Integer.valueOf(Key.CNTRMASK.hashValue), new CharStringCommand(Key.CNTRMASK));
        charStringCommandMap.put(Integer.valueOf(Key.RMOVETO.hashValue), new CharStringCommand(Key.RMOVETO));
        charStringCommandMap.put(Integer.valueOf(Key.HMOVETO.hashValue), new CharStringCommand(Key.HMOVETO));
        charStringCommandMap.put(Integer.valueOf(Key.VSTEMHM.hashValue), new CharStringCommand(Key.VSTEMHM));
        charStringCommandMap.put(Integer.valueOf(Key.RCURVELINE.hashValue), new CharStringCommand(Key.RCURVELINE));
        charStringCommandMap.put(Integer.valueOf(Key.RLINECURVE.hashValue), new CharStringCommand(Key.RLINECURVE));
        charStringCommandMap.put(Integer.valueOf(Key.VVCURVETO.hashValue), new CharStringCommand(Key.VVCURVETO));
        charStringCommandMap.put(Integer.valueOf(Key.HHCURVETO.hashValue), new CharStringCommand(Key.HHCURVETO));
        charStringCommandMap.put(Integer.valueOf(Key.SHORTINT.hashValue), new CharStringCommand(Key.SHORTINT));
        charStringCommandMap.put(Integer.valueOf(Key.CALLGSUBR.hashValue), new CharStringCommand(Key.CALLGSUBR));
        charStringCommandMap.put(Integer.valueOf(Key.VHCURVETO.hashValue), new CharStringCommand(Key.VHCURVETO));
        charStringCommandMap.put(Integer.valueOf(Key.HVCURVETO.hashValue), new CharStringCommand(Key.HVCURVETO));
        charStringCommandMap.put(Integer.valueOf(Key.DOTSECTION.hashValue), new CharStringCommand(12, 0));
        charStringCommandMap.put(Integer.valueOf(Key.VSTEM3.hashValue), new CharStringCommand(12, 1));
        charStringCommandMap.put(Integer.valueOf(Key.HSTEM3.hashValue), new CharStringCommand(12, 2));
        charStringCommandMap.put(Integer.valueOf(Key.AND.hashValue), new CharStringCommand(12, 3));
        charStringCommandMap.put(Integer.valueOf(Key.OR.hashValue), new CharStringCommand(12, 4));
        charStringCommandMap.put(Integer.valueOf(Key.NOT.hashValue), new CharStringCommand(12, 5));
        charStringCommandMap.put(Integer.valueOf(Key.SEAC.hashValue), new CharStringCommand(12, 6));
        charStringCommandMap.put(Integer.valueOf(Key.SBW.hashValue), new CharStringCommand(12, 7));
        charStringCommandMap.put(Integer.valueOf(Key.ABS.hashValue), new CharStringCommand(12, 9));
        charStringCommandMap.put(Integer.valueOf(Key.ADD.hashValue), new CharStringCommand(12, 10));
        charStringCommandMap.put(Integer.valueOf(Key.SUB.hashValue), new CharStringCommand(12, 11));
        charStringCommandMap.put(Integer.valueOf(Key.DIV.hashValue), new CharStringCommand(12, 12));
        charStringCommandMap.put(Integer.valueOf(Key.NEG.hashValue), new CharStringCommand(12, 14));
        charStringCommandMap.put(Integer.valueOf(Key.EQ.hashValue), new CharStringCommand(12, 15));
        charStringCommandMap.put(Integer.valueOf(Key.CALLOTHERSUBR.hashValue), new CharStringCommand(12, 16));
        charStringCommandMap.put(Integer.valueOf(Key.POP.hashValue), new CharStringCommand(12, 17));
        charStringCommandMap.put(Integer.valueOf(Key.DROP.hashValue), new CharStringCommand(12, 18));
        charStringCommandMap.put(Integer.valueOf(Key.PUT.hashValue), new CharStringCommand(12, 20));
        charStringCommandMap.put(Integer.valueOf(Key.GET.hashValue), new CharStringCommand(12, 21));
        charStringCommandMap.put(Integer.valueOf(Key.IFELSE.hashValue), new CharStringCommand(12, 22));
        charStringCommandMap.put(Integer.valueOf(Key.RANDOM.hashValue), new CharStringCommand(12, 23));
        charStringCommandMap.put(Integer.valueOf(Key.MUL.hashValue), new CharStringCommand(12, 24));
        charStringCommandMap.put(Integer.valueOf(Key.SQRT.hashValue), new CharStringCommand(12, 26));
        charStringCommandMap.put(Integer.valueOf(Key.DUP.hashValue), new CharStringCommand(12, 27));
        charStringCommandMap.put(Integer.valueOf(Key.EXCH.hashValue), new CharStringCommand(12, 28));
        charStringCommandMap.put(Integer.valueOf(Key.INDEX.hashValue), new CharStringCommand(12, 29));
        charStringCommandMap.put(Integer.valueOf(Key.ROLL.hashValue), new CharStringCommand(12, 30));
        charStringCommandMap.put(Integer.valueOf(Key.SETCURRENTPOINT.hashValue), new CharStringCommand(12, 33));
        charStringCommandMap.put(Integer.valueOf(Key.HFLEX.hashValue), new CharStringCommand(12, 34));
        charStringCommandMap.put(Integer.valueOf(Key.FLEX.hashValue), new CharStringCommand(12, 35));
        charStringCommandMap.put(Integer.valueOf(Key.HFLEX1.hashValue), new CharStringCommand(12, 36));
        charStringCommandMap.put(Integer.valueOf(Key.FLEX1.hashValue), new CharStringCommand(12, 37));
        return charStringCommandMap;
    }

    private CharStringCommand(Key key) {
        this.type1KeyWord = Type1KeyWord.valueOfKey(key.hashValue);
        this.type2KeyWord = Type2KeyWord.valueOfKey(key.hashValue);
    }

    private CharStringCommand(int b0, int b1) {
        this.type1KeyWord = Type1KeyWord.valueOfKey(b0, b1);
        this.type2KeyWord = Type2KeyWord.valueOfKey(b0, b1);
    }

    public static CharStringCommand getInstance(int b0) {
        CharStringCommand command = CHAR_STRING_COMMANDS.get(Integer.valueOf(b0));
        return command != null ? command : COMMAND_UNKNOWN;
    }

    public static CharStringCommand getInstance(int b0, int b1) {
        CharStringCommand command = CHAR_STRING_COMMANDS.get(Integer.valueOf(getKeyHashValue(b0, b1)));
        return command != null ? command : COMMAND_UNKNOWN;
    }

    public static CharStringCommand getInstance(int[] values) {
        if (values.length == 1) {
            return getInstance(values[0]);
        }
        if (values.length == 2) {
            return getInstance(values[0], values[1]);
        }
        return COMMAND_UNKNOWN;
    }

    private static int getKeyHashValue(int b0, int b1) {
        Type1KeyWord type1Key = Type1KeyWord.valueOfKey(b0, b1);
        if (type1Key == null) {
            Type2KeyWord type2Key = Type2KeyWord.valueOfKey(b0, b1);
            if (type2Key == null) {
                return 99;
            }
            return type2Key.key.hashValue;
        }
        return type1Key.key.hashValue;
    }

    public Type1KeyWord getType1KeyWord() {
        return this.type1KeyWord;
    }

    public Type2KeyWord getType2KeyWord() {
        return this.type2KeyWord;
    }

    public String toString() {
        String str;
        if (this.type2KeyWord != null) {
            str = this.type2KeyWord.toString();
        } else if (this.type1KeyWord != null) {
            str = this.type1KeyWord.toString();
        } else {
            str = "unknown command";
        }
        return str + '|';
    }

    public int hashCode() {
        if (this.type1KeyWord != null) {
            return this.type1KeyWord.key.hashCode();
        }
        if (this.type2KeyWord != null) {
            return this.type2KeyWord.key.hashCode();
        }
        return 0;
    }

    public boolean equals(Object object) {
        if (object instanceof CharStringCommand) {
            CharStringCommand that = (CharStringCommand) object;
            if (this.type1KeyWord != null && this.type1KeyWord == that.getType1KeyWord()) {
                return true;
            }
            if (this.type2KeyWord != null && this.type2KeyWord == that.getType2KeyWord()) {
                return true;
            }
            if (this.type1KeyWord == null && this.type2KeyWord == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    /* loaded from: target.jar:org/apache/fontbox/cff/CharStringCommand$Type1KeyWord.class */
    public enum Type1KeyWord {
        HSTEM(Key.HSTEM),
        VSTEM(Key.VSTEM),
        VMOVETO(Key.VMOVETO),
        RLINETO(Key.RLINETO),
        HLINETO(Key.HLINETO),
        VLINETO(Key.VLINETO),
        RRCURVETO(Key.RRCURVETO),
        CLOSEPATH(Key.CLOSEPATH),
        CALLSUBR(Key.CALLSUBR),
        RET(Key.RET),
        ESCAPE(Key.ESCAPE),
        DOTSECTION(Key.DOTSECTION),
        VSTEM3(Key.VSTEM3),
        HSTEM3(Key.HSTEM3),
        SEAC(Key.SEAC),
        SBW(Key.SBW),
        DIV(Key.DIV),
        CALLOTHERSUBR(Key.CALLOTHERSUBR),
        POP(Key.POP),
        SETCURRENTPOINT(Key.SETCURRENTPOINT),
        HSBW(Key.HSBW),
        ENDCHAR(Key.ENDCHAR),
        RMOVETO(Key.RMOVETO),
        HMOVETO(Key.HMOVETO),
        VHCURVETO(Key.VHCURVETO),
        HVCURVETO(Key.HVCURVETO);

        final Key key;
        private static final Map<Key, Type1KeyWord> BY_KEY = new EnumMap(Key.class);

        static {
            for (Type1KeyWord e : values()) {
                BY_KEY.put(e.key, e);
            }
        }

        Type1KeyWord(Key key) {
            this.key = key;
        }

        public static Type1KeyWord valueOfKey(int b0) {
            return BY_KEY.get(Key.valueOfKey(b0));
        }

        public static Type1KeyWord valueOfKey(int b0, int b1) {
            return BY_KEY.get(Key.valueOfKey(b0, b1));
        }

        public static Type1KeyWord valueOfKey(Key key) {
            return BY_KEY.get(key);
        }
    }

    /* loaded from: target.jar:org/apache/fontbox/cff/CharStringCommand$Type2KeyWord.class */
    public enum Type2KeyWord {
        HSTEM(Key.HSTEM),
        VSTEM(Key.VSTEM),
        VMOVETO(Key.VMOVETO),
        RLINETO(Key.RLINETO),
        HLINETO(Key.HLINETO),
        VLINETO(Key.VLINETO),
        RRCURVETO(Key.RRCURVETO),
        CALLSUBR(Key.CALLSUBR),
        RET(Key.RET),
        ESCAPE(Key.ESCAPE),
        AND(Key.AND),
        OR(Key.OR),
        NOT(Key.NOT),
        ABS(Key.ABS),
        ADD(Key.ADD),
        SUB(Key.SUB),
        DIV(Key.DIV),
        NEG(Key.NEG),
        EQ(Key.EQ),
        DROP(Key.DROP),
        PUT(Key.PUT),
        GET(Key.GET),
        IFELSE(Key.IFELSE),
        RANDOM(Key.RANDOM),
        MUL(Key.MUL),
        SQRT(Key.SQRT),
        DUP(Key.DUP),
        EXCH(Key.EXCH),
        INDEX(Key.INDEX),
        ROLL(Key.ROLL),
        HFLEX(Key.HFLEX),
        FLEX(Key.FLEX),
        HFLEX1(Key.HFLEX1),
        FLEX1(Key.FLEX1),
        ENDCHAR(Key.ENDCHAR),
        HSTEMHM(Key.HSTEMHM),
        HINTMASK(Key.HINTMASK),
        CNTRMASK(Key.CNTRMASK),
        RMOVETO(Key.RMOVETO),
        HMOVETO(Key.HMOVETO),
        VSTEMHM(Key.VSTEMHM),
        RCURVELINE(Key.RCURVELINE),
        RLINECURVE(Key.RLINECURVE),
        VVCURVETO(Key.VVCURVETO),
        HHCURVETO(Key.HHCURVETO),
        SHORTINT(Key.SHORTINT),
        CALLGSUBR(Key.CALLGSUBR),
        VHCURVETO(Key.VHCURVETO),
        HVCURVETO(Key.HVCURVETO);

        final Key key;
        private static final Map<Key, Type2KeyWord> BY_KEY = new EnumMap(Key.class);

        static {
            for (Type2KeyWord e : values()) {
                BY_KEY.put(e.key, e);
            }
        }

        Type2KeyWord(Key key) {
            this.key = key;
        }

        public static Type2KeyWord valueOfKey(int b0) {
            return BY_KEY.get(Key.valueOfKey(b0));
        }

        public static Type2KeyWord valueOfKey(int b0, int b1) {
            return BY_KEY.get(Key.valueOfKey(b0, b1));
        }

        public static Type2KeyWord valueOfKey(Key key) {
            return BY_KEY.get(key);
        }
    }

    /* loaded from: target.jar:org/apache/fontbox/cff/CharStringCommand$Key.class */
    public enum Key {
        HSTEM(1),
        VSTEM(3),
        VMOVETO(4),
        RLINETO(5),
        HLINETO(6),
        VLINETO(7),
        RRCURVETO(8),
        CLOSEPATH(9),
        CALLSUBR(10),
        RET(11),
        ESCAPE(12),
        DOTSECTION(12, 0),
        VSTEM3(12, 1),
        HSTEM3(12, 2),
        AND(12, 3),
        OR(12, 4),
        NOT(12, 5),
        SEAC(12, 6),
        SBW(12, 7),
        ABS(12, 9),
        ADD(12, 10),
        SUB(12, 11),
        DIV(12, 12),
        NEG(12, 14),
        EQ(12, 15),
        CALLOTHERSUBR(12, 16),
        POP(12, 17),
        DROP(12, 18),
        PUT(12, 20),
        GET(12, 21),
        IFELSE(12, 22),
        RANDOM(12, 23),
        MUL(12, 24),
        SQRT(12, 26),
        DUP(12, 27),
        EXCH(12, 28),
        INDEX(12, 29),
        ROLL(12, 30),
        SETCURRENTPOINT(12, 33),
        HFLEX(12, 34),
        FLEX(12, 35),
        HFLEX1(12, 36),
        FLEX1(12, 37),
        HSBW(13),
        ENDCHAR(14),
        HSTEMHM(18),
        HINTMASK(19),
        CNTRMASK(20),
        RMOVETO(21),
        HMOVETO(22),
        VSTEMHM(23),
        RCURVELINE(24),
        RLINECURVE(25),
        VVCURVETO(26),
        HHCURVETO(27),
        SHORTINT(28),
        CALLGSUBR(29),
        VHCURVETO(30),
        HVCURVETO(31);

        private final int hashValue;
        private static final Map<Integer, Key> BY_KEY = new HashMap();

        static {
            for (Key e : values()) {
                BY_KEY.put(Integer.valueOf(e.hashValue), e);
            }
        }

        Key(int b0) {
            this.hashValue = b0;
        }

        Key(int b0, int b1) {
            this.hashValue = (b0 << 4) + b1;
        }

        public static Key valueOfKey(int b0) {
            return BY_KEY.get(Integer.valueOf(b0));
        }

        public static Key valueOfKey(int b0, int b1) {
            return BY_KEY.get(Integer.valueOf((b0 << 4) + b1));
        }
    }
}
