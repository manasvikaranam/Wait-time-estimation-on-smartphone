package Prediction;

import java.util.HashMap;

import android.content.Context;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.model.EntityStatus;
import com.amazonaws.services.machinelearning.model.GetMLModelRequest;
import com.amazonaws.services.machinelearning.model.GetMLModelResult;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.RealtimeEndpointStatus;
import com.example.database.Data;

public class Prediction {
	
	private Context context;
	private AmazonMachineLearningClient client;
	private CognitoCachingCredentialsProvider credentialsProvider;
	private final String mlModelId = "ml-sRHm7r8mYgf";
	private GetMLModelResult mlModelResult;
	public Prediction(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public int getWaitTime(Data data) {
		// TODO Auto-generated method stub
		BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJYCGDFILHTZR5XDA","lZnftWMzIgQmdCStMVGggRrjOCDHGieZqsZYzpnv");
		client  =  new AmazonMachineLearningClient(credentials);
		createEndpoint();
		PredictRequest  predictRequest = new PredictRequest();
		predictRequest.setMLModelId(mlModelId);
		HashMap<String, String> record = new HashMap<String, String>();
		record.put("weekofyear", data.getWeekOfYear()+"");
		record.put("dayofweek", data.getDayOfWeek()+"");
		record.put("dayinterval", data.getDayInterval()+"");
		predictRequest.setRecord(record);
		predictRequest.setPredictEndpoint(mlModelResult.getEndpointInfo().getEndpointUrl());

		// Call Predict and print out your prediction
		PredictResult predictResult = client.predict(predictRequest);
		return predictResult.getPrediction().getPredictedValue().intValue();
	
	}
	private void createEndpoint() {
		// TODO Auto-generated method stub
		GetMLModelRequest getMLModelRequest = new GetMLModelRequest();
		getMLModelRequest.setMLModelId(mlModelId);
		mlModelResult = client.getMLModel(getMLModelRequest);

		// Validate that the ML model is completed
		if (!mlModelResult.getStatus().equals(EntityStatus.COMPLETED.toString())) {
		        System.out.println("ML Model is not completed: + mlModelResult.getStatus()");
		        return;
		}

		// Validate that the realtime endpoint is ready
		if (!mlModelResult.getEndpointInfo().getEndpointStatus().equals(RealtimeEndpointStatus.READY.toString()))
		{
		        System.out.println("Realtime endpoint is not ready: " + mlModelResult.getEndpointInfo().getEndpointStatus());
		        return;
		}
		
	}
	
	

}
