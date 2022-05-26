

export default function Introduce(){
    return(
    <div className="flex flex-col items-center w-full h-screen p-5 gap-2" >
        <h1 className="text-4xl" >Introduce</h1>
        <p>Java game engine is a lightweight 2D game engine built 
            apon the java graphics api swing. The engine is simular 
            to the popular unity engine. 
        </p>
        <p>
            Gameobjects are is a component that can have other Components
            as children and can add functionallity to the gameobject.
            Example a player have a Physicsbody component and a Sqarecollider component
            as children. 
        </p>
    </div>)
}