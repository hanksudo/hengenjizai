var Hello = React.createClass({
    render: function() {
        return <div>Hello {this.props.name}</div>;
    }
});

ReactDOM.render(
    <Hello name="React" />,
    document.getElementById('container')
);

ReactDOM.render(
    <h1>Hello, ReactJS!</h1>,
    document.getElementById('example')
);
