package com.lc.ooo.dummy;

import com.lc.ooo.models.SportItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LC on 15-06-13.
 */
public class DummyData {
    private static List<SportItem> sportItems = new ArrayList<>();

    public static void populateDummy(){
        SportItem item = new SportItem();

        item.setAvatar("http://im.rediff.com/sports/2005/nov/18fed.jpg");
        item.setRating("99");
        item.setLocation("Toronto");
        item.setSport("Tennis");
        item.setUsername("User1");

        for(int i = 0; i < 20; i++){
            sportItems.add(item);
        }
    }

    public static List<SportItem> getFriends(){
        return sportItems;
    }
}
