package asteroids.model.programs;



import java.util.*;
import asteroids.model.CircularObject;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class GetDir extends Expression<Double>{
	
	
	
	
	public GetDir(SourceLocation location) {
		super(location);
		// TODO Auto-generated constructor stub
	}





	@Override
	public Double evaluate(){
		return getProgram().getShip().getDirection();
	}
	
	//nodig?
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
	}
	

}
