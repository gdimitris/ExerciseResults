/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testAppHasAGreeting() {
        App classUnderTest = new App();
    }

    @Test
    public void processHash() {
        Map<Integer, String> process_hash = new HashMap<>();
        process_hash.put(1, "Dadda aa");

        Gson gson = new Gson();
        String str = gson.toJson(process_hash);

        Type collectionType = new TypeToken<Map<Integer, String>>(){}.getType();
        Map<Integer, String> new_process = gson.fromJson(str, collectionType);

    }
}