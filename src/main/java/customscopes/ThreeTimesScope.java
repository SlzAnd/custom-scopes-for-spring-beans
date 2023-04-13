package customscopes;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ThreeTimesScope implements Scope {

    // create a map where we will collect objects created in this scope
    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<String, Object>());

    // create counter with value = 0;
    int counterOfCall = 0;
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        //each third call of this method we create a new Object in our scope(add Object to Map)
        if (counterOfCall % 3 == 0)
            scopedObjects.put(name, objectFactory.getObject());
        //increase counter of calls;
        counterOfCall++;
        //return object for current name
        return scopedObjects.get(name);
    }

    @Override
    public Object remove(String s) {
        return null;
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
