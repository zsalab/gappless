package com.gappless.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Min(1)
	private Long id;
	
	@Column(nullable=false, length=128)
	@Size(min=1, max=64)
	private String title;
	
	@Column(name="nr_of_pages")
	@Min(1)
	private Integer nrOfPages;

	public Book() {
		super();
	}

	public Book(Long id, String title, Integer nrOfPages) {
		super();
		this.id = id;
		this.title = title;
		this.nrOfPages = nrOfPages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNrOfPages() {
		return nrOfPages;
	}

	public void setNrOfPages(Integer nrOfPages) {
		this.nrOfPages = nrOfPages;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", nrOfPages=" + nrOfPages + "]";
	}
}
