package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;

/**
 * Node for holding values for for loops.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class ForLoopNode extends Node {

	/**
	 * Variable of a for loop.
	 */
	private ElementVariable variable;
	
	/**
	 * Start expression of a for loop.
	 */
	private Element startExpression;
	
	/**
	 * End expression of a for loop.
	 */
	private Element endExpression;
	
	/**
	 * Step expression of a for loop.(can be null)
	 */
	private Element stepExpression;
	
	/**
	 * Constructs a <code>ForLoopNode</code> object.
	 * 
	 * @param elems variable for a for loop
	 * @param startExpression start expression of a for loop
	 * @param endExpression end expression of a for loop
	 * @param stepExpression step expression of a for loop.(can be null)
	 */
	public ForLoopNode(Element elems, Element startExpression, Element endExpression,
			Element stepExpression) {
		if(elems == null || startExpression == null || endExpression == null) {
			throw new IllegalArgumentException();
		}
		
		this.variable = (ElementVariable) elems;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(variable.asText() + " ");
		builder.append(startExpression.asText() + " ");
		builder.append(endExpression.asText() + " ");
		
		if(stepExpression != null) {
			builder.append(stepExpression.asText());
		}
		
		return builder.toString();
	}

}
