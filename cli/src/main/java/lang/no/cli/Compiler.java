package lang.no.cli;

import lang.no.codegen.CodeGenerator;
import lang.no.concrete.TopStmt;
import lang.no.parser.NoLexer;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws IOException {
        var inputFileName = args[0];
        var in = CharStreams.fromFileName(inputFileName);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var tops = parser.prog().tops;
        var outputFile = new File(inputFileName.replace("no", "ss"));
        var codeGenerator = new CodeGenerator(new FileOutputStream(outputFile));
        for (TopStmt stmt : tops) {
            codeGenerator.visit(stmt);
        }
    }
}
