package lang.no.concrete;

import lang.no.codegen.CodeGenerator;
import lang.no.tyck.TypeChecker;

import java.io.IOException;

public interface TopStmt {
    void accept(TypeChecker tc);
    void accept(CodeGenerator c) throws IOException;
}
