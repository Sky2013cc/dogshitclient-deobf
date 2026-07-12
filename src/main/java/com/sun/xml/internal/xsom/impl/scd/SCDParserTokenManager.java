package com.sun.xml.internal.xsom.impl.scd;

import com.sun.tools.javac.code.Flags;
import java.io.IOException;
import java.io.PrintStream;
import jdk.internal.dynalink.CallSiteDescriptor;
import kotlin.text.Typography;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;
import sun.tools.java.Scanner;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/SCDParserTokenManager.class */
public class SCDParserTokenManager implements SCDParserConstants {
    public PrintStream debugStream;
    static final long[] jjbitVec0 = {0, -16384, -17590038560769L, 8388607};
    static final long[] jjbitVec2 = {0, 0, 0, -36028797027352577L};
    static final long[] jjbitVec3 = {9219994337134247935L, 9223372036854775294L, -1, -274156627316187121L};
    static final long[] jjbitVec4 = {16777215, -65536, -576458553280167937L, 3};
    static final long[] jjbitVec5 = {0, 0, -17179879616L, 4503588160110591L};
    static final long[] jjbitVec6 = {-8194, -536936449, -65533, 234134404065073567L};
    static final long[] jjbitVec7 = {-562949953421312L, -8547991553L, 127, 1979120929931264L};
    static final long[] jjbitVec8 = {576460743713488896L, -562949953419266L, 9007199254740991999L, 412319973375L};
    static final long[] jjbitVec9 = {2594073385365405664L, 17163091968L, 271902628478820320L, 844440767823872L};
    static final long[] jjbitVec10 = {247132830528276448L, 7881300924956672L, 2589004636761075680L, Scanner.LINEINC};
    static final long[] jjbitVec11 = {2579997437506199520L, 15837691904L, 270153412153034720L, 0};
    static final long[] jjbitVec12 = {283724577500946400L, 12884901888L, 283724577500946400L, 13958643712L};
    static final long[] jjbitVec13 = {288228177128316896L, 12884901888L, 0, 0};
    static final long[] jjbitVec14 = {3799912185593854L, 63, 2309621682768192918L, 31};
    static final long[] jjbitVec15 = {0, 4398046510847L, 0, 0};
    static final long[] jjbitVec16 = {0, 0, -4294967296L, 36028797018898495L};
    static final long[] jjbitVec17 = {5764607523034749677L, 12493387738468353L, -756383734487318528L, 144405459145588743L};
    static final long[] jjbitVec18 = {-1, -1, -4026531841L, 288230376151711743L};
    static final long[] jjbitVec19 = {-3233808385L, 4611686017001275199L, 6908521828386340863L, 2295745090394464220L};
    static final long[] jjbitVec20 = {83837761617920L, 0, 7, 0};
    static final long[] jjbitVec21 = {4389456576640L, -2, -8587837441L, 576460752303423487L};
    static final long[] jjbitVec22 = {35184372088800L, 0, 0, 0};
    static final long[] jjbitVec23 = {-1, -1, 274877906943L, 0};
    static final long[] jjbitVec24 = {-1, -1, 68719476735L, 0};
    static final long[] jjbitVec25 = {0, 0, 36028797018963968L, -36028797027352577L};
    static final long[] jjbitVec26 = {16777215, -65536, -576458553280167937L, 196611};
    static final long[] jjbitVec27 = {-1, 12884901951L, -17179879488L, 4503588160110591L};
    static final long[] jjbitVec28 = {-8194, -536936449, -65413, 234134404065073567L};
    static final long[] jjbitVec29 = {-562949953421312L, -8547991553L, -4899916411759099777L, 1979120929931286L};
    static final long[] jjbitVec30 = {576460743713488896L, -277081224642561L, 9007199254740991999L, 288017070894841855L};
    static final long[] jjbitVec31 = {-864691128455135250L, 281268803485695L, -3186861885341720594L, 1125692414638495L};
    static final long[] jjbitVec32 = {-3211631683292264476L, 9006925953907079L, -869759877059465234L, 281204393786303L};
    static final long[] jjbitVec33 = {-878767076314341394L, 281215949093263L, -4341532606274353172L, 280925229301191L};
    static final long[] jjbitVec34 = {-4327961440926441490L, 281212990012895L, -4327961440926441492L, 281214063754719L};
    static final long[] jjbitVec35 = {-4323457841299070996L, 281212992110031L, 0, 0};
    static final long[] jjbitVec36 = {576320014815068158L, 67076095, 4323293666156225942L, 67059551};
    static final long[] jjbitVec37 = {-4422530440275951616L, -558551906910465L, 215680200883507167L, 0};
    static final long[] jjbitVec38 = {0, 0, 0, 9126739968L};
    static final long[] jjbitVec39 = {17732914942836896L, -2, -6876561409L, 8646911284551352319L};
    static final int[] jjnextStates = {3, 4, 103, 113, 123, 133, 140, 147};
    public static final String[] jjstrLiteralImages = {"", null, null, null, null, null, null, null, null, null, null, null, null, null, null, CallSiteDescriptor.TOKEN_DELIMITER, RuntimeConstants.SIG_PACKAGE, "//", "attribute::", "@", "element::", "substitutionGroup::", "type::", "~", "baseType::", "primitiveType::", "itemType::", "memberType::", "scope::", "attributeGroup::", "group::", "identityContraint::", "key::", "notation::", "model::sequence", "model::choice", "model::all", "model::*", "any::*", "anyAttribute::*", "facet::*", "facet::", "component::*", "x-schema::", "x-schema::*", Marker.ANY_MARKER, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES};
    public static final String[] lexStateNames = {"DEFAULT"};
    static final long[] jjtoToken = {140737488351233L};
    static final long[] jjtoSkip = {62};
    protected SimpleCharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;

