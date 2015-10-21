package model;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import view.Data.Data;

public abstract class ThreadPool implements Callable<Data> {

	public final static ExecutorService executor = Executors.newCachedThreadPool();
	
	
	
}
