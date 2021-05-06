package lang.no.concrete;

import lang.no.core.Type;
import lang.no.tyck.TypeChecker;

import java.util.List;

public record FnDef(String name, List<Param> params, Type retType, Body body) implements TopStmt {
    @Override
    public void accept(TypeChecker tc) { tc.visit(this); }
}
