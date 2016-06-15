package hr.fer.zemris.java.custom.scripting.lexer;

import java.util.regex.Pattern;

/**
 * Possible states of a <code>Token</code>:
 * <p>- EOF (end of line)</p>
 * <p>- KEYWORD</p>
 * <p>- VARIABLE</p>
 * <p>- FUNCTION</p>
 * <p>- TAGNAME</p>
 * <p>- OPERATOR</p>
 * <p>- INTEGER</p>
 * <p>- DOUBLE</p>
 * <p>- STRING</p>
 * <p>- START</p>
 * <p>- END</p>
 * <p>- TEXT</p>
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public enum TokenType {
    /** End of file token type. */
	EOF("")
	/** Keyword token type. */
	, KEYWORD("^(for|end|FOR|END)$")
	/** Variable token type. */
	, VARIABLE("^\\p{L}[\\p{L}\\d_]*$")
	/** Function token type. */
	, FUNCTION("^@\\p{L}[\\p{L}\\d_]*$")
	/** Tag name token type. */
	, TAGNAME("^(=|\\p{L}[\\p{L}\\d_]*)$")	
	/** Operator token type. */
	, OPERATOR("^[\\+\\-\\*/^]$")
	/** Integer constant token type. */
	, INTEGER("^[+-]?\\d+$")
	/** Double constant token type. */
	, DOUBLE("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$")
	/** String constant token type. */
	, STRING("^\\\".*\\\"$")
	/** Start of tag token type. */
	, START("^\\{\\$$")
	/** End of tag token type. */
	, END("^\\$\\}$")
	/** Text token type. */
	, TEXT("^[.\b]*$");
	
	/** Pattern used for recognition of the token type. */
	private Pattern pattern;

	/**
	 * Constructs a new {@code TokenType} with specified regular expression.
	 * 
	 * @param regEx regular expression used for recognition of the token type
	 */
	private TokenType(String regEx) {
		this.pattern = Pattern.compile(regEx);
	}

	/**
	 * Returns the pattern used for recognition of the token type.
	 * 
	 * @return the pattern used for recognition of the token type
	 */
	public Pattern getPattern() {
		return this.pattern;
	}
}
