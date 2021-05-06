package lang.no.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.*;

public class ParserTest {
    @Test void testProgAll() {
        var in = new ANTLRInputStream(
                "x := 1 " +
                "   y:=2"
        );
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var c = parser.prog();
        var tops = c.top();
        assertEquals("x", tops.get(0).def().ID().getText());
        assertEquals("y", tops.get(1).def().ID().getText());
    }
    @Test void testVarDef() {
        var in = new ANTLRInputStream("x := 1");
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var c = parser.top();
        assertEquals("x", c.def().ID().getText());
    }
}
