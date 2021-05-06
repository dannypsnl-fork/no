package lang.no.tyck;

import lang.no.core.Type;

import java.util.HashMap;

public class TypeEnv {
    HashMap<String, Type> self; TypeEnv parent;

    Type lookup(String name) {
        if (self.get(name) != null) {
            return self.get(name);
        } else {
            return parent.lookup(name);
        }
    }

    TypeEnv() { new TypeEnv(null); }
    TypeEnv(TypeEnv parent) {
        this.parent = parent;
        this.self = new HashMap<>();
    }
}
