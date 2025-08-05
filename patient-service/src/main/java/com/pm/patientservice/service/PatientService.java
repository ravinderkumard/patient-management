package com.pm.patientservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;
	private BillingServiceGrpcClient billingServiceGrpcClient;
	
	public PatientService(PatientRepository patientRespository,BillingServiceGrpcClient billingServiceGrpcClient) {
		this.patientRepository = patientRespository;
		this.billingServiceGrpcClient = billingServiceGrpcClient;
	}
	
	public List<PatientResponseDTO> getPatients(){
		List<Patient> patients = patientRepository.findAll();
		
		List<PatientResponseDTO> patientResponseDTO = patients.stream()
				.map(PatientMapper::toDTO)
				.toList();
		
		return patientResponseDTO;
	}
	
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
		
		if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email already exists "+patientRequestDTO.getEmail());
		}
		
		Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
		
		billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),
				newPatient.getName(), newPatient.getEmail());
		
		return PatientMapper.toDTO(newPatient);
	}
	
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
		
		Patient patient = patientRepository.findById(id).orElseThrow(
				()->new PatientNotFoundException("Patient not found with ID :"+id)
			);
		
		if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
			throw new EmailAlreadyExistsException("A patient with this email already exists "+patientRequestDTO.getEmail());
		}
		
			patient.setName(patientRequestDTO.getName());
		
			patient.setAddress(patientRequestDTO.getAddress());
		
			patient.setEmail(patientRequestDTO.getEmail());
		
			patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		
		patient = patientRepository.save(patient);
		
		return PatientMapper.toDTO(patient);
	}
	
	public void deletePatient(UUID id) {
		patientRepository.deleteById(id);
	}
}
