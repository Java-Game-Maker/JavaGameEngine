package JavaGameEngine.Backend.Node;

import JavaGameEngine.Components.Component;

public class Node {

    private Component value;

    public Node(Component value) {
        this.value = value;
    }

    public Node() {
    }

    public Component getValue() {
        return value;
    }

    public void setValue(Component value) {
        this.value = value;
    }
}
