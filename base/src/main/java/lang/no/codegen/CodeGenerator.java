package lang.no.codegen;

import lang.no.concrete.*;
import lang.no.concrete.stmt.Return;
import lang.no.concrete.stmt.Stmt;
import lang.no.core.expr.*;

import java.io.IOException;
import java.io.OutputStream;

public class CodeGenerator {
    OutputStream target;
    public CodeGenerator(OutputStream t) {
        target = t;
    }
    public void visit(TopStmt stmt) throws IOException {
        if (stmt instanceof FnDef fnDef) {
            write("(define (");
            write(fnDef.name);
            for (Param p : fnDef.params) {
                write(" ");
                write(p.name());
            }
            write(")");
            write(fnDef.body);
            write(")");
        } else if (stmt instanceof VarDef varDef) {
            write("(define ");
            write(varDef.name);
            write(" ");
            write(varDef.expr);
            write(")");
        } else {
            visit((Stmt) stmt);
        }
    }

    private void write(Body body) throws IOException {
        if (body instanceof Block b) {
            for (Stmt s : b.stmtList()) {
                visit(s);
            }
        } else if (body instanceof Expr e) {
            visit(e);
        }
    }

    public void visit(Stmt stmt) throws IOException {
        if (stmt instanceof FnCall f) {
            write("(");
            visit(f.fn);
            for (Expr arg : f.args) {
                write(" ");
                visit(arg);
            }
            write(")");
        } else if (stmt instanceof Return r) {
            // FIXME: return needs `let/cc`
            write(r.expr());
        }
    }

    public void visit(Expr expr) throws IOException {
        if (expr instanceof Var v) {
            write(v.name());
        } else if (expr instanceof Str s) {
            write(s.value());
        } else if (expr instanceof Int i) {
            write(String.valueOf(i.value()));
        } else if (expr instanceof FnCall f) {
            visit(f);
        } else if (expr instanceof Binary b) {
            write("(");
            switch (b.op()) {
                case ADD -> write("+");
                case MUL -> write("*");
                case DIV -> write("/");
                case SUB -> write("-");
            }
            write(" ");
            visit(b.left());
            write(" ");
            visit(b.right());
            write(")");
        }
    }

    void write(String s) throws IOException {
        target.write(s.getBytes());
    }
}
