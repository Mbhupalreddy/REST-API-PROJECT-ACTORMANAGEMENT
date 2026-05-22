package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.ActorInfoEntity;

public interface IActorInfoRepository extends JpaRepository<ActorInfoEntity, Integer> {
	
}
