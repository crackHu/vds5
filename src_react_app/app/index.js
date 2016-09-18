require('es5-shim');
require('es5-shim/es5-sham');
require('console-polyfill');

const React = require('react');
const ReactDOM = require('react-dom');

import {
	useRouterHistory
} from 'react-router';
import {
	createHashHistory
} from 'history';

import configureStore from './store/configureStore';
import Root from './containers/Root';

import './assets/scss/style.scss';

const store = configureStore();
const app = document.querySelector('.root');

const appHistory = useRouterHistory(createHashHistory)({
	queryKey: false
})

ReactDOM.render(
	<Root
      store={ store } history={appHistory}
    />,
	app
);

if (module.hot) {
	module.hot.accept('./containers/Root', () => {
		const RootContainer = require('./containers/Root').default;
		ReactDOM.render(
			<RootContainer
          store={ store } history={appHistory}
        />,
			app
		);
	});
}