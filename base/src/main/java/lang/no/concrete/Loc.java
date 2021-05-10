package lang.no.concrete;

import org.antlr.v4.runtime.Token;

public class Loc {
    private int line = 1, column = 0;

    public Loc(Token token) {
        line = token.getLine();
        column = token.getCharPositionInLine();
    }

    public int getLine() { return line; }
    public int getColumn() { return column; }
}
