package lang.no.concrete.expr;

import lang.no.tyck.TypeChecker;

public record Int(int value) implements Expr {
    static public Int fromText(String text) {
        if (text != null) {
            return new Int(Integer.parseInt(text));
        }
        throw new NullPointerException();
    }

    @Override
    public void accept(TypeChecker tc) {
    }
}
