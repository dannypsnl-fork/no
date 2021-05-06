package lang.no.concrete;

import lang.no.tyck.TypeChecker;

public record Using(String module) implements TopStmt {
    @Override
    public void accept(TypeChecker tc) {
        tc.visit(this);
    }
}
