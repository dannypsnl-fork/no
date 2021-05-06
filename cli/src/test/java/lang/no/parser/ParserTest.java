package lang.no.parser;

import lang.no.concrete.expr.*;
import lang.no.concrete.*;
import org.junit.jupiter.api.Disabled;
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
        var prog = parser.prog().top();
        assertEquals("x", ((VarDef) prog.get(0).s).name());
        assertEquals("y", ((VarDef) prog.get(1).s).name());
    }
    @Test void testVarDef() {
        var in = new ANTLRInputStream("x := 1");
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var v = parser.top().def().v;
        assertEquals("x", v.name());
        assertEquals(new Int(1), v.expr());
    }
}
