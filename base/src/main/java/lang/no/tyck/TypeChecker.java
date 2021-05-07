package lang.no.tyck;

import lang.no.concrete.*;
import lang.no.core.expr.Expr;
import lang.no.core.expr.Int;
import lang.no.core.expr.Var;
import lang.no.concrete.stmt.Return;
import lang.no.concrete.stmt.Stmt;
import lang.no.core.Type;

public class TypeChecker {
    // TODO: error collector
    public TypeEnv topEnv = new TypeEnv();

    public void visit(VarDef v) {
        check(topEnv, null, v);
    }
    public void visit(FnDef f) {
        if (f.body() instanceof Expr e) {
            typeMustEq(f.retType(), infer(e));
        } else {
            var fnTypeEnv = new TypeEnv(topEnv);
            for (Param p : f.params()) {
                fnTypeEnv.bind(p.name(), p.type());
            }
            check(fnTypeEnv, f.retType(), f.body());
        }
    }
    public void visit(Using u) {
        // TODO: update current environment, but this have to wait more module details
    }

    private void typeMustEq(Type expected, Type actual) {
        assert expected.equals(actual);
    }

    public void check(TypeEnv env, Type expected, Stmt stmt) {
        if (stmt instanceof Block b) {
            for (Stmt s : b.stmtList()) {
                check(env, expected, s);
            }
        } else if (stmt instanceof VarDef v) {
            var typeOfExpr = infer(env, v.expr);
            if (v.type != null) {
                typeMustEq(v.type, typeOfExpr);
            } else {
                v.type = typeOfExpr;
            }
        } else if (stmt instanceof Return r) {
            typeMustEq(expected, infer(env, r.expr()));
        }
    }
    public Type infer(Expr expr) {
        return infer(topEnv, expr);
    }
    public Type infer(TypeEnv env, Expr expr) {
        if (expr instanceof Int) {
            return Type.INT;
        } else if (expr instanceof Var v) {
            return env.lookup(v.name());
        } else {
            return null;
        }
    }
}
