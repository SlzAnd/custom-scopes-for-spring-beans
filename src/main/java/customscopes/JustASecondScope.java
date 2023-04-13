package customscopes;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.*;


public class JustASecondScope implements Scope {

    // create a map where we will collect objects created in this scope
    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<String, Object>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        // if bean with current name doesn't exist - create new Bean(add new Object to Map)
        if(!scopedObjects.containsKey(name)) {
            scopedObjects.put(name, objectFactory.getObject());
            // run a timer for 1 second -> after 1 second we remove this Object from our scope
            oneSecondTimer(name);
        }
        return scopedObjects.get(name);
    }

    private void oneSecondTimer(String name) {
        Timer timer = new Timer();
        // removing an Objects from scope after 1 second
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(name);
            }
        }, 1000);
    }

    @Override
    public Object remove(String s) {
        // remove an Objects from Map
        return scopedObjects.remove(s);
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
