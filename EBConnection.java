package com.eb.model;


public class EBConnection {
	private int connectionId;
	private String ownerName;
	private  String connectionType;  //Can be Domestic, Commercial or Industrial
	private String meterId;
	private String phase;    //Can bbe Single or Three phase

	public EBConnection(){
		
	}

	public EBConnection(int connectionId, String ownerName, String connectionType, String meterId, String phase) {
		super();
		this.connectionId = connectionId;
		this.ownerName = ownerName;
		this.connectionType = connectionType;
		this.meterId = meterId;
		this.phase = phase;
	}

	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
}
