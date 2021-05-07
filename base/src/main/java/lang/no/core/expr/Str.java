package lang.no.core.expr;

import lang.no.tyck.TypeChecker;

public record Str(String value) implements Expr {
    @Override
    public void accept(TypeChecker tc) {}
}
