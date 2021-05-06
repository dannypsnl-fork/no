package lang.no.core;

public interface Type {
    static Type fromText(String name) {
        switch (name) {
            case "int": return new TInt();
            case "f32": return new TF32();
            default: throw new RuntimeException();
        }
    }
}
