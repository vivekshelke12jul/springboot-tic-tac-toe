import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { FaRegCircle } from "react-icons/fa6";
import { RxCross1 } from "react-icons/rx";



const MainGame = () => {

    const [data,setData] = useState(null)
    const [player,setPlayer] = useState(0)
    const [clickedBoxes, setClickedBoxes] = useState([])
    const [winner,setWinner] = useState(null)

    useEffect(()=>{
      const fetchData = async () => {
        try {
          const response = await axios.get('http://localhost:8080/state');
          console.log(response.data)
          setData(response.data);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
  
      fetchData();
    },[player,winner])

    useEffect(()=>{
      const fetchData = async () => {
        try {
          const response = await axios.get('http://localhost:8080/winner');
          if(response.data==0){
            setWinner("Player 1")
          }else if(response.data == 1){
            setWinner("Player 2")
          }
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
      fetchData();
    })

    const onBoxClick = ( index) => {
      if (clickedBoxes.includes(index)) {
        return;
    }

      const postData = {
          player:player,
          pos: index
      };
  
      axios.post('http://localhost:8080/state', postData)
        .then(response => {
          console.log('Post request successful:', response.data);
        })
        .catch(error => {
          console.error('Error posting data:', error);
      });

      setClickedBoxes([...clickedBoxes, index]);
      setPlayer(player === 0 ? 1 : 0);
    };

    const BoxIcon = ({value}) =>{
      if(value==-1){
        return "";
      }else if(value==1){
        return <RxCross1 className='icon' />
      }else if(value==0){
        return <FaRegCircle className='icon' />
      }
    }

    const newGame = () => {
      const fetchData = async () => {
          try {
              const response = await axios.get('http://localhost:8080/reset');
              return response.data;
          } catch (error) {
              console.error('Error fetching data:', error);
              throw error;
          }
      };
  
      fetchData()
          .then(() => {
              Promise.all([
                  setWinner(null),
                  setClickedBoxes([]),
                  setPlayer(0),
                  setData(null)
              ]).then(() => {
                  console.log('All state updates completed.');
              }).catch(error => {
                  console.error('Error updating state:', error);
              });
          });
  };

  return (
    <div className='game-container'>
      {winner ?
        <div className='new-game'>
          <h1 className='winner'>{winner} won!🎉</h1>
          <button className='new-game-button' onClick={newGame}>New Game</button>
        </div>
        :
        <>
          <div className='box-container'>

            {data &&
              data.map((item, index)=>{
                return <div onClick={()=>onBoxClick(index)} className='box' key={index}>{<BoxIcon value={item} />}</div>
              })
            }
          </div>
          {player==0? <p>Player 1</p> : <p>Plyer 2</p>}
        </>
      }
      {
        clickedBoxes.length == 9 &&
          <button className='new-game-button' onClick={newGame}>New Game</button>
      }
    </div>
  )
}

export default MainGame