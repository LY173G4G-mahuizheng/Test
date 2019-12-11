package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    //1.承装任务的集合
    private ConcurrentLinkedQueue<Task> workerQuere = new ConcurrentLinkedQueue<>();

    //2.使用hashmap存放worker对象
    private HashMap<String,Thread> workers = new HashMap<>();

    //3.使用容器承装每个workers返回的结果
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();

    /*
     *4.构造方法
     */
    public Master(Worker worker,int workercount){

        //每一个对象worker都需要有对象master的引用
        // workerQuere用于任务的领域
        //resultMap用于任务的提交
        worker.setWorkerQuere(this.workerQuere);
        worker.setResultMap(this.resultMap);
        for (int i = 0; i < workercount; i++) {
            //key表示workes名字，value表示线程执行对象
            workers.put("任务1"+Integer.toString(i),new Thread(worker));
        }

    }

    public  void submit(Task task){
       this.workerQuere.add(task);
    }

    //需要有一个执行的方法，让所有的worker开始工作
    public  void execute(){
        for (Map.Entry<String,Thread> me : workers.entrySet()){
            me.getValue().start();
        }
    }
}
