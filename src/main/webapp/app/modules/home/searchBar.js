import { React, useState } from 'react';

function App() {
  return (
    <div className="main">
      <h1>React Search</h1>
      <div className="search">
        <TextField fullWidth label="Search" />
      </div>
      <List />
    </div>
  );
}

export default App;
