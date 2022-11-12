package testing.portalgame;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.Grabber;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Portal extends Component {

    PortalPiece portal1 = new PortalPiece(); //entrance
    PortalPiece portal2 = new PortalPiece(); // exit

    public Portal(Vector2 entrance, Vector2 exit){

        portal1.other = portal2;
        portal2.other = portal1;

        add(portal1);
        add(portal2);

        portal1.setParentOffset(entrance);
        portal2.setParentOffset(exit);

        setPosition(Vector2.zero);
    }

    class PortalPiece extends GameObject{
        public PortalPiece other;
        @Override
        public void start() {

            super.start();

        }

        public PortalPiece(){
            Collider c1 = new Collider();
            c1.setTrigger(true);
            add(c1);
            add(new Grabber(this));
        }

        @Override
        protected void onTriggerEnter(CollisionEvent collisionEvent) {
            super.onTriggerEnter(collisionEvent);
            Debug.log("asd");
            Component ob = collisionEvent.getCollider2().getParent();
            Vector2 newPos = other.getPosition().add(ob.getScale().add(this.getScale()).removeX());
            ob.setPosition(newPos);
        }

        @Override
        public void setMouseInside(boolean mouseInside) {
            super.setMouseInside(mouseInside);
            Debug.log("asd");
        }
    }

}
