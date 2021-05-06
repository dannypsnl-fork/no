package lang.no.concrete;

import java.util.List;

public abstract class Program {
    public List<TopStmt> stmtList;

    public void addStmt(TopStmt s) {
        stmtList.add(s);
    }
}
