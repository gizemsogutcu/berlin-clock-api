package com.api.berlinclock.service;

import com.api.berlinclock.core.APIResponse;

public interface BerlinClockService {
    APIResponse getBerlinClock(String time) throws Exception;
}
