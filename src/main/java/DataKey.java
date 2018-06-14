import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class DataKey {

    @AffinityKeyMapped
    String type;
    String mya;
    public DataKey(String type, String mya){


        this.type = type;
        this.mya = mya;
    }
}
