package lang.no.concrete.stmt;

import lang.no.core.expr.Expr;

public record Return(Expr expr) implements Stmt {
}
