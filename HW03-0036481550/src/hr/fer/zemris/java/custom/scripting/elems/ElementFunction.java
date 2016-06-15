package hr.fer.zemris.java.custom.scripting.elems;

import hr.fer.zemris.java.custom.scripting.nodes.Node;

/**
 * {@code ElementFunction} class used to make {@link Node} objects that hold a
 * name of a function.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class ElementFunction extends Element {

    /**
     * Name of this element
     */
    private String name;

    /**
     * Construct {@code ElementFunction} object.
     * 
     * @param name
     *            name of this element
     */
    public ElementFunction(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asText() {
        return this.name;
    }

}