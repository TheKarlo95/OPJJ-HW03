package hr.fer.zemris.java.custom.scripting.parser;

import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.lexer.Lexer;
import hr.fer.zemris.java.custom.scripting.lexer.TokenType;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.lexer.Token;

/**
 * {@code SmartScriptParser} takes {@link Token} objects from {@link Lexer} and
 * makes a parsing tree.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class SmartScriptParser {
    /** Document node of the parsed string. */
    private DocumentNode document;
    /** Lexer used to tokenize string input. */
    private Lexer lexer;
    /** Stack used in parsing process. */
    private ObjectStack stack;
    /** Current number of for-loops. */
    private int numOfFors;

    /**
     * Constructs a parser.
     * 
     * @param docBody
     *            {@code String} to be parsed by this parser
     */
    public SmartScriptParser(String docBody) {
        document = new DocumentNode();

        lexer = new Lexer(docBody);
        stack = new ObjectStack();
        numOfFors = 0;

        stack.push(document);
        parse(document);
    }

    /**
     * Starts parsing process.
     * 
     * @param node
     *            first node of parsing tree
     */
    private void parse(Node node) {
        Token token = lexer.nextToken();
        Node newFor = null;

        if (token.getType().equals(TokenType.EOF)) {
            return;
        }

        if (token != null && token.getType().equals(TokenType.TEXT)) {
            TextNode newNode = new TextNode("");
            while (token.getType().equals(TokenType.TEXT)) {
                newNode.append(" " + token.getValue());
                token = lexer.nextToken();
            }
            node.addChildNode(newNode);
        } else if (token != null && token.getType().equals(TokenType.KEYWORD) && token.getValue().equals("FOR")
                || token.getValue().equals("for")) {
            Element[] elems = new Element[4];
            int index = 0;
            do {
                token = lexer.nextToken();
                elems[index] = token.toElement();
                index++;
            } while (!token.getType().equals(TokenType.END) && index < 4);
            numOfFors++;
            ForLoopNode newNode = new ForLoopNode(elems[0], elems[1], elems[2], elems[3]);
            newFor = newNode;
            node.addChildNode(newNode);
            stack.push(node);
        } else if (token != null && token.getType().equals(TokenType.TAGNAME)) {
            Element[] array = new Element[15];
            int i = 0;

            while (true) {
                token = lexer.nextToken();
                if (token.getType().equals(TokenType.END)) {
                    break;
                }
                array[i] = token.toElement();
                i++;
            }
            EchoNode newNode = new EchoNode(array);
            node.addChildNode(newNode);
        } else if (token != null && token.getType().equals(TokenType.KEYWORD) && token.getValue().equals("END")
                || token.getValue().equals("end")) {
            lexer.nextToken();
            lexer.nextToken();

            numOfFors--;
            parse((Node) stack.pop());
        }

        // da li je bilo novih for-ova ili ne
        if (numOfFors > 0 && newFor != null) {
            parse(newFor);
        } else {
            parse(node);
        }
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(document.toString());
        treePrint(builder, document, 1);
        return builder.toString();
    }

    /**
     * Returns the parsing tree in {@code String} format.
     * 
     * @param builder
     *            <code>StringBuilder</code> used for concatenating strings
     * @param node
     *            <code>Node</code> which we print out
     * @param index
     *            used for right indentation
     * @return parsing tree in {@code String} format
     */
    private String treePrint(StringBuilder builder, Node node, int index) {
        for (Node n : node.getAllChildren()) {
            builder.append("\n" + indentation(index) + n.toString().trim());
            if (n.numberOfChildren() != 0) {
                treePrint(builder, n, index + 1);
            }
        }

        return builder.toString();
    }

    /**
     * Helper method for {@link #treePrint(StringBuilder, Node, int)}. Returns
     * {@code String} which consists of spaces so tree looks better.
     * 
     * @param num
     *            number of spaces we want
     * @return {@code String} which consists of 3 * {@code num} spaces
     */
    private String indentation(int num) {
        String str = "";

        for (int i = 0; i < num; i++) {
            str += "   ";
        }

        return str;
    }

    /**
     * Returns the document node.
     * 
     * @return the document node
     */
    public DocumentNode getDocumentNode() {
        return this.document;
    }
}
