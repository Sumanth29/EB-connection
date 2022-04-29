package com.eb.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.eb.exception.InvalidEBConnectionException;
import com.eb.model.EBConnection;


public class ElectricityBoard {
	
	List<EBConnection> ebConnectionList = new ArrayList<>();
	
	public List<EBConnection> getEbConnectionList() {
		return ebConnectionList;
	}

	public void setEbConnectionList(List<EBConnection> ebConnectionList) {
		this.ebConnectionList = ebConnectionList;
	}

	public boolean validateConnectionType(String connectionType) throws InvalidEBConnectionException  {
	   if(connectionType.equalsIgnoreCase("domestic") || connectionType.equalsIgnoreCase("commercial")  || connectionType.equalsIgnoreCase("industrial"))
		   return true;
	   else
		   throw new InvalidEBConnectionException("Connection type invalid");
	}
	
	public EBConnection viewEBConnectionById(int connectionId) throws InvalidEBConnectionException {
		if(ebConnectionList.size()==0){
			throw new InvalidEBConnectionException("List is empty");
		}
		else {
			for(EBConnection e : ebConnectionList){
				if(e.getConnectionId()==connectionId)
					return e;
			}
			throw new InvalidEBConnectionException("Connection ID is invalid");	
		}
	}
	
	public List<EBConnection> viewEBConnectionsByConnectionType(String connectionType) throws InvalidEBConnectionException {
		if(ebConnectionList.size()==0){
			throw new InvalidEBConnectionException("List is empty");
		}
		else {
			List<EBConnection> result = new ArrayList<>();
			for(EBConnection e : ebConnectionList){
				if(e.getConnectionType().equals(connectionType))
					result.add(e);
			}
			return result;	
		}
	}
	
	public Map<String,List<EBConnection>> viewEBConnectionsConnectionTypeWise() throws InvalidEBConnectionException {
		if(ebConnectionList.size()==0){
			throw new InvalidEBConnectionException("List is empty");
		}
		else {
			Map<String,List<EBConnection>> result = new LinkedHashMap<>();
			
			for(EBConnection e : ebConnectionList){
				if(!result.containsKey(e.getConnectionType())){
					result.put(e.getConnectionType(),new ArrayList<EBConnection>());
				}
				List<EBConnection> temp=result.get(e.getConnectionType());
				temp.add(e);
				result.put(e.getConnectionType(), temp);			
			}
			return result;
		}
	}
	
	public  Map<String,Integer> countTotalConnectionForEachPhase() throws InvalidEBConnectionException {
		if(ebConnectionList.size()==0){
			throw new InvalidEBConnectionException("List is empty");
		}
		else {
			Map<String,Integer> result = new LinkedHashMap<>();
			
			for(EBConnection e : ebConnectionList){
				if(!result.containsKey(e.getPhase())){
					result.put(e.getPhase(),1);
				}
				else
				{
					int temp=result.get(e.getPhase());					
					result.put(e.getPhase(), temp+1);
				}				
			}
			return result;
		}
	}	
}

