package asteroids.model.programs;

import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class While extends Statement {
	
	//Initialize Variables
	private Expression<Boolean> cond;
	private Statement body;
	private boolean bodyexist;
	private boolean noTimeConsumed;

	//Constructor
	public While(Expression<Boolean> cond, Statement body,
			SourceLocation location) {
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
		noTimeConsumed = false;
		if(!bodyexist){
			if(cond.calculate()) bodyexist = true;
			else return;
		}
		body.run();
		if (body.NoTimeConsumed()){
			noTimeConsumed = true;
			return;
		}
		while(cond.calculate() && !body.breakDiscovered()){
			getProgram().setLocation(this.getLocation());
			body.run();
			if (body.NoTimeConsumed()){
				noTimeConsumed = true;
				return;
			}
		}
		bodyexist = false;
	}
	
	//NoTimeConsumed
	public boolean noTimeConsumed(){
		return noTimeConsumed;
	}
	
	
	//Run in function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals) {
		// TODO Auto-generated method stub
		noTimeConsumed = false;
		if(cond.calculate(arguments, locals)){ 
			bodyexist = true;
			}
		else return Optional.empty();
		Optional result = body.run(arguments, locals);
		if (result.isPresent()) return result;
		while (cond.calculate(arguments, locals) && !body.breakDiscovered()){
			result = body.run(arguments, locals);
			if(result.isPresent()) return result;
		}
		bodyexist = false;
		return Optional.empty();
	}

	//Set program
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		cond.setProgram(program);
		body.setProgram(program);
	}

	
	


}
