package com.shruthan.jobportalbackend.model;

import java.util.Set;

public enum ApplicationStatus {

	REJECTED(Set.of()),
	HIRED(Set.of()),
	SHORTLISTED(Set.of(HIRED, REJECTED)),
	APPLIED(Set.of(SHORTLISTED, REJECTED));
	
	private final Set<ApplicationStatus> allowedTransitions;

	ApplicationStatus(Set<ApplicationStatus> allowedTransitions) {
		this.allowedTransitions = allowedTransitions;
	}
	
	public boolean canTransistionTo(ApplicationStatus newStatus) {
		return allowedTransitions.contains(newStatus);
	}
}
