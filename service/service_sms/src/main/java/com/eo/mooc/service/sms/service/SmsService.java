package com.eo.mooc.service.sms.service;

import java.util.concurrent.ExecutionException;

public interface SmsService {
    void sendCode(String mobile, String checkCode) throws ExecutionException, InterruptedException;
}
