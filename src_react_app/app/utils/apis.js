import {
	config
} from './utils'

export const reqUrl = `${config.baseUrl}/vds5s1/ba/boCallMethodPC.jsp?data=`

export const getArchiveList = () => {
	const query = {
		boName: "boVdsLogin",
		funcName: "login",
		procedure: "procJBZLSelect",
		pageNo: 1,
		pageSize: 20
	}
	const url = reqUrl + JSON.stringify(query);
	console.debug("getArchiveList:" + url);
	return url;
}