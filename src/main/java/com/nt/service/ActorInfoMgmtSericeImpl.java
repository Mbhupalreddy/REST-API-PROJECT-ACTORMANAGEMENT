package com.nt.service;

import java.util.List;
import java.util.stream.Collectors;
import java.lang.String;

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

}
