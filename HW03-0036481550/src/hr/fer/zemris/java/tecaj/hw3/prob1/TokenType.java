package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * Possible states of a {@link Token}: <br>
 * - EOF (end of line) <br>
 * - WORD <br>
 * - NUMBER (only {@code long} type) <br>
 * - SYMBOL
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public enum TokenType {
    /** End of file token type. */
    EOF,
    /** Word token type. */
    WORD,
    /** Number token type. */
    NUMBER,
    /** Symbol token type. */
    SYMBOL;
}
