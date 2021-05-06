package lang.no.concrete.expr;

import lang.no.tyck.TypeChecker;

public record Str(String value) implements Expr {
    @Override
    public void accept(TypeChecker tc) {}
}
