import React, {
	Component,
	PropTypes
} from 'react'
import {
	Form,
	Input,
} from 'antd'

class FamiLivelHistoryFrom extends React.Component {

	constructor(props) {
		super(props);
	}

	componentWillMount = () => {}

	componentDidMount = () => {}

	render() {

		const FormItem = Form.Item;
		const {
			getFieldProps
		} = this.props.form

		return (
			<p>FamiLivelHistoryFrom</p>
		)
	}
}

function onFieldsChange(props, fields) {
	console.log("FamiLivelHistoryFrom onFieldsChange")
}

function mapPropsToFields(props) {
	console.log("FamiLivelHistoryFrom mapPropsToFields")
}

export default Form.create()(FamiLivelHistoryFrom)