package lang.no.conrete;

import lang.no.conrete.expr.Expr;

public record VarDef(String name, Expr expr) implements Stmt {
}
