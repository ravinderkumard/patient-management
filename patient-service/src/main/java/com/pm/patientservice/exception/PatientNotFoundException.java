package com.pm.patientservice.exception;

public class PatientNotFoundException extends RuntimeException {

	public PatientNotFoundException(String message) {
		super("Patient Not found Exception");
	}
	
	
	
}
