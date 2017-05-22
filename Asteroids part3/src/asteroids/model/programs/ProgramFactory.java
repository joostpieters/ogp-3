package asteroids.model.programs;

import java.util.List;
import java.util.Optional;

import asteroids.model.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		// TODO Auto-generated method stub
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName,
			Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName,
			Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Assignment(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Break(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Return(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IfElse(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	
	public Statement createPrintStatement(Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Sequence(statements, sourceLocation);
	}

	@Override
	public Expression createReadVariableExpression(String variableName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReadVariable(variableName, sourceLocation);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Param(parameterName, sourceLocation);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName,
			List<Expression> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new FunctionCall(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression<Double> createChangeSignExpression(Expression expression,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SignChange(expression, sourceLocation);
	}

	@Override
	public Expression<Boolean> createNotExpression(Expression expression,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new LogicalNegation(expression, sourceLocation);
	}

	@Override
	public Expression<Double> createDoubleLiteralExpression(double value,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new DoubleLiteral(value, location);
	}

	@Override
	public Expression<CircularObject> createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new CircObj(location){

			@Override
			public CircularObject calculate() {
				return null;
			}
			
		};
	}

	@Override
	public Expression<CircularObject> createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new CircObj(location){

			@Override
			public Ship calculate() {
				return getProgram().getShip();
			}
		};
	}

	@Override
	public Expression<CircularObject> createShipExpression(SourceLocation location) {
		return new CircObj(location){

			@Override
			public Ship calculate() {
				Ship self = getProgram().getShip();
				Optional<Ship> closestShip = self.getWorld().getAllShipsInWorld().stream().filter(ship -> !ship.equals(self)).
					reduce((ship1, ship2) -> (self.getDistanceBetween(ship1) < self.getDistanceBetween(ship2) ? ship1 : ship2));
				if (closestShip.isPresent()) return closestShip.get();
				return null;
			}
			
		};
	}

	@Override
	public Expression<CircularObject> createAsteroidExpression(SourceLocation location) {
		return new CircObj(location){

			@Override
			public Asteroid calculate() {
				Ship self = getProgram().getShip();
				Optional<Asteroid> closestAsteroid = self.getWorld().getAllAsteroidsInWorld().stream().
					reduce((asteroid1, asteroid2) -> (self.getDistanceBetween(asteroid1) < self.getDistanceBetween(asteroid2) ? asteroid1 : asteroid2));
				if (closestAsteroid.isPresent()) return closestAsteroid.get();
				return null;
			}
			
			@Override
			public String toString() {
				return "[AsteroidExpression]";
			}
			
		};
	}

	@Override
	public Expression<CircularObject> createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new CircObj(location){

			public Planetoid calculate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Planetoid> closestPlanetoid = self.getWorld().getAllPlanetoidsInWorld().stream().
						reduce((planetoid1, planetoid2) -> (self.getDistanceBetween(planetoid1) < self.getDistanceBetween(planetoid2) ? planetoid1 : planetoid2));
					if (closestPlanetoid.isPresent()) return closestPlanetoid.get();
					return null;
			}
			
		};
	}

	@Override
	public Expression<CircularObject> createBulletExpression(SourceLocation location) {
		return new CircObj(location){

			@Override
			public Bullet calculate() {
				Ship self = getProgram().getShip();
				Optional<Bullet> closestBullet = self.getWorld().getAllBulletsInWorld().stream().filter(bullet -> bullet.getSourceShip() == self).
						reduce((bullet1, bullet2) -> (self.getDistanceBetween(bullet1) < self.getDistanceBetween(bullet2) ? bullet1 : bullet2));
					if (closestBullet.isPresent()) return closestBullet.get();
					return null;
			}
			
			
		};
	}

	@Override
	public Expression<CircularObject> createPlanetExpression(SourceLocation location) {
		return new CircObj(location){

			@Override
			public MinorPlanet calculate() {
				Ship self = getProgram().getShip();
				Optional<CircularObject> closestPlanet = self.getWorld().getAllCircularObjectsInWorld().stream().filter(entity -> entity instanceof MinorPlanet).
						reduce((planet1, planet2) -> (self.getDistanceBetween(planet1) < self.getDistanceBetween(planet2) ? planet1 : planet2));
					if (closestPlanet.isPresent()) return (MinorPlanet)closestPlanet.get();
					return null;
			}
			
		};
	}

	@Override
	public Expression<CircularObject> createAnyExpression(SourceLocation location) {
		return new CircObj(location){

			@Override
			public CircularObject calculate() {
				Ship self = getProgram().getShip();
				Optional<CircularObject> anyEntity = self.getWorld().getAllCircularObjectsInWorld().stream().findAny();
					if (anyEntity.isPresent()) return anyEntity.get();
					return null;
			}
			
		
		};
	}

	@Override
	public Expression<Double> createGetXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new Getx(e, location);
	}

	@Override
	public Expression<Double> createGetYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new Gety(e, location);
	}

	@Override
	public Expression<Double> createGetVXExpression(Expression e,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetVx(e, location);
	}

	@Override
	public Expression<Double> createGetVYExpression(Expression e,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetVy(e, location);
	}

	@Override
	public Expression<Double> createGetRadiusExpression(Expression e,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetRadius(e, location);
	}

	@Override
	public Expression<Boolean> createLessThanExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new Comparison(e1, e2, location);
	}

	@Override
	public Expression<Boolean> createEqualityExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new Equals(e1, e2, location);
	}

	@Override
	public Expression<Double> createAdditionExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated method stub
		//return new AdditionExpression(e1, e2, location);
		return new Addition(e1, e2, location);
	}

	@Override
	public Expression<Double> createMultiplicationExpression(Expression e1,
			Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new Multiply(e1, e2, location);
	}

	@Override
	public Expression<Double> createSqrtExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		
		return new SquareRoot(e, location);
	}

	@Override
	public Expression<Double> createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetDir(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ThrustOn(location);

			
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ThrustOff(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new Fire(location);
	}

	@Override
	public Statement createTurnStatement(Expression angle,
			SourceLocation location) {
		return new Turn(angle, location);

	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new Skip(location);
	}


}
