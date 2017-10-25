import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class TestableRequest extends Request {
    private String body;
    private Map<String,String> params;

    @Override
    public String body() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setParam(Map<String,String> params) {
        this.params = params;
    }

    @Override
    public Map<String,String> params() {
        return params;
    }

}

class TestableResponse extends Response {

}

public class AwesomeAppTest {
    private Gson gson = new Gson();
    private TestableRequest req;
    private TestableResponse res;

    @Before
    public void setUp() {
        req = new TestableRequest();
        res = new TestableResponse();
        AwesomeApp.resetProcesses(req, res);
    }

    @Test
    public void itBeginsWithNoProcesses() {
        String processesJSON = AwesomeApp.listProcesses(req, res);

        Map<String, ProcessInfo> processes = deserializeProcesses(processesJSON);

        assertTrue(processes.isEmpty());
    }

    @Test
    public void itCanCreateAProcess() {
        AwesomeApp.createProcess(req, res);
        String processesJSON = AwesomeApp.listProcesses(req, res);

        Map<String, ProcessInfo> processes = deserializeProcesses(processesJSON);

        assertFalse("There should be processes", processes.isEmpty());
    }

    @Test
    public void itCanCreateAProcessWithResponse() {
        String body = "{}";
        req.setBody(body);

        String result = AwesomeApp.createProcess(req, res);

        CreateResponse res = gson.fromJson(result, CreateResponse.class);
        assertFalse(res.getId().isEmpty());
    }

    private Map<String, ProcessInfo> deserializeProcesses(String processesJSON) {
        Type collectionType = new TypeToken<Map<String, ProcessInfo>>() {}.getType();
        return gson.fromJson(processesJSON, collectionType);
    }

    @Test
    public void testDeleteProcess(){
        String result = AwesomeApp.createProcess(req, res);

        CreateResponse res = gson.fromJson(result, CreateResponse.class);
        String oid = res.getId();

        Map<String,String> params = new HashMap<>();
        params.put(":oid",oid);

        req.setParam(params);

        String deleteResponse = AwesomeApp.deleteProcess(this.req, this.res);

        assertEquals("successfully deleted", deleteResponse );
        String processesJSON = AwesomeApp.listProcesses(req, this.res);
        Map<String, ProcessInfo> processes = deserializeProcesses(processesJSON);
        assertTrue("should be empty", processes.isEmpty());
    }
    @Test
    public void delteProcessNotExist() {
        Map<String,String> params = new HashMap<>();

        params.put(":oid",UUID.randomUUID().toString());

        req.setParam(params);

        String deleteResponse = AwesomeApp.deleteProcess(this.req, this.res);

        assertEquals("could not find id", deleteResponse );
        String processesJSON = AwesomeApp.listProcesses(req, this.res);
    }

    @Test
    public void itCanResetProcesses() {
        AwesomeApp.createProcess(req, res);
        AwesomeApp.resetProcesses(req, res);
        String processesJSON = AwesomeApp.listProcesses(req, res);
        Map<String, ProcessInfo> processes = deserializeProcesses(processesJSON);
        assertTrue("should be empty", processes.isEmpty());
    }

}
