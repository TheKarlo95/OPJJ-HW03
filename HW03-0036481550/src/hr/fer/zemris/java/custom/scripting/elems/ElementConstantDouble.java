package hr.fer.zemris.java.custom.scripting.elems;

import hr.fer.zemris.java.custom.scripting.nodes.Node;

/**
 * {@code ElementConstantDouble} class used to make {@link Node} objects that
 * hold double value.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class ElementConstantDouble extends Element {

    /**
     * Value of this element.
     */
    private double value;

    /**
     * Construct {@code ElementConstantDouble} object.
     * 
     * @param value
     *            value of this element
     */
    public ElementConstantDouble(double value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asText() {
        return Double.toString(this.value);
    }

}
