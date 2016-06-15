package hr.fer.zemris.java.custom.scripting.elems;

import hr.fer.zemris.java.custom.scripting.nodes.Node;

/**
 * {@code ElementConstantInteger} class used to make {@link Node} objects that
 * hold integer value.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class ElementConstantInteger extends Element {

    /**
     * Value of this element.
     */
    private int value;

    /**
     * Construct {@code ElementConstantInteger} object.
     * 
     * @param value
     *            value of this element
     */
    public ElementConstantInteger(int value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asText() {
        return Integer.toString(this.value);
    }

}
