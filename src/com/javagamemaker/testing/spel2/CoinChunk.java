package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.ArrayList;

public class CoinChunk extends Component {
    public static Vector2[] box = new Vector2[]{
            new Vector2(0,0),
            new Vector2(1,0),
            new Vector2(2,0),
            new Vector2(3,0),
            new Vector2(4,0),
            new Vector2(5,0),
            new Vector2(0,1),
            new Vector2(1,1),
            new Vector2(2,1),
            new Vector2(3,1),
            new Vector2(4,1),
            new Vector2(5,1),
    };
    public static Vector2[] pipe = new Vector2[]{
            new Vector2(3,0),
            new Vector2(3,1),
            new Vector2(3,2),
            new Vector2(3,3),
            new Vector2(3,4),
            new Vector2(3,5),
            new Vector2(3,6),
            new Vector2(3,7),
            new Vector2(3,8),
            new Vector2(3,9),
    };
    public static Vector2[] spiral = new Vector2[]{
            new Vector2(0,0),
            new Vector2(1,1),
            new Vector2(2,2),
            new Vector2(3,3),
            new Vector2(4,4),
            new Vector2(5,5),
            new Vector2(4,6),
            new Vector2(3,7),
            new Vector2(2,8),
            new Vector2(1,9),
            new Vector2(0,10),
    };
    public CoinChunk(Vector2[] coinPos,Vector2 pos){
        for(Vector2 offset : coinPos){
            Coin c = new Coin(pos.add(offset.multiply(100).add(new Vector2(50,0))));
            Main.getSelectedScene().instantiate(c);

        }
    }


}
