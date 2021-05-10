package lang.no.concrete;

import org.antlr.v4.runtime.Token;

public class Loc {
    private int line = 1, column = 0, endLine = 1, endColumn = 0;

    public Loc(Token token, Token endToken) {
        line = token.getLine();
        column = token.getCharPositionInLine();
        endLine = endToken.getLine();
        endColumn = endToken.getCharPositionInLine();
    }

    public int getLine() { return line; }
    public int getColumn() { return column; }
    public int getEndLine() { return endLine; }
    public int getEndColumn() { return endColumn; }
}
