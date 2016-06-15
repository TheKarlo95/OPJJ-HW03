package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

/**
 * Node for holding values from tokens and used for parsing and creation of
 * parsing tree.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Node {

	/**
	 * Children of this node
	 */
	private ArrayIndexedCollection children;

	/**
	 * Adds a child to this node.
	 * 
	 * @param child node to be added as a child
	 */
	public void addChildNode(Node child) {
		if (child == null) {
			throw new IllegalArgumentException("You can't add null reference as child Node!");
		} else if (children == null) {
			children = new ArrayIndexedCollection();
		}

		children.add(child);
	}

	/**
	 * Returns the number of children.
	 * @return the number of children
	 */
	public int numberOfChildren() {
		if (children == null) {
			return 0;
		}

		return children.size();
	}

	/**
	 * Returns the child at given index.
	 * 
	 * @param index index of a child
	 * @return child node
	 */
	public Node getChild(int index) {
		if (children == null) {
			throw new IllegalStateException("There is no chilren for this node!");
		} else if (index >= children.size()) {
			throw new IndexOutOfBoundsException("Index should be between 0 and " + (children.size() - 1) + "!");
		}

		return (Node) children.get(index);
	}
	
	/**
	 * Returns all of the children.
	 * 
	 * @return all children
	 */
	public Node[] getAllChildren() {
		int size = numberOfChildren();
		Node[] children = new Node[size];
		
		for(int i = 0; i < size; i++) {
			children[i] = getChild(i);
		}
		
		return children;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "";
	}

}
