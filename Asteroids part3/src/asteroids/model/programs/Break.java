package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;


public class Break extends Statement {

	public Break(SourceLocation location) {
		super(location);
	}
	
	
	public void run(){
		return;
	}
	
	public boolean brakeDiscovered(){
		return true;
	}
	
	
	
	
	
	

}
