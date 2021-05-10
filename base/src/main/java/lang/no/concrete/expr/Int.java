package lang.no.concrete.expr;

import lang.no.codegen.CodeGenerator;

import java.io.IOException;

public record Int(int value) implements Expr {
    static public Int fromText(String text) {
        if (text != null) {
            return new Int(Integer.parseInt(text));
        }
        throw new NullPointerException();
    }

    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
