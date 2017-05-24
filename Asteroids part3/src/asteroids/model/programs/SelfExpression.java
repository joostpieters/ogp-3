package asteroids.model.programs;

import asteroids.model.*;
import java.util.*;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends CircObj{

	//Constructor for SelfExpression
	public SelfExpression(SourceLocation location) {
		super(location);
	}

	//Calculate the expression, i.e. return the ship that runs
	@Override
	public CircularObject calculate() throws IllegalArgumentException {
		return getProgram().getShip();
	}
	

}
