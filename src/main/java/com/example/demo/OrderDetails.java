package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetails {

	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private String itemName;
	    private Long itemId;
	    private String userEmail;
	    private Long units;
	    private String statusUser;
	    private String statusDp;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Long getUnits() {
			return units;
		}
		public void setUnits(Long units) {
			this.units = units;
		}
		public String getStatusUser() {
			return statusUser;
		}
		public void setStatusUser(String statusUser) {
			this.statusUser = statusUser;
		}
		public String getStatusDp() {
			return statusDp;
		}
		public void setStatusDp(String statusDp) {
			this.statusDp = statusDp;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
	
}
