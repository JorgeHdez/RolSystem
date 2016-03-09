package com.kapeks.web.model;

import java.util.Map;

public class Ability {
	protected int id;
	protected String name;
	protected String creator;
	protected String lastChange;
	protected int difficulty;
	protected String type;
	protected String description;
	protected Map<String, Effect> effects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Effect> getEffects() {
		return effects;
	}

	public void setEffects(Map<String, Effect> effects) {
		this.effects = effects;
	}
}