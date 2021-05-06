package lang.no.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.*;

public class ParserTest {
    @Test void testVarDef() {
        var in = new ANTLRInputStream("x := 1 ");
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var c = parser.top();
        assertEquals("x", c.def().ID().getText());
    }
}
