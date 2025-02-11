import './App.css';
import { useContext } from 'react';
import EntrarPage from './Entrar/Entrar';
import GlobalConte from './Context/GlobalContext';

function App() {

  return (
    <div className="App">
      <GlobalConte>
          <EntrarPage />
      </GlobalConte>

    </div>
  ); 
}

export default App;
