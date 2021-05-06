package lang.no.parser;

import lang.no.concrete.FnDef;
import lang.no.concrete.TopStmt;
import lang.no.concrete.Using;
import lang.no.concrete.VarDef;
import lang.no.concrete.expr.Binary;
import lang.no.concrete.expr.Expr;
import lang.no.concrete.expr.Int;
import lang.no.concrete.expr.Op;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test void testProgAll() {
        var tops = parseTops(
            """
            using io;
            x := 1;
            y:=2;
            z() := 2;
            add(a: int, b: int) := a+b;
            add2(a: int, b: int): int {
                x := a;
                y := b;
                return x+y;
            }
            """
        );
        assertEquals("io", ((Using) tops.get(0)).module());
        assertEquals("x", ((VarDef) tops.get(1)).name());
        assertEquals("y", ((VarDef) tops.get(2)).name());
        assertEquals("z", ((FnDef) tops.get(3)).name());
        assertEquals("add", ((FnDef) tops.get(4)).name());
        assertEquals("add2", ((FnDef) tops.get(5)).name());
    }
    @Test void testVarDef() {
        var tops = parseTops("x := 1*2+3;");
        assertEquals("x", ((VarDef) tops.get(0)).name());
    }
    @Test void testExpr() {
        assertEquals(new Int(1), parseExpr("1"));
        assertEquals(new Binary(Op.ADD, new Int(2), new Int(3)), parseExpr("2+3"));
        assertEquals(new Binary(Op.ADD,
                new Binary(Op.MUL, new Int(1), new Int(2)),
                new Int(3)), parseExpr("1*2+3"));
    }

    Expr parseExpr(String code) {
        var in = new ANTLRInputStream(code);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        return parser.expr().e;
    }
    List<TopStmt> parseTops(String code) {
        var in = new ANTLRInputStream(code);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        return parser.prog().tops;
    }
}
