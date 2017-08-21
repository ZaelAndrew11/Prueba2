package cl.aguzman.prueba2.data;

import java.util.ArrayList;
import java.util.List;

import cl.aguzman.prueba2.models.Gift;

public class Queries {
    public List<Gift> gifts(){
        List<Gift> gifts = new ArrayList<>();
        List<Gift> listGifts = Gift.find(Gift.class, "bought = '0'");
        if (listGifts != null && listGifts.size() > 0){
            gifts.addAll(listGifts);
        }
        return gifts;
    }

    public List<Gift> giftsbought(String doneVal){
        List<Gift> gifts = new ArrayList<>();
        List<Gift> listGifts = Gift.find(Gift.class, "bought ="+doneVal);
        if (listGifts != null && listGifts.size() > 0){
            gifts.addAll(listGifts);
        }
        return gifts;
    }
}
