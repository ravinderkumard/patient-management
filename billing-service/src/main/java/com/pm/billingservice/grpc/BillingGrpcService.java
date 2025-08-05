package com.pm.billingservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase{
	
	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
	
	@Override
	public void createBillingAccount(billing.BillingRequest billingRequest, 
			StreamObserver<billing.BillingResponse> responseObserver) {
		
		log.info("Create Billing Account request received {}",billingRequest.toString());
		
		// Just returning response for the time.
		
		BillingResponse response = BillingResponse.newBuilder()
				.setAccountId(billingRequest.getPatientId())
				.setStatus("ACTIVE")
				.build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
		
	}
}
