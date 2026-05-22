package com.nt.service;

import java.util.List;

import com.nt.bindings.ActorData;

public interface IActorInfoMgmtService {
	public String registerActor(ActorData data);
	public List<ActorData> showAllActors();

}
