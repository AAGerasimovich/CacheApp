

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.affinity.Affinity;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.lang.IgniteRunnable;


public final class CacheAffinityExample {

    private static final String CACHE_NAME = "mya";


    private static final int KEY_CNT = 20;

    public static void main(String[] args) throws IgniteException {
        Ignite ignite = Ignition.start();
        System.out.println();
        System.out.println(">>> Cache affinity example started.");

        CacheConfiguration<DataKey, Data> cfg = new CacheConfiguration<>();

        cfg.setCacheMode(CacheMode.PARTITIONED);
        cfg.setName(CACHE_NAME);

        IgniteCache<DataKey, Data> cache = ignite.getOrCreateCache(cfg);

        DataKey Key1 = new DataKey("cat", "sef");

        DataKey Key3 = new DataKey("smallCat", "segdd");
        DataKey Key4 = new DataKey("cat", "24");

        DataKey Key6 = new DataKey("smallCat", "45");
        DataKey Key7 = new DataKey("smallCat", "333");

        Data c1 = new Data(Key1, "cat1");

        Data c3 = new Data(Key3, "cat3");
        Data c4 = new Data(Key4, "cat4");

        Data c6 = new Data(Key6, "cat6");
        Data c7 = new Data(Key7, "cat7");


        cache.put(Key1, c1);

        cache.put(Key3, c3);
        cache.put(Key4, c4);

        cache.put(Key6, c6);
        cache.put(Key7, c7);


        visitUsingAffinityRun();


    }


    private static void visitUsingAffinityRun() {
        Ignite ignite = Ignition.ignite();

        final IgniteCache<DataKey, Data> cache = ignite.cache(CACHE_NAME);

        DataKey Key1 = new DataKey("cat", "sef");
        DataKey Key2 = new DataKey("bigCat", "dsh");
        DataKey Key3 = new DataKey("smallCat", "segdd");
        DataKey Key4 = new DataKey("cat", "24");

        DataKey Key6 = new DataKey("smallCat", "45");
        DataKey Key7 = new DataKey("smallCat", "333");


        ignite.compute().affinityRun(CACHE_NAME, Key6,
                () -> {
                    long start_affinity = System.currentTimeMillis();
                    System.err.println("[key= " + Key6 + ", value=" + cache.localPeek(Key6).data + ']');
                    long end_affinity = System.currentTimeMillis();
                    long timeConsumedMillis = end_affinity - start_affinity;
                    System.err.println(timeConsumedMillis + " affinity Key6");
                });

        DataKey Key5 = new DataKey("bigCat", "23");

        ignite.compute().affinityRun(CACHE_NAME, Key5,
                () -> {
                    long start_affinity = System.currentTimeMillis();
                    System.err.println("[key= " + Key5 + ", value=" + cache.localPeek(Key5).data + ']');
                    long end_affinity = System.currentTimeMillis();
                    long timeConsumedMillis = end_affinity - start_affinity;
                    System.err.println(timeConsumedMillis + " affinity Key5");
                });

        ignite.compute().broadcast(
                () -> {
                    long start_affinity = System.currentTimeMillis();
                    System.err.println("[key= " + Key6 + ", value=" + cache.get(Key6).data + ']');
                    long end_affinity = System.currentTimeMillis();
                    long timeConsumedMillis = end_affinity - start_affinity;
                    System.err.println(timeConsumedMillis + " Key6");
                }
        );

        ignite.compute().broadcast(
                () -> {
                    long start_affinity = System.currentTimeMillis();
                    System.err.println("[key= " + Key5 + ", value=" + cache.get(Key5).data + ']');
                    long end_affinity = System.currentTimeMillis();
                    long timeConsumedMillis = end_affinity - start_affinity;
                    System.err.println(timeConsumedMillis + " Key5");
                }
        );

    }


}


