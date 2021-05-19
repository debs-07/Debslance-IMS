package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Query {

	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private Long itemId;
	    private String userEmail;
	    private String message;
	    private String statusUser;
	    private String statusAdmin;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		public String getStatusUser() {
			return statusUser;
		}
		public void setStatusUser(String statusUser) {
			this.statusUser = statusUser;
		}
		public String getStatusAdmin() {
			return statusAdmin;
		}
		public void setStatusAdmin(String statusAdmin) {
			this.statusAdmin = statusAdmin;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
}
