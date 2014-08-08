package com.sinius15.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MtTest {
	
	public static void main(String[] args) throws Throwable {
		final ExecutorService es = Executors.newCachedThreadPool();
		final CompletionService<Integer> cs = new ExecutorCompletionService<>(es);
		
		submitSomeCalculations(cs);
		while (true) {
			Thread.sleep(500);
			processFinishedCalculations(cs);
		}
	}
	
	private static void submitSomeCalculations(final CompletionService<Integer> cs) {
		for (int i = 0; i < 10; i++) {
			submitAsyncSum(cs, i, i * i);
		}
	}
	
	private static void submitAsyncSum(CompletionService<Integer> cs, final int a, final int b) {
		cs.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(100 + (long) (Math.random() * 900));
				return a + b;
			}
		});
	}
	
	private static void processFinishedCalculations(CompletionService<Integer> cs) throws ExecutionException, InterruptedException {
		while (true) {
			final Future<Integer> result = cs.poll();
			if (result == null) {
				System.out.println("> no finished results...");
				break;
			} else {
				System.out.println("> result: " + result.get());
			}
		}
	}

}
