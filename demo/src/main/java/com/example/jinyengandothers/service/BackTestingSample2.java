package com.example.jinyengandothers.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.jinyengandothers.cotroller.HomeController;

@Service
public class BackTestingSample2 {
	private static final Logger LOG = LoggerFactory.getLogger(BackTestingSample2.class);

	private ProcessBuilder pb = new ProcessBuilder("python",new ClassPathResource("")+"\\helloWord.py");
	
	public void runPython() {
		try {
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
			LOG.info(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
