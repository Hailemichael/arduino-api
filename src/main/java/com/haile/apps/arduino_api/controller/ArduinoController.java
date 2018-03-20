package com.haile.apps.arduino_api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableTransactionManagement // As <tx:annotation-config />
//@Transactional(readOnly = true) to enable transaction
@Controller
@EnableWebMvc
public class ArduinoController {

	private static final Logger logger = LogManager.getLogger(ArduinoController.class.getName());
	private static boolean DEBUG = true;

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		if (DEBUG)
			logger.info("Home Page is opening...");
		ModelAndView model = new ModelAndView();
		model.addObject("waterplant", 90);
		model.addObject("opengarage", 90);
		model.addObject("closegarage", 90);
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = { "/waterplant" }, method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public ModelAndView waterPlant() {
		if (DEBUG)
			logger.info("Watering plants initiated");
		SerialCommunicationHandler sch = new SerialCommunicationHandler();
		Thread listeningThread=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(10000);} catch (InterruptedException ie) {}
			}
		};
		listeningThread.start();
		if (DEBUG)
			logger.info("Listening thread Started. Thread ID: " + listeningThread.getId());
		
		//Water plants
		int response = sch.writeToSerial('A');
		
		ModelAndView model = new ModelAndView();
		model.addObject("waterplant", response);
		model.addObject("opengarage", 100);
		model.addObject("closegarage", 100);
		model.setViewName("index");
		if (DEBUG)
			logger.info("model output: " + model.getModel().get("waterplant"));
		sch.close();
		
		return model;
	}
	
	@RequestMapping(value = { "/opengarage" }, method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public ModelAndView openGarage() {
		if (DEBUG)
			logger.info("Opening Garage door ...");
		SerialCommunicationHandler sch = new SerialCommunicationHandler();
		Thread listeningThread=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(10000);} catch (InterruptedException ie) {}
			}
		};
		listeningThread.start();
		if (DEBUG)
			logger.info("Listening thread Started. Thread ID: " + listeningThread.getId());
		
		//Water plants
		int response = sch.writeToSerial('B');
		
		ModelAndView model = new ModelAndView();
		model.addObject("opengarage", response);
		model.addObject("closegarage", 90);
		model.addObject("waterplant", 100);
		model.setViewName("index");
		if (DEBUG)
			logger.info("model output: " + model.getModel().get("opengarage"));
		sch.close();
		
		return model;
	}
	
	@RequestMapping(value = { "/closegarage" }, method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public ModelAndView closeGarage() {
		if (DEBUG)
			logger.info("Closing garage door ...");
		SerialCommunicationHandler sch = new SerialCommunicationHandler();
		Thread listeningThread=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(10000);} catch (InterruptedException ie) {}
			}
		};
		listeningThread.start();
		if (DEBUG)
			logger.info("Listening thread Started. Thread ID: " + listeningThread.getId());
		
		//Water plants
		int response = sch.writeToSerial('C');
		
		ModelAndView model = new ModelAndView();
		model.addObject("closegarage", response);
		model.addObject("opengarage", 90);
		model.addObject("waterplant", 100);
		model.setViewName("index");
		if (DEBUG)
			logger.info("model output: " + model.getModel().get("closegarage"));
		sch.close();
		
		return model;
	}

}
