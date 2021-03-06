package lang.no.concrete.expr;

import lang.no.codegen.CodeGenerator;

import java.io.IOException;
import java.util.List;

public class FnCall implements Expr {
    public Expr fn;
    public List<Expr> args;
    public FnCall(List<Expr> input) {
        this.fn = input.get(0);
        this.args = input.subList(1, input.size());
    }

    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
