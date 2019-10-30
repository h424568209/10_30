import java.util.HashMap;
import java.util.Map;

public class Contact {
    Map<String,User> map = new HashMap<>();
    public void add(String name,String mobilePhone,String officeNumber) throws NameExistExceptioin{
        User user = new User(name,mobilePhone,officeNumber);
        if(map.containsKey(name)){
            throw new NameExistExceptioin();
        }
        map.put(name,user);
    }
    public User get(String name){
        return map.get(name);
    }
    public String toString(){
        return map.toString();
    }
    public void remove(String name)throws NameNotExistException{
        if(!map.containsKey(name)){
            throw  new NameNotExistException();
        }
        map.remove(name);
    }
}
