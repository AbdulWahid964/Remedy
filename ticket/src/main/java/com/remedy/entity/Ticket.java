package com.remedy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity()
@Table(name = "ticket_details")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private int ticketId;
	@OneToOne
	@JoinColumn(name = "customerId")
	private Users user;

	private String state;
	private String priority;
	@NotEmpty(message = "This Field Should be empty")
	@NotNull(message = "This Field Should be empty")
	private String shortDescription;

	public int getTicketId() {
		return ticketId;
	}
	public Users getUser() {
		return user;
	}
	public String getState() {
		return state;
	}
	public String getPriority() {
		return priority;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
