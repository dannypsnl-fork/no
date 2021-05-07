package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.tyck.TypeChecker;

public record Using(String module) implements TopStmt {
    @Override
    public void accept(TypeChecker tc) {
        tc.visit(this);
    }

    @Override
    public void accept(CodeGenerator c) {
        c.visit(this);
    }
}
