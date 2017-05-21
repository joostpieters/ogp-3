package asteroids.model.programs;



public class Variable<S> {
	
	//Initialize Variables
	private String name;
	private S value;
	
	
	//Constructor
	public Variable(String name, S value){
		this.name = name;
		this.value = value;
	}

	//SetValue
	public void setVariableValue(S value){
		this.value = value;
	}
	
	public S getVariableValue(){
		return value;
	}
	
	public String getVaribleName(){
		return name;
	}
	
	//CanhaveAsvalue
	
}
