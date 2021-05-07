package lang.no.concrete;

import lang.no.core.expr.Expr;
import lang.no.concrete.stmt.Stmt;
import lang.no.core.Type;
import lang.no.tyck.TypeChecker;

public record VarDef(String name, Type type, Expr expr) implements TopStmt, Stmt {
    @Override
    public void accept(TypeChecker tc) { tc.visit(this); }
}
