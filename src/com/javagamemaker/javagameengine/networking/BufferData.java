package javagameengine.networking;

import javagameengine.msc.Vector2;

import java.util.LinkedList;

class Header
{
    String clientId;
    String custom;      // Pack data into a string.
}

class Body
{
    LinkedList<obVec> deltaPos = new LinkedList<obVec>();
}

class obVec
{
    String ID;
    Vector2 vec;
}

public class BufferData {
    Header h;
    Body   b;
}
