import React, { useState } from 'react';

function ChatGenerator(){

    const[prompt,setPrompt]=useState('');
    const[chatResponse,setChatResponse] = useState('');
    const askAI = async() =>{
         try{
            const response = await fetch(`http://localhost:8080/ask-ai?prompt=${prompt}`);
            const data= await response.text();
            setChatResponse(data);
        }catch(error){
            console.error("Error generating image : ",error)
        }
    }
    return(
        <div>
            <h2>Generate Chat</h2>
            <input type='text'
            value={prompt}
            onChange={(e)=>setPrompt(e.target.value)}
            placeholder='Enter prompt for AI'/>
            <button onClick={askAI}>ASK AI</button>
            <div className='output'>
                <p>{chatResponse}</p>
            </div>
        </div>
    );
}

export default ChatGenerator;