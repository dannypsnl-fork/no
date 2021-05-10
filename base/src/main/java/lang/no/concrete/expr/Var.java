package lang.no.concrete.expr;

import lang.no.codegen.CodeGenerator;

import java.io.IOException;

public record Var(String name) implements Expr {
    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
