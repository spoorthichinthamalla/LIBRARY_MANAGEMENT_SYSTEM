package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowRecord {

    private String recordId;
    private String memberId;
    private String itemId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public BorrowRecord(String recordId, String memberId, String itemId) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.itemId = itemId;
        this.issueDate = LocalDate.now();
    }

    // ✅ MATCHING METHODS (VERY IMPORTANT)
    public String getMemberId() {
        return memberId;
    }

    public String getItemId() {
        return itemId;
    }

    public boolean isClosed() {
        return returnDate != null;
    }

    public void closeRecord() {
        returnDate = LocalDate.now();
    }

    public long computePenalty() {
        if (returnDate == null) return 0;

        long days = ChronoUnit.DAYS.between(issueDate, returnDate);

        if (days <= 7) return 0;
        return (days - 7) * 10;
    }

    @Override
    public String toString() {
        return recordId + " | " + memberId + " | " + itemId +
                " | Issued: " + issueDate +
                " | Returned: " + (returnDate == null ? "Not yet" : returnDate);
    }
}