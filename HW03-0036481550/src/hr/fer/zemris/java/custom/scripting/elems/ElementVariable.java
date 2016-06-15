package hr.fer.zemris.java.custom.scripting.elems;

import hr.fer.zemris.java.custom.scripting.nodes.Node;

/**
 * {@code ElementVariable} class used to make {@link Node} objects that hold a
 * name of a variable.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class ElementVariable extends Element {

    /**
     * Name of this element
     */
    private String name;

    /**
     * Construct <code>ElementFunction</code> object.
     * 
     * @param name
     *            name of this element
     */

    public ElementVariable(String name) {
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
