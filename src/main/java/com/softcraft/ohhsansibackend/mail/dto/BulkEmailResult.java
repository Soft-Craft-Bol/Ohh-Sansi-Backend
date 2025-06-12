package com.softcraft.ohhsansibackend.mail.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BulkEmailResult {
    private int totalEmails;
    private int successCount;
    private int failureCount;
    private List<String> errors;
    private LocalDateTime processedAt;

    public BulkEmailResult() {
        this.errors = new ArrayList<>();
        this.processedAt = LocalDateTime.now();
    }

    public int getTotalEmails() { return totalEmails; }
    public void setTotalEmails(int totalEmails) { this.totalEmails = totalEmails; }

    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }

    public int getFailureCount() { return failureCount; }
    public void setFailureCount(int failureCount) { this.failureCount = failureCount; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }

    public void incrementSuccessCount() { this.successCount++; }
    public void incrementFailureCount() { this.failureCount++; }
    public void addError(String error) { this.errors.add(error); }

    public double getSuccessRate() {
        return totalEmails > 0 ? (double) successCount / totalEmails * 100 : 0;
    }
}
