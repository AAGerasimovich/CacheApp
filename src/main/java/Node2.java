import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.util.Collections;

public class Node2 {


    public static String CACHE_NAME = "mya";

    public static void main(String[] args) throws InterruptedException {


        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setUserAttributes(Collections.singletonMap("may", true));
        Ignite ignite = Ignition.start(cfg);

        final IgniteCache<DataKey, Data> cache = ignite.getOrCreateCache(CACHE_NAME);

        DataKey Key1 = new DataKey("cat", "sef");

        DataKey Key4 = new DataKey("cat", "24");
        DataKey Key5 = new DataKey("bigCat", "23");


        ignite.compute().affinityRun(CACHE_NAME, Key1,

                () -> System.err.println("[key= " + Key1 + ", value=" + cache.localPeek(Key1).data + ']'));
        ignite.compute().affinityRun(CACHE_NAME, Key1,
                () -> System.err.println("[key= " + Key4 + ", value=" + cache.localPeek(Key4).data + ']'));



    }
}