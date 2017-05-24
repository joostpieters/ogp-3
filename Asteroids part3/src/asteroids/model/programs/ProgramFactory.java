package asteroids.model.programs;

import java.util.List;
import java.util.Optional;

import asteroids.model.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName,
			Statement body, SourceLocation sourceLocation) {
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName,
			Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value,
			SourceLocation sourceLocation) {
		return new Return(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		return new IfElse(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression value,
			SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new Sequence(statements, sourceLocation);
	}

	@Override
	public Expression createReadVariableExpression(String variableName,
			SourceLocation sourceLocation) {
		return new ReadVariable(variableName, sourceLocation);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName,
			SourceLocation sourceLocation) {
		return new Param(parameterName, sourceLocation);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName,
			List<Expression> actualArgs, SourceLocation sourceLocation) {
		return new FunctionCall(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression<Double> createChangeSignExpression(Expression expression,
			SourceLocation sourceLocation) {
		return new SignChange(expression, sourceLocation);
	}

	@Override
	public Expression<Boolean> createNotExpression(Expression expression,
			SourceLocation sourceLocation) {
		return new LogicalNegation(expression, sourceLocation);
	}

	@Override
	public Expression<Double> createDoubleLiteralExpression(double value,
			SourceLocation location) {
		return new DoubleLiteral(value, location);
	}

	@Override
	public Expression<CircularObject> createNullExpression(SourceLocation location) {
		return new NullExpression(location);
	}

	@Override
	public Expression<CircularObject> createSelfExpression(SourceLocation location) {
		return new SelfExpression(location);
	}

	@Override
	public Expression<CircularObject> createShipExpression(SourceLocation location) {
		return new ShipExpression(location);
	}

	@Override
	public Expression<CircularObject> createAsteroidExpression(SourceLocation location) {
		return new AsteroidsExpression(location);
	}

	@Override
	public Expression<CircularObject> createPlanetoidExpression(SourceLocation location) {
		return new PlanetoidExpression(location);
	}

	@Override
	public Expression<CircularObject> createBulletExpression(SourceLocation location) {
		return new BulletExpression(location);
	}

	@Override
	public Expression<CircularObject> createPlanetExpression(SourceLocation location) {
		return new PlanetExpression(location);
	}

	@Override
	public Expression<CircularObject> createAnyExpression(SourceLocation location) {
		return new AnyExpression(location);
	}

	@Override
	public Expression<Double> createGetXExpression(Expression e, SourceLocation location) {
		return new Getx(e, location);
	}

	@Override
	public Expression<Double> createGetYExpression(Expression e, SourceLocation location) {
		return new Gety(e, location);
	}

	@Override
	public Expression<Double> createGetVXExpression(Expression e,
			SourceLocation location) {
		return new GetVx(e, location);
	}

	@Override
	public Expression<Double> createGetVYExpression(Expression e,
			SourceLocation location) {
		return new GetVy(e, location);
	}

	@Override
	public Expression<Double> createGetRadiusExpression(Expression e,
			SourceLocation location) {
		return new GetRadius(e, location);
	}

	@Override
	public Expression<Boolean> createLessThanExpression(Expression e1, Expression e2,
			SourceLocation location) {
		return new Comparison(e1, e2, location);
	}

	@Override
	public Expression<Boolean> createEqualityExpression(Expression e1, Expression e2,
			SourceLocation location) {
		return new Equals(e1, e2, location);
	}

	@Override
	public Expression<Double> createAdditionExpression(Expression e1, Expression e2,
			SourceLocation location) {
		return new Addition(e1, e2, location);
	}

	@Override
	public Expression<Double> createMultiplicationExpression(Expression e1,
			Expression e2, SourceLocation location) {
		return new Multiply(e1, e2, location);
	}

	@Override
	public Expression<Double> createSqrtExpression(Expression e, SourceLocation location) {
		return new SquareRoot(e, location);
	}

	@Override
	public Expression<Double> createGetDirectionExpression(SourceLocation location) {
		return new GetDir(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new ThrustOn(location);

			
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new ThrustOff(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new Fire(location);
	}

	@Override
	public Statement createTurnStatement(Expression angle,
			SourceLocation location) {
		return new Turn(angle, location);

	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new Skip(location);
	}


}
