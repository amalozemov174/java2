package abc;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Phonebook {
    private Map<String, HashSet<String>> phonebook;

    Phonebook(){
        phonebook = new HashMap<String, HashSet<String>>();
    }

    public void setPhoneNumber(String name, String phoneNumber){
        if(this.phonebook.containsKey(name)){
            phonebook.get(name).add(phoneNumber);
        }
        else {
            HashSet<String> tmp = new HashSet<String>();
            tmp.add(phoneNumber);
            phonebook.put(name, tmp);
        }
    }

    public void getPhoneBySurname(String s){
        System.out.println("Все номера телефона по фамилии " + s + " " + phonebook.get(s));
    }



}
