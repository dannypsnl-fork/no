package lang.no.tyck;

import lang.no.TestHelper;
import lang.no.concrete.VarDef;
import org.junit.jupiter.api.Test;

public class CheckTest {
    @Test
    void testDef() {
        var tops = TestHelper.parseTops("""
        x : int := 1;
        """);
        var tc = new TypeChecker();
        tc.visit((VarDef) tops.get(0));
    }
}
