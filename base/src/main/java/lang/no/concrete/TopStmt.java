package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.concrete.stmt.Stmt;
import lang.no.tyck.TypeChecker;

import java.io.IOException;

public interface TopStmt extends Stmt {
    void accept(TypeChecker tc);
    void accept(CodeGenerator c) throws IOException;
}
