
import Exampel from "../Components/Example";

export default function Exampels(){
    
    let first="package example;\nimport JavaGameEngine.JavaGameEngine;\n\npublic class Main extends JavaGameEngine{ // We extend JavaGameEngine\n\n    public static void main(String[] args){ // Main function\n        Main m = new Main(); // create a new instance of our main class\n        m.init(); // init our game\n\n        m.start(); // start our game\n    }\n}\n"
    let secound="package example;\nimport JavaGameEngine.Components.GameObject;\nimport JavaGameEngine.JavaGameEngine;\nimport JavaGameEngine.Backend.ComponentHandler;\n\n\npublic class Main extends JavaGameEngine{ // We extend JavaGameEngine\n\n    public static void main(String[] args){ // Main function\n        Main m = new Main(); // create a new instance of our main class\n        m.init(); // init our game\n        ComponentHandler.addObject(new GameObject()); // adding a empty Gameobject to our scene\n        m.start(); // start our game\n    }\n}\n"
    let third="import JavaGameEngine.Backend.Input.Input;\nimport JavaGameEngine.Backend.Input.Keys;\nimport JavaGameEngine.Components.GameObject;\nimport JavaGameEngine.msc.Vector2;\n\nimport java.awt.*;\n\npublic class Player extends GameObject {\n    public Player(){\n        setPosition(new Vector2(300,300)); // change spawn position of our player\n        setScale(new Vector2(50,50)); // change spawn scale to 50*50 units\n    }\n\n    // draw function draws every update\n    // u can use the graphics as usal here\n    @Override\n    public void draw(Graphics g) {\n        super.draw(g);\n    }\n\n    @Override\n    public void update() { // update function, runs every update\n        super.update();\n        if(Input.isKeyDown(Keys.D)){ //Check if D key is down\n            movePosition(getPosition().add(Vector2.right)); // move player position right if so\n        }\n    }\n\n}\n"
    let forth = "import JavaGameEngine.Backend.Input.Input;\nimport JavaGameEngine.Backend.Input.Keys;\nimport JavaGameEngine.Components.Collider.SquareCollider;\nimport JavaGameEngine.Components.GameObject;\nimport JavaGameEngine.Components.Physics.PhysicsBody;\nimport JavaGameEngine.msc.Vector2;\n\nimport java.awt.*;\n\npublic class Player extends GameObject {\n    PhysicsBody physicsBody = new PhysicsBody(); // create new physicsbody\n    SquareCollider collider = new SquareCollider(); // create new collider\n\n    public Player(){\n        setPosition(new Vector2(300,300)); // change spawn position of our player\n        setScale(new Vector2(50,50)); // change spawn scale to 50*50 units\n        addChild(physicsBody); //add physicsbody component\n        addChild(collider); //add collider component\n    }\n\n    // draw function draws every update\n    // u can use the graphics as usal here\n    @Override\n    public void draw(Graphics g) {\n        super.draw(g);\n    }\n\n    @Override\n    public void update() { // update function, runs every update\n        super.update();\n        if(Input.isKeyDown(Keys.D)){ //Check if D key is down\n            movePosition(getPosition().add(Vector2.right)); // move player position right if so\n        }\n    }\n\n}\n";
    let fith = "package example;\nimport JavaGameEngine.JavaGameEngine;\n\npublic class Main extends JavaGameEngine{ // We extend JavaGameEngine\n\n    public static void main(String[] args){ // Main function\n        Main m = new Main(); // create a new instance of our main class\n        m.init(); // init our game\n\n        m.start(); // start our game\n    }\n\n    @Override\n    public void update() { // Updates every update\n        super.update();\n\n    }\n}\n"

    return(
    <div className="flex flex-col p-2 gap-10 w-full" >
        <h1>Exampels</h1>
        <Exampel title="To create a a window we write this" code={first} ></Exampel>
        <Exampel title="Update function" code={fith} ></Exampel>
        <Exampel code={first} title={<p>Adding objects to our scene we use the <a href="" >ComponentHandler.addObject()</a> where we pass GameObject </p>} ></Exampel>
        <Exampel code={secound} title={<p>Adding objects to our scene we use the <a href="" >ComponentHandler.addObject()</a> where we pass GameObject </p>} ></Exampel>
        <Exampel code={third} title={<p>To create a custom <a href="/GameObject" >GameObject</a> you can extend</p>} ></Exampel>
        <Exampel code={forth} title={<p>Now we add <a href="/PhysicsBody" >PhysicsBody</a> and a <a href="/SqareCollider" >SqareCollider</a> to our player</p>} ></Exampel>

        
    </div>)
}