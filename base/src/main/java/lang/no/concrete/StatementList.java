package lang.no.concrete;

import lang.no.concrete.stmt.Stmt;

import java.util.List;

public record StatementList(List<Stmt> stmtList) implements Body, Stmt {
    public void add(Stmt s) {
        stmtList.add(s);
    }
}
