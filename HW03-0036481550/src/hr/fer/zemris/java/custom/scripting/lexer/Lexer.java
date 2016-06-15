package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * <code>Lexer</code> is lexical analyzer which takes a string and splits it
 * into a series of <code>Token</code> objects.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Lexer {

    /**
     * String constant used for indicating start of a tag
     */
    private static final String START = "{$";

    /**
     * String constant used for indicating end of a tag.
     */
    private static final String END = "$}";

    /**
     * <code>char</code> array for input data
     */
    private char[] data;

    /**
     * Last used <code>Token</code>
     */
    private Token token;

    /**
     * Current index is used to remember where we stopped reading data last time
     * we used <code>nexToken()</code> method.
     */
    private int currentIndex;

    /**
     * Current state of <code>Lexer</code> class
     */
    private LexerState state;

    /**
     * Constructs a <code>Lexer</code> class from <code>String</code> parameter.
     * 
     * @param text
     *            text to be split into a series of <code>Token</code> objects.
     */
    public Lexer(String text) {
        if (text == null) {
            throw new IllegalArgumentException("You tried to run lexical " + "analyzer on null reference");
        }

        this.data = text.trim().toCharArray();
        this.token = null;
        this.currentIndex = 0;

        if (text.trim().startsWith(START)) {
            this.state = LexerState.TAG;
            this.currentIndex = 2;
        } else {
            this.state = LexerState.TEXT;
        }
    }

    /**
     * Returns last found <code>Token</code>.
     * 
     * @return last found <code>Token</code>
     */
    public Token getToken() {
        return this.token;
    }

    /**
     * Returns next <code>Token</code> data in data predefined with constructor.
     * <code>LexerException</code> can be thrown if bad input is given.
     * 
     * @return next <code>Token</code>
     */
    public Token nextToken() {
        String value = "";
        boolean flag = false;

        if (currentIndex >= data.length) {
            return new Token(TokenType.EOF, null);
        }
        skipWhitespaces();
        do {
            value += data[currentIndex];
            currentIndex++;

            if (value.startsWith(START)) {
                state = LexerState.TAG;
            } else if (value.endsWith(END)) {
                flag = true;
            }
        } while (canProceed(value));

        skipWhitespaces();

        value = adjustValue(value);
        this.token = new Token(getTokenType(value), value);

        if (flag) {
            state = LexerState.TEXT;
        }

        return this.token;
    }

    /**
     * Returns current state of <code>Lexer</code> class.
     * 
     * @return current state of <code>Lexer</code> class
     */
    public LexerState getState() {
        return this.state;
    }

    /**
     * Removes unnecessary backslashes
     * 
     * @param value
     *            <code>String</code> value of a token
     * @return adjusted <code>String</code> value of a token
     */
    private String adjustValue(String value) {
        if (state.equals(LexerState.TAG)) {
            value.replace("\\\\", "\\");
            value.replace("\\n", "\n");
        }
        return value;
    }

    /**
     * Tells <code>Lexer</code> if it can proceed with its work
     * 
     * @param value
     *            <code>String</code> value of a token
     * @return <code>true</code> if it can proceed and <code>false</code>
     *         otherwise
     */
    private boolean canProceed(String value) {
        if (currentIndex >= data.length) {
            return false;
        } else if (value.trim().equals("")) {
            return true;
        } else if (data[currentIndex] == '{') {
            return false;
        } else if (Character.isWhitespace(data[currentIndex])) {
            return false;
        } else if (!isValidToken(value)) {
            return true;
        } else if (isValidToken(value) && !isValidToken(value + data[currentIndex])) {
            return false;
        } else if (isValidToken(value) && isValidToken(value + data[currentIndex])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if value of a <code>Token</code> is valid.
     * 
     * @param value
     *            value of a <code>Token</code>
     * @return <code>true</code> if value is valid and <code>false</code>
     *         otherwise
     */
    private boolean isValidToken(String value) {
        if (value.startsWith("\\\"")) {
            return true;
        }

        try {
            getTokenType(value);
        } catch (LexerException e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if value of a <code>Token</code> is valid.
     * 
     * @param str
     *            value of a <code>Token</code>
     * @return <code>TokenType</code> of a <code>Token</code>
     */
    public TokenType getTokenType(String str) {
        if (state.equals(LexerState.TEXT)) {
            return TokenType.TEXT;
        }

        for (TokenType type : TokenType.values()) {
            if (type.getPattern().matcher(str).matches()
                    && !type.equals(TokenType.TEXT)) {
                return type;
            }
        }

        throw new LexerException("Invalid token value!");
    }

    /**
     * Skips whitespaces in data array
     */
    private void skipWhitespaces() {
        while (currentIndex < data.length && Character.isWhitespace(data[currentIndex])) {
            currentIndex++;
        }
    }

}
