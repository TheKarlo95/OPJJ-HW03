package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * {@code Token} is lexical analyzer which takes a string and splits it into a
 * series of {@link Token} objects.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Lexer {
    /**
     * {@code char} array for input data
     */
    private char[] data;

    /**
     * Last used {@link Token}
     */
    private Token token;

    /**
     * Current index is used to remember where we stopped reading data last time
     * we used {@link #nextToken()} method.
     */
    private int currentIndex;

    /**
     * Current state of {@code Token} class
     */
    private LexerState state;

    /**
     * Indicates if EOF {@link Token} is already acquired
     */
    private boolean eofAcquired;

    /**
     * Constructs a {@code Token} class from {@code String} parameter.
     * 
     * @param text
     *            text to be split into a series of {@link Token} objects.
     */
    public Lexer(String text) {
        if (text == null) {
            throw new IllegalArgumentException("You tried to run lexic " + "analysator on null reference");
        }

        this.data = text.trim().toCharArray();
        this.token = null;
        this.currentIndex = 0;
        this.state = LexerState.BASIC;

        this.eofAcquired = false;
    }

    /**
     * Returns next {@link Token} data in data predefined with constructor.
     * {@link LexerException} can be thrown if bad input is given.
     * 
     * @return next {@link Token}
     */
    public Token nextToken() {
        String value = getNextTokenValue();

        // Iterates over whitespaces in character array
        while (this.data.length > this.currentIndex && Character.isWhitespace(data[currentIndex])) {
            this.currentIndex++;
        }

        token = makeTokenFromValue(value);

        return token;
    }

    /**
     * Reads the data array and returns the {@code String} value of a
     * {@link Token}
     *
     * @return next {@link Token} value
     */
    private String getNextTokenValue() {
        StringBuilder builder = new StringBuilder();
        boolean flag = false;

        while (data.length > currentIndex
                && !Character.isWhitespace(data[currentIndex])) {
            String nextToken = builder.toString() + data[currentIndex];
            if (flag && !this.isToken(nextToken)) {
                if (data.length > currentIndex + 1) {
                    if (!this.isToken(nextToken + data[currentIndex + 1])) {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                flag = true;
            }

            builder.append(data[currentIndex]);
            currentIndex++;

            if (data.length > currentIndex
                    && (data[currentIndex] == '#' || data[currentIndex - 1] == '#')) {
                break;
            }
        }

        return builder.toString();
    }

    /**
     * Makes {@link Token} from {@code String} value and it assigns
     * {@link TokenType} to {@link Token}.
     * 
     * @param value
     *            value of a {@link Token}
     * @return {@link Token} created from {@code String} value
     */
    private Token makeTokenFromValue(String value) {
        if (!value.equals("")) {
            if (value.length() == 0) {
                throw new LexerException();
            }

            TokenType type = getType(value);
            value = value.replaceAll("\\\\(\\d)", "$1").replaceAll("\\\\{2}", "\\\\");

            if (type.equals(TokenType.NUMBER)) {
                try {
                    return new Token(type, Long.parseLong(value));
                } catch (NumberFormatException e) {
                    throw new LexerException("Number too big!");
                }
            } else if (type.equals(TokenType.WORD)) {
                return new Token(type, value);
            } else if (type.equals(TokenType.SYMBOL)) {
                return new Token(type, value.charAt(0));
            }
        } else {
            if (!eofAcquired) {
                eofAcquired = true;

                return new Token(TokenType.EOF, null);
            } else {
                throw new LexerException("You already reached EOF in last token!");
            }
        }

        throw new LexerException();
    }

    /**
     * Returns {@link TokenType} from given value.
     * 
     * @param value
     *            value of a {@link Token}
     * @return {@link TokenType} of a {@link Token}
     */
    private TokenType getType(String value) {
        if (state.equals(LexerState.BASIC)) {
            if (value.matches("^(\\\\\\d|\\p{L})(\\\\\\\\|\\\\\\d|\\p{L})*$")) {
                return TokenType.WORD;
            } else if (value.matches("^[\\d]+$")) {
                return TokenType.NUMBER;
            } else if (value.length() == 1 && isSymbol(value)) {
                return TokenType.SYMBOL;
            } else {
                throw new LexerException();
            }
        } else {
            if (value.length() == 1 && value.equals("#")) {
                return TokenType.SYMBOL;
            } else {
                return TokenType.WORD;
            }
        }
    }

    /**
     * Checks if value of a {@link Token} is valid.
     * 
     * @param value
     *            value of a {@link Token}
     * @return {@code true} if value is valid and {@code false} otherwise
     */
    private boolean isToken(String value) {
        try {
            getType(value);
        } catch (LexerException e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if value of a {@link Token} is of type {@link TokenType#SYMBOL}
     * 
     * @param value
     *            value of a {@link Token}
     * @return {@code true} if of type {@link TokenType#SYMBOL} is valid and
     *         {@code false} otherwise
     */
    private boolean isSymbol(String value) {
        if (value == null) {
            return false;
        } else if (value.length() != 1) {
            return false;
        }
        return value.equals(".") || value.equals(".") || value.equals(",") || value.equals("/") || value.equals("=")
                || value.equals("#") || value.equals("?") || value.equals("!") || value.equals("-")
                || value.equals(";");
    }

    /**
     * Returns last found {@link Token}.
     * 
     * @return last found {@link Token}
     */
    public Token getToken() {
        return this.token;
    }

    /**
     * Sets the state of {@code Token}
     * 
     * @param state
     *            {@link LexerState} of {@code Token}
     */
    public void setState(LexerState state) {
        if (state == null) {
            throw new IllegalArgumentException("You can't change state of lexer to null!");
        }

        this.state = state;
    }

}
