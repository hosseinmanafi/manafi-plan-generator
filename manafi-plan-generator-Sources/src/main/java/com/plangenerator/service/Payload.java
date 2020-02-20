package com.plangenerator.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.plangenerator.model.PayloadRequest;
import com.plangenerator.model.PayloadResponse;

public class Payload {
	
	private PayloadRequest payloadRequest;	
	PayloadResponse payloadResponse = new PayloadResponse();
	private int upperDuration;
	private String result = "";
	
	public Payload(PayloadRequest payloadRequest) {
		this.payloadRequest = payloadRequest;
		this.upperDuration = payloadRequest.getDuration();
		payloadResponse.setAnnuity(219.36);
	}
	
	public void computing() throws ParseException {
		while (payloadRequest.getDuration() > 0) {	
			
			String date = payloadRequest.getStartDate();
			double interest = computInterest(payloadRequest.getNominalRate(), payloadRequest.getLoanAmount());
			double annuity = payloadResponse.getAnnuity(); 
			double principal = computPrincipal(interest, annuity);
			double initialLoanAmount = payloadRequest.getLoanAmount();
			double remainingOutstandingPrincipal = initialLoanAmount - principal;

			if (remainingOutstandingPrincipal < 0) {
				annuity = changeDoubleFormat(annuity + remainingOutstandingPrincipal);
				principal = changeDoubleFormat(principal + remainingOutstandingPrincipal);
				remainingOutstandingPrincipal =0;
			}

			payloadResponse.setBorrowerPaymentAmount(changeDoubleFormat(annuity));
			payloadResponse.setInitialOutstandingPrincipal(changeDoubleFormat(initialLoanAmount));
			payloadResponse.setDate(date);
			payloadResponse.setInterest(interest);
			payloadResponse.setPrincipal(principal);
			payloadResponse.setRemainingOutstandingPrincipal(changeDoubleFormat(remainingOutstandingPrincipal));			
			
			if (payloadRequest.getDuration() < upperDuration)
				result = result + ",";

			result = result + "{" 
			        + "\"borrowerPaymentAmount\": \""           + payloadResponse.getBorrowerPaymentAmount()
					+ "\"," 
			        + "\"date\": \""                            + payloadResponse.getDate() 
			        + "\"," 
			        + "\"initialOutstandingPrincipal\": \""	    + payloadResponse.getInitialOutstandingPrincipal() 
			        + "\"," 
			        + "\"interest\": \""					    + payloadResponse.getInterest() 
			        + "\"," 
			        + "\"principal\": \""                       + payloadResponse.getPrincipal() 
			        + "\","
					+ "\"remainingOutstandingPrincipal\": \""   + payloadResponse.getRemainingOutstandingPrincipal() 
					+ "\""
					+ "}";
  
			payloadRequest.setStartDate(addMonth(date));
			payloadRequest.setLoanAmount(remainingOutstandingPrincipal);
			payloadRequest.setDuration(payloadRequest.getDuration() - 1);
		}
	}	
	
	
	private double changeDoubleFormat(double number) {
		DecimalFormat num = new DecimalFormat("0.00");
		return Double.valueOf(num.format(number));
	}

	
	private double computPrincipal(double interest, double annuity) {
		double principal = annuity - interest;
		return changeDoubleFormat(principal);
	}

	
	private double computInterest(double nominalRate, double loanAmount) {
		double interest = (nominalRate * 30 * loanAmount) / 36000;
		return changeDoubleFormat(interest);
	}	
	
	private String addMonth(String datex) throws ParseException {
		String resultDate;
		String myDate = datex;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myDate = myDate.replace("T", " ");
		myDate = myDate.replace("Z", "");
		Date date = dateFormat.parse(myDate);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		if (calendar.get(Calendar.MONTH) == 12) {
			calendar.set(GregorianCalendar.YEAR, calendar.get(Calendar.YEAR) + 1);
			calendar.set(GregorianCalendar.MONTH, 1);
		} else
			calendar.set(GregorianCalendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		resultDate = simpleDateFormat.format(date).toString() + "T00:00:00Z";
		return resultDate;
	}
	
	
	@Override
	public String toString() {
		return "{ [ " + result + " ] }";
	}

}
