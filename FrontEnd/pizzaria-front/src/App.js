import './App.css';
import { useContext } from 'react';
import EntrarPage from './Entrar/Entrar';
import GlobalConte from './Context/GlobalContext';
import Header from './Header/Header';

function App() {

  return (
    <div className="App">
      <GlobalConte>
          <Header />
          <EntrarPage />
      </GlobalConte>
    </div>
  ); 
}

export default App;
