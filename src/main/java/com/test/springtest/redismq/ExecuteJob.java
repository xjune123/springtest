package com.test.springtest.redismq;

import com.test.springtest.redismq.dto.DelayJobDto;

public interface ExecuteJob {

     void execute(DelayJobDto job);
}