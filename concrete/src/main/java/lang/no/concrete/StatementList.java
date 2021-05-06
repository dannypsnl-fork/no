package lang.no.concrete;

import lang.no.concrete.stmt.Stmt;

import java.util.List;

public class StatementList implements Body {
    public List<Stmt> stmtList;

    public void add(Stmt s) {
        stmtList.add(s);
    }
}
