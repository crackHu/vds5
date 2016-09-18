import {
	GET_APP_INDEX_MENU,
	INCREMENT_COUNTER,
	DECREMENT_COUNTER,
	GET_ARCHIVES
} from '../constants/ActionTypes';

export default function app1(state = {}, action) {
	let pState = [...state]
	switch (action.type) {
		case GET_APP_INDEX_MENU:
			return action.data.items
		default:
			return state
	}
}