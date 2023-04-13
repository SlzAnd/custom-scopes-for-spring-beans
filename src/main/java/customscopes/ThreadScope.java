package customscopes;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadScope implements Scope {

    // create threadScope - it's a ThreadLocal which save Map of scoped objects for each thread
    private final ThreadLocal<Map<String, Object>> threadScope =
            ThreadLocal.withInitial(() -> new ConcurrentHashMap<>());
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        // get scoped objects for current thread
        Map<String,Object> scopedObjects = threadScope.get();
        // if bean with current name doesn't exist - create new Bean(add new Object to Map)
        if(!scopedObjects.containsKey(name)){
            scopedObjects.put(name,objectFactory.getObject());
        }
        //return Object with current name(for current thread)
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
