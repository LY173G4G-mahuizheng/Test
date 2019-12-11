package com.example.demo.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

    private ConcurrentLinkedQueue<Task> workerQuere;
    private ConcurrentHashMap<String ,Object> resultMap;

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void setWorkerQuere(ConcurrentLinkedQueue<Task> workerQuere) {
        this.workerQuere = workerQuere;
    }

    @Override
    public void run() {
        while (true){
            Task input = this.workerQuere.poll();
            if (input == null){
                break;
            }
            //真正的去做业务处理
            Object output =  handle(input);

            this.resultMap.put(Integer.toString(input.getId()),output);
        }
    }

    private Object handle(Task input) {

        Object output = null;
        try {
            Thread.sleep(500);
            output = input.getResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }

}
