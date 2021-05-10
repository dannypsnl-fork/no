package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.concrete.stmt.Stmt;

import java.io.IOException;

public interface TopStmt extends Stmt {
    void accept(CodeGenerator c) throws IOException;
}
