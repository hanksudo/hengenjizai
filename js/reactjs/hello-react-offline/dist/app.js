var Hello = React.createClass({
    displayName: 'Hello',

    render: function () {
        return React.createElement(
            'div',
            null,
            'Hello ',
            this.props.name
        );
    }
});

ReactDOM.render(React.createElement(Hello, { name: 'React' }), document.getElementById('container'));

ReactDOM.render(React.createElement(
    'h1',
    null,
    'Hello, ReactJS!'
), document.getElementById('example'));