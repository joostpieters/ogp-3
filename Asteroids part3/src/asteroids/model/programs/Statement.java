package asteroids.model.programs;


import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement {

	private SourceLocation currentlocation;
	private Program currentprogram;
	public abstract void run();


	public Statement(SourceLocation location) {
		this.currentlocation = location;
	}

	public void setProgram(Program program){
		this.currentprogram = program;
	}
	
	public Program getProgram(){
		return currentprogram;
	}
	
	public boolean NoTimeConsumed(){
		return false;
	}
	
	public SourceLocation getLocation(){
		return currentlocation;
	}
	
	public boolean breakDiscovered(){
		return false;
	}
	
}
