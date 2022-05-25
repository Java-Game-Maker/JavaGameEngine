import Exampel from "../Components/Example";
import FunctionList from "../Components/FunctionList";

export default function GameObject(){
    let third="import JavaGameEngine.Backend.Input.Input;\nimport JavaGameEngine.Backend.Input.Keys;\nimport JavaGameEngine.Components.GameObject;\nimport JavaGameEngine.msc.Vector2;\n\nimport java.awt.*;\n\npublic class Player extends GameObject {\n    public Player(){\n        setPosition(new Vector2(300,300)); // change spawn position of our player\n        setScale(new Vector2(50,50)); // change spawn scale to 50*50 units\n    }\n\n    // draw function draws every update\n    // u can use the graphics as usal here\n    @Override\n    public void draw(Graphics g) {\n        super.draw(g);\n    }\n\n    @Override\n    public void update() { // update function, runs every update\n        super.update();\n        if(Input.isKeyDown(Keys.D)){ //Check if D key is down\n            movePosition(getPosition().add(Vector2.right)); // move player position right if so\n        }\n    }\n"

    return(
    <div className="flex flex-col items-center w-full p-5 gap-2" >
        <h1 className="text-4xl" >Gameobject</h1>
        <p>
            The GameObject is a subset of the Components class. The GameObject has the 
            movePosition function that sets the postion if the GameObject doesn't collide 
            with another object
        </p>
        <FunctionList list={[{name:"setPostion(Vector2)",params:"new position (Vector2)",disc:"setPosition"},
    {name:"getPostion()",params:"",disc:"returns Vector2 position"},
    {name:"setScale(Vector2)",params:"new scale (Vector2)",disc:"set Scale"},
    {name:"getScale()",params:"",disc:"returns scale vector2"},
    {name:"setSpritePosition(Vector2)",params:"new SpritePosition",disc:"sets the position of where the component will be renderd"+
" this updates automatecly and should not be set normaly"},
    {name:"getSpritePostion()",params:"",disc:"returns SpritePosition vector2"},
    {name:"getScale()",params:"",disc:"returns scale vector2"},

    ]} ></FunctionList>
        <Exampel code={third} title="Example of a player" ></Exampel>
    </div>)
}