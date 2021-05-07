package lang.no.core.expr;

import lang.no.codegen.CodeGenerator;
import lang.no.tyck.TypeChecker;

import java.io.IOException;

public record Str(String value) implements Expr {
    @Override
    public void accept(TypeChecker tc) {}

    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
