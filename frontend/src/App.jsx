import React from 'react'
import {Routes, Route} from 'react-router-dom';
import MainGame from './pages/MainGame';

const App = () => {
  return (
    <div>
      <Routes>
        <Route path='/game' element={<MainGame />} />
      </Routes>
    </div>
  )
}

export default App