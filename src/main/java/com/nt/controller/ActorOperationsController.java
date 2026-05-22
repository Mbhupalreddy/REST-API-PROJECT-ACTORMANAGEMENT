package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.bindings.ActorData;
import com.nt.service.IActorInfoMgmtService;

@Controller
@RequestMapping("/actor-api")
public class ActorOperationsController {
	@Autowired
	private IActorInfoMgmtService actorService;
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveActor(@RequestBody ActorData data){
		String msg = actorService.registerActor(data);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	@GetMapping("/showAll")
	public ResponseEntity<?> showAllActors(){
		List<ActorData> listData = actorService.showAllActors();
		 return new ResponseEntity<List<ActorData>>(listData,HttpStatus.OK);
	}

}
