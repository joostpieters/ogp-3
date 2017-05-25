package asteroids.model.programs;

import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class While extends Statement {
	
	//Initialize Variables
	private Expression<Boolean> cond;
	private Statement body;
	private boolean loadbody;
	private boolean TimeConsumed;

	//Constructor
	public While(Expression<Boolean> cond, Statement body, SourceLocation location) {
		super(location);
		this.cond = cond;
		this.body = body;
	}
	
	//getCondition
	public Expression getCondition() {
		return this.cond;
	}
	
	//SetCondition
	public void setCondition(Expression condition) {
		this.cond = condition;
	}
	
	//Getbody
	public Statement getBody() {
		return this.body;
	}
	
	//setbody
	public void setBody(Statement body) {
		this.body = body;
	}

	
	//Run while
	@Override
	public void run() {
		TimeConsumed = false;
		//Look if condition is true to start running the body, if not, do nothing
		if(!loadbody){
			if(cond.calculate()) loadbody = true;
			else return;
		}
		//Load body
		body.run();
		if (body.NoTimeConsumed()){
			TimeConsumed = true;
			return;
		}
		//Check if we can run again
		while(cond.calculate() && !body.breakDiscovered()){
			getProgram().setLocation(this.getLocation());
			body.run();
			if (body.NoTimeConsumed()){
				TimeConsumed = true;
				return;
			}
		}
		loadbody = false;
	}
	
	//NoTimeConsumed
	public boolean TimeConsumed(){
		return TimeConsumed;
	}
	
	
	//Run in function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals) {
		TimeConsumed = false;
		//Check if we can run the body, i.e. the while condition is true
		if(cond.calculate(arguments, locals)){ 
			loadbody = true;
			}
		//If not, do nothing
		else return Optional.empty();
		//Run the body if the condition is true
		Optional toreturn = body.run(arguments, locals);
		if (toreturn.isPresent()) return toreturn;
		//While the condition is true load the body again and again
		while (cond.calculate(arguments, locals) && !body.breakDiscovered()){
			toreturn = body.run(arguments, locals);
			if(toreturn.isPresent()) return toreturn;
		}
		loadbody = false;
		return Optional.empty();
	}

	//Set program for whole while loop and its elements
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		cond.setProgram(program);
		body.setProgram(program);
	}

	
	


}
