package com.pm.patientservice.kafka;


import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import com.pm.patientservice.model.Patient;

import patient.events.PatientEvent;

@Service
public class KafkaProducer {

	public static final Logger log = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);
	private final KafkaTemplate<String, byte[]> kafkaTemplate;
	
	public KafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendEvent(Patient patient) {
		PatientEvent event = PatientEvent.newBuilder()
				.setPatientId(patient.getId().toString())
				.setName(patient.getName())
				.setEmail(patient.getEmail())
				.setEventType("PATIENT_CREATED")
				.build();
		
		try {
			kafkaTemplate.send("patient-management-topic",event.toByteArray());
		} catch(Exception e) {
			log.error("Error sending PatientCreated event: {}",event);
		}
	}
}
