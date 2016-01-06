# webpack intro

### installation

```bash
npm install -g webpack
```

### without webpack.config

```bash
webpack -w ./entry.js bundle.js
webpack -w ./entry.js bundle.js --module-bind 'css=style!css'
```

### with webpack.config

```bash
webpack -w
```

### use webpack-dev-server

```bash
npm install -g webpack-dev-server
webpack-dev-server
open http://localhost:8080/webpack-dev-server/bundle
```

