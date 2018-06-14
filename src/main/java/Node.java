


import org.apache.ignite.Ignite;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;


import java.util.Collections;

public class Node {


    public static String CACHE_NAME = "mya";

    public static void main(String[] args)  {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setUserAttributes(Collections.singletonMap("may", true));
        Ignite ignite = Ignition.start(cfg);

        final IgniteCache<DataKey, Data> cache = ignite.getOrCreateCache(CACHE_NAME);

        DataKey Key5 = new DataKey("bigCat", "23");



        Data c5 = new Data(Key5, "cat5");

        cache.put(Key5, c5);




        ignite.compute().affinityRun(CACHE_NAME, Key5,
                () -> System.err.println("[key= " + Key5 + ", value=" + cache.localPeek(Key5).data + ']'));

    }

}