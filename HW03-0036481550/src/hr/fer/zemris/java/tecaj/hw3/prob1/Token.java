package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * <code>Token</code> is class which is holding value and it's type. It is used
 * in <code>Lexer</code> to split <code>String</code> in <code>Token</code>
 * objects.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 *
 */
public class Token {

	/**
	 * Type of a token.
	 */
	private TokenType type;
	
	/**
	 * Value that token holds.
	 */
	private Object value;

	/**
	 * Constructs a <code>Token</code> object.
	 * 
	 * @param type type of a token
	 * @param value value that token holds
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * Returns the value of token.
	 * 
	 * @return value of token
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Returns the type of token.
	 * 
	 * @return type of token
	 */
	public TokenType getType() {
		return this.type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "(" + this.type + ", " + this.value + ")";
	}
}
