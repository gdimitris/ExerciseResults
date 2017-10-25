import java.util.UUID;
import java.util.function.Supplier;

public class ProcessInfo {
    private UUID oid;

    public ProcessInfo() {
        oid = UUID.randomUUID();
    }

    public UUID getOID() {
        return oid;
    }
}
