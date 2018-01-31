package com.sa.icarasia.model;

import android.text.TextUtils;

public class UserFactory {
    private static volatile UserFactory userFactory;
    private UserFactory() {

    }

    public static UserFactory getInstance() {
        UserFactory instance = userFactory;
        if (instance == null) {
            synchronized (UserFactory.class) {
                instance = userFactory;
                if (instance == null) {
                    instance = userFactory = new UserFactory();
                }
            }
        }

        return instance;
    }

    //use getShape method to get object of type shape
    public IUser getUser(String userType){
        if(TextUtils.isEmpty(userType)){
            return null;
        }
        if(userType.equalsIgnoreCase(Agent.class.getSimpleName())){
            return new Agent();

        } else if(userType.equalsIgnoreCase(Broker.class.getSimpleName())){
            return new Broker();

        } else if(userType.equalsIgnoreCase(Dealer.class.getSimpleName())){
            return new Dealer();
        }
        else if(userType.equalsIgnoreCase(Private.class.getSimpleName())){
            return new Private();
        }

        return null;
    }
}
