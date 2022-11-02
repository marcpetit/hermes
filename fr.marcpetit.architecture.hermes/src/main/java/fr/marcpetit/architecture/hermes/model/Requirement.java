package fr.marcpetit.architecture.hermes.model;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Requirement {
	private String id;
	public void setId( String id ) {
		this.id =id;
		System.out.println("Requirement.setId("+id+")");
	}
	@XmlAttribute
	public String getId() {
		return id;
	}

	private String text;
	public void setText( String text ) {
		this.text = text;
		System.out.println("Requirement.setText("+text+")");
	}
	@XmlAttribute
	public String getText() {
		return text;
	}

	private Milestone milestone;
	public void setMilestone( Milestone milestone ) {
		this.milestone = milestone;
		System.out.println("Requirement.setMilestone("+milestone+")");
	}
	public Milestone getMilestone() {
		return milestone;
	}

	private Category category;
	public void setCategory( Category category ) {
		this.category = category;
		System.out.println("Requirement.setCategory("+category+")");
	}
	public Category getCategory() {
		return category;
	}
}
