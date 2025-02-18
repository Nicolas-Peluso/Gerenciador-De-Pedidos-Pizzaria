import './App.css';
import EntrarPage from './Entrar/Entrar';
import GlobalConte from './Context/GlobalContext';
import Header from './Header/Header';
import { BrowserRouter, Route, Routes } from 'react-router';
import DashBoard from './DashBoard/DashBoard';

function App() {

  return (
    <div className="App">
     <BrowserRouter>
        <GlobalConte>
            <Header />
              <Routes>
                  <Route path="entrar" element={<EntrarPage />} />
                  <Route path="dashboard" element={<DashBoard />} />
              </Routes>
        </GlobalConte>
      </BrowserRouter>
    </div>
  ); 
}

export default App;
