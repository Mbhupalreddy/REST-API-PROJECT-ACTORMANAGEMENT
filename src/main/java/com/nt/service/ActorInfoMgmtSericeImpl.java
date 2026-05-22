package com.nt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nt.bindings.ActorData;
import com.nt.entity.ActorInfoEntity;
import com.nt.repository.IActorInfoRepository;


@Service
public class ActorInfoMgmtSericeImpl implements IActorInfoMgmtService {

	@Autowired
	private IActorInfoRepository actorRepo;
	
	/*@Autowired
	private String osuser;*/
	
	@Value("${user.name}")
	private String osuser;
	
	@Override
	public String registerActor(ActorData data) {
		//convert binding object data to Entity object
		ActorInfoEntity entity= new ActorInfoEntity();
		BeanUtils.copyProperties(data, entity);
		
		//set data meta to Entity object
		entity.setCreatedBy(osuser);
		entity.setUpdatedBy(osuser);
		
		//save the entity object
		int aid = actorRepo.save(entity).getAid();
		return  "The Actor is saved with the id value::  "+aid;
	}

	@Override
	public List<ActorData> showAllActors() {
		//use repo
		List<ActorInfoEntity> listEntities = actorRepo.findAll();
		//convert list of entity to list of binding  class objects
		
	/*	List<ActorData> listData=new ArrayList();
		
		for(ActorInfoEntity entity:listEntities) {
			ActorData data =new ActorData();
			BeanUtils.copyProperties(entity, data);
			listData.add(data);
		}*/
		//by using Streams
		List<ActorData>listData=listEntities.stream().map(entity->
												{ActorData data=new ActorData();
												BeanUtils.copyProperties(entity, data);
												return data;})
												.collect(Collectors.toList());
		
		return listData;
	}
	
	
	@Override
	public ActorData showActorById(int id) {
	ActorInfoEntity entity	=actorRepo.findById(id)
											.orElseThrow(()-> new IllegalArgumentException("id is not found!"));
		ActorData data=new ActorData();
		BeanUtils.copyProperties(entity, data);
		return data;
	}
	
	@Override
	public String updateActorData(ActorData data) {
		ActorInfoEntity entity = actorRepo.findById(data.getAid())
				.orElseThrow(()->new IllegalArgumentException("id not found!"));
		//copy ActorData to ActorInfo obj 
		BeanUtils.copyProperties(data, entity);
		entity.setUpdatedBy(osuser);
		//update the entity obj
		Integer idval = actorRepo.save(entity).getAid();
		return  idval+"  ActorData is Updated!!";
	}
	
	@Override
	public String updateActorRemuneration(int id, double amount) {
		ActorInfoEntity entity = actorRepo.findById(id)
					.orElseThrow(()->new IllegalArgumentException("Id Not foundor iNvalid id"));
		//modify the entity obj
		entity.setRemuneration(amount);
		//update the obj
		Integer aid = actorRepo.save(entity).getAid();
		
		return aid+"  actor remunation is updated!!";
	}
	
	@Override
	public String updateActorStatus(int id, String status) {
		ActorInfoEntity entity = actorRepo.findById(id)
				.orElseThrow(()->new IllegalArgumentException("Id Not foundor iNvalid id"));
		//modify entity obj
		entity.setActive_SW(status);
		//update the obj
		Integer aid = actorRepo.save(entity).getAid();
		return aid+"  actor Status is updated!!";
	}
	
	@Override
	public String deleteActorById(int aid) {
		//use repo
		ActorInfoEntity entity = actorRepo.findById(aid)
				.orElseThrow(()->new IllegalArgumentException("Id Not foundor iNvalid id"));
		//delete obj
		actorRepo.deleteById(aid);
		
		return aid+"  :: Actor is deleted!!" ;
	}
}
