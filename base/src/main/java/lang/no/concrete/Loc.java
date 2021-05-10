package lang.no.concrete;

import org.antlr.v4.runtime.Token;

public class Loc {
    int line, column, endLine, endColumn;

    public Loc(Token token, Token endToken) {
        line = token.getLine();
        column = token.getCharPositionInLine();
        endLine = endToken.getLine();
        endColumn = endToken.getCharPositionInLine();
    }
}
