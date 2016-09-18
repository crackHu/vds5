import {
	combineReducers
} from 'redux';
import 'babel-polyfill'
import counter from './counter';
import app from './app';
import archive from './archive';

const rootReducer = combineReducers({
	counter,
	app,
	archive,
});

export default rootReducer;