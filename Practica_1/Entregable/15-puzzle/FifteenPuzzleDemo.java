package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.fifteenpuzzle.FifteenPuzzleBoard;
import aima.core.environment.fifteenpuzzle.FifteenPuzzleFunctionFactory;
import aima.core.environment.fifteenpuzzle.FifteenPuzzleGoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.BreadthFirstSearch;
//import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
//import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author Ravi Mohan
 * 
 */

public class FifteenPuzzleDemo {
	static FifteenPuzzleBoard boardWithThreeMoveSolution = new FifteenPuzzleBoard(
			new int[] { 4, 1, 2, 3, 8, 5, 6, 7, 12, 9, 10, 11, 0, 13, 14, 15 });

	public static void main(String[] args) {
		fifteenPuzzleBFSDemo();
		//fifteenpuzzleDLSDemo();
	}

	private static void fifteenpuzzleDLSDemo() {
		System.out.println("\nfifteenpuzzleDemo recursive DLS (3) -->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			Search search = new DepthLimitedSearch(3);
			SearchAgent agent = new SearchAgent(problem, search);
			executeActions(agent.getActions(), problem);
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void fifteenPuzzleBFSDemo() {
		System.out.println("\nFifteenPuzzleDemo BreadthFirstSearch BFS -->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			Search search = new BreadthFirstSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			executeActions(agent.getActions(), problem);
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}
	
	public static void executeActions(List<Action> actions, Problem problem) {
		Object initialState = problem.getInitialState();
		ResultFunction resultFunction = problem.getResultFunction();
		Object state = initialState;
		System.out.println("INITIAL STATE");
		System.out.println(state);
		for (Action action : actions) {
		 System.out.println(action.toString());
		 state = resultFunction.result(state, action);
		 System.out.println(state);
		 System.out.println("- - -");
		}
	}

}