package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Constants;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.RINDetails;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.ReturnedInwardNote;

@RestController
@RequestMapping(value="/service/rin")
public class RINRestController extends BaseRestController{
	

 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getRINs() {
    	
        List<ReturnedInwardNote> rins = new ArrayList<ReturnedInwardNote>();
   	
        	try {
				rins = rinService.listRINs();
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        return new ResponseDTO(rins);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO createRIN(@RequestBody ReturnedInwardNote rin) {
 
    	String rinNo = "";
    	rin.setStatus("pending");
    	try {
    		rinNo = rinService.createRIN(rin);
		}  catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO(rinNo);
    }
    
      
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateRIN(@RequestBody ReturnedInwardNote rin) {
 
    	try {
			rinService.updateRIN(rin);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         return "Success";
    }
    
    @RequestMapping(value="/{rinNo}",method = RequestMethod.DELETE)
    public String deleteRIN(@PathVariable String rinNo) {
    	
    	try {
			rinService.deleteRIN(rinNo);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           return "Success";
    }
    
    @RequestMapping(value="/{rinNo}",method = RequestMethod.PUT)
    public String updateRINStatus(@PathVariable String rinNo) {
    	
    	ReturnedInwardNote rin = new ReturnedInwardNote();
    	rin.setRinNo(rinNo);
    	rin.setStatus(Constants.CLOSED);
    	try {
			rinService.updateRINStatus(rin);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           return "Success";
    }
    
    @RequestMapping(value="/details/{rinNo}/{fromToInfo}", method = RequestMethod.GET)
    public ResponseDTO buildRINDetails(@PathVariable String rinNo, @PathVariable boolean fromToInfo ) {
    	
        	try {
        		RINDetails  rinDetails = rinService.buildRINDetails(rinNo, fromToInfo);      
        		
        		if (null == rinDetails.getRin()) {
    				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_DATA_FOUND));
    			}
        		return new ResponseDTO(rinDetails);
        		
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        
    }
    
    @RequestMapping(value="/{rinNo}", method = RequestMethod.GET)
    public ResponseDTO fetchRINData(@PathVariable String rinNo) {
    	
        	try {
        		ReturnedInwardNote  rin = rinService.getRINbyNo(rinNo);        		
        		
        		return new ResponseDTO(rin);
        		
			} catch (OmsServiceException e) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
			}
        
    }
 
    
 
}