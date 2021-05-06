package lang.no.conrete;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lang.no.conrete.expr.*;
import lang.no.conrete.*;

public class ConcreteTest {
    @Test void testProgAll() {
        assertEquals(
            new VarDef("x", new Int(1)),
            new VarDef("x", new Int(1))
        );
    }
}
