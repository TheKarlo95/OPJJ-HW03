package hr.fer.zemris.java.custom.scripting.parser;

import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class SmartScriptParserTest {

	@Ignore
	@Test
	public void testParse() {
		SmartScriptParser parser = new SmartScriptParser("-123 - dasd @fsd \"dasda\"");
		System.out.println(parser);
	}
	
	@Test
	public void testIzZadace() {
		String str = "This is sample text."
				+ "\n{$ FOR i 1 10 1 $}"
				+ "\n\tThis is {$= i $}-th time this message is generated."
				+ "\n{$END$}"
				+ "\n{$FOR i 0 10 2 $}"
				+ "\n\tsin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}"
				+ "\n{$END$}";
		
		SmartScriptParser parser = new SmartScriptParser(str);
		
		System.out.println(parser);
	}
}