package lang.no.concrete;

import lang.no.concrete.stmt.Stmt;
import lang.no.core.Type;
import lang.no.core.expr.Expr;
import lang.no.tyck.TypeChecker;

public class VarDef implements TopStmt, Stmt {
    public String name;
    public Type type;
    public Expr expr;

    public VarDef(String name, Type type, Expr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }
    @Override
    public void accept(TypeChecker tc) { tc.visit(this); }
}
