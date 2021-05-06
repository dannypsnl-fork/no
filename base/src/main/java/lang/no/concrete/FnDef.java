package lang.no.concrete;

import java.util.List;

public record FnDef(String name, List<Param> params, Body body) implements TopStmt {
}
