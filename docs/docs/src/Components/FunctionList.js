
function Row(props){
    return(
    <tr className="border-b-2 border-gray-300  " >
        <td>{props.name}</td>
        <td>{props.params}</td>
        <td>{props.disc}</td>
    </tr>)
}

export default function FunctionList(props){
    let rows = props.list.map(row=>
        <Row name={row.name} params={row.params} disc={row.disc} ></Row>)
    return(
        <div className="flex flex-col gap-2 p-2 rounded-lg bg-gray-200 w-max text-gray-900">
            <table>
                <tr  className="border-b-2 border-gray-300">
                    <th>Function name</th>
                    <th>Params</th>
                    <th>Discription</th>
                </tr>
                {rows}
            </table> 
        </div>
    );
}