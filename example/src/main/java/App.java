import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/api/list_processes", AwesomeApp::listProcesses);
        post("/api/create_process", AwesomeApp::createProcess);
        post("/api/reset_server", AwesomeApp::resetProcesses);
        delete("/api/process/:oid", AwesomeApp::deleteProcess);
    }
}
