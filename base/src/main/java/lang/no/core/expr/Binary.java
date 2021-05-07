package lang.no.core.expr;

import lang.no.tyck.TypeChecker;

public record Binary(Op op, Expr left, Expr right) implements Expr {
    @Override
    public void accept(TypeChecker tc) {
    }
}
