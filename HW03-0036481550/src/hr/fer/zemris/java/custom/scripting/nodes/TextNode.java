package hr.fer.zemris.java.custom.scripting.nodes;

/**
 * Node for holding values from text.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class TextNode extends Node {

	/**
	 * Text which this node holds.
	 */
	private String text;

	/**
	 * Constructs a <code>TextNode</code> obejct
	 * 
	 * @param text text which this node holds
	 */
	public TextNode(String text) {
		if(text == null) {
			throw new IllegalArgumentException();
		}
		
		this.text = text;
	}
	
	/**
	 * Appends given text to existing one
	 * 
	 * @param text text to be appended
	 */
	public void append(String text) {
		if(text == null) {
			throw new IllegalArgumentException();
		}
		
		this.text += text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return text;
	}

}
