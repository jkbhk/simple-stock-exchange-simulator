package Exchange;

import java.util.HashMap;

public class CounterManager {

    public static CounterManager instance;

    private HashMap<String, Counter> m_counters = new HashMap<>();

    public CounterManager(Counter... counters) {

        instance = this;

        for (Counter c : counters) {
            m_counters.put(c.getName(), c);
        }
    }

    public HashMap<String, Counter> getCounters() {
        return this.m_counters;
    }

}
