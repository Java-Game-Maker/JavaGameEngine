
function Button(props){
    return(
    <div onClick={props.trigger} className=" text-left cursor-pointer p-2 scale-100 hover:scale-105  " >
        <h1>{props.children}</h1>
    </div>)
}

export default function Menu(props) {
    let buttons = props.pages.map((page,index) =>
        <div>
            <Button trigger={()=>props.trigger(index)}>{page}</Button>
        </div>)
    
    return (
    <div className="flex flex-col fixed w-44 shadow-lg h-screen bg-slate-400 p-4 ">
        {buttons}
    </div>
  );
}
