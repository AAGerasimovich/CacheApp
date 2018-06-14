import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.Collections;

public class App {
    public static void main(String[] args) {


        Ignite ignite = Ignition.start(
                new IgniteConfiguration()
                        .setUserAttributes(
                                Collections.singletonMap("svc-node", "true")
                        )
                        .setDiscoverySpi(new TcpDiscoverySpi()
                                .setIpFinder(new TcpDiscoveryVmIpFinder()
                                        .setAddresses(Collections.singletonList("127.0.0.1"))))
                        .setServiceConfiguration(
                        )
        );

        IgniteCache<DataKey, Data> cache =  ignite.getOrCreateCache("mya");


        DataKey Key1 = new DataKey("cat", "sef");
        DataKey Key2 = new DataKey("bigCat", "dsh");
        DataKey Key3 = new DataKey("smallCat", "segdd");
        DataKey Key4 = new DataKey("cat", "24");
        DataKey Key5 = new DataKey("bigCat", "23");
        DataKey Key6 = new DataKey("smallCat", "45");
        DataKey Key7 = new DataKey("smallCat", "333");

        Data c1 = new Data(Key1, "cat1");
        Data c2 = new Data(Key2, "cat2");
        Data c3 = new Data(Key3, "cat3");
        Data c4 = new Data(Key4, "cat4");
        Data c5 = new Data(Key5, "cat5");
        Data c6 = new Data(Key6, "cat6");
        Data c7 = new Data(Key7, "cat7");


        cache.put(Key1, c1);
        cache.put(Key2, c2);
        cache.put(Key3, c3);
        cache.put(Key4, c4);
        cache.put(Key5, c5);
        cache.put(Key6, c6);
        cache.put(Key7, c7);


    }



}
