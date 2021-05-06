package lang.no.cli;

import lang.no.concrete.TopStmt;
import lang.no.concrete.expr.Expr;
import lang.no.concrete.expr.FnCall;
import lang.no.concrete.expr.Str;
import lang.no.concrete.expr.Var;
import lang.no.parser.NoLexer;
import lang.no.parser.NoParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws IOException {
        var in = new ANTLRFileStream(args[0]);
        var lexer = new NoLexer(in);
        var tokens = new CommonTokenStream(lexer);
        var parser = new NoParser(tokens);
        var tops = parser.prog().tops;
        for (TopStmt stmt : tops) {
            if (stmt instanceof FnCall f) {
                System.out.print('(');
                System.out.print(((Var) f.fn).name());
                for (Expr e : f.args) {
                    System.out.print(' ');
                    System.out.print(((Str) e).value());
                }
                System.out.print(')');
            }
        }
    }
}
