package com.nt.service;

import java.util.List;

import com.nt.bindings.ActorData;

public interface IActorInfoMgmtService {
	public String registerActor(ActorData data);
	public List<ActorData> showAllActors();
	public ActorData showActorById(int id);
	public String updateActorData(ActorData data);
	public String updateActorRemuneration(int id,double amount);
	public String updateActorStatus(int id,String status);
	public String deleteActorById(int aid);

}
