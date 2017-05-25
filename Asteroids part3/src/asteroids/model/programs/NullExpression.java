package asteroids.model.programs;

import asteroids.model.*;
import java.util.*;
import asteroids.part3.programs.SourceLocation;

public class NullExpression extends CircObj{

	//Constructor for NullExpression
	public NullExpression(SourceLocation location) {
		super(location);
	}
	
	//Calculate, i.e return null
	@Override
	public CircularObject calculate() {
		return null;
	}

}
