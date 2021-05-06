package lang.no.concrete.expr;

public record Binary(Op op, Expr left, Expr right) implements Expr {
}
