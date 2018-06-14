import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.util.Collections;

public class Node3 {
    public static String CACHE_NAME = "mya";

    public static void main(String[] args)  {


        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setUserAttributes(Collections.singletonMap("may",true));
    Ignite ignite = Ignition.start(cfg);

    final IgniteCache<DataKey, Data> cache = ignite.getOrCreateCache(CACHE_NAME);
}

}
