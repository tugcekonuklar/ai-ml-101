package com.tkonuklar.search.york;

import java.util.*;

public class CleanerBFS {
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private static final String SUCK = "SUCK";

    private Queue<Node> frontier = new LinkedList<>();
    private Set<Node> explodedSet = new HashSet<>();
    private List<String> actions = List.of(LEFT, RIGHT, SUCK);

    public static void main(String[] args) {
        State initialState = new State(LEFT, true, new ArrayList<>(Arrays.asList(LEFT, RIGHT)));
        List<State> goalStates = List.of(new State(LEFT, false, List.of()),
                new State(RIGHT, false, List.of()));
        CleanerBFS main = new CleanerBFS();
        boolean result = main.search(initialState, goalStates);
        System.out.println(result);
    }

    public boolean search(final State initialState, final List<State> goalStates) {
        if (initialState == null) {
            return false;
        }
        if (goalTest(initialState, goalStates)) {
            return true;
        }
        Node root = new Node(initialState);
        frontier.add(root);
        do {
            Node node = frontier.remove();
            explodedSet.add(node);
            for (String action : actions) {
                Node child = getChildNode(node, action);
                if (!explodedSet.contains(child)) {
                    if (goalTest(child.getState(), goalStates)) {
                        return true;
                    }
                    frontier.add(child);
                }
            }
        } while (!frontier.isEmpty());
        return false;
    }

    private Node getChildNode(Node node, String action) {
        State state = transitionModelResult(node.getState(), action);
        return new Node(state);
    }

    private State transitionModelResult(State state, String action) {
        State resultState = new State(state.getPosition(), state.isHasDirt(), new ArrayList<>(state.getSides()));
        switch (action) {
            case RIGHT:
            case LEFT:
                if (!resultState.getPosition().equals(action)) {
                    resultState.setPosition(action);
                }
                break;
            case SUCK:
                if (resultState.isHasDirt()
                        && resultState.getSides().contains(resultState.getPosition())) {
                    resultState.getSides().remove(resultState.getPosition());
                    if (resultState.getSides().isEmpty()) {
                        resultState.setHasDirt(false);
                    }
                }
                break;
        }
        return resultState;
    }

    private boolean goalTest(State state, List<State> goalStates) {
        return goalStates.stream()
                .anyMatch(goal -> goal.isEqual(state));
    }

}

