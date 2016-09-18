import {
	GET_APP_INDEX_MENU,
	INCREMENT_COUNTER,
} from '../constants/ActionTypes';
import fetch from 'isomorphic-fetch'

export function getMenu(test) {
	return dispatch => {
		fetch(`https://api.github.com/search/users?q=a`)
			.then(response => response.json())
			.then((data) => {
				dispatch({
					type: GET_APP_INDEX_MENU,
					data: data
				})
			})
			.catch(e => console.error("Oops, error", e))
	}
}