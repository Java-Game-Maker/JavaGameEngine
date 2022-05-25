
import { useState } from 'react';
import './App.css';
import Menu from './Components/Menu';
import Introduce from "./Pages/Introduce"
import Exampels from './Pages/Exampels';
import GameObject from './Pages/GameObject';
import Component from './Pages/Component'


function App() {
  const [page,setPage] = useState(0);
  const pages = [<Introduce></Introduce>,<GameObject></GameObject>,<Component></Component>,<Exampels></Exampels>]

  return (
    <div className="bg-gray-800 text-white">
      <Menu trigger={setPage} pages={["Introduce","GameObject","Component","Exaples"]} ></Menu>
      <div className='pl-44 flex flex-col items-center p-2 '>
        {pages[page]}
      </div>
    </div>
  );
}

export default App;
