import React,{useState} from 'react';
import './App.css';
import ImageGenerator from './components/ImageGenerator';
import ChatGenerator from './components/ChatGenerator';
import RecipeGenerator from './components/RecipeGenerator';

function App() {

  const [activeTab,setActiveTab] = useState('image-generator');
  const handleTabChange = (tab) =>{
    //alert(tab);
    setActiveTab(tab);
  }
  return (
    <div className="App">
      <button className={activeTab === 'image-generator'?'active':''} 
      onClick={()=>handleTabChange('image-generator')}>
        Image Genereator</button>
      <button className={activeTab === 'chat-generator'?'active':''}  
      onClick={()=>handleTabChange('chat-generator')}>
        Ask AI</button>
      <button  className={activeTab === 'recipe-generator'?'active':''}
      onClick={()=>handleTabChange('recipe-generator')}>
        Recipe Genereator</button>
        <div>
          {activeTab==='image-generator' && <ImageGenerator/>}
          {activeTab==='chat-generator' && <ChatGenerator/>}
          {activeTab==='recipe-generator' && <RecipeGenerator/>}
        </div>
    </div>
  );
}

export default App;
