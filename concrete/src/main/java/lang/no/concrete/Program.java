package lang.no.concrete;

import java.util.List;

public abstract class Program {
    public List<Stmt> stmtList;

    public void addStmt(Stmt s) {
        stmtList.add(s);
    }
}
