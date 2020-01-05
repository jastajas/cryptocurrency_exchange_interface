package com.example.cryptocurrency_exchange_interface.repository;

import com.example.cryptocurrency_exchange_interface.model.ExceptionLog;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ExceptionLogRepository {

    private List<ExceptionLog> exceptionLogs;

    public ExceptionLogRepository(List<ExceptionLog> exceptionLogs) {
        this.exceptionLogs = exceptionLogs;
    }

    public List<ExceptionLog> getExceptionLogs() {
        return exceptionLogs;
    }

    public void setExceptionLogs(List<ExceptionLog> exceptionLogs) {
        this.exceptionLogs = exceptionLogs;
    }

    public void addExceptionLog(ExceptionLog exceptionLog){
        this.exceptionLogs.add(exceptionLog);
    }


}
