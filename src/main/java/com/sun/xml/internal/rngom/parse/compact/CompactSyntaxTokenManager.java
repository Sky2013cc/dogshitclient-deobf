package com.sun.xml.internal.rngom.parse.compact;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import com.sun.tools.javac.code.Flags;
import java.io.IOException;
import java.io.PrintStream;
import jdk.internal.dynalink.CallSiteDescriptor;
import kotlin.text.Typography;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.ws.RealWebSocket;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;
import sun.tools.java.Scanner;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/compact/CompactSyntaxTokenManager.class */
public class CompactSyntaxTokenManager implements CompactSyntaxConstants {
    public PrintStream debugStream;
    static final long[] jjbitVec0 = {-2, -1, -1, -1};
    static final long[] jjbitVec2 = {0, 0, -1, -1};
    static final long[] jjbitVec3 = {0, -16384, -17590038560769L, 8388607};
    static final long[] jjbitVec4 = {0, 0, 0, -36028797027352577L};
    static final long[] jjbitVec5 = {9219994337134247935L, 9223372036854775294L, -1, -274156627316187121L};
    static final long[] jjbitVec6 = {16777215, -65536, -576458553280167937L, 3};
    static final long[] jjbitVec7 = {0, 0, -17179879616L, 4503588160110591L};
    static final long[] jjbitVec8 = {-8194, -536936449, -65533, 234134404065073567L};
    static final long[] jjbitVec9 = {-562949953421312L, -8547991553L, 127, 1979120929931264L};
    static final long[] jjbitVec10 = {576460743713488896L, -562949953419266L, 9007199254740991999L, 412319973375L};
    static final long[] jjbitVec11 = {2594073385365405664L, 17163091968L, 271902628478820320L, 844440767823872L};
    static final long[] jjbitVec12 = {247132830528276448L, 7881300924956672L, 2589004636761075680L, Scanner.LINEINC};
    static final long[] jjbitVec13 = {2579997437506199520L, 15837691904L, 270153412153034720L, 0};
    static final long[] jjbitVec14 = {283724577500946400L, 12884901888L, 283724577500946400L, 13958643712L};
    static final long[] jjbitVec15 = {288228177128316896L, 12884901888L, 0, 0};
    static final long[] jjbitVec16 = {3799912185593854L, 63, 2309621682768192918L, 31};
    static final long[] jjbitVec17 = {0, 4398046510847L, 0, 0};
    static final long[] jjbitVec18 = {0, 0, -4294967296L, 36028797018898495L};
    static final long[] jjbitVec19 = {5764607523034749677L, 12493387738468353L, -756383734487318528L, 144405459145588743L};
    static final long[] jjbitVec20 = {-1, -1, -4026531841L, 288230376151711743L};
    static final long[] jjbitVec21 = {-3233808385L, 4611686017001275199L, 6908521828386340863L, 2295745090394464220L};
    static final long[] jjbitVec22 = {83837761617920L, 0, 7, 0};
    static final long[] jjbitVec23 = {4389456576640L, -2, -8587837441L, 576460752303423487L};
    static final long[] jjbitVec24 = {35184372088800L, 0, 0, 0};
    static final long[] jjbitVec25 = {-1, -1, 274877906943L, 0};
    static final long[] jjbitVec26 = {-1, -1, 68719476735L, 0};
    static final long[] jjbitVec27 = {0, 0, 36028797018963968L, -36028797027352577L};
    static final long[] jjbitVec28 = {16777215, -65536, -576458553280167937L, 196611};
    static final long[] jjbitVec29 = {-1, 12884901951L, -17179879488L, 4503588160110591L};
    static final long[] jjbitVec30 = {-8194, -536936449, -65413, 234134404065073567L};
    static final long[] jjbitVec31 = {-562949953421312L, -8547991553L, -4899916411759099777L, 1979120929931286L};
    static final long[] jjbitVec32 = {576460743713488896L, -277081224642561L, 9007199254740991999L, 288017070894841855L};
    static final long[] jjbitVec33 = {-864691128455135250L, 281268803485695L, -3186861885341720594L, 1125692414638495L};
    static final long[] jjbitVec34 = {-3211631683292264476L, 9006925953907079L, -869759877059465234L, 281204393786303L};
    static final long[] jjbitVec35 = {-878767076314341394L, 281215949093263L, -4341532606274353172L, 280925229301191L};
    static final long[] jjbitVec36 = {-4327961440926441490L, 281212990012895L, -4327961440926441492L, 281214063754719L};
    static final long[] jjbitVec37 = {-4323457841299070996L, 281212992110031L, 0, 0};
    static final long[] jjbitVec38 = {576320014815068158L, 67076095, 4323293666156225942L, 67059551};
    static final long[] jjbitVec39 = {-4422530440275951616L, -558551906910465L, 215680200883507167L, 0};
    static final long[] jjbitVec40 = {0, 0, 0, 9126739968L};
    static final long[] jjbitVec41 = {17732914942836896L, -2, -6876561409L, 8646911284551352319L};
    static final int[] jjnextStates = {16, 17, 18, 19, 21, 25, 26, 27, 28, 30, 35, 36, 38, 39, 40, 10, 11, 13, 14, 3, 6, 7, 8};
    public static final String[] jjstrLiteralImages = {"", RuntimeConstants.SIG_ARRAY, "=", "&=", "|=", VisibleMemberMap.STARTLEVEL, "div", "include", "~", "]", "grammar", "{", "}", Constants.ATTR_NAMESPACE, "default", "inherit", "datatypes", "empty", "text", "notAllowed", CallSiteDescriptor.OPERATOR_DELIMITER, "&", DocLint.TAGS_SEPARATOR, Marker.ANY_NON_NULL_MARKER, "?", Marker.ANY_MARKER, Constants.ATTR_ELEMENT, "attribute", RuntimeConstants.SIG_METHOD, RuntimeConstants.SIG_ENDMETHOD, TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, Constants.ATTRVALUE_LIST, Constants.ATTR_MIXED, "external", "parent", "string", "token", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ">>", null};
    public static final String[] lexStateNames = {"DEFAULT", "AFTER_SINGLE_LINE_COMMENT", "AFTER_DOCUMENTATION"};
    public static final int[] jjnewLexState = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 2, -1, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    static final long[] jjtoToken = {2287840842771070975L};
    static final long[] jjtoSkip = {22539988369408L};
    static final long[] jjtoSpecial = {21990232555520L};
    protected JavaCharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    private final StringBuilder jjimage;
    private StringBuilder image;
    private int jjimageLen;
    private int lengthOfMatch;
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
                if ((active0 & 135493838048L) != 0) {
                    this.jjmatchedKind = 54;
                    return 43;
                }
                if ((active0 & 576460752303423488L) != 0) {
                    this.jjmatchedKind = 60;
                    return -1;
                }
                return -1;
            case 1:
                if ((active0 & 135493838048L) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 1;
                    return 43;
                }
                if ((active0 & 576460752303423488L) != 0 && this.jjmatchedPos == 0) {
                    this.jjmatchedKind = 60;
                    this.jjmatchedPos = 0;
                    return -1;
                }
                return -1;
            case 2:
                if ((active0 & 135493837984L) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 2;
                    return 43;
                }
                if ((active0 & 64) != 0) {
                    return 43;
                }
                return -1;
            case 3:
                if ((active0 & 133346092192L) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 3;
                    return 43;
                }
                if ((active0 & 2147745792L) != 0) {
                    return 43;
                }
                return -1;
            case 4:
                if ((active0 & 60331517056L) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 4;
                    return 43;
                }
                if ((active0 & 73014575136L) != 0) {
                    return 43;
                }
                return -1;
            case 5:
                if ((active0 & 8791909504L) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 5;
                    return 43;
                }
                if ((active0 & 51539607552L) != 0) {
                    return 43;
                }
                return -1;
            case 6:
                if ((active0 & 8724750336L) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 6;
                    return 43;
                }
                if ((active0 & 67159168) != 0) {
                    return 43;
                }
                return -1;
            case 7:
                if ((active0 & 134815744) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 7;
                    return 43;
                }
                if ((active0 & 8589934592L) != 0) {
                    return 43;
                }
                return -1;
            case 8:
                if ((active0 & 524288) != 0) {
                    this.jjmatchedKind = 54;
                    this.jjmatchedPos = 8;
                    return 43;
                }
                if ((active0 & 134291456) != 0) {
                    return 43;
                }
                return -1;
            default:
                return -1;
        }
    }

    private final int jjStartNfa_0(int pos, long active0) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    private int jjStopAtPos(int pos, int kind) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        return pos + 1;
    }

    private int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '&':
                this.jjmatchedKind = 21;
                return jjMoveStringLiteralDfa1_0(8L);
            case '\'':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '@':
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
            case '\\':
            case '^':
            case '_':
            case '`':
            case 'b':
            case 'c':
            case 'f':
            case 'h':
            case 'j':
            case 'k':
            case 'o':
            case 'q':
            case 'r':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
            default:
                return jjMoveNfa_0(3, 0);
            case '(':
                return jjStopAtPos(0, 28);
            case ')':
                return jjStopAtPos(0, 29);
            case '*':
                return jjStopAtPos(0, 25);
            case '+':
                return jjStopAtPos(0, 23);
            case ',':
                return jjStopAtPos(0, 22);
            case '-':
                return jjStopAtPos(0, 30);
            case '=':
                return jjStopAtPos(0, 2);
            case '>':
                return jjMoveStringLiteralDfa1_0(576460752303423488L);
            case '?':
                return jjStopAtPos(0, 24);
            case '[':
                return jjStopAtPos(0, 1);
            case ']':
                return jjStopAtPos(0, 9);
            case 'a':
                return jjMoveStringLiteralDfa1_0(134217728L);
            case 'd':
                return jjMoveStringLiteralDfa1_0(81984L);
            case 'e':
                return jjMoveStringLiteralDfa1_0(8657174528L);
            case 'g':
                return jjMoveStringLiteralDfa1_0(RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE);
            case 'i':
                return jjMoveStringLiteralDfa1_0(32896L);
            case 'l':
                return jjMoveStringLiteralDfa1_0(Flags.BRIDGE);
            case 'm':
                return jjMoveStringLiteralDfa1_0(Scanner.LINEINC);
            case 'n':
                return jjMoveStringLiteralDfa1_0(532480L);
            case 'p':
                return jjMoveStringLiteralDfa1_0(Flags.VARARGS);
            case 's':
                return jjMoveStringLiteralDfa1_0(34359738400L);
            case 't':
                return jjMoveStringLiteralDfa1_0(68719738880L);
            case '{':
                return jjStopAtPos(0, 11);
            case '|':
                this.jjmatchedKind = 20;
                return jjMoveStringLiteralDfa1_0(16L);
            case '}':
                return jjStopAtPos(0, 12);
            case '~':
                return jjStopAtPos(0, 8);
        }
    }

    private int jjMoveStringLiteralDfa1_0(long active0) {
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '=':
                    if ((active0 & 8) != 0) {
                        return jjStopAtPos(1, 3);
                    }
                    if ((active0 & 16) != 0) {
                        return jjStopAtPos(1, 4);
                    }
                    break;
                case '>':
                    if ((active0 & 576460752303423488L) != 0) {
                        return jjStopAtPos(1, 59);
                    }
                    break;
                case 'a':
                    return jjMoveStringLiteralDfa2_0(active0, 17179942912L);
                case 'e':
                    return jjMoveStringLiteralDfa2_0(active0, 278528L);
                case 'i':
                    return jjMoveStringLiteralDfa2_0(active0, 6442451008L);
                case 'l':
                    return jjMoveStringLiteralDfa2_0(active0, 67108864L);
                case 'm':
                    return jjMoveStringLiteralDfa2_0(active0, 131072L);
                case 'n':
                    return jjMoveStringLiteralDfa2_0(active0, 32896L);
                case 'o':
                    return jjMoveStringLiteralDfa2_0(active0, 68720001024L);
                case 'r':
                    return jjMoveStringLiteralDfa2_0(active0, RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE);
                case 't':
                    return jjMoveStringLiteralDfa2_0(active0, 34493956128L);
                case 'x':
                    return jjMoveStringLiteralDfa2_0(active0, 8589934592L);
            }
            return jjStartNfa_0(0, active0);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
    }

    private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(0, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'a':
                    return jjMoveStringLiteralDfa3_0(active02, 1056L);
                case 'c':
                    return jjMoveStringLiteralDfa3_0(active02, 128L);
                case 'e':
                    return jjMoveStringLiteralDfa3_0(active02, 67108864L);
                case 'f':
                    return jjMoveStringLiteralDfa3_0(active02, Http2Stream.EMIT_BUFFER_SIZE);
                case 'h':
                    return jjMoveStringLiteralDfa3_0(active02, 32768L);
                case 'k':
                    return jjMoveStringLiteralDfa3_0(active02, Flags.GENERATEDCONSTR);
                case 'm':
                    return jjMoveStringLiteralDfa3_0(active02, 8192L);
                case 'p':
                    return jjMoveStringLiteralDfa3_0(active02, 131072L);
                case 'r':
                    return jjMoveStringLiteralDfa3_0(active02, 51539607552L);
                case 's':
                    return jjMoveStringLiteralDfa3_0(active02, Flags.BRIDGE);
                case 't':
                    return jjMoveStringLiteralDfa3_0(active02, 8724742144L);
                case 'v':
                    if ((active02 & 64) != 0) {
                        return jjStartNfaWithStates_0(2, 6, 43);
                    }
                    break;
                case 'x':
                    return jjMoveStringLiteralDfa3_0(active02, 4295229440L);
            }
            return jjStartNfa_0(1, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(1, active02);
            return 2;
        }
    }

    private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(1, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'A':
                    return jjMoveStringLiteralDfa4_0(active02, 524288L);
                case 'a':
                    return jjMoveStringLiteralDfa4_0(active02, 81920L);
                case 'e':
                    return jjMoveStringLiteralDfa4_0(active02, 98784288768L);
                case 'i':
                    return jjMoveStringLiteralDfa4_0(active02, Flags.ACYCLIC_ANN);
                case 'l':
                    return jjMoveStringLiteralDfa4_0(active02, 128L);
                case 'm':
                    return jjMoveStringLiteralDfa4_0(active02, 67109888L);
                case 'r':
                    return jjMoveStringLiteralDfa4_0(active02, 134217760L);
                case 't':
                    if ((active02 & 262144) != 0) {
                        return jjStartNfaWithStates_0(3, 18, 43);
                    }
                    if ((active02 & Flags.BRIDGE) != 0) {
                        return jjStartNfaWithStates_0(3, 31, 43);
                    }
                    return jjMoveStringLiteralDfa4_0(active02, 131072L);
                default:
                    return jjStartNfa_0(2, active02);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(2, active02);
            return 3;
        }
    }

    private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(2, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'd':
                    if ((active02 & Scanner.LINEINC) != 0) {
                        return jjStartNfaWithStates_0(4, 32, 43);
                    }
                    break;
                case 'e':
                    return jjMoveStringLiteralDfa5_0(active02, 67108864L);
                case 'i':
                    return jjMoveStringLiteralDfa5_0(active02, 134217728L);
                case 'l':
                    return jjMoveStringLiteralDfa5_0(active02, 524288L);
                case 'm':
                    return jjMoveStringLiteralDfa5_0(active02, RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE);
                case 'n':
                    if ((active02 & Flags.GENERATEDCONSTR) != 0) {
                        return jjStartNfaWithStates_0(4, 36, 43);
                    }
                    return jjMoveStringLiteralDfa5_0(active02, 51539607552L);
                case 'r':
                    return jjMoveStringLiteralDfa5_0(active02, 8589967360L);
                case 's':
                    return jjMoveStringLiteralDfa5_0(active02, 8192L);
                case 't':
                    if ((active02 & 32) != 0) {
                        return jjStartNfaWithStates_0(4, 5, 43);
                    }
                    return jjMoveStringLiteralDfa5_0(active02, 65536L);
                case 'u':
                    return jjMoveStringLiteralDfa5_0(active02, 16512L);
                case 'y':
                    if ((active02 & 131072) != 0) {
                        return jjStartNfaWithStates_0(4, 17, 43);
                    }
                    break;
            }
            return jjStartNfa_0(3, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(3, active02);
            return 4;
        }
    }

    private int jjMoveStringLiteralDfa5_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(3, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'a':
                    return jjMoveStringLiteralDfa6_0(active02, RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE);
                case 'b':
                    return jjMoveStringLiteralDfa6_0(active02, 134217728L);
                case 'd':
                    return jjMoveStringLiteralDfa6_0(active02, 128L);
                case 'g':
                    if ((active02 & Flags.ACYCLIC_ANN) != 0) {
                        return jjStartNfaWithStates_0(5, 35, 43);
                    }
                    break;
                case 'i':
                    return jjMoveStringLiteralDfa6_0(active02, 32768L);
                case 'l':
                    return jjMoveStringLiteralDfa6_0(active02, 540672L);
                case 'n':
                    return jjMoveStringLiteralDfa6_0(active02, 8657043456L);
                case 'p':
                    return jjMoveStringLiteralDfa6_0(active02, 8192L);
                case 't':
                    if ((active02 & Flags.VARARGS) != 0) {
                        return jjStartNfaWithStates_0(5, 34, 43);
                    }
                    break;
                case 'y':
                    return jjMoveStringLiteralDfa6_0(active02, 65536L);
            }
            return jjStartNfa_0(4, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(4, active02);
            return 5;
        }
    }

    private int jjMoveStringLiteralDfa6_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(4, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'a':
                    return jjMoveStringLiteralDfa7_0(active02, 8589942784L);
                case 'e':
                    if ((active02 & 128) != 0) {
                        return jjStartNfaWithStates_0(6, 7, 43);
                    }
                    break;
                case 'o':
                    return jjMoveStringLiteralDfa7_0(active02, 524288L);
                case 'p':
                    return jjMoveStringLiteralDfa7_0(active02, 65536L);
                case 'r':
                    if ((active02 & RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) != 0) {
                        return jjStartNfaWithStates_0(6, 10, 43);
                    }
                    break;
                case 't':
                    if ((active02 & Http2Stream.EMIT_BUFFER_SIZE) != 0) {
                        return jjStartNfaWithStates_0(6, 14, 43);
                    }
                    if ((active02 & 32768) != 0) {
                        return jjStartNfaWithStates_0(6, 15, 43);
                    }
                    if ((active02 & 67108864) != 0) {
                        return jjStartNfaWithStates_0(6, 26, 43);
                    }
                    break;
                case 'u':
                    return jjMoveStringLiteralDfa7_0(active02, 134217728L);
            }
            return jjStartNfa_0(5, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(5, active02);
            return 6;
        }
    }

    private int jjMoveStringLiteralDfa7_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(5, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'c':
                    return jjMoveStringLiteralDfa8_0(active02, 8192L);
                case 'e':
                    return jjMoveStringLiteralDfa8_0(active02, 65536L);
                case 'l':
                    if ((active02 & 8589934592L) != 0) {
                        return jjStartNfaWithStates_0(7, 33, 43);
                    }
                    break;
                case 't':
                    return jjMoveStringLiteralDfa8_0(active02, 134217728L);
                case 'w':
                    return jjMoveStringLiteralDfa8_0(active02, 524288L);
            }
            return jjStartNfa_0(6, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(6, active02);
            return 7;
        }
    }

    private int jjMoveStringLiteralDfa8_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(6, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'e':
                    if ((active02 & 8192) != 0) {
                        return jjStartNfaWithStates_0(8, 13, 43);
                    }
                    if ((active02 & 134217728) != 0) {
                        return jjStartNfaWithStates_0(8, 27, 43);
                    }
                    return jjMoveStringLiteralDfa9_0(active02, 524288L);
                case 's':
                    if ((active02 & 65536) != 0) {
                        return jjStartNfaWithStates_0(8, 16, 43);
                    }
                    break;
            }
            return jjStartNfa_0(7, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(7, active02);
            return 8;
        }
    }

    private int jjMoveStringLiteralDfa9_0(long old0, long active0) {
        long active02 = active0 & old0;
        if (active02 == 0) {
            return jjStartNfa_0(7, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'd':
                    if ((active02 & 524288) != 0) {
                        return jjStartNfaWithStates_0(9, 19, 43);
                    }
                    break;
            }
            return jjStartNfa_0(8, active02);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(8, active02);
            return 9;
        }
    }

    private int jjStartNfaWithStates_0(int pos, int kind, int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
            return jjMoveNfa_0(state, pos + 1);
        } catch (IOException e) {
            return pos + 1;
        }
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
    private int jjMoveNfa_0(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 3192
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.internal.rngom.parse.compact.CompactSyntaxTokenManager.jjMoveNfa_0(int, int):int");
    }

    private int jjMoveStringLiteralDfa0_1() {
        return jjMoveNfa_1(1, 0);
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
    private int jjMoveNfa_1(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 913
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.internal.rngom.parse.compact.CompactSyntaxTokenManager.jjMoveNfa_1(int, int):int");
    }

    private int jjMoveStringLiteralDfa0_2() {
        return jjMoveNfa_2(1, 0);
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
    private int jjMoveNfa_2(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 714
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.internal.rngom.parse.compact.CompactSyntaxTokenManager.jjMoveNfa_2(int, int):int");
    }

    private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0:
                return (jjbitVec2[i2] & l2) != 0;
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
                return (jjbitVec4[i2] & l2) != 0;
            case 1:
                return (jjbitVec5[i2] & l2) != 0;
            case 2:
                return (jjbitVec6[i2] & l2) != 0;
            case 3:
                return (jjbitVec7[i2] & l2) != 0;
            case 4:
                return (jjbitVec8[i2] & l2) != 0;
            case 5:
                return (jjbitVec9[i2] & l2) != 0;
            case 6:
                return (jjbitVec10[i2] & l2) != 0;
            case 9:
                return (jjbitVec11[i2] & l2) != 0;
            case 10:
                return (jjbitVec12[i2] & l2) != 0;
            case 11:
                return (jjbitVec13[i2] & l2) != 0;
            case 12:
                return (jjbitVec14[i2] & l2) != 0;
            case 13:
                return (jjbitVec15[i2] & l2) != 0;
            case 14:
                return (jjbitVec16[i2] & l2) != 0;
            case 15:
                return (jjbitVec17[i2] & l2) != 0;
            case 16:
                return (jjbitVec18[i2] & l2) != 0;
            case 17:
                return (jjbitVec19[i2] & l2) != 0;
            case 30:
                return (jjbitVec20[i2] & l2) != 0;
            case 31:
                return (jjbitVec21[i2] & l2) != 0;
            case 33:
                return (jjbitVec22[i2] & l2) != 0;
            case 48:
                return (jjbitVec23[i2] & l2) != 0;
            case 49:
                return (jjbitVec24[i2] & l2) != 0;
            case 159:
                return (jjbitVec25[i2] & l2) != 0;
            case Typography.times /* 215 */:
                return (jjbitVec26[i2] & l2) != 0;
            default:
                if ((jjbitVec3[i1] & l1) != 0) {
                    return true;
                }
                return false;
        }
    }

    private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0:
                return (jjbitVec27[i2] & l2) != 0;
            case 1:
                return (jjbitVec5[i2] & l2) != 0;
            case 2:
                return (jjbitVec28[i2] & l2) != 0;
            case 3:
                return (jjbitVec29[i2] & l2) != 0;
            case 4:
                return (jjbitVec30[i2] & l2) != 0;
            case 5:
                return (jjbitVec31[i2] & l2) != 0;
            case 6:
                return (jjbitVec32[i2] & l2) != 0;
            case 9:
                return (jjbitVec33[i2] & l2) != 0;
            case 10:
                return (jjbitVec34[i2] & l2) != 0;
            case 11:
                return (jjbitVec35[i2] & l2) != 0;
            case 12:
                return (jjbitVec36[i2] & l2) != 0;
            case 13:
                return (jjbitVec37[i2] & l2) != 0;
            case 14:
                return (jjbitVec38[i2] & l2) != 0;
            case 15:
                return (jjbitVec39[i2] & l2) != 0;
            case 16:
                return (jjbitVec18[i2] & l2) != 0;
            case 17:
                return (jjbitVec19[i2] & l2) != 0;
            case 30:
                return (jjbitVec20[i2] & l2) != 0;
            case 31:
                return (jjbitVec21[i2] & l2) != 0;
            case 32:
                return (jjbitVec40[i2] & l2) != 0;
            case 33:
                return (jjbitVec22[i2] & l2) != 0;
            case 48:
                return (jjbitVec41[i2] & l2) != 0;
            case 49:
                return (jjbitVec24[i2] & l2) != 0;
            case 159:
                return (jjbitVec25[i2] & l2) != 0;
            case Typography.times /* 215 */:
                return (jjbitVec26[i2] & l2) != 0;
            default:
                if ((jjbitVec3[i1] & l1) != 0) {
                    return true;
                }
                return false;
        }
    }

    public CompactSyntaxTokenManager(JavaCharStream stream) {
        this.debugStream = System.out;
        this.jjrounds = new int[43];
        this.jjstateSet = new int[86];
        this.jjimage = new StringBuilder();
        this.image = this.jjimage;
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = stream;
    }

    public CompactSyntaxTokenManager(JavaCharStream stream, int lexState) {
        this(stream);
        SwitchTo(lexState);
    }

    public void ReInit(JavaCharStream stream) {
        this.jjnewStateCnt = 0;
        this.jjmatchedPos = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        ReInitRounds();
    }

    private void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 43;
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

    public void ReInit(JavaCharStream stream, int lexState) {
        ReInit(stream);
        SwitchTo(lexState);
    }

    public void SwitchTo(int lexState) {
        if (lexState >= 3 || lexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }

    protected Token jjFillToken() {
        String im = jjstrLiteralImages[this.jjmatchedKind];
        String curTokenImage = im == null ? this.input_stream.GetImage() : im;
        int beginLine = this.input_stream.getBeginLine();
        int beginColumn = this.input_stream.getBeginColumn();
        int endLine = this.input_stream.getEndLine();
        int endColumn = this.input_stream.getEndColumn();
        Token t = Token.newToken(this.jjmatchedKind, curTokenImage);
        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;
        return t;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x003e. Please report as an issue. */
    public Token getNextToken() {
        Token specialToken = null;
        int curPos = 0;
        while (true) {
            try {
                this.curChar = this.input_stream.BeginToken();
                this.image = this.jjimage;
                this.image.setLength(0);
                this.jjimageLen = 0;
                switch (this.curLexState) {
                    case 0:
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = jjMoveStringLiteralDfa0_0();
                        break;
                    case 1:
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = jjMoveStringLiteralDfa0_1();
                        break;
                    case 2:
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = jjMoveStringLiteralDfa0_2();
                        break;
                }
                if (this.jjmatchedKind != Integer.MAX_VALUE) {
                    if (this.jjmatchedPos + 1 < curPos) {
                        this.input_stream.backup((curPos - this.jjmatchedPos) - 1);
                    }
                    if ((jjtoToken[this.jjmatchedKind >> 6] & (1 << (this.jjmatchedKind & 63))) != 0) {
                        Token matchedToken = jjFillToken();
                        matchedToken.specialToken = specialToken;
                        if (jjnewLexState[this.jjmatchedKind] != -1) {
                            this.curLexState = jjnewLexState[this.jjmatchedKind];
                        }
                        return matchedToken;
                    }
                    if ((jjtoSpecial[this.jjmatchedKind >> 6] & (1 << (this.jjmatchedKind & 63))) != 0) {
                        Token matchedToken2 = jjFillToken();
                        if (specialToken == null) {
                            specialToken = matchedToken2;
                        } else {
                            matchedToken2.specialToken = specialToken;
                            specialToken.next = matchedToken2;
                            specialToken = matchedToken2;
                        }
                        SkipLexicalActions(matchedToken2);
                    } else {
                        SkipLexicalActions(null);
                    }
                    if (jjnewLexState[this.jjmatchedKind] != -1) {
                        this.curLexState = jjnewLexState[this.jjmatchedKind];
                    }
                } else {
                    int error_line = this.input_stream.getEndLine();
                    int error_column = this.input_stream.getEndColumn();
                    String error_after = null;
                    boolean EOFSeen = false;
                    try {
                        this.input_stream.readChar();
                        this.input_stream.backup(1);
                    } catch (IOException e) {
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
            } catch (IOException e2) {
                this.jjmatchedKind = 0;
                Token matchedToken3 = jjFillToken();
                matchedToken3.specialToken = specialToken;
                return matchedToken3;
            }
        }
    }

    void SkipLexicalActions(Token matchedToken) {
        switch (this.jjmatchedKind) {
            default:
                return;
        }
    }

    private void jjCheckNAdd(int state) {
        if (this.jjrounds[state] != this.jjround) {
            int[] iArr = this.jjstateSet;
            int i = this.jjnewStateCnt;
            this.jjnewStateCnt = i + 1;
            iArr[i] = state;
            this.jjrounds[state] = this.jjround;
        }
    }

    private void jjAddStates(int start, int end) {
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

    private void jjCheckNAddTwoStates(int state1, int state2) {
        jjCheckNAdd(state1);
        jjCheckNAdd(state2);
    }

    private void jjCheckNAddStates(int start, int end) {
        int i;
        do {
            jjCheckNAdd(jjnextStates[start]);
            i = start;
            start++;
        } while (i != end);
    }
}
