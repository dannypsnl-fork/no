package lang.no.tyck;

import lang.no.concrete.TopStmt;
import lang.no.concrete.VarDef;
import lang.no.parser.NoLexer;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CheckTest {
    @Test
    void testDef() {
        var tops = parseTops("""
        x : int := 1;
        """);
        var tc = new TypeChecker();
        tc.visit((VarDef) tops.get(0));
    }

    List<TopStmt> parseTops(String code) {
        var in = new ANTLRInputStream(code);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        return parser.prog().tops;
    }
}
