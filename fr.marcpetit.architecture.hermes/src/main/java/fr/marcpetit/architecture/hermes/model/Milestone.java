package fr.marcpetit.architecture.hermes.model;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Milestone {
	private String id;
	public void setId( String id ) {
		this.id =id;
		System.out.println("Milestone.setId("+id+")");
	}
	
	@XmlAttribute
	public String getId() {
		return id;
	}
	
	public String toString() {
		return id;
	}
}
