package com.example.cryptocurrency_exchange_interface.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.cryptocurrency_exchange_interface.model.SubscriptionResponse;

/**
 * Object represent local repository for SubscriptionResponse objects.
 */
@Repository
public class WebSocketResponseRepository {

	private List<SubscriptionResponse> subscriptionsList;


	public WebSocketResponseRepository() {
		this.subscriptionsList = new ArrayList<>();
	}


	public List<SubscriptionResponse> getSubscriptionsList() {
		return subscriptionsList;
	}


	public void setSubscriptionsList(List<SubscriptionResponse> subscriptionsList) {
		this.subscriptionsList = subscriptionsList;
	}

	public void addSubscription(SubscriptionResponse subscription) {
		this.subscriptionsList.add(subscription);
	}

	/**
	 * Method filter collection by subscription channel name and return last object
	 * @param channelName represents subscription channel name
	 * @return last Optional of SubscriptionResponse object for subscription indicated by user
	 */
	public Optional<SubscriptionResponse> getRecentSubscription(String channelName) {
		return this.subscriptionsList
					.stream()
					.filter(subscription -> channelName.equals(subscription.getChannel()))
		        	.reduce((first, second) -> second);
	}

}
