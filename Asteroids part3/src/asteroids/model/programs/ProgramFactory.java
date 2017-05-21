package asteroids.model.programs;

import asteroids.model.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import java.util.*;


public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program>{

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		// TODO Auto-generated method stub
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
		//TODO
		return null;
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		return new Return(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfElse(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		//TODO
		return null;
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		return new SignChange(expression, sourceLocation);
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		return new LogicalNegation(expression, sourceLocation);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		return new Getx(e, location);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		return new Gety(e, location);
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		return new GetVx(e, location);
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		return new GetVy(e, location);
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		return new GetRadius(e, location);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		return new Comparison(e1, e2, location);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		return new Equals(e1, e2, location);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		return new Addition(e1, e2, location);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		return new Multiply(e1, e2, location);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		return new SquareRoot(e, location);
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		return new GetDir(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new Action(location){
			
			@Override
			public void run(){
				setNoTimeConsumed(false);
				getProgram().setLocation(getLocation());
				if (getProgram().getTime() < 0.2){
					setNoTimeConsumed(true);
					return;
				}
				getProgram().getShip().thrustOn();
				getProgram().moveTime();
			}
		};
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
return new Action(location){
			
			@Override
			public void run(){
				setNoTimeConsumed(false);
				getProgram().setLocation(getLocation());
				if (getProgram().getTime() < 0.2){
					setNoTimeConsumed(true);
					return;
				}
				getProgram().getShip().thrustOff();
				getProgram().moveTime();
			}
		};
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new Action(location){
			
			@Override
			public void run(){
				setNoTimeConsumed(false);
				getProgram().setLocation(getLocation());
				if (getProgram().getTime() < 0.2){
					setNoTimeConsumed(true);
					return;
				}
				getProgram().getShip().fire();
				getProgram().moveTime();
			}
		};
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		return new Action(location){
			
			@Override
			public void run(){
				setNoTimeConsumed(false);
				getProgram().setLocation(getLocation());
				if (getProgram().getTime() < 0.2){
					setNoTimeConsumed(true);
					return;
				}
				getProgram().getShip().turn((Double)angle.evaluate());
				getProgram().moveTime();
			}
		};
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
return new Action(location){
			
			@Override
			public void run(){
				setNoTimeConsumed(false);
				getProgram().setLocation(getLocation());
				if (getProgram().getTime() < 0.2){
					setNoTimeConsumed(true);
					return;
				}
				
				getProgram().moveTime();
			}
		};
	}

}
