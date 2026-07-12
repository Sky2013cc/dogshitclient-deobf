package com.sun.tools.example.debug.expr;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import com.sun.tools.javac.code.Flags;
import java.io.IOException;
import jdk.internal.dynalink.CallSiteDescriptor;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketProtocol;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;
import sun.tools.java.Scanner;

/* loaded from: target.jar:com/sun/tools/example/debug/expr/ExpressionParserTokenManager.class */
public class ExpressionParserTokenManager implements ExpressionParserConstants {
    static final long[] jjbitVec0 = {-2, -1, -1, -1};
    static final long[] jjbitVec2 = {0, 0, -1, -1};
    static final long[] jjbitVec3 = {2301339413881290750L, -16384, 4294967295L, 432345564227567616L};
    static final long[] jjbitVec4 = {0, 0, 0, -36028797027352577L};
    static final long[] jjbitVec5 = {0, -1, -1, -1};
    static final long[] jjbitVec6 = {-1, -1, WebSocketProtocol.PAYLOAD_SHORT_MAX, 0};
    static final long[] jjbitVec7 = {-1, -1, 0, 0};
    static final long[] jjbitVec8 = {70368744177663L, 0, 0, 0};
    static final int[] jjnextStates = {30, 31, 36, 37, 40, 41, 8, 49, 60, 61, 19, 20, 22, 10, 12, 45, 47, 2, 50, 51, 53, 4, 5, 8, 19, 20, 24, 22, 32, 33, 8, 40, 41, 8, 56, 57, 59, 63, 64, 66, 6, 7, 13, 14, 16, 21, 23, 25, 34, 35, 38, 39, 42, 43};
    public static final String[] jjstrLiteralImages = {"", null, null, null, null, null, null, null, null, Constants.ATTR_ABSTRACT, sun.rmi.rmic.iiop.Constants.IDL_BOOLEAN, "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", sun.rmi.rmic.iiop.Constants.IDL_DOUBLE, "else", "extends", Constants.FALSE, Constants.ATTR_FINAL, "finally", sun.rmi.rmic.iiop.Constants.IDL_FLOAT, "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", sun.rmi.rmic.iiop.Constants.IDL_INT, "native", "new", "null", "package", "private", "protected", Constants.ATTR_PUBLIC, "return", sun.rmi.rmic.iiop.Constants.IDL_SHORT, "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", Constants.TRUE, "try", sun.rmi.rmic.iiop.Constants.IDL_VOID, "volatile", "while", null, null, null, null, null, null, null, null, null, null, null, RuntimeConstants.SIG_METHOD, RuntimeConstants.SIG_ENDMETHOD, "{", "}", RuntimeConstants.SIG_ARRAY, "]", RuntimeConstants.SIG_ENDCLASS, DocLint.TAGS_SEPARATOR, sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR, "=", ">", "<", "!", "~", "?", CallSiteDescriptor.TOKEN_DELIMITER, "==", "<=", ">=", "!=", "||", "&&", "++", "--", Marker.ANY_NON_NULL_MARKER, TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, Marker.ANY_MARKER, RuntimeConstants.SIG_PACKAGE, "&", CallSiteDescriptor.OPERATOR_DELIMITER, "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>="};
    public static final String[] lexStateNames = {"DEFAULT"};
    static final long[] jjtoToken = {-8070450532247929343L, 4503599627370446L};
    static final long[] jjtoSkip = {510, 0};
    static final long[] jjtoSpecial = {448, 0};
    private ASCII_UCodeESC_CharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;

