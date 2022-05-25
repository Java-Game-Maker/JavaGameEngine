import { CopyBlock,dracula } from "react-code-blocks";

export default function Exampel(props){
    return(
        <div>
            <p>{props.title}</p>
            <CopyBlock
                text={props.code}
                language={"java"}
                showLineNumbers={true}
                theme={dracula}/>
        </div>
    )
}