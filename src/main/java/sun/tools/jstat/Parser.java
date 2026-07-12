package sun.tools.jstat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Set;

/* loaded from: target.jar:sun/tools/jstat/Parser.class */
public class Parser {
    private static final char OPENBLOCK = '{';
    private static final char CLOSEBLOCK = '}';
    private static final char DOUBLEQUOTE = '\"';
    private static final char PERCENT_CHAR = '%';
    private static final char OPENPAREN = '(';
    private static final char CLOSEPAREN = ')';
    private static final char OPERATOR_PLUS = '+';
    private static final char OPERATOR_MINUS = '-';
    private static final char OPERATOR_MULTIPLY = '*';
    private static final char OPERATOR_DIVIDE = '/';
    private static final String OPTION = "option";
    private static final String START = "option";
    private static Set<String> reservedWords;
    private StreamTokenizer st;
    private String filename;
    private Token lookahead;
    private Token previous;
    private int columnCount;
    private OptionFormat optionFormat;
    private static boolean pdebug = Boolean.getBoolean("jstat.parser.debug");
    private static boolean ldebug = Boolean.getBoolean("jstat.lex.debug");
    private static final Set scaleKeyWords = Scale.keySet();
    private static final Set alignKeyWords = Alignment.keySet();
    private static final String COLUMN = "column";
    private static final String DATA = "data";
    private static final String HEADER = "header";
    private static final String WIDTH = "width";
    private static final String FORMAT = "format";
    private static final String ALIGN = "align";
    private static final String SCALE = "scale";
    private static String[] otherKeyWords = {"option", COLUMN, DATA, HEADER, WIDTH, FORMAT, ALIGN, SCALE};
    private static char[] infixOps = {'+', '-', '*', '/'};
    private static char[] delimiters = {'{', '}', '%', '(', ')'};

    public Parser(String str) throws FileNotFoundException {
        this.filename = str;
        new BufferedReader(new FileReader(str));
    }

    public Parser(Reader reader) {
        this.st = new StreamTokenizer(reader);
        this.st.ordinaryChar(47);
        this.st.wordChars(95, 95);
        this.st.slashSlashComments(true);
        this.st.slashStarComments(true);
        reservedWords = new HashSet();
        for (int i = 0; i < otherKeyWords.length; i++) {
            reservedWords.add(otherKeyWords[i]);
        }
        for (int i2 = 0; i2 < delimiters.length; i2++) {
            this.st.ordinaryChar(delimiters[i2]);
        }
        for (int i3 = 0; i3 < infixOps.length; i3++) {
            this.st.ordinaryChar(infixOps[i3]);
        }
    }

    private void pushBack() {
        this.lookahead = this.previous;
        this.st.pushBack();
    }

    private void nextToken() throws ParserException, IOException {
        this.st.nextToken();
        this.previous = this.lookahead;
        this.lookahead = new Token(this.st.ttype, this.st.sval, this.st.nval);
        log(ldebug, "lookahead = " + this.lookahead);
    }

    private Token matchOne(Set set) throws ParserException, IOException {
        if (this.lookahead.ttype == -3 && set.contains(this.lookahead.sval)) {
            Token token = this.lookahead;
            nextToken();
            return token;
        }
        throw new SyntaxException(this.st.lineno(), set, this.lookahead);
    }

    private void match(int i, String str) throws ParserException, IOException {
        if (this.lookahead.ttype == i && this.lookahead.sval.compareTo(str) == 0) {
            nextToken();
            return;
        }
        throw new SyntaxException(this.st.lineno(), new Token(i, str), this.lookahead);
    }

    private void match(int i) throws ParserException, IOException {
        if (this.lookahead.ttype == i) {
            nextToken();
            return;
        }
        throw new SyntaxException(this.st.lineno(), new Token(i), this.lookahead);
    }

    private void match(char c) throws ParserException, IOException {
        if (this.lookahead.ttype == c) {
            nextToken();
            return;
        }
        throw new SyntaxException(this.st.lineno(), new Token(c), this.lookahead);
    }

    private void matchQuotedString() throws ParserException, IOException {
        match('\"');
    }

    private void matchNumber() throws ParserException, IOException {
        match(-2);
    }

    private void matchID() throws ParserException, IOException {
        match(-3);
    }

    private void match(String str) throws ParserException, IOException {
        match(-3, str);
    }

    private boolean isReservedWord(String str) {
        return reservedWords.contains(str);
    }

    private boolean isInfixOperator(char c) {
        for (int i = 0; i < infixOps.length; i++) {
            if (c == infixOps[i]) {
                return true;
            }
        }
        return false;
    }

    private void scaleStmt(ColumnFormat columnFormat) throws ParserException, IOException {
        match(SCALE);
        Token matchOne = matchOne(scaleKeyWords);
        columnFormat.setScale(Scale.toScale(matchOne.sval));
        log(pdebug, "Parsed: scale -> " + matchOne.sval);
    }

    private void alignStmt(ColumnFormat columnFormat) throws ParserException, IOException {
        match(ALIGN);
        Token matchOne = matchOne(alignKeyWords);
        columnFormat.setAlignment(Alignment.toAlignment(matchOne.sval));
        log(pdebug, "Parsed: align -> " + matchOne.sval);
    }

    private void headerStmt(ColumnFormat columnFormat) throws ParserException, IOException {
        match(HEADER);
        String str = this.lookahead.sval;
        matchQuotedString();
        columnFormat.setHeader(str);
        log(pdebug, "Parsed: header -> " + str);
    }

    private void widthStmt(ColumnFormat columnFormat) throws ParserException, IOException {
        match(WIDTH);
        double d = this.lookahead.nval;
        matchNumber();
        columnFormat.setWidth((int) d);
        log(pdebug, "Parsed: width -> " + d);
    }

    private void formatStmt(ColumnFormat columnFormat) throws ParserException, IOException {
        match(FORMAT);
        String str = this.lookahead.sval;
        matchQuotedString();
        columnFormat.setFormat(str);
        log(pdebug, "Parsed: format -> " + str);
    }

