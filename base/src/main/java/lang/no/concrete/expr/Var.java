package lang.no.concrete.expr;

import lang.no.tyck.TypeChecker;

public record Var(String name) implements Expr {
    @Override
    public void accept(TypeChecker tc) {
    }
}
