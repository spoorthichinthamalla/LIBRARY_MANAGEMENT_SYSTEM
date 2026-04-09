package repository;

import model.BorrowRecord;
import java.util.*;

public class RecordManager {

    private Map<String, BorrowRecord> dataStore = new HashMap<>();
    private int idSeed = 1;

    public String nextId() {
        return "REC" + idSeed++;
    }

    public void saveRecord(BorrowRecord record) {
        dataStore.put(UUID.randomUUID().toString(), record);
    }

    public List<BorrowRecord> fetchAll() {
        return new ArrayList<>(dataStore.values());
    }

    public BorrowRecord findOpenRecord(String memberId, String itemId) {
        for (BorrowRecord r : dataStore.values()) {
            if (r.getMemberId().equals(memberId)
                    && r.getItemId().equals(itemId)
                    && !r.isClosed()) {
                return r;
            }
        }
        return null;
    }
}