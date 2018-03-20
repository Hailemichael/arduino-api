package com.haile.apps.arduino_api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.Enumeration;

import org.apache.log4j.Logger;

public class SerialCommunicationHandler implements SerialPortEventListener {

	private static final Logger logger = Logger.getLogger(SerialCommunicationHandler.class);
	private static boolean DEBUG = true;

	SerialPort serialPort;
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac
			"/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM3" // Windows
	};
	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	private BufferedReader input;
	/** The output stream to the port */
	private static OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	private char receivedFromSerial;

	public SerialCommunicationHandler() {
		initialize();
	}

	public int writeToSerial(char input) {
		try {
			Thread.sleep(TIME_OUT);
			output.write(input);
			output.flush();
			Thread.sleep(TIME_OUT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Response from arduino: " + ((int) receivedFromSerial));

		return ((int) receivedFromSerial);
	}

	public void initialize() {
		// the next line is for Raspberry Pi and
		// gets us into the while loop and was suggested here was suggested
		// http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
		// System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
		receivedFromSerial = 0;
		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		boolean read = true;
		int readByte;
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			while (read) {
				try {
					while ((readByte = input.read()) != -1) {
						if (DEBUG)
							logger.info("readByte: " + readByte);
						receivedFromSerial = (char) readByte;
					}
				} catch (IOException e) {
					// If nothing is read for more than 200 ms stop reading
					read = false;
				}
			}

		}
	}
}