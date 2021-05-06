package lang.no.concrete;

import lang.no.concrete.expr.Expr;

public record VarDef(String name, Expr expr) implements Stmt {
}
