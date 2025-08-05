package com.pm.patientservice.mapper;

import java.time.LocalDate;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

public class PatientMapper {

	public static PatientResponseDTO toDTO(Patient patient) {
		PatientResponseDTO patientDTO = new PatientResponseDTO();
		patientDTO.setId(patient.getId().toString());
		patientDTO.setName(patient.getName());
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
		patientDTO.setEmail(patient.getEmail());
		return patientDTO;
	}
	
	public static Patient toModel(PatientRequestDTO patientRequestDTO) {
		Patient patient = new Patient();
		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
		return patient;
	}
}
