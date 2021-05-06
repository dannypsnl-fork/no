package lang.no.concrete.expr;

public record Binary(String op, Expr left, Expr right) implements Expr {
}