    private final int jjStopStringLiteralDfa_0(int i, long j, long j2) {
        switch (i) {
            case 0:
                if ((j2 & Http2Stream.EMIT_BUFFER_SIZE) != 0) {
                    return 4;
                }
                if ((j & 576460752303422976L) != 0) {
                    this.jjmatchedKind = 67;
                    return 28;
                }
                if ((j2 & 17600775979008L) != 0) {
                    return 49;
                }
                return -1;
            case 1:
                if ((j & 576460751226535424L) == 0) {
                    if ((j & 1076887552) != 0) {
                        return 28;
                    }
                    return -1;
                }
                if (this.jjmatchedPos != 1) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 1;
                    return 28;
                }
                return 28;
            case 2:
                if ((j & 540431627523718656L) == 0) {
                    if ((j & 36029123704913920L) != 0) {
                        return 28;
                    }
                    return -1;
                }
                if (this.jjmatchedPos != 2) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 2;
                    return 28;
                }
                return 28;
            case 3:
                if ((j & 449233150412803584L) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 3;
                    return 28;
                }
                if ((j & 91198511470653440L) != 0) {
                    return 28;
                }
                return -1;
            case 4:
                if ((j & 154071452707718656L) == 0) {
                    if ((j & 295161697705084928L) != 0) {
                        return 28;
                    }
                    return -1;
                }
                if (this.jjmatchedPos != 4) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 4;
                    return 28;
                }
                return 28;
            case 5:
                if ((j & 153693079038854656L) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 5;
                    return 28;
                }
                if ((j & 4881973363343360L) != 0) {
                    return 28;
                }
                return -1;
            case 6:
                if ((j & 153689780427948544L) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 6;
                    return 28;
                }
                if ((j & 3298610906112L) != 0) {
                    return 28;
                }
                return -1;
            case 7:
                if ((j & 9574592351830016L) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 7;
                    return 28;
                }
                if ((j & 144115188076118528L) != 0) {
                    return 28;
                }
                return -1;
            case 8:
                if ((j & 562960690839552L) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 8;
                    return 28;
                }
                if ((j & 9011631660990464L) != 0) {
                    return 28;
                }
                return -1;
            case 9:
                if ((j & Flags.LAMBDA_METHOD) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 9;
                    return 28;
                }
                if ((j & 10737418240L) != 0) {
                    return 28;
                }
                return -1;
            case 10:
                if ((j & Flags.LAMBDA_METHOD) != 0) {
                    this.jjmatchedKind = 67;
                    this.jjmatchedPos = 10;
                    return 28;
                }
                return -1;
            default:
                return -1;
        }
    }

    private final int jjStartNfa_0(int i, long j, long j2) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(i, j, j2), i + 1);
    }

    private final int jjStopAtPos(int i, int i2) {
        this.jjmatchedKind = i2;
        this.jjmatchedPos = i;
        return i + 1;
    }

    private final int jjStartNfaWithStates_0(int i, int i2, int i3) {
        this.jjmatchedKind = i2;
        this.jjmatchedPos = i;
        try {
            this.curChar = this.input_stream.readChar();
            return jjMoveNfa_0(i3, i + 1);
        } catch (IOException e) {
            return i + 1;
        }
    }

    private final int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '!':
                this.jjmatchedKind = 82;
                return jjMoveStringLiteralDfa1_0(0L, 33554432L);
            case '\"':
            case '#':
            case '$':
            case '\'':
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
            case '_':
            case '`':
            case 'h':
            case 'j':
            case 'k':
            case 'm':
            case 'o':
            case 'q':
            case 'u':
            case 'x':
            case 'y':
            case 'z':
            default:
                return jjMoveNfa_0(0, 0);
            case '%':
                this.jjmatchedKind = 101;
                return jjMoveStringLiteralDfa1_0(0L, Flags.POTENTIALLY_AMBIGUOUS);
            case '&':
                this.jjmatchedKind = 98;
                return jjMoveStringLiteralDfa1_0(0L, 35184506306560L);
            case '(':
                return jjStopAtPos(0, 70);
            case ')':
                return jjStopAtPos(0, 71);
            case '*':
                this.jjmatchedKind = 96;
                return jjMoveStringLiteralDfa1_0(0L, Flags.DEFAULT);
            case '+':
                this.jjmatchedKind = 94;
                return jjMoveStringLiteralDfa1_0(0L, 2199291691008L);
            case ',':
                return jjStopAtPos(0, 77);
            case '-':
                this.jjmatchedKind = 95;
                return jjMoveStringLiteralDfa1_0(0L, 4398583382016L);
            case '.':
                return jjStartNfaWithStates_0(0, 78, 4);
            case '/':
                this.jjmatchedKind = 97;
                return jjMoveStringLiteralDfa1_0(0L, Flags.AUXILIARY);
            case ':':
                return jjStopAtPos(0, 85);
            case ';':
                return jjStopAtPos(0, 76);
            case '<':
                this.jjmatchedKind = 81;
                return jjMoveStringLiteralDfa1_0(0L, 563224839716864L);
            case '=':
                this.jjmatchedKind = 79;
                return jjMoveStringLiteralDfa1_0(0L, 4194304L);
            case '>':
                this.jjmatchedKind = 80;
                return jjMoveStringLiteralDfa1_0(0L, 3379349004746752L);
            case '?':
                return jjStopAtPos(0, 84);
            case '[':
                return jjStopAtPos(0, 74);
            case ']':
                return jjStopAtPos(0, 75);
            case '^':
                this.jjmatchedKind = 100;
                return jjMoveStringLiteralDfa1_0(0L, Flags.THROWS);
            case 'a':
                return jjMoveStringLiteralDfa1_0(512L, 0L);
            case 'b':
                return jjMoveStringLiteralDfa1_0(7168L, 0L);
            case 'c':
                return jjMoveStringLiteralDfa1_0(516096L, 0L);
            case 'd':
                return jjMoveStringLiteralDfa1_0(3670016L, 0L);
            case 'e':
                return jjMoveStringLiteralDfa1_0(12582912L, 0L);
            case 'f':
                return jjMoveStringLiteralDfa1_0(520093696L, 0L);
            case 'g':
                return jjMoveStringLiteralDfa1_0(536870912L, 0L);
            case 'i':
                return jjMoveStringLiteralDfa1_0(67645734912L, 0L);
            case 'l':
                return jjMoveStringLiteralDfa1_0(Flags.GENERATEDCONSTR, 0L);
            case 'n':
                return jjMoveStringLiteralDfa1_0(962072674304L, 0L);
            case 'p':
                return jjMoveStringLiteralDfa1_0(16492674416640L, 0L);
            case 'r':
                return jjMoveStringLiteralDfa1_0(Flags.AUXILIARY, 0L);
            case 's':
                return jjMoveStringLiteralDfa1_0(1090715534753792L, 0L);
            case 't':
                return jjMoveStringLiteralDfa1_0(70931694131085312L, 0L);
            case 'v':
                return jjMoveStringLiteralDfa1_0(216172782113783808L, 0L);
            case 'w':
                return jjMoveStringLiteralDfa1_0(288230376151711744L, 0L);
            case '{':
                return jjStopAtPos(0, 72);
            case '|':
                this.jjmatchedKind = 99;
                return jjMoveStringLiteralDfa1_0(0L, 70368811286528L);
            case '}':
                return jjStopAtPos(0, 73);
            case '~':
                return jjStopAtPos(0, 83);
        }
    }

    private final int jjMoveStringLiteralDfa1_0(long j, long j2) {
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '&':
                    if ((j2 & 134217728) != 0) {
                        return jjStopAtPos(1, 91);
                    }
                    break;
                case '+':
                    if ((j2 & 268435456) != 0) {
                        return jjStopAtPos(1, 92);
                    }
                    break;
                case '-':
                    if ((j2 & 536870912) != 0) {
                        return jjStopAtPos(1, 93);
                    }
                    break;
                case '<':
                    if ((j2 & Flags.PROPRIETARY) != 0) {
                        this.jjmatchedKind = 102;
                        this.jjmatchedPos = 1;
                    }
                    return jjMoveStringLiteralDfa2_0(j, 0L, j2, Flags.LAMBDA_METHOD);
                case '=':
                    if ((j2 & 4194304) != 0) {
                        return jjStopAtPos(1, 86);
                    }
                    if ((j2 & 8388608) != 0) {
                        return jjStopAtPos(1, 87);
                    }
                    if ((j2 & 16777216) != 0) {
                        return jjStopAtPos(1, 88);
                    }
                    if ((j2 & 33554432) != 0) {
                        return jjStopAtPos(1, 89);
                    }
                    if ((j2 & Flags.EFFECTIVELY_FINAL) != 0) {
                        return jjStopAtPos(1, 105);
                    }
                    if ((j2 & Flags.CLASH) != 0) {
                        return jjStopAtPos(1, 106);
                    }
                    if ((j2 & Flags.DEFAULT) != 0) {
                        return jjStopAtPos(1, 107);
                    }
                    if ((j2 & Flags.AUXILIARY) != 0) {
                        return jjStopAtPos(1, 108);
                    }
                    if ((j2 & 35184372088832L) != 0) {
                        return jjStopAtPos(1, 109);
                    }
                    if ((j2 & Flags.SIGNATURE_POLYMORPHIC) != 0) {
                        return jjStopAtPos(1, 110);
                    }
                    if ((j2 & Flags.THROWS) != 0) {
                        return jjStopAtPos(1, 111);
                    }
                    if ((j2 & Flags.POTENTIALLY_AMBIGUOUS) != 0) {
                        return jjStopAtPos(1, 112);
                    }
                    break;
                case '>':
                    if ((j2 & Flags.UNION) != 0) {
                        this.jjmatchedKind = 103;
                        this.jjmatchedPos = 1;
                    }
                    return jjMoveStringLiteralDfa2_0(j, 0L, j2, 3378799232155648L);
                case 'a':
                    return jjMoveStringLiteralDfa2_0(j, 1236967383040L, j2, 0L);
                case 'b':
                    return jjMoveStringLiteralDfa2_0(j, 512L, j2, 0L);
                case 'e':
                    return jjMoveStringLiteralDfa2_0(j, 17867064475648L, j2, 0L);
                case 'f':
                    if ((j & 1073741824) != 0) {
                        return jjStartNfaWithStates_0(1, 30, 28);
                    }
                    break;
                case 'h':
                    return jjMoveStringLiteralDfa2_0(j, 296146859871731712L, j2, 0L);
                case 'i':
                    return jjMoveStringLiteralDfa2_0(j, 100663296L, j2, 0L);
                case 'l':
                    return jjMoveStringLiteralDfa2_0(j, 138477568L, j2, 0L);
                case 'm':
                    return jjMoveStringLiteralDfa2_0(j, 6442450944L, j2, 0L);
                case 'n':
                    return jjMoveStringLiteralDfa2_0(j, 60129542144L, j2, 0L);
                case 'o':
                    if ((j & 1048576) != 0) {
                        this.jjmatchedKind = 20;
                        this.jjmatchedPos = 1;
                    }
                    return jjMoveStringLiteralDfa2_0(j, 216172851641058304L, j2, 0L);
                case 'r':
                    return jjMoveStringLiteralDfa2_0(j, 63056991852955648L, j2, 0L);
                case 't':
                    return jjMoveStringLiteralDfa2_0(j, Flags.SIGNATURE_POLYMORPHIC, j2, 0L);
                case 'u':
                    return jjMoveStringLiteralDfa2_0(j, 150083337191424L, j2, 0L);
                case 'w':
                    return jjMoveStringLiteralDfa2_0(j, Flags.POTENTIALLY_AMBIGUOUS, j2, 0L);
                case 'x':
                    return jjMoveStringLiteralDfa2_0(j, 8388608L, j2, 0L);
                case 'y':
                    return jjMoveStringLiteralDfa2_0(j, 562949953425408L, j2, 0L);
                case '|':
                    if ((j2 & 67108864) != 0) {
                        return jjStopAtPos(1, 90);
                    }
                    break;
            }
            return jjStartNfa_0(0, j, j2);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(0, j, j2);
            return 1;
        }
    }

    private final int jjMoveStringLiteralDfa2_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        if ((j5 | (j4 & j3)) == 0) {
            return jjStartNfa_0(0, j, j3);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '=':
                    if ((j5 & Flags.LAMBDA_METHOD) != 0) {
                        return jjStopAtPos(2, 113);
                    }
                    if ((j5 & Flags.TYPE_TRANSLATED) != 0) {
                        return jjStopAtPos(2, 114);
                    }
                    break;
                case '>':
                    if ((j5 & Flags.OVERRIDE_BRIDGE) != 0) {
                        this.jjmatchedKind = 104;
                        this.jjmatchedPos = 2;
                    }
                    return jjMoveStringLiteralDfa3_0(j5, 0L, j5, 2251799813685248L);
                case 'a':
                    return jjMoveStringLiteralDfa3_0(j5, 9077567999016960L, j5, 0L);
                case 'b':
                    return jjMoveStringLiteralDfa3_0(j5, Flags.DEFAULT, j5, 0L);
                case 'c':
                    return jjMoveStringLiteralDfa3_0(j5, Flags.OVERRIDE_BRIDGE, j5, 0L);
                case 'e':
                    return jjMoveStringLiteralDfa3_0(j5, 2048L, j5, 0L);
                case 'f':
                    return jjMoveStringLiteralDfa3_0(j5, 524288L, j5, 0L);
                case 'i':
                    return jjMoveStringLiteralDfa3_0(j5, 361697544096448512L, j5, 0L);
                case 'l':
                    return jjMoveStringLiteralDfa3_0(j5, 144115737848446976L, j5, 0L);
                case 'n':
                    return jjMoveStringLiteralDfa3_0(j5, 563018773954560L, j5, 0L);
                case 'o':
                    return jjMoveStringLiteralDfa3_0(j5, 39582552818688L, j5, 0L);
                case 'p':
                    return jjMoveStringLiteralDfa3_0(j5, 140743930806272L, j5, 0L);
                case 'r':
                    if ((j5 & 268435456) != 0) {
                        return jjStartNfaWithStates_0(2, 28, 28);
                    }
                    return jjMoveStringLiteralDfa3_0(j5, 6755399441055744L, j5, 0L);
                case 's':
                    return jjMoveStringLiteralDfa3_0(j5, 8594137600L, j5, 0L);
                case 't':
                    if ((j5 & Flags.VARARGS) != 0) {
                        this.jjmatchedKind = 34;
                        this.jjmatchedPos = 2;
                    }
                    return jjMoveStringLiteralDfa3_0(j5, 17764530016256L, j5, 0L);
                case 'u':
                    return jjMoveStringLiteralDfa3_0(j5, 18014398511579136L, j5, 0L);
                case 'w':
                    if ((j5 & Flags.PROPRIETARY) != 0) {
                        return jjStartNfaWithStates_0(2, 38, 28);
                    }
                    break;
                case 'y':
                    if ((j5 & 36028797018963968L) != 0) {
                        return jjStartNfaWithStates_0(2, 55, 28);
                    }
                    break;
            }
            return jjStartNfa_0(1, j5, j5);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(1, j5, j5);
            return 2;
        }
    }

    private final int jjMoveStringLiteralDfa3_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        if ((j5 | (j4 & j3)) == 0) {
            return jjStartNfa_0(1, j, j3);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case '=':
                    if ((j5 & 2251799813685248L) != 0) {
                        return jjStopAtPos(3, 115);
                    }
                    break;
                case 'a':
                    return jjMoveStringLiteralDfa4_0(j5, 144115188311263232L, j5, 0L);
                case 'b':
                    return jjMoveStringLiteralDfa4_0(j5, 2097152L, j5, 0L);
                case 'c':
                    return jjMoveStringLiteralDfa4_0(j5, 562949953437696L, j5, 0L);
                case 'd':
                    if ((j5 & 72057594037927936L) != 0) {
                        return jjStartNfaWithStates_0(3, 56, 28);
                    }
                    break;
                case 'e':
                    if ((j5 & 4096) != 0) {
                        return jjStartNfaWithStates_0(3, 12, 28);
                    }
                    if ((j5 & 8192) != 0) {
                        return jjStartNfaWithStates_0(3, 13, 28);
                    }
                    if ((j5 & 4194304) != 0) {
                        return jjStartNfaWithStates_0(3, 22, 28);
                    }
                    if ((j5 & 18014398509481984L) != 0) {
                        return jjStartNfaWithStates_0(3, 54, 28);
                    }
                    return jjMoveStringLiteralDfa4_0(j5, 140771856482304L, j5, 0L);
                case 'g':
                    if ((j5 & Flags.GENERATEDCONSTR) != 0) {
                        return jjStartNfaWithStates_0(3, 36, 28);
                    }
                    break;
                case 'i':
                    return jjMoveStringLiteralDfa4_0(j5, Flags.HYPOTHETICAL, j5, 0L);
                case 'k':
                    return jjMoveStringLiteralDfa4_0(j5, Flags.OVERRIDE_BRIDGE, j5, 0L);
                case 'l':
                    if ((j5 & Flags.UNION) != 0) {
                        return jjStartNfaWithStates_0(3, 39, 28);
                    }
                    return jjMoveStringLiteralDfa4_0(j5, 288239174392218624L, j5, 0L);
                case 'n':
                    return jjMoveStringLiteralDfa4_0(j5, 9007199254740992L, j5, 0L);
                case 'o':
                    if ((j5 & 536870912) != 0) {
                        return jjStartNfaWithStates_0(3, 29, 28);
                    }
                    return jjMoveStringLiteralDfa4_0(j5, 6755403736023040L, j5, 0L);
                case 'r':
                    if ((j5 & 32768) != 0) {
                        return jjStartNfaWithStates_0(3, 15, 28);
                    }
                    return jjMoveStringLiteralDfa4_0(j5, 35184372088832L, j5, 0L);
                case 's':
                    if ((j5 & Flags.TYPE_TRANSLATED) != 0) {
                        return jjStartNfaWithStates_0(3, 50, 28);
                    }
                    return jjMoveStringLiteralDfa4_0(j5, 16973824L, j5, 0L);
                case 't':
                    return jjMoveStringLiteralDfa4_0(j5, 356250357596672L, j5, 0L);
                case 'u':
                    return jjMoveStringLiteralDfa4_0(j5, Flags.AUXILIARY, j5, 0L);
                case 'v':
                    return jjMoveStringLiteralDfa4_0(j5, Flags.EFFECTIVELY_FINAL, j5, 0L);
            }
            return jjStartNfa_0(2, j5, j5);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(2, j5, j5);
            return 3;
        }
    }

    private final int jjMoveStringLiteralDfa4_0(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        if ((j5 | (j4 & j3)) == 0) {
            return jjStartNfa_0(2, j, j3);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'a':
                    return jjMoveStringLiteralDfa5_0(j5, 3307124817920L);
                case 'c':
                    return jjMoveStringLiteralDfa5_0(j5, Flags.POTENTIALLY_AMBIGUOUS);
                case 'e':
                    if ((j5 & 16777216) != 0) {
                        return jjStartNfaWithStates_0(4, 24, 28);
                    }
                    if ((j5 & 288230376151711744L) != 0) {
                        return jjStartNfaWithStates_0(4, 58, 28);
                    }
                    return jjMoveStringLiteralDfa5_0(j5, 4400193995776L);
                case 'h':
                    if ((j5 & Http2Stream.EMIT_BUFFER_SIZE) != 0) {
                        return jjStartNfaWithStates_0(4, 14, 28);
                    }
                    return jjMoveStringLiteralDfa5_0(j5, Flags.LAMBDA_METHOD);
                case 'i':
                    return jjMoveStringLiteralDfa5_0(j5, 79164837462016L);
                case 'k':
                    if ((j5 & 2048) != 0) {
                        return jjStartNfaWithStates_0(4, 11, 28);
                    }
                    break;
                case 'l':
                    if ((j5 & 33554432) != 0) {
                        this.jjmatchedKind = 25;
                        this.jjmatchedPos = 4;
                    }
                    return jjMoveStringLiteralDfa5_0(j5, 69206016L);
                case 'n':
                    return jjMoveStringLiteralDfa5_0(j5, 8388608L);
                case 'r':
                    if ((j5 & Flags.THROWS) != 0) {
                        return jjStartNfaWithStates_0(4, 47, 28);
                    }
                    return jjMoveStringLiteralDfa5_0(j5, 17630840750592L);
                case 's':
                    if ((j5 & 65536) != 0) {
                        return jjStartNfaWithStates_0(4, 16, 28);
                    }
                    return jjMoveStringLiteralDfa5_0(j5, 9007199254740992L);
                case 't':
                    if ((j5 & 131072) != 0) {
                        return jjStartNfaWithStates_0(4, 17, 28);
                    }
                    if ((j5 & 134217728) != 0) {
                        return jjStartNfaWithStates_0(4, 27, 28);
                    }
                    if ((j5 & 35184372088832L) != 0) {
                        return jjStartNfaWithStates_0(4, 45, 28);
                    }
                    return jjMoveStringLiteralDfa5_0(j5, 144115188075855872L);
                case 'u':
                    return jjMoveStringLiteralDfa5_0(j5, 524288L);
                case 'v':
                    return jjMoveStringLiteralDfa5_0(j5, Flags.HYPOTHETICAL);
                case 'w':
                    if ((j5 & 2251799813685248L) != 0) {
                        this.jjmatchedKind = 51;
                        this.jjmatchedPos = 4;
                    }
                    return jjMoveStringLiteralDfa5_0(j5, 4503599627370496L);
            }
            return jjStartNfa_0(3, j5, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(3, j5, 0L);
            return 4;
        }
    }

    private final int jjMoveStringLiteralDfa5_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(3, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'a':
                    return jjMoveStringLiteralDfa6_0(j3, 1536L);
                case 'c':
                    if ((j3 & Flags.DEFAULT) != 0) {
                        return jjStartNfaWithStates_0(5, 43, 28);
                    }
                    if ((j3 & Flags.SIGNATURE_POLYMORPHIC) != 0) {
                        return jjStartNfaWithStates_0(5, 46, 28);
                    }
                    return jjMoveStringLiteralDfa6_0(j3, Flags.CLASH);
                case 'd':
                    return jjMoveStringLiteralDfa6_0(j3, 8388608L);
                case 'e':
                    if ((j3 & 2097152) != 0) {
                        return jjStartNfaWithStates_0(5, 21, 28);
                    }
                    if ((j3 & Flags.HYPOTHETICAL) != 0) {
                        return jjStartNfaWithStates_0(5, 37, 28);
                    }
                    break;
                case 'f':
                    return jjMoveStringLiteralDfa6_0(j3, Flags.ACYCLIC_ANN);
                case 'g':
                    return jjMoveStringLiteralDfa6_0(j3, Flags.OVERRIDE_BRIDGE);
                case 'h':
                    if ((j3 & Flags.POTENTIALLY_AMBIGUOUS) != 0) {
                        return jjStartNfaWithStates_0(5, 48, 28);
                    }
                    break;
                case 'i':
                    return jjMoveStringLiteralDfa6_0(j3, 153122387330596864L);
                case 'l':
                    return jjMoveStringLiteralDfa6_0(j3, 67633152L);
                case 'm':
                    return jjMoveStringLiteralDfa6_0(j3, Flags.BRIDGE);
                case 'n':
                    if ((j3 & Flags.AUXILIARY) != 0) {
                        return jjStartNfaWithStates_0(5, 44, 28);
                    }
                    return jjMoveStringLiteralDfa6_0(j3, 8590196736L);
                case 'r':
                    return jjMoveStringLiteralDfa6_0(j3, Flags.LAMBDA_METHOD);
                case 's':
                    if ((j3 & 4503599627370496L) != 0) {
                        return jjStartNfaWithStates_0(5, 52, 28);
                    }
                    break;
                case 't':
                    if ((j3 & Scanner.LINEINC) != 0) {
                        return jjStartNfaWithStates_0(5, 32, 28);
                    }
                    return jjMoveStringLiteralDfa6_0(j3, Flags.EFFECTIVELY_FINAL);
            }
            return jjStartNfa_0(4, j3, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(4, j3, 0L);
            return 5;
        }
    }

    private final int jjMoveStringLiteralDfa6_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(4, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'a':
                    return jjMoveStringLiteralDfa7_0(j3, Flags.ACYCLIC_ANN);
                case 'c':
                    return jjMoveStringLiteralDfa7_0(j3, 8589935104L);
                case 'e':
                    if ((j3 & Flags.OVERRIDE_BRIDGE) != 0) {
                        return jjStartNfaWithStates_0(6, 40, 28);
                    }
                    if ((j3 & Flags.EFFECTIVELY_FINAL) != 0) {
                        return jjStartNfaWithStates_0(6, 41, 28);
                    }
                    return jjMoveStringLiteralDfa7_0(j3, 9007201402224640L);
                case 'l':
                    return jjMoveStringLiteralDfa7_0(j3, 144115188075855872L);
                case 'n':
                    if ((j3 & RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) != 0) {
                        return jjStartNfaWithStates_0(6, 10, 28);
                    }
                    break;
                case 'o':
                    return jjMoveStringLiteralDfa7_0(j3, Flags.LAMBDA_METHOD);
                case 's':
                    if ((j3 & 8388608) != 0) {
                        return jjStartNfaWithStates_0(6, 23, 28);
                    }
                    break;
                case 't':
                    if ((j3 & 524288) != 0) {
                        return jjStartNfaWithStates_0(6, 19, 28);
                    }
                    return jjMoveStringLiteralDfa7_0(j3, Flags.CLASH);
                case 'u':
                    return jjMoveStringLiteralDfa7_0(j3, 262144L);
                case 'y':
                    if ((j3 & 67108864) != 0) {
                        return jjStartNfaWithStates_0(6, 26, 28);
                    }
                    break;
            }
            return jjStartNfa_0(5, j3, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(5, j3, 0L);
            return 6;
        }
    }

    private final int jjMoveStringLiteralDfa7_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(5, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'c':
                    return jjMoveStringLiteralDfa8_0(j3, Flags.ACYCLIC_ANN);
                case 'e':
                    if ((j3 & 262144) != 0) {
                        return jjStartNfaWithStates_0(7, 18, 28);
                    }
                    if ((j3 & 144115188075855872L) != 0) {
                        return jjStartNfaWithStates_0(7, 57, 28);
                    }
                    return jjMoveStringLiteralDfa8_0(j3, 4406636445696L);
                case 'n':
                    return jjMoveStringLiteralDfa8_0(j3, 9570151355645952L);
                case 't':
                    if ((j3 & 512) != 0) {
                        return jjStartNfaWithStates_0(7, 9, 28);
                    }
                    break;
            }
            return jjStartNfa_0(6, j3, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(6, j3, 0L);
            return 7;
        }
    }

    private final int jjMoveStringLiteralDfa8_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(6, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'd':
                    if ((j3 & Flags.CLASH) != 0) {
                        return jjStartNfaWithStates_0(8, 42, 28);
                    }
                    break;
                case 'e':
                    if ((j3 & Flags.ACYCLIC_ANN) != 0) {
                        return jjStartNfaWithStates_0(8, 35, 28);
                    }
                    break;
                case 'i':
                    return jjMoveStringLiteralDfa9_0(j3, Flags.LAMBDA_METHOD);
                case 'o':
                    return jjMoveStringLiteralDfa9_0(j3, 8589934592L);
                case 't':
                    if ((j3 & 9007199254740992L) != 0) {
                        return jjStartNfaWithStates_0(8, 53, 28);
                    }
                    return jjMoveStringLiteralDfa9_0(j3, Flags.BRIDGE);
            }
            return jjStartNfa_0(7, j3, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(7, j3, 0L);
            return 8;
        }
    }

    private final int jjMoveStringLiteralDfa9_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(7, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'f':
                    if ((j3 & 8589934592L) != 0) {
                        return jjStartNfaWithStates_0(9, 33, 28);
                    }
                    break;
                case 's':
                    if ((j3 & Flags.BRIDGE) != 0) {
                        return jjStartNfaWithStates_0(9, 31, 28);
                    }
                    break;
                case 'z':
                    return jjMoveStringLiteralDfa10_0(j3, Flags.LAMBDA_METHOD);
            }
            return jjStartNfa_0(8, j3, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(8, j3, 0L);
            return 9;
        }
    }

    private final int jjMoveStringLiteralDfa10_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(8, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'e':
                    return jjMoveStringLiteralDfa11_0(j3, Flags.LAMBDA_METHOD);
                default:
                    return jjStartNfa_0(9, j3, 0L);
            }
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(9, j3, 0L);
            return 10;
        }
    }

    private final int jjMoveStringLiteralDfa11_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(9, j, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
            switch (this.curChar) {
                case 'd':
                    if ((j3 & Flags.LAMBDA_METHOD) != 0) {
                        return jjStartNfaWithStates_0(11, 49, 28);
                    }
                    break;
            }
            return jjStartNfa_0(10, j3, 0L);
        } catch (IOException e) {
            jjStopStringLiteralDfa_0(10, j3, 0L);
            return 11;
        }
    }

    private final void jjCheckNAdd(int i) {
        if (this.jjrounds[i] != this.jjround) {
            int[] iArr = this.jjstateSet;
            int i2 = this.jjnewStateCnt;
            this.jjnewStateCnt = i2 + 1;
            iArr[i2] = i;
            this.jjrounds[i] = this.jjround;
        }
    }

    private final void jjAddStates(int i, int i2) {
        int i3;
        do {
            int[] iArr = this.jjstateSet;
            int i4 = this.jjnewStateCnt;
            this.jjnewStateCnt = i4 + 1;
            iArr[i4] = jjnextStates[i];
            i3 = i;
            i++;
        } while (i3 != i2);
    }

    private final void jjCheckNAddTwoStates(int i, int i2) {
        jjCheckNAdd(i);
        jjCheckNAdd(i2);
    }

    private final void jjCheckNAddStates(int i, int i2) {
        int i3;
        do {
            jjCheckNAdd(jjnextStates[i]);
            i3 = i;
            i++;
        } while (i3 != i2);
    }

    private final void jjCheckNAddStates(int i) {
        jjCheckNAdd(jjnextStates[i]);
        jjCheckNAdd(jjnextStates[i + 1]);
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
            Method dump skipped, instructions count: 3200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.example.debug.expr.ExpressionParserTokenManager.jjMoveNfa_0(int, int):int");
    }

    private static final boolean jjCanMove_0(int i, int i2, int i3, long j, long j2) {
        switch (i) {
            case 0:
                return (jjbitVec2[i3] & j2) != 0;
            default:
                if ((jjbitVec0[i2] & j) != 0) {
                    return true;
                }
                return false;
        }
    }

    private static final boolean jjCanMove_1(int i, int i2, int i3, long j, long j2) {
        switch (i) {
            case 0:
                return (jjbitVec4[i3] & j2) != 0;
            case 48:
                return (jjbitVec5[i3] & j2) != 0;
            case 49:
                return (jjbitVec6[i3] & j2) != 0;
            case 51:
                return (jjbitVec7[i3] & j2) != 0;
            case 61:
                return (jjbitVec8[i3] & j2) != 0;
            default:
                if ((jjbitVec3[i2] & j) != 0) {
                    return true;
                }
                return false;
        }
    }

    public ExpressionParserTokenManager(ASCII_UCodeESC_CharStream aSCII_UCodeESC_CharStream) {
        this.jjrounds = new int[67];
        this.jjstateSet = new int[134];
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = aSCII_UCodeESC_CharStream;
    }

    public ExpressionParserTokenManager(ASCII_UCodeESC_CharStream aSCII_UCodeESC_CharStream, int i) {
        this(aSCII_UCodeESC_CharStream);
        SwitchTo(i);
    }

    public void ReInit(ASCII_UCodeESC_CharStream aSCII_UCodeESC_CharStream) {
        this.jjnewStateCnt = 0;
        this.jjmatchedPos = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = aSCII_UCodeESC_CharStream;
        ReInitRounds();
    }

    private final void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 67;
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

    public void ReInit(ASCII_UCodeESC_CharStream aSCII_UCodeESC_CharStream, int i) {
        ReInit(aSCII_UCodeESC_CharStream);
        SwitchTo(i);
    }

    public void SwitchTo(int i) {
        if (i >= 1 || i < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + i + ". State unchanged.", 2);
        }
        this.curLexState = i;
    }

    private final Token jjFillToken() {
        Token newToken = Token.newToken(this.jjmatchedKind);
        newToken.kind = this.jjmatchedKind;
        String str = jjstrLiteralImages[this.jjmatchedKind];
        newToken.image = str == null ? this.input_stream.GetImage() : str;
        newToken.beginLine = this.input_stream.getBeginLine();
        newToken.beginColumn = this.input_stream.getBeginColumn();
        newToken.endLine = this.input_stream.getEndLine();
        newToken.endColumn = this.input_stream.getEndColumn();
        return newToken;
    }

    public final Token getNextToken() {
        Token token = null;
        while (true) {
            try {
                this.curChar = this.input_stream.BeginToken();
                while (this.curChar <= ' ' && (4294981120L & (1 << this.curChar)) != 0) {
                    try {
                        this.curChar = this.input_stream.BeginToken();
                    } catch (IOException e) {
                    }
                }
                this.jjmatchedKind = Integer.MAX_VALUE;
                this.jjmatchedPos = 0;
                int jjMoveStringLiteralDfa0_0 = jjMoveStringLiteralDfa0_0();
                if (this.jjmatchedKind != Integer.MAX_VALUE) {
                    if (this.jjmatchedPos + 1 < jjMoveStringLiteralDfa0_0) {
                        this.input_stream.backup((jjMoveStringLiteralDfa0_0 - this.jjmatchedPos) - 1);
                    }
                    if ((jjtoToken[this.jjmatchedKind >> 6] & (1 << (this.jjmatchedKind & 63))) != 0) {
                        Token jjFillToken = jjFillToken();
                        jjFillToken.specialToken = token;
                        return jjFillToken;
                    }
                    if ((jjtoSpecial[this.jjmatchedKind >> 6] & (1 << (this.jjmatchedKind & 63))) != 0) {
                        Token jjFillToken2 = jjFillToken();
                        if (token == null) {
                            token = jjFillToken2;
                        } else {
                            jjFillToken2.specialToken = token;
                            token.next = jjFillToken2;
                            token = jjFillToken2;
                        }
                    }
                } else {
                    int endLine = this.input_stream.getEndLine();
                    int endColumn = this.input_stream.getEndColumn();
                    String str = null;
                    boolean z = false;
                    try {
                        this.input_stream.readChar();
                        this.input_stream.backup(1);
                    } catch (IOException e2) {
                        z = true;
                        str = jjMoveStringLiteralDfa0_0 <= 1 ? "" : this.input_stream.GetImage();
                        if (this.curChar == '\n' || this.curChar == '\r') {
                            endLine++;
                            endColumn = 0;
                        } else {
                            endColumn++;
                        }
                    }
                    if (!z) {
                        this.input_stream.backup(1);
                        str = jjMoveStringLiteralDfa0_0 <= 1 ? "" : this.input_stream.GetImage();
                    }
                    throw new TokenMgrError(z, this.curLexState, endLine, endColumn, str, this.curChar, 0);
                }
            } catch (IOException e3) {
                this.jjmatchedKind = 0;
                Token jjFillToken3 = jjFillToken();
                jjFillToken3.specialToken = token;
                return jjFillToken3;
            }
        }
    }
}
