package fr.marcpetit.architecture.hermes.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
public class Product {
	private String name;
	public void setName( String name ) {
		this.name = name;
		System.out.println("Product.setName("+name+")");
	}
	@XmlAttribute
	public String getName() {
		return name;
	}

	private String reference;
	public void setReference( String reference ) {
		this.reference = reference;
		System.out.println("Product.setReference("+reference+")");
	}
	@XmlAttribute
	public String getReference() {
		return reference;
	}

	private ObservableList<Requirement> requirements = FXCollections.observableArrayList();
	public void setRequirements( ArrayList<Requirement> requirements ) {
		this.requirements.addAll( requirements );
	}
	@XmlElement(name="requirements")
	public List<Requirement> getRequirements() {
		return requirements;
	}
	@XmlTransient
	public ObservableList<Requirement> getRequirementsProperty() {
		return requirements;
	}

	private ObservableList<Milestone> milestones = FXCollections.observableArrayList();
	public void setMilestones( ArrayList<Milestone> requirements ) {
		this.milestones.addAll( milestones );
	}
	@XmlElement(name="milestones")
	public List<Milestone> getMilestones() {
		return milestones;
	}
	@XmlTransient
	public ObservableList<Milestone> getMilestonesProperty() {
		return milestones;
	}


	private Category category;
	public void setCategory( Category category ) {
		this.category = category;
		System.out.println("Requirement.setCategory("+category+")");
	}
	public Category getCategory() {
		return category;
	}
	private ObservableList<Category> categories = FXCollections.observableArrayList();
	@XmlTransient
	public ObservableList<Category> getCategories() {
		categories.clear();
		if (getCategory() != null) {
			categories.addAll(getCategory().getCategoriesList());			
		}
		return categories;
	}
}
