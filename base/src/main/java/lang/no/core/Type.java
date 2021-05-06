package lang.no.core;

public interface Type {
    final Type INT = new TInt();
    final Type F32 = new TF32();
    static Type fromText(String name) {
        switch (name) {
            case "int": return INT;
            case "f32": return F32;
            default: throw new RuntimeException();
        }
    }
}