    private Expression primary() throws ParserException, IOException {
        Expression literal;
        switch (this.lookahead.ttype) {
            case -3:
                String str = this.lookahead.sval;
                if (isReservedWord(str)) {
                    throw new SyntaxException(this.st.lineno(), "IDENTIFIER", "Reserved Word: " + this.lookahead.sval);
                }
                matchID();
                literal = new Identifier(str);
                log(pdebug, "Parsed: ID -> " + str);
                break;
            case -2:
                double d = this.lookahead.nval;
                matchNumber();
                literal = new Literal(new Double(d));
                log(pdebug, "Parsed: number -> " + d);
                break;
            case 40:
                match('(');
                literal = expression();
                match(')');
                break;
            default:
                throw new SyntaxException(this.st.lineno(), "IDENTIFIER", this.lookahead);
        }
        log(pdebug, "Parsed: primary -> " + literal);
        return literal;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x003e, code lost:
    
        r0 = primary();
        log(sun.tools.jstat.Parser.pdebug, "Parsed: unary -> " + r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005f, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Expression unary() throws ParserException, IOException {
        Operator operator;
        Expression expression = null;
        while (true) {
            switch (this.lookahead.ttype) {
                case 43:
                    match('+');
                    operator = Operator.PLUS;
                    break;
                case 45:
                    match('-');
                    operator = Operator.MINUS;
                    break;
            }
            Operator operator2 = operator;
            Expression expression2 = new Expression();
            expression2.setOperator(operator2);
            expression2.setRight(expression);
            log(pdebug, "Parsed: unary -> " + expression2);
            expression2.setLeft(new Literal(new Double(0.0d)));
            expression = expression2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0042, code lost:
    
        log(sun.tools.jstat.Parser.pdebug, "Parsed: multExpression -> " + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Expression multExpression() throws ParserException, IOException {
        Operator operator;
        Expression unary = unary();
        while (true) {
            switch (this.lookahead.ttype) {
                case 42:
                    match('*');
                    operator = Operator.MULTIPLY;
                    break;
                case 47:
                    match('/');
                    operator = Operator.DIVIDE;
                    break;
            }
            Operator operator2 = operator;
            Expression expression = new Expression();
            expression.setOperator(operator2);
            expression.setLeft(unary);
            expression.setRight(unary());
            unary = expression;
            log(pdebug, "Parsed: multExpression -> " + unary);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0042, code lost:
    
        log(sun.tools.jstat.Parser.pdebug, "Parsed: addExpression -> " + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Expression addExpression() throws ParserException, IOException {
        Operator operator;
        Expression multExpression = multExpression();
        while (true) {
            switch (this.lookahead.ttype) {
                case 43:
                    match('+');
                    operator = Operator.PLUS;
                    break;
                case 45:
                    match('-');
                    operator = Operator.MINUS;
                    break;
            }
            Operator operator2 = operator;
            Expression expression = new Expression();
            expression.setOperator(operator2);
            expression.setLeft(multExpression);
            expression.setRight(multExpression());
            multExpression = expression;
            log(pdebug, "Parsed: addExpression -> " + multExpression);
        }
    }

    private Expression expression() throws ParserException, IOException {
        Expression addExpression = addExpression();
        log(pdebug, "Parsed: expression -> " + addExpression);
        return addExpression;
    }

    private void dataStmt(ColumnFormat columnFormat) throws ParserException, IOException {
        match(DATA);
        Expression expression = expression();
        columnFormat.setExpression(expression);
        log(pdebug, "Parsed: data -> " + expression);
    }

    private void statementList(ColumnFormat columnFormat) throws ParserException, IOException {
        while (this.lookahead.ttype == -3) {
            if (this.lookahead.sval.compareTo(DATA) == 0) {
                dataStmt(columnFormat);
            } else if (this.lookahead.sval.compareTo(HEADER) == 0) {
                headerStmt(columnFormat);
            } else if (this.lookahead.sval.compareTo(WIDTH) == 0) {
                widthStmt(columnFormat);
            } else if (this.lookahead.sval.compareTo(FORMAT) == 0) {
                formatStmt(columnFormat);
            } else if (this.lookahead.sval.compareTo(ALIGN) == 0) {
                alignStmt(columnFormat);
            } else if (this.lookahead.sval.compareTo(SCALE) == 0) {
                scaleStmt(columnFormat);
            } else {
                return;
            }
        }
    }

    private void optionList(OptionFormat optionFormat) throws ParserException, IOException {
        while (this.lookahead.ttype == -3) {
            match(COLUMN);
            match('{');
            int i = this.columnCount;
            this.columnCount = i + 1;
            ColumnFormat columnFormat = new ColumnFormat(i);
            statementList(columnFormat);
            match('}');
            columnFormat.validate();
            optionFormat.addSubFormat(columnFormat);
        }
    }

    private OptionFormat optionStmt() throws ParserException, IOException {
        match("option");
        String str = this.lookahead.sval;
        matchID();
        match('{');
        OptionFormat optionFormat = new OptionFormat(str);
        optionList(optionFormat);
        match('}');
        return optionFormat;
    }

    public OptionFormat parse(String str) throws ParserException, IOException {
        nextToken();
        while (this.lookahead.ttype != -1) {
            if (this.lookahead.ttype != -3 || this.lookahead.sval.compareTo("option") != 0) {
                nextToken();
            } else {
                match("option");
                if (this.lookahead.ttype == -3 && this.lookahead.sval.compareTo(str) == 0) {
                    pushBack();
                    return optionStmt();
                }
                nextToken();
            }
        }
        return null;
    }

    public Set<OptionFormat> parseOptions() throws ParserException, IOException {
        HashSet hashSet = new HashSet();
        nextToken();
        while (this.lookahead.ttype != -1) {
            if (this.lookahead.ttype != -3 || this.lookahead.sval.compareTo("option") != 0) {
                nextToken();
            } else {
                hashSet.add(optionStmt());
            }
        }
        return hashSet;
    }

    OptionFormat getOptionFormat() {
        return this.optionFormat;
    }

    private void log(boolean z, String str) {
        if (z) {
            System.out.println(str);
        }
    }
}