    public void setDebugStream(PrintStream ds) {
        this.debugStream = ds;
    }

    private final int jjStopStringLiteralDfa_0(int pos, long active0) {
        switch (pos) {
            case 0:
                if ((active0 & 257832255488L) != 0) {
                    this.jjmatchedKind = 12;
                    return 103;
                }
                if ((active0 & 4194304) != 0) {
                    this.jjmatchedKind = 12;
                    return 55;
                }
                if ((active0 & 3298534883328L) != 0) {
                    this.jjmatchedKind = 12;
                    return 68;
                }
                if ((active0 & 33554432) != 0) {
                    this.jjmatchedKind = 12;
                    return 81;
                }
                if ((active0 & 8589934592L) != 0) {
                    this.jjmatchedKind = 12;
                    return 23;
                }
                if ((active0 & Flags.CLASH) != 0) {
                    this.jjmatchedKind = 12;
                    return 34;
                }
                if ((active0 & 1048576) != 0) {
                    this.jjmatchedKind = 12;
                    return 91;
                }
                if ((active0 & 27221303754752L) != 0) {
                    this.jjmatchedKind = 12;
                    return 1;
                }
                if ((active0 & 16777216) != 0) {
                    this.jjmatchedKind = 12;
                    return 16;
                }
                return -1;
            case 1:
                if ((active0 & 35184362913792L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 1;
                    return 1;
                }
                return -1;
            case 2:
                if ((active0 & 35184362913792L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 2;
                    return 1;
                }
                return -1;
            case 3:
                if ((active0 & 279172874240L) != 0) {
                    if (this.jjmatchedPos < 2) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 2;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 34905190039552L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 3;
                    return 1;
                }
                return -1;
            case 4:
                if ((active0 & 279172874240L) != 0) {
                    if (this.jjmatchedPos < 2) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 2;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 4194304) != 0) {
                    if (this.jjmatchedPos < 3) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 3;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 34905185845248L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 4;
                    return 1;
                }
                return -1;
            case 5:
                if ((active0 & Flags.PROPRIETARY) != 0) {
                    if (this.jjmatchedPos < 2) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 2;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 3557575098368L) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 4194304) != 0) {
                    if (this.jjmatchedPos < 3) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 3;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 31347610746880L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 5;
                    return 1;
                }
                return -1;
            case 6:
                if ((active0 & 3557575098368L) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 31347610746880L) != 0) {
                    if (this.jjmatchedPos != 6) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 6;
                        return 1;
                    }
                    return 1;
                }
                return -1;
            case 7:
                if ((active0 & 1048576) != 0) {
                    if (this.jjmatchedPos < 6) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 6;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 1357209665536L) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 31347609698304L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 7;
                    return 1;
                }
                return -1;
            case 8:
                if ((active0 & 4950656811008L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 8;
                    return 1;
                }
                if ((active0 & 26396952887296L) != 0) {
                    if (this.jjmatchedPos < 7) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 7;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 1048576) != 0) {
                    if (this.jjmatchedPos < 6) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 6;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 120259084288L) != 0 && this.jjmatchedPos < 4) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 4;
                    return -1;
                }
                return -1;
            case 9:
                if ((active0 & 552610037760L) != 0) {
                    if (this.jjmatchedPos != 9) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 9;
                        return 1;
                    }
                    return 1;
                }
                if ((active0 & 26396952887296L) != 0) {
                    if (this.jjmatchedPos < 7) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 7;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 4398046773248L) != 0) {
                    if (this.jjmatchedPos < 8) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 8;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 120259084288L) != 0 && this.jjmatchedPos < 4) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 4;
                    return -1;
                }
                return -1;
            case 10:
                if ((active0 & Flags.AUXILIARY) != 0) {
                    if (this.jjmatchedPos < 7) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 7;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 134217728) != 0) {
                    if (this.jjmatchedPos < 9) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 9;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 4398046773248L) != 0) {
                    if (this.jjmatchedPos < 8) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 8;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 552475820032L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 10;
                    return 1;
                }
                if ((active0 & 51539607552L) != 0 && this.jjmatchedPos < 4) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 4;
                    return -1;
                }
                return -1;
            case 11:
                if ((active0 & Flags.CLASH) != 0) {
                    if (this.jjmatchedPos < 8) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 8;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 134217728) != 0) {
                    if (this.jjmatchedPos < 9) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 9;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 51539607552L) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 552475820032L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 11;
                    return 1;
                }
                return -1;
            case 12:
                if ((active0 & Flags.UNION) != 0) {
                    if (this.jjmatchedPos < 11) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 11;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 51539607552L) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 2720006144L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 12;
                    return 1;
                }
                return -1;
            case 13:
                if ((active0 & Flags.UNION) != 0) {
                    if (this.jjmatchedPos < 11) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 11;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 33554432) != 0) {
                    if (this.jjmatchedPos < 12) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 12;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & Flags.VARARGS) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 2686451712L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 13;
                    return 1;
                }
                return -1;
            case 14:
                if ((active0 & Flags.UNION) != 0) {
                    if (this.jjmatchedPos < 11) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 11;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 536870912) != 0) {
                    if (this.jjmatchedPos < 13) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 13;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 33554432) != 0) {
                    if (this.jjmatchedPos < 12) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 12;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & Flags.VARARGS) != 0) {
                    if (this.jjmatchedPos < 4) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 4;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 2149580800L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 14;
                    return 1;
                }
                return -1;
            case 15:
                if ((active0 & 536870912) != 0) {
                    if (this.jjmatchedPos < 13) {
                        this.jjmatchedKind = 12;
                        this.jjmatchedPos = 13;
                        return -1;
                    }
                    return -1;
                }
                if ((active0 & 2149580800L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 15;
                    return 1;
                }
                return -1;
            case 16:
                if ((active0 & 2149580800L) != 0) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 16;
                    return 1;
                }
                return -1;
            case 17:
                if ((active0 & 2149580800L) != 0 && this.jjmatchedPos < 16) {
                    this.jjmatchedKind = 12;
                    this.jjmatchedPos = 16;
                    return -1;
                }
                return -1;
            default:
                return -1;
        }
    }

    private final int jjStartNfa_0(int pos, long active0) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    private final int jjStopAtPos(int pos, int kind) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        return pos + 1;
    }

    private final int jjStartNfaWithStates_0(int pos, int kind, int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
            return jjMoveNfa_0(state, pos + 1);
        } catch (IOException e) {
            return pos + 1;
        }
    }

    private final int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '*':
                return jjStopAtPos(0, 45);
            case '+':
            case ',':
            case '-':
            case '.':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'd':
            case 'h':
            case 'j':
            case 'l':
            case 'o':
            case 'q':
            case 'r':
            case 'u':
            case 'v':
            case 'w':
            case 'y':
            case 'z':
            case '{':
            case '|':
            case '}':
            default:
                return jjMoveNfa_0(0, 0);
            case '/':
                this.jjmatchedKind = 16;
                return jjMoveStringLiteralDfa1_0(131072L);
            case '0':
                return jjStopAtPos(0, 46);
            case ':':
                return jjStopAtPos(0, 15);
            case '@':
                return jjStopAtPos(0, 19);
            case 'a':
                return jjMoveStringLiteralDfa1_0(825170853888L);
            case 'b':
                return jjMoveStringLiteralDfa1_0(16777216L);
            case 'c':
                return jjMoveStringLiteralDfa1_0(Flags.CLASH);
            case 'e':
                return jjMoveStringLiteralDfa1_0(1048576L);
            case 'f':
                return jjMoveStringLiteralDfa1_0(3298534883328L);
            case 'g':
                return jjMoveStringLiteralDfa1_0(1073741824L);
            case 'i':
                return jjMoveStringLiteralDfa1_0(2214592512L);
            case 'k':
                return jjMoveStringLiteralDfa1_0(Scanner.LINEINC);
            case 'm':
                return jjMoveStringLiteralDfa1_0(257832255488L);
            case 'n':
                return jjMoveStringLiteralDfa1_0(8589934592L);
            case 'p':
                return jjMoveStringLiteralDfa1_0(33554432L);
            case 's':
                return jjMoveStringLiteralDfa1_0(270532608L);
            case 't':
                return jjMoveStringLiteralDfa1_0(4194304L);
            case 'x':
                return jjMoveStringLiteralDfa1_0(26388279066624L);
            case '~':
                return jjStopAtPos(0, 23);
        }
    }

    private final int jjMoveStringLiteralDfa1_0(long active0) {
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '-':
                    return jjMoveStringLiteralDfa2_0(active0, 26388279066624L);
                case '/':
                    if ((active0 & 131072) != 0) {
                        return jjStopAtPos(1, 17);
                    }
                    break;
                case 'a':
                    return jjMoveStringLiteralDfa2_0(active0, 3298551660544L);
                case 'c':
                    return jjMoveStringLiteralDfa2_0(active0, 268435456L);
                case 'd':
                    return jjMoveStringLiteralDfa2_0(active0, Flags.BRIDGE);
                case 'e':
                    return jjMoveStringLiteralDfa2_0(active0, 4429185024L);
                case 'l':
                    return jjMoveStringLiteralDfa2_0(active0, 1048576L);
                case 'n':
                    return jjMoveStringLiteralDfa2_0(active0, 824633720832L);
                case 'o':
                    return jjMoveStringLiteralDfa2_0(active0, 4664334483456L);
                case 'r':
                    return jjMoveStringLiteralDfa2_0(active0, 1107296256L);
                case 't':
                    return jjMoveStringLiteralDfa2_0(active0, 604241920L);
                case 'u':
                    return jjMoveStringLiteralDfa2_0(active0, 2097152L);
                case 'y':
                    return jjMoveStringLiteralDfa2_0(active0, 4194304L);
            }
            return jjStartNfa_0(0, active0);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
    }

    private final int jjMoveStringLiteralDfa2_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(0, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'b':
                    return jjMoveStringLiteralDfa3_0(active02, 2097152L);
                case 'c':
                    return jjMoveStringLiteralDfa3_0(active02, 3298534883328L);
                case 'd':
                    return jjMoveStringLiteralDfa3_0(active02, 257698037760L);
                case 'e':
                    return jjMoveStringLiteralDfa3_0(active02, 2215641088L);
                case 'f':
                case 'g':
                case 'h':
                case 'j':
                case 'k':
                case 'l':
                case 'n':
                case 'q':
                case 'r':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                default:
                    return jjStartNfa_0(1, active02);
                case 'i':
                    return jjMoveStringLiteralDfa3_0(active02, 33554432L);
                case 'm':
                    return jjMoveStringLiteralDfa3_0(active02, 4398180728832L);
                case 'o':
                    return jjMoveStringLiteralDfa3_0(active02, 1342177280L);
                case 'p':
                    return jjMoveStringLiteralDfa3_0(active02, 4194304L);
                case 's':
                    return jjMoveStringLiteralDfa3_0(active02, 26388295843840L);
                case 't':
                    return jjMoveStringLiteralDfa3_0(active02, 9127067648L);
                case 'y':
                    return jjMoveStringLiteralDfa3_0(active02, 828928688128L);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(1, active02);
            return 2;
        }
    }

    private final int jjMoveStringLiteralDfa3_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(1, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    return jjMoveStringLiteralDfa4_0(active02, 279172874240L);
                case 'A':
                    return jjMoveStringLiteralDfa4_0(active02, Flags.UNION);
                case 'a':
                    return jjMoveStringLiteralDfa4_0(active02, 8589934592L);
                case 'b':
                    return jjMoveStringLiteralDfa4_0(active02, 134217728L);
                case 'c':
                    return jjMoveStringLiteralDfa4_0(active02, 26388279066624L);
                case 'e':
                    return jjMoveStringLiteralDfa4_0(active02, 3556253892608L);
                case 'm':
                    return jjMoveStringLiteralDfa4_0(active02, 101711872L);
                case 'n':
                    return jjMoveStringLiteralDfa4_0(active02, Flags.BRIDGE);
                case 'p':
                    return jjMoveStringLiteralDfa4_0(active02, 4398314946560L);
                case 'r':
                    return jjMoveStringLiteralDfa4_0(active02, 537133056L);
                case 's':
                    return jjMoveStringLiteralDfa4_0(active02, 2097152L);
                case 'u':
                    return jjMoveStringLiteralDfa4_0(active02, 1073741824L);
                default:
                    return jjStartNfa_0(2, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(2, active02);
            return 3;
        }
    }

    private final int jjMoveStringLiteralDfa4_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(2, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    if ((active02 & Scanner.LINEINC) != 0) {
                        return jjStopAtPos(4, 32);
                    }
                    return jjMoveStringLiteralDfa5_0(active02, 274882101248L);
                case 'T':
                    return jjMoveStringLiteralDfa5_0(active02, 83886080L);
                case 'e':
                    return jjMoveStringLiteralDfa5_0(active02, 403701760L);
                case 'h':
                    return jjMoveStringLiteralDfa5_0(active02, 26388279066624L);
                case 'i':
                    return jjMoveStringLiteralDfa5_0(active02, 570687488L);
                case 'l':
                    return jjMoveStringLiteralDfa5_0(active02, 257698037760L);
                case 'o':
                    return jjMoveStringLiteralDfa5_0(active02, Flags.CLASH);
                case 'p':
                    return jjMoveStringLiteralDfa5_0(active02, 1073741824L);
                case 't':
                    return jjMoveStringLiteralDfa5_0(active02, 3859030212608L);
                default:
                    return jjStartNfa_0(3, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(3, active02);
            return 4;
        }
    }

    private final int jjMoveStringLiteralDfa5_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(3, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '*':
                    if ((active02 & Flags.PROPRIETARY) != 0) {
                        return jjStopAtPos(5, 38);
                    }
                    break;
                case ':':
                    if ((active02 & 4194304) != 0) {
                        return jjStopAtPos(5, 22);
                    }
                    return jjMoveStringLiteralDfa6_0(active02, 3557575098368L);
                case 'b':
                    return jjMoveStringLiteralDfa6_0(active02, 537133056L);
                case 'e':
                    return jjMoveStringLiteralDfa6_0(active02, 26388279066624L);
                case 'i':
                    return jjMoveStringLiteralDfa6_0(active02, 10739515392L);
                case 'n':
                    return jjMoveStringLiteralDfa6_0(active02, 4398047559680L);
                case 'r':
                    return jjMoveStringLiteralDfa6_0(active02, 134217728L);
                case 't':
                    return jjMoveStringLiteralDfa6_0(active02, 549789368320L);
                case 'y':
                    return jjMoveStringLiteralDfa6_0(active02, 83886080L);
            }
            return jjStartNfa_0(4, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(4, active02);
            return 5;
        }
    }

    private final int jjMoveStringLiteralDfa6_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(4, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    if ((active02 & 268435456) != 0) {
                        return jjStopAtPos(6, 28);
                    }
                    if ((active02 & 1073741824) != 0) {
                        return jjStopAtPos(6, 30);
                    }
                    if ((active02 & Flags.EFFECTIVELY_FINAL) != 0) {
                        this.jjmatchedKind = 41;
                        this.jjmatchedPos = 6;
                    }
                    return jjMoveStringLiteralDfa7_0(active02, 1357209665536L);
                case 'T':
                    return jjMoveStringLiteralDfa7_0(active02, 134217728L);
                case 'e':
                    return jjMoveStringLiteralDfa7_0(active02, Flags.CLASH);
                case 'i':
                    return jjMoveStringLiteralDfa7_0(active02, 33554432L);
                case 'm':
                    return jjMoveStringLiteralDfa7_0(active02, 26388279066624L);
                case 'o':
                    return jjMoveStringLiteralDfa7_0(active02, 8589934592L);
                case 'p':
                    return jjMoveStringLiteralDfa7_0(active02, 83886080L);
                case 'r':
                    return jjMoveStringLiteralDfa7_0(active02, Flags.UNION);
                case 't':
                    return jjMoveStringLiteralDfa7_0(active02, 2150629376L);
                case 'u':
                    return jjMoveStringLiteralDfa7_0(active02, 537133056L);
                default:
                    return jjStartNfa_0(5, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(5, active02);
            return 6;
        }
    }

    private final int jjMoveStringLiteralDfa7_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(5, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '*':
                    if ((active02 & Flags.HYPOTHETICAL) != 0) {
                        return jjStopAtPos(7, 37);
                    }
                    if ((active02 & Flags.OVERRIDE_BRIDGE) != 0) {
                        return jjStopAtPos(7, 40);
                    }
                    break;
                case ':':
                    return jjMoveStringLiteralDfa8_0(active02, 1048576L);
                case 'a':
                    return jjMoveStringLiteralDfa8_0(active02, 26456998543360L);
                case 'c':
                    return jjMoveStringLiteralDfa8_0(active02, Flags.ACYCLIC_ANN);
                case 'e':
                    return jjMoveStringLiteralDfa8_0(active02, 83886080L);
                case 'i':
                    return jjMoveStringLiteralDfa8_0(active02, Flags.UNION);
                case 'n':
                    return jjMoveStringLiteralDfa8_0(active02, 4406636445696L);
                case 's':
                    return jjMoveStringLiteralDfa8_0(active02, Flags.VARARGS);
                case 't':
                    return jjMoveStringLiteralDfa8_0(active02, 537133056L);
                case 'u':
                    return jjMoveStringLiteralDfa8_0(active02, 2097152L);
                case 'v':
                    return jjMoveStringLiteralDfa8_0(active02, 33554432L);
                case 'y':
                    return jjMoveStringLiteralDfa8_0(active02, 2281701376L);
            }
            return jjStartNfa_0(6, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(6, active02);
            return 7;
        }
    }

    private final int jjMoveStringLiteralDfa8_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(6, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    if ((active02 & 1048576) != 0) {
                        return jjStopAtPos(8, 20);
                    }
                    return jjMoveStringLiteralDfa9_0(active02, 26396952887296L);
                case 'C':
                    return jjMoveStringLiteralDfa9_0(active02, Flags.BRIDGE);
                case 'b':
                    return jjMoveStringLiteralDfa9_0(active02, Flags.UNION);
                case 'e':
                    return jjMoveStringLiteralDfa9_0(active02, 17750556672L);
                case 'h':
                    return jjMoveStringLiteralDfa9_0(active02, Flags.ACYCLIC_ANN);
                case 'l':
                    return jjMoveStringLiteralDfa9_0(active02, Flags.GENERATEDCONSTR);
                case 'p':
                    return jjMoveStringLiteralDfa9_0(active02, 134217728L);
                case 't':
                    return jjMoveStringLiteralDfa9_0(active02, 4398048608256L);
                default:
                    return jjStartNfa_0(7, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(7, active02);
            return 8;
        }
    }

    private final int jjMoveStringLiteralDfa9_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(7, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    if ((active02 & 16777216) != 0) {
                        return jjStopAtPos(9, 24);
                    }
                    if ((active02 & 67108864) != 0) {
                        return jjStopAtPos(9, 26);
                    }
                    if ((active02 & 8589934592L) != 0) {
                        return jjStopAtPos(9, 33);
                    }
                    if ((active02 & Flags.DEFAULT) != 0) {
                        this.jjmatchedKind = 43;
                        this.jjmatchedPos = 9;
                    }
                    return jjMoveStringLiteralDfa10_0(active02, 21990232817664L);
                case 'G':
                    return jjMoveStringLiteralDfa10_0(active02, 536870912L);
                case 'T':
                    return jjMoveStringLiteralDfa10_0(active02, 33554432L);
                case 'e':
                    return jjMoveStringLiteralDfa10_0(active02, 134217728L);
                case 'i':
                    return jjMoveStringLiteralDfa10_0(active02, 2097152L);
                case 'l':
                    if ((active02 & Flags.GENERATEDCONSTR) != 0) {
                        return jjStopAtPos(9, 36);
                    }
                    break;
                case 'o':
                    return jjMoveStringLiteralDfa10_0(active02, 36507222016L);
                case 'q':
                    return jjMoveStringLiteralDfa10_0(active02, Flags.VARARGS);
                case 'u':
                    return jjMoveStringLiteralDfa10_0(active02, Flags.UNION);
            }
            return jjStartNfa_0(8, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(8, active02);
            return 9;
        }
    }

    private final int jjMoveStringLiteralDfa10_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(8, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '*':
                    if ((active02 & Flags.AUXILIARY) != 0) {
                        return jjStopAtPos(10, 44);
                    }
                    break;
                case ':':
                    if ((active02 & 262144) != 0) {
                        return jjStopAtPos(10, 18);
                    }
                    return jjMoveStringLiteralDfa11_0(active02, 4398180728832L);
                case 'i':
                    return jjMoveStringLiteralDfa11_0(active02, Flags.ACYCLIC_ANN);
                case 'n':
                    return jjMoveStringLiteralDfa11_0(active02, Flags.BRIDGE);
                case 'o':
                    return jjMoveStringLiteralDfa11_0(active02, 2097152L);
                case 'r':
                    return jjMoveStringLiteralDfa11_0(active02, 536870912L);
                case 't':
                    return jjMoveStringLiteralDfa11_0(active02, Flags.UNION);
                case 'u':
                    return jjMoveStringLiteralDfa11_0(active02, Flags.VARARGS);
                case 'y':
                    return jjMoveStringLiteralDfa11_0(active02, 33554432L);
            }
            return jjStartNfa_0(9, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(9, active02);
            return 10;
        }
    }

    private final int jjMoveStringLiteralDfa11_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(9, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '*':
                    if ((active02 & Flags.CLASH) != 0) {
                        return jjStopAtPos(11, 42);
                    }
                    break;
                case ':':
                    if ((active02 & 134217728) != 0) {
                        return jjStopAtPos(11, 27);
                    }
                    break;
                case 'c':
                    return jjMoveStringLiteralDfa12_0(active02, Flags.ACYCLIC_ANN);
                case 'e':
                    return jjMoveStringLiteralDfa12_0(active02, 566935683072L);
                case 'n':
                    return jjMoveStringLiteralDfa12_0(active02, 2097152L);
                case 'o':
                    return jjMoveStringLiteralDfa12_0(active02, 536870912L);
                case 'p':
                    return jjMoveStringLiteralDfa12_0(active02, 33554432L);
                case 't':
                    return jjMoveStringLiteralDfa12_0(active02, Flags.BRIDGE);
            }
            return jjStartNfa_0(10, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(10, active02);
            return 11;
        }
    }

    private final int jjMoveStringLiteralDfa12_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(10, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    return jjMoveStringLiteralDfa13_0(active02, Flags.UNION);
                case 'G':
                    return jjMoveStringLiteralDfa13_0(active02, 2097152L);
                case 'e':
                    if ((active02 & Flags.ACYCLIC_ANN) != 0) {
                        return jjStopAtPos(12, 35);
                    }
                    return jjMoveStringLiteralDfa13_0(active02, 33554432L);
                case 'n':
                    return jjMoveStringLiteralDfa13_0(active02, Flags.VARARGS);
                case 'r':
                    return jjMoveStringLiteralDfa13_0(active02, Flags.BRIDGE);
                case 'u':
                    return jjMoveStringLiteralDfa13_0(active02, 536870912L);
                default:
                    return jjStartNfa_0(11, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(11, active02);
            return 12;
        }
    }

    private final int jjMoveStringLiteralDfa13_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(11, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    return jjMoveStringLiteralDfa14_0(active02, 549789368320L);
                case 'a':
                    return jjMoveStringLiteralDfa14_0(active02, Flags.BRIDGE);
                case 'c':
                    return jjMoveStringLiteralDfa14_0(active02, Flags.VARARGS);
                case 'p':
                    return jjMoveStringLiteralDfa14_0(active02, 536870912L);
                case 'r':
                    return jjMoveStringLiteralDfa14_0(active02, 2097152L);
                default:
                    return jjStartNfa_0(12, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(12, active02);
            return 13;
        }
    }

    private final int jjMoveStringLiteralDfa14_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(12, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '*':
                    if ((active02 & Flags.UNION) != 0) {
                        return jjStopAtPos(14, 39);
                    }
                    break;
                case ':':
                    if ((active02 & 33554432) != 0) {
                        return jjStopAtPos(14, 25);
                    }
                    return jjMoveStringLiteralDfa15_0(active02, 536870912L);
                case 'e':
                    if ((active02 & Flags.VARARGS) != 0) {
                        return jjStopAtPos(14, 34);
                    }
                    break;
                case 'i':
                    return jjMoveStringLiteralDfa15_0(active02, Flags.BRIDGE);
                case 'o':
                    return jjMoveStringLiteralDfa15_0(active02, 2097152L);
            }
            return jjStartNfa_0(13, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(13, active02);
            return 14;
        }
    }

    private final int jjMoveStringLiteralDfa15_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(13, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    if ((active02 & 536870912) != 0) {
                        return jjStopAtPos(15, 29);
                    }
                    break;
                case 'n':
                    return jjMoveStringLiteralDfa16_0(active02, Flags.BRIDGE);
                case 'u':
                    return jjMoveStringLiteralDfa16_0(active02, 2097152L);
            }
            return jjStartNfa_0(14, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(14, active02);
            return 15;
        }
    }

    private final int jjMoveStringLiteralDfa16_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(14, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'p':
                    return jjMoveStringLiteralDfa17_0(active02, 2097152L);
                case 't':
                    return jjMoveStringLiteralDfa17_0(active02, Flags.BRIDGE);
                default:
                    return jjStartNfa_0(15, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(15, active02);
            return 16;
        }
    }

    private final int jjMoveStringLiteralDfa17_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(15, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    return jjMoveStringLiteralDfa18_0(active02, 2149580800L);
                default:
                    return jjStartNfa_0(16, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(16, active02);
            return 17;
        }
    }

    private final int jjMoveStringLiteralDfa18_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(16, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case ':':
                    if ((active02 & 2097152) != 0) {
                        return jjStopAtPos(18, 21);
                    }
                    if ((active02 & Flags.BRIDGE) != 0) {
                        return jjStopAtPos(18, 31);
                    }
                    break;
            }
            return jjStartNfa_0(17, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(17, active02);
            return 18;
        }
    }

    private final void jjCheckNAdd(int state) {
        if (this.jjrounds[state] != this.jjround) {
            int[] iArr = this.jjstateSet;
            int i = this.jjnewStateCnt;
            this.jjnewStateCnt = i + 1;
            iArr[i] = state;
            this.jjrounds[state] = this.jjround;
        }
    }

    private final void jjAddStates(int start, int end) {
        int i;
        do {
            int[] iArr = this.jjstateSet;
            int i2 = this.jjnewStateCnt;
            this.jjnewStateCnt = i2 + 1;
            iArr[i2] = jjnextStates[start];
            i = start;
            start++;
        } while (i != end);
    }

    private final void jjCheckNAddTwoStates(int state1, int state2) {
        jjCheckNAdd(state1);
        jjCheckNAdd(state2);
    }

    private final void jjCheckNAddStates(int start, int end) {
        int i;
        do {
            jjCheckNAdd(jjnextStates[start]);
            i = start;
            start++;
        } while (i != end);
    }

    private final void jjCheckNAddStates(int start) {
        jjCheckNAdd(jjnextStates[start]);
        jjCheckNAdd(jjnextStates[start + 1]);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:242)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private final int jjMoveNfa_0(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 6444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.internal.xsom.impl.scd.SCDParserTokenManager.jjMoveNfa_0(int, int):int");
    }

    private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0:
                return (jjbitVec2[i2] & l2) != 0;
            case 1:
                return (jjbitVec3[i2] & l2) != 0;
            case 2:
                return (jjbitVec4[i2] & l2) != 0;
            case 3:
                return (jjbitVec5[i2] & l2) != 0;
            case 4:
                return (jjbitVec6[i2] & l2) != 0;
            case 5:
                return (jjbitVec7[i2] & l2) != 0;
            case 6:
                return (jjbitVec8[i2] & l2) != 0;
            case 9:
                return (jjbitVec9[i2] & l2) != 0;
            case 10:
                return (jjbitVec10[i2] & l2) != 0;
            case 11:
                return (jjbitVec11[i2] & l2) != 0;
            case 12:
                return (jjbitVec12[i2] & l2) != 0;
            case 13:
                return (jjbitVec13[i2] & l2) != 0;
            case 14:
                return (jjbitVec14[i2] & l2) != 0;
            case 15:
                return (jjbitVec15[i2] & l2) != 0;
            case 16:
                return (jjbitVec16[i2] & l2) != 0;
            case 17:
                return (jjbitVec17[i2] & l2) != 0;
            case 30:
                return (jjbitVec18[i2] & l2) != 0;
            case 31:
                return (jjbitVec19[i2] & l2) != 0;
            case 33:
                return (jjbitVec20[i2] & l2) != 0;
            case 48:
                return (jjbitVec21[i2] & l2) != 0;
            case 49:
                return (jjbitVec22[i2] & l2) != 0;
            case 159:
                return (jjbitVec23[i2] & l2) != 0;
            case Typography.times /* 215 */:
                return (jjbitVec24[i2] & l2) != 0;
            default:
                if ((jjbitVec0[i1] & l1) != 0) {
                    return true;
                }
                return false;
        }
    }

    private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0:
                return (jjbitVec25[i2] & l2) != 0;
            case 1:
                return (jjbitVec3[i2] & l2) != 0;
            case 2:
                return (jjbitVec26[i2] & l2) != 0;
            case 3:
                return (jjbitVec27[i2] & l2) != 0;
            case 4:
                return (jjbitVec28[i2] & l2) != 0;
            case 5:
                return (jjbitVec29[i2] & l2) != 0;
            case 6:
                return (jjbitVec30[i2] & l2) != 0;
            case 9:
                return (jjbitVec31[i2] & l2) != 0;
            case 10:
                return (jjbitVec32[i2] & l2) != 0;
            case 11:
                return (jjbitVec33[i2] & l2) != 0;
            case 12:
                return (jjbitVec34[i2] & l2) != 0;
            case 13:
                return (jjbitVec35[i2] & l2) != 0;
            case 14:
                return (jjbitVec36[i2] & l2) != 0;
            case 15:
                return (jjbitVec37[i2] & l2) != 0;
            case 16:
                return (jjbitVec16[i2] & l2) != 0;
            case 17:
                return (jjbitVec17[i2] & l2) != 0;
            case 30:
                return (jjbitVec18[i2] & l2) != 0;
            case 31:
                return (jjbitVec19[i2] & l2) != 0;
            case 32:
                return (jjbitVec38[i2] & l2) != 0;
            case 33:
                return (jjbitVec20[i2] & l2) != 0;
            case 48:
                return (jjbitVec39[i2] & l2) != 0;
            case 49:
                return (jjbitVec22[i2] & l2) != 0;
            case 159:
                return (jjbitVec23[i2] & l2) != 0;
            case Typography.times /* 215 */:
                return (jjbitVec24[i2] & l2) != 0;
            default:
                if ((jjbitVec0[i1] & l1) != 0) {
                    return true;
                }
                return false;
        }
    }

    public SCDParserTokenManager(SimpleCharStream stream) {
        this.debugStream = System.out;
        this.jjrounds = new int[148];
        this.jjstateSet = new int[296];
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = stream;
    }

    public SCDParserTokenManager(SimpleCharStream stream, int lexState) {
        this(stream);
        SwitchTo(lexState);
    }

    public void ReInit(SimpleCharStream stream) {
        this.jjnewStateCnt = 0;
        this.jjmatchedPos = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        ReInitRounds();
    }

    private final void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 148;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                this.jjrounds[i] = Integer.MIN_VALUE;
            } else {
                return;
            }
        }
    }

    public void ReInit(SimpleCharStream stream, int lexState) {
        ReInit(stream);
        SwitchTo(lexState);
    }

    public void SwitchTo(int lexState) {
        if (lexState >= 1 || lexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }

    protected Token jjFillToken() {
        Token t = Token.newToken(this.jjmatchedKind);
        t.kind = this.jjmatchedKind;
        String im = jjstrLiteralImages[this.jjmatchedKind];
        t.image = im == null ? this.input_stream.GetImage() : im;
        t.beginLine = this.input_stream.getBeginLine();
        t.beginColumn = this.input_stream.getBeginColumn();
        t.endLine = this.input_stream.getEndLine();
        t.endColumn = this.input_stream.getEndColumn();
        return t;
    }

    public Token getNextToken() {
        int curPos;
        while (true) {
            try {
                this.curChar = this.input_stream.BeginToken();
                try {
                    this.input_stream.backup(0);
                    while (this.curChar <= ' ' && (4294981120L & (1 << this.curChar)) != 0) {
                        this.curChar = this.input_stream.BeginToken();
                    }
                    this.jjmatchedKind = Integer.MAX_VALUE;
                    this.jjmatchedPos = 0;
                    curPos = jjMoveStringLiteralDfa0_0();
                } catch (IOException e) {
                }
                if (this.jjmatchedKind != Integer.MAX_VALUE) {
                    if (this.jjmatchedPos + 1 < curPos) {
                        this.input_stream.backup((curPos - this.jjmatchedPos) - 1);
                    }
                    if ((jjtoToken[this.jjmatchedKind >> 6] & (1 << (this.jjmatchedKind & 63))) != 0) {
                        Token matchedToken = jjFillToken();
                        return matchedToken;
                    }
                } else {
                    int error_line = this.input_stream.getEndLine();
                    int error_column = this.input_stream.getEndColumn();
                    String error_after = null;
                    boolean EOFSeen = false;
                    try {
                        this.input_stream.readChar();
                        this.input_stream.backup(1);
                    } catch (IOException e2) {
                        EOFSeen = true;
                        error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
                        if (this.curChar == '\n' || this.curChar == '\r') {
                            error_line++;
                            error_column = 0;
                        } else {
                            error_column++;
                        }
                    }
                    if (!EOFSeen) {
                        this.input_stream.backup(1);
                        error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
                    }
                    throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
                }
            } catch (IOException e3) {
                this.jjmatchedKind = 0;
                Token matchedToken2 = jjFillToken();
                return matchedToken2;
            }
        }
    }
}
