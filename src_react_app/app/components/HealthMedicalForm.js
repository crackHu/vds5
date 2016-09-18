import React, {
	Component,
	PropTypes
} from 'react'
import {
	Form,
	Input,
} from 'antd'

class HealthMedicalForm extends React.Component {

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
			<p>HealthMedicalForm</p>
		)
	}
}

function onFieldsChange(props, fields) {
	console.log("HealthMedicalForm onFieldsChange")
}

function mapPropsToFields(props) {
	console.log("HealthMedicalForm mapPropsToFields")
}

export default Form.create()(HealthMedicalForm)