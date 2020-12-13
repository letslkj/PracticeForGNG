package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class BookDTO {
	private int id;
	private String title;
	private String author;
	private String publisher;
	private Date publicationDate;
	private int price;
	
	public BookDTO() {
	}

	public BookDTO(String title, String author, String publisher, Date publicationDate, int price) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.price = price;
	}
	
	public BookDTO(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public BookDTO(int id, String title, String author, String publisher, Date publicationDate, int price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.price = price;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
		
}
