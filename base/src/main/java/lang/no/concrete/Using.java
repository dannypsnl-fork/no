package lang.no.concrete;

import lang.no.codegen.CodeGenerator;

import java.io.IOException;

public record Using(String module) implements TopStmt {
    @Override
    public void accept(CodeGenerator c) throws IOException {
        c.visit(this);
    }
}
