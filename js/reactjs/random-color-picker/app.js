'use strict';

function App(props) {
  const [color, setColor] = React.useState([123, 123, 123]);

  React.useEffect(() => {
    document.body.style.background = formatColor(color);
  })

  const formatColor = (arr) => "rgb(" + arr.join(", ") + ")";
  const changeColor = () => {
    const random = [];
    for (let i = 0; i < 3; i++) {
      random.push(Math.floor(Math.random() * 256));
    }
    setColor(random);
  }

  return (
    <div>
      <h1>Current color: {formatColor(color)}</h1>
      <Button onClick={changeColor} />
    </div>
  )
}

function Button(props) {
  return (
    <button onClick={props.onClick}>Change color</button>
  )
}

ReactDOM.render(<App />, document.querySelector('#root'));
