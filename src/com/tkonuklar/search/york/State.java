package com.tkonuklar.search.york;

import java.util.List;

public class State {
    private String position;
    private boolean hasDirt;
    private List<String> sides;

    public State(String position, boolean hasDirt, List<String> sides) {
        this.setPosition(position);
        this.setHasDirt(hasDirt);
        this.setSides(sides);
    }

    public State() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isHasDirt() {
        return hasDirt;
    }

    public void setHasDirt(boolean hasDirt) {
        this.hasDirt = hasDirt;
    }

    public List<String> getSides() {
        return sides;
    }

    public void setSides(List<String> sides) {
        this.sides = sides;
    }

    public boolean isEqual(State state) {
        return this.isHasDirt() == state.isHasDirt()
                && this.getPosition().equals(state.getPosition())
                && this.getSides().containsAll(state.getSides());
    }

    @Override
    public boolean equals(Object stateIn) {
        State state = (State) stateIn;
        return this.isHasDirt() == state.isHasDirt()
                && this.getPosition().equals(state.getPosition())
                && this.getSides().containsAll(state.getSides());
    }
}
