package lang.no;

import lang.no.core.expr.Expr;
import lang.no.parser.NoLexer;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

public class TestHelper {
    public static Expr parseExpr(String code) {
        var in = new ANTLRInputStream(code);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        return parser.expr().e;
    }
    public static List<TopStmt> parseTops(String code) {
        var in = new ANTLRInputStream(code);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        return parser.prog().tops;
    }
}
