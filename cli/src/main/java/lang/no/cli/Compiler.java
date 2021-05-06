package lang.no.cli;

import lang.no.parser.NoLexer;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);
        var in = new ANTLRFileStream(args[0]);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
    }
}
