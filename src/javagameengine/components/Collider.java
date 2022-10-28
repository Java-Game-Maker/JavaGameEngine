package javagameengine.components;

import javagameengine.CollisionEvent;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Debug;
import testing.Main;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Collider extends Component{

    protected boolean visible = true;
    protected LinkedList<String> ignoreTags = new LinkedList<>();
    protected boolean trigger = false;
    public Collider(LinkedList<Vector2> localVertices) {
        super(localVertices);
    }
    public Collider(boolean visible) {
        this.visible = visible;
    }
    public Collider() {
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public void addIgnoreTag(String s) {
        ignoreTags.add(s);
    }
    public void removeIgnoreTag(String s){
        ignoreTags.remove(s);
    }
    public LinkedList<String> getIgnoreTags(){
        return ignoreTags;
    }
    /**
     * If true it will be renderd as a green outline
     * @return true if it should be renderd
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * If true it will render as a green outline
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Checks all the components in the scene of they have collided
     * @param c component
     * @return
     */
    public Point collision(Collider c){
        // check if my points are inside c
        for (Vector2 vertex : vertices){
            Point p = new Point((int) vertex.getX(), (int) vertex.getY());
            if(c.getPolygon().contains(p)){
                return p;
            }
        }
        return null;
    }

    /**
     * checks all the components if they have collided with me
     * @param me the component who called
     * @return the our point that collided
     */
    public static Point isCollision(Component me){
        // check if my points are inside c
        for(Component component: Main.gameInstance.getSelectedScene().getComponents1()) {
            if (component != me) {
                for (Component c : component.getChildren(new Collider())) {
                    for (Vector2 vertex : me.vertices) {
                        Point p = new Point((int) vertex.getX(), (int) vertex.getY());
                        if (c.getPolygon().contains(p)) {
                            return p;
                        }
                    }
                }
            }
        }
        return null;
    }

    private Vector2 getNearestPoint(LinkedList<Vector2> vertices,Vector2 point){
        Vector2 oldPoint = vertices.get(0);

        for(Vector2 p : vertices){
            if(p.getDistance(point) < oldPoint.getDistance(point)) oldPoint = p;
        }
        return oldPoint;
    }

    /**
     * moves us back so we dont collide anymore
     * @param c the component we collided with
     * @param point is the point which collided
     */
    public void moveBack(Component c, Vector2 point){
        // the direction to move back the object
        Vector2 dir = Vector2.getDirection(getFirstParent().getPosition().lookAt(prevPosition));
        if(!((Float) getFirstParent().getPosition().lookAt(prevPosition)).isNaN()){
            int i = 0;

            while(true){
                // gets the direction to move
                getFirstParent().setPosition(getFirstParent().getPosition().add(dir)); // move back
                getFirstParent().updateVertices(); // update shape
                vertices = getFirstParent().vertices; // update collider shape
                if(collision( (Collider) c )==null){ // check if we still are collided if so we stop
                    break;
                }
                if(i>=10000)
                    break;
                i++;
            }
        }
        //sets the rotational point
        PhysicsBody b = ((PhysicsBody) getFirstParent().getChild(new PhysicsBody()));
        if(b!=null){
            b.setRotationalPoint(point);
        }
        PhysicsBody b2 = ((PhysicsBody) c.getFirstParent().getChild(new PhysicsBody()));
        if(b2!=null){
            b2.setRotationalPoint(point);
        }

    }
    public boolean inside(Component component){
        //Debug.log(component.getPosition().getDistance(JavaGameEngine.getSelectedScene().getCamera().getPosition()));
        //return (component.getPosition().getDistance(JavaGameEngine.getSelectedScene().getCamera().getPosition()) < 1500);
        //Debug.log(String.valueOf(JavaGameEngine.getSelectedScene().getCamera().getPosition().add(component.getPosition()).getMagnitude()<1000));
        return JavaGameEngine.getSelectedScene().getCamera().getPosition().add(component.getPosition()).getMagnitude()<1000;
    }
    Vector2 point = null;
    LinkedList<Vector2> lastVerices = new LinkedList<>();
    @Override
    public void update() {
        super.update();
        //loops though all the components in the scene
        // and checks if their colliders are colling and resolve it
        point = null;
        for(Component component: JavaGameEngine.getSelectedScene().getComponents1()){
            if(component != getFirstParent()){ // dont check us
                Collider c = (Collider) component.getChild(new Collider());
                //ignores objects with a tag that is inside ignore tag
                if (c!=null && !ignoreTags.contains(c.getTag()) && !c.ignoreTags.contains(getTag())){
                    Point collsionPoint = null; // the point which collided (null if not collided)

                    if((collsionPoint=collision( (Collider) c ) )!=null){
                        point = new Vector2((float) collsionPoint.getX(), (float) collsionPoint.getY());

                        try{
                            // first we move back our object then we can response
                            PhysicsBody me = (PhysicsBody) getFirstParent().getChild(new PhysicsBody());

                            CollisionEvent collisionEvent = new CollisionEvent(this,(Collider) c,point);
                            if(isTrigger() && ((Collider) c).isTrigger()){
                                getFirstParent().onTriggerEnter(collisionEvent);
                                c.getFirstParent().onTriggerEnter(collisionEvent);
                            }else{
                                moveBack(c, point); // move the object back
                                try {
                                    me.response(collisionEvent); // responde with physics
                                }catch (NullPointerException e){
                                    Debug.log(getFirstParent()+" has no physicsbody to respond to the collision ");
                                }
                                // calls events
                                getFirstParent().onCollisionEnter(collisionEvent);
                                c.getFirstParent().onCollisionEnter(collisionEvent);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        lastVerices = localVertices;
                        prevPosition = getFirstParent().getPosition();
                    }

                }
            }
        }
    }

    /**
     * renders green outline
     * @param g what graphics to render to
     */
    @Override
    public void render(Graphics2D g) {
        super.render(g);
        if(visible){
            Color c = g.getColor();
            g.setColor(Color.green);
            g.drawPolygon(getPolygon());
            g.setColor(Color.red);
            Vector2 dir = Vector2.getDirection(getFirstParent().getPosition().lookAt(prevPosition));
            /*if(point != null)
                g.fillOval((int) point.getX(), (int) point.getY(),10,10);*/
            g.setColor(c);

        }
    }
}
