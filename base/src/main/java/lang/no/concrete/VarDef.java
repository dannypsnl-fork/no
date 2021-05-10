package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.core.Type;
import lang.no.core.expr.Expr;
import lang.no.tyck.TypeChecker;

import java.io.IOException;

public class VarDef implements TopStmt {
    public Loc loc;
    public String name;
    public Type type;
    public Expr expr;

    public VarDef(Loc loc, String name, Type type, Expr expr) {
        this.loc = loc;
        this.name = name;
        this.type = type;
        this.expr = expr;
    }
    @Override
    public void accept(TypeChecker tc) { tc.visit(this); }

    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
