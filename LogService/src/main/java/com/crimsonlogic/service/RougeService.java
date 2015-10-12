package com.crimsonlogic.service;

import org.springframework.stereotype.Service;

import com.crimsonlogic.service.web.dto.RougeRequest;

@Service
public class RougeService {

	public void logMessage(RougeRequest rougeRequest) throws Exception {
		if ("OOM".equalsIgnoreCase(rougeRequest.getMessage())) {
			generateOOM();
		} else if ("EXIT".equalsIgnoreCase(rougeRequest.getMessage())) {
			exit();
		} else if ("HALT".equalsIgnoreCase(rougeRequest.getMessage())) {
			halt();
		} else if ("CPU".equalsIgnoreCase(rougeRequest.getMessage())) {
			cpu();
		}else{
			generateOOM();
		}
	}

	private void generateOOM() throws Exception {
		int arraySize = 50;
		// Create arrays in an infinite loop
		while (true) {
			System.out.println("Available memory (in bytes): "
					+ Runtime.getRuntime().freeMemory());
			int[] fillMemory = new int[arraySize];
			arraySize = arraySize * 5;
			Thread.sleep(1000);
		}
	}

	private void exit() {
		System.exit(0);
	}

	private void halt() {
		Runtime.getRuntime().halt(0);
	}

	private void cpu() {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("Thread "
							+ Thread.currentThread().getName() + " started");
					double val = 10;
					for (;;) {
						Math.atan(Math.sqrt(Math.pow(val, 10)));
					}
				}
			}).start();
		}
	}
}
