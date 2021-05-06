package lang.no.concrete.stmt;

import lang.no.concrete.expr.Expr;

public record Return(Expr expr) implements Stmt {
}
