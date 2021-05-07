package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.core.Type;
import lang.no.tyck.TypeChecker;

import java.io.IOException;
import java.util.List;

public class FnDef implements TopStmt {
    public String name;
    public List<Param> params;
    public Type retType;
    public Body body;
    public FnDef(String name, List<Param> params, Type retType, Body body) {
        this.name = name;
        this.params = params;
        this.retType = retType;
        this.body = body;
    }

    @Override
    public void accept(TypeChecker tc) { tc.visit(this); }

    @Override
    public void accept(CodeGenerator c) throws IOException { c.visit(this); }
}
