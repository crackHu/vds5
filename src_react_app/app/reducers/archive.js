import {
	GET_ARCHIVES
} from '../constants/ActionTypes';

const initialState = {
	archiveListloading: true,
	data: null
}

export default function ArchiveReducer(state = initialState, action) {
	switch (action.type) {
		case GET_ARCHIVES:
			return {
				archiveListloading: false,
				data: action.data
			}
		default:
			return state
	}
}