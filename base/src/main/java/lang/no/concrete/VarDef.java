package lang.no.concrete;

import lang.no.concrete.expr.Expr;
import lang.no.core.Type;

public record VarDef(String name, Type type, Expr expr) implements TopStmt {
}
