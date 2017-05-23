package beanscope;

import static java.time.LocalTime.now;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

import javafx.util.Pair;
public class PeroidocalScopeConfigurer  implements Scope{
	private Map<String,Pair<LocalTime,Object>>map=new HashMap<>();
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if(map.containsKey(name)){
			Pair<LocalTime, Object> pair = map.get(name);
			int secondSinceLastRequest=now().getSecond()-pair.getKey().getSecond();
			if(secondSinceLastRequest>5){
				map.put(name,new Pair<LocalTime, Object>(now(), objectFactory.getObject()));
			}
		}else{
			map.put(name,new Pair<LocalTime, Object>(now(), objectFactory.getObject()));
		}
		return map.get(name).getValue();
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String arg0, Runnable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
