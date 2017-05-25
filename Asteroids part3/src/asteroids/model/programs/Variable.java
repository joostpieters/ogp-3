package asteroids.model.programs;

import asteroids.model.CircularObject;

public class Variable<S> {
	
	//Initialize variables
	private String name;
	private S value;

	//Constructor of a variable using name and value
	public Variable(String name, S value){
		this.setName(name);
		this.setValue(value);
	}
	
	//Get the value of the variable
	public S getValue(){
		return value;
	}

	
	//setValue of the variable
	public void setValue(S value) {
		if (!validValueType(value)) throw new IllegalArgumentException("Null is not a valid value");
		this.value = value;
	}
	
	//Is the value of a variable of a valid type
	private boolean validValueType(S value) {
		return (noValue(value) || value instanceof Double && getValue() instanceof Double) || (value instanceof Boolean && getValue() instanceof Boolean)
			||(value instanceof CircularObject && getValue() instanceof CircularObject);
	}
	
	//noValue
	private boolean noValue(S value){
		return getValue() == null;
	}
	
	//Get the name of the variable
	public String getName() {
		return name;
	}
	
	//Setname
	public void setName(String name){
		this.name = name;
	}
	



	
}
