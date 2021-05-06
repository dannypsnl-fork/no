package lang.no.parser;

import lang.no.concrete.FnDef;
import lang.no.concrete.Using;
import lang.no.concrete.VarDef;
import lang.no.concrete.expr.Binary;
import lang.no.concrete.expr.Expr;
import lang.no.concrete.expr.Int;
import lang.no.concrete.expr.Op;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test void testProgAll() {
        var in = new ANTLRInputStream(
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
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var prog = parser.prog().tops;
        assertEquals("io", ((Using) prog.get(0)).module());
        assertEquals("x", ((VarDef) prog.get(1)).name());
        assertEquals("y", ((VarDef) prog.get(2)).name());
        assertEquals("z", ((FnDef) prog.get(3)).name());
        assertEquals("add", ((FnDef) prog.get(4)).name());
        assertEquals("add2", ((FnDef) prog.get(5)).name());
    }
    @Test void testVarDef() {
        var in = new ANTLRInputStream("x := 1*2+3;");
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var v = (VarDef) parser.prog().tops.get(0);
        assertEquals("x", v.name());
    }
    @Test void testExpr() {
        assertEquals(new Int(1), parseExpr("1"));
        assertEquals(new Binary(Op.ADD, new Int(2), new Int(3)), parseExpr("2+3"));
        assertEquals(new Binary(Op.ADD,
                new Binary(Op.MUL, new Int(1), new Int(2)),
                new Int(3)), parseExpr("1*2+3"));
    }

    static Expr parseExpr(String code) {
        var in = new ANTLRInputStream(code);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        return parser.expr().e;
    }
}
