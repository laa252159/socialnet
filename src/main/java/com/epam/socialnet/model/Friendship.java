package com.epam.socialnet.model;

import java.util.Date;

public class Friendship {
	
	private long firsPersonId;
	
	private long secondPersonId;
	
	private Date dateOfFriendshipStarting;
	
	private boolean isFriendshipApproved;

	public long getFirsPersonId() {
		return firsPersonId;
	}

	public void setFirsPersonId(long firsPersonId) {
		this.firsPersonId = firsPersonId;
	}

	public long getSecondPersonId() {
		return secondPersonId;
	}

	public void setSecondPersonId(long secondPersonId) {
		this.secondPersonId = secondPersonId;
	}

	public Date getDateOfFriendshipStarting() {
		return dateOfFriendshipStarting;
	}

	public void setDateOfFriendshipStarting(Date dateOfFriendshipStarting) {
		this.dateOfFriendshipStarting = dateOfFriendshipStarting;
	}

	public boolean isFriendshipApproved() {
		return isFriendshipApproved;
	}

	public void setFriendshipApproved(boolean isFriendshipApproved) {
		this.isFriendshipApproved = isFriendshipApproved;
	}
}
