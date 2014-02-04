package com.enirvana.rulesapp;

public class Field {
	private String name;	
	private String source;
	private String category;
	private String notes;
	private String displayName;
	private String format;
	private String displayOrder;
	private String alwaysRequired;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getAlwaysRequired() {
		return alwaysRequired;
	}
	public void setAlwaysRequired(String alwaysRequired) {
		this.alwaysRequired = alwaysRequired;
	}
	@Override
	public String toString() {
		return "Field [name=" + name + ", source=" + source + ", category="
				+ category + ", displayName=" + displayName + ", format="
				+ format + ", displayOrder=" + displayOrder
				+ ", alwaysRequired=" + alwaysRequired + "]";
	}	
}
