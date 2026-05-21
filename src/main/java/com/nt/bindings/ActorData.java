package com.nt.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorData {
	private Integer aid;
	private String aname;
	private  String addrs;
	private Double remuneration;
	private String active_SW;

}
