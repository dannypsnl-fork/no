package lang.no.concrete;

import lang.no.codegen.CodeGenerator;

import java.io.IOException;
import java.util.List;

public record FnDef(String name, List<Param> params, Body body) implements TopStmt {
    @Override
    public void accept(CodeGenerator c) throws IOException { c.visit(this); }
}
