	
	Description My Services :
	-------------------------------------------------------------------
	Service URL: http://localhost:8080/generate-plan
	Method TYPE : POST
	Data Format : JSON

	-------------------------------------------------------------------
	com.plangenerator.model 
	  
	    Defenitation : 		
		Input Data (Class :  PayloadRequest.JAVA))
		Output Data(Class :  PayloadResponse.JAVA))	
		
		In the Input and Output class layers are defined with the following parameters 
		and for them Getter and Setter methods are defined.
		
		
		Input Data (Class :  PayloadResponse.JAVA)) :
		
                      loanAmount
	      nominalRate
	      duration
	      startDate
		  
		Outpot Data (Class :  PayloadRequest.JAVA)) :

		  borrowerPaymentAmount
		  date
		  initialOutstandingPrincipal
		  interest
		  principal
	                  remainingOutstandingPrincipal
		  annuity			
		
	-------------------------------------------------------------------	

	com.plangenerator.service

                Implimentung Calculate Payload(Class : Payload.JAVA)
	   
	   In this layer, the loan calculator algorithm is implemented
	   
	   This layer contains 5 methods :
	   
	   computing          : This method includes implementing the loan calculator algorithm and building the JSON output
	   calcAnnuity        : This method is used to calculate the principal value with the Annuity Payment formula 
	   changeDoubleFormat : This method is used to convert the number format to "0.00"
	   computPrincipal    : This method is used to calculate the principal value with the principal = annuity - interest formula
	   computInterest     : This method is used to calculate the interest value with the interest = (nominalRate * 30 * loanAmount) / 36000 formula
	   addMonth           : This is the method to add the next month to the desired date	   

    -------------------------------------------------------------------
	com.plangenerator.manafiplangenerator

         Defination Controller(Class : HomeController.JAVA)	
		 
		 This layer is a control layer for receiving data from the input 
		 and calling the loan computing service and displaying it at the output.
		
	-------------------------------------------------------------------
