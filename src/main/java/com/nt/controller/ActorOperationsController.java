package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ActorData;
import com.nt.service.IActorInfoMgmtService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/actor-api")
@Tag(name="actor",description = "Actor operations controller!")
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
	@GetMapping("/find/{id}")
	public ResponseEntity<?> showActorsByIds(@PathVariable Integer id){
		ActorData  actorid = actorService.showActorById(id);
		return new ResponseEntity<ActorData>(actorid,HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateActor(@RequestBody ActorData data){
		String msg = actorService.updateActorData(data);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/rupdate/{id}/{amount}")
	public ResponseEntity<?> updateActorRemuneration(@PathVariable Integer id,@PathVariable Double  amount ){
		String msg = actorService.updateActorRemuneration(id,amount );
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/supdate/{id}/{status}")
	public ResponseEntity<?> updateActorStatus(@PathVariable Integer id,@PathVariable   String status ){
		String msg = actorService.updateActorStatus(id,status );
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeActorById(@PathVariable Integer id ){
		String msg = actorService.deleteActorById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}
