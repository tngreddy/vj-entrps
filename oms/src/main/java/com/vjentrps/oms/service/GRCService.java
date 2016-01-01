package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.GRCDetails;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.PendingGRC;

public interface GRCService {
	
	String createGRC(GoodsReturnableChallan grc) throws ParseException, OmsServiceException;

	List<GoodsReturnableChallan> listGRCs() throws OmsServiceException;

	void updateGRC(GoodsReturnableChallan grc) throws OmsServiceException;

	void deleteGRC(String grcNo) throws OmsServiceException;

	void updateGRCStatus(GoodsReturnableChallan grc) throws OmsServiceException;

	List<String> getGRCNoList(String fromName) throws OmsServiceException;

	GRCDetails buildGRCDetails(String grcNo, boolean fromToInfo) throws OmsServiceException;

	GoodsReturnableChallan getGRCbyNo(String grcNo) throws OmsServiceException;

	List<PendingGRC> fetchAllGRCPendingProdInfo(String status) throws OmsServiceException;
	
	

}
