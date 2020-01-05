package com.example.cryptocurrency_exchange_interface.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.cryptocurrency_exchange_interface.model.Subscription;
import org.springframework.stereotype.Repository;

/**
 * Object represent local repository for SubscriptionResponse objects.
 */
@Repository
public class SubscriptionsRepository {

    private List<Subscription> subscriptionsList;


    public SubscriptionsRepository() {
        this.subscriptionsList = new ArrayList<>();
    }


    public List<Subscription> getSubscriptionsList() {
        return subscriptionsList;
    }


    public void setSubscriptionsList(List<Subscription> subscriptionsList) {
        this.subscriptionsList = subscriptionsList;
    }

    /**
     *
     * @param subscription
     */
    public void addSubscription(Subscription subscription) {
        this.subscriptionsList.add(subscription);
    }

    /**
     * Method filter collection by subscription channel name and return active object
     *
     * @param channelName represents subscription channel name
     * @return last Optional of SubscriptionResponse object for subscription indicated by user
     */

    public Optional<Subscription> getSubscriptionByChannel(String channelName) {
        return this.subscriptionsList
                .stream()
                .filter(subscription -> subscription.isActive() && channelName.equals(subscription.getSubscriptionResponse().getChannel()))
                .findFirst();
    }

    /**
     *
     * @param pair
     * @return
     */

    public boolean containActiveSubscriptionForPair(final String pair){

    	return this.subscriptionsList
				.stream()
				.filter(subscription -> pair.equals(subscription.getSubscriptionResponse().getPair()) && subscription.isActive())
				.findAny()
                .isPresent();
	}

}
