package lang.no.concrete.expr;

public enum Op {
    MUL, DIV, ADD, SUB;
    public static Op fromText(String text) {
        switch (text) {
            case "+": return Op.ADD;
            case "-": return Op.SUB;
            case "*": return Op.MUL;
            case "/": return Op.DIV;
            default:
                throw new UnsupportedOperationException();
        }
    }
}