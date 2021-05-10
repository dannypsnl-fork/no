package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.concrete.expr.Expr;

import java.io.IOException;

public record VarDef(Loc loc, String name, Expr expr) implements TopStmt {
    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
