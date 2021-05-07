package lang.no.codegen;

import lang.no.concrete.FnDef;
import lang.no.concrete.Using;
import lang.no.concrete.VarDef;
import lang.no.core.expr.*;

import java.io.IOException;
import java.io.OutputStream;

public class CodeGenerator {
    OutputStream target;
    public CodeGenerator(OutputStream t) {
        target = t;
    }
    public void visit(FnDef fnDef) { }
    public void visit(VarDef varDef) { }
    public void visit(Using using) { }
    public void visit(FnCall f) throws IOException {
        write("(");
        visit(f.fn);
        for (Expr arg : f.args) {
            write(" ");
            visit(arg);
        }
        write(")");
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
