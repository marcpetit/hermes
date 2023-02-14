package fr.marcpetit.architecture.hermes.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

public class Category {
	public String toString() {
		return name;
	}
	
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute
	public String getName() {
		return name;
	}
	
	private String uri;
	public void setUri(String uri) {
		this.uri = uri;
	}
	@XmlAttribute
	public String getUri() {
		return uri;
	}
	
	private String description;
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlAttribute
	public String getDescription() {
		return description;
	}
	
	private List<Category> subCategories = new ArrayList<>();
	public void setCategories(ArrayList<Category> categories) {
		this.subCategories = categories;
	}
	@XmlElement(name="category")
	public List<Category> getSubSubCategories() {
		return subCategories;
	}
	
	@XmlTransient
	public List<Category> getCategoriesList() {
		List<Category> result = new ArrayList<>();
		result.add(this);
		getSubSubCategories().forEach(sub -> {
			result.addAll( sub.getCategoriesList() );
		});
		result.sort((c1,c2)->(c1.getName().compareTo(c2.getName())));
		return result;
	}
}
