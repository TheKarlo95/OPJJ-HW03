package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;

/**
 * Node for holding values for tags.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class EchoNode extends Node {

	/**
	 * Elements of this node
	 */
	private Element[] element;

	/**
	 * Constructs a <code>EchoNode</code> object.
	 * 
	 * @param element elements to be inserted in this node
	 */
	public EchoNode(Element[] element) {
		this.element = element;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(Element e : element) {
			if(e == null) {
				break;
			}
			builder.append(e.asText());
		}
		
		return builder.toString();
	}
}
