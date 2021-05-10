package lang.no.codegen;

import lang.no.TestHelper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodegenTest {
    @Test
    void testVarDef() throws IOException {
        var v = TestHelper.parseTop("a := 1;");
        var buf = new ByteArrayOutputStream();
        var c = new CodeGenerator(buf);
        c.visit(v);
        assertEquals("(define a 1)", buf.toString());
    }
    @Test
    void testFnDef() throws IOException {
        var t = TestHelper.parseTop("add(a, b) := a+b;");
        var buf = new ByteArrayOutputStream();
        var c = new CodeGenerator(buf);
        c.visit(t);
        assertEquals("(define (add a b)(+ a b))", buf.toString());
    }
}
