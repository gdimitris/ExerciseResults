import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AwesomeApp {
    private static Gson g = new Gson();
    public static Map<UUID, ProcessInfo> processes = new HashMap<>();

    public static String listProcesses(Request req, Response res) {
        return g.toJson(processes);
    }

    public static String createProcess(Request req, Response res) {
        ProcessInfo process = new ProcessInfo();
        processes.put(process.getOID(), process);

        CreateResponse response = new CreateResponse(process.getOID().toString());
        return g.toJson(response);
    }

    public static String deleteProcess(Request req, Response res) {
        String id = req.params().get(":oid");
        UUID uuid = UUID.fromString(id);
        if ( !processes.containsKey( uuid) ) {
            return "could not find id";
        } else {
            processes.remove(uuid);
        }

        return "successfully deleted";
    }

    public static String resetProcesses(Request req, Response res) {
        processes = new HashMap<>();
        return "";
    }
}
