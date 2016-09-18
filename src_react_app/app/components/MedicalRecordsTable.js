import React, {
	Component,
	PropTypes
} from 'react'
import {
	Form,
	Input,
	Table,
	Select,
	DatePicker,
	TimePicker,
	Icon,
	Pagination
} from 'antd'

class MedicalRecordsTable extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			"data": [{}],
			pagination: {
				pageSize: 5
			}
		}

		this.addRow = (e) => {
			let data = this.state.data
			data.push({})
			this.setState({
				data: data
			})
		}
	}

	componentWillMount = () => {}

	componentDidMount = () => {}

	render() {

		const FormItem = Form.Item;
		const {
			getFieldProps
		} = this.props.form

		const rowSelection = {
			onChange(selectedRowKeys, selectedRows) {
				console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
			},
			onSelect(record, selected, selectedRows) {
				console.log(record, selected, selectedRows);
			},
			onSelectAll(selected, selectedRows, changeRows) {
				console.log(selected, selectedRows, changeRows);
			},
		};

		const renderContent = function(value, row, index) {
			const obj = {
				children: value,
				props: {},
			};
			if (index === 4) {
				obj.props.colSpan = 0;
			}
			return obj;
		};
		const columns = [{
			title: '#',
			dataIndex: '#',
			width: 50,
			render: (text, record, index) => <span>{index}</span>,
		}, {
			title: '类别',
			dataIndex: 'age',
			key: 'name',
			width: 150,
			render: (text) =>
				<Select defaultValue={"test"}>
					<Option value="phone">{text}</Option> 
					<Option value="a">{new Date().getTime()}</Option> 
				</Select>,
		}, {
			title: '疾病名称',
			dataIndex: 'name',
			key: 'age',
			width: 150,
			render: (text) => <Input defaultValue={text}/>,
		}, {
			title: '确诊时间',
			dataIndex: 'address',
			dataIndex: '1',
			width: 200,
			render: (text) => (
				<DatePicker showTime defaultValue="2015-01-01"/>
			),
		}, {
			title: '备注',
			dataIndex: 'address1',
			dataIndex: '2',
			width: 300,
			render: (text, record) => <Input type="textarea" defaultValue={JSON.stringify(record)} autosize={{ minRows: 1, maxRows: 3 }} />,
		}, {
			title: '操作',
			key: 'operation',
			width: 50,
			render: () => (
				<span>
			      <a href="#">删除</a>
			      {/*<span className="ant-divider"></span>*/}
			    </span>
			),
		}];
		const data = [];
		for (let i = 0; i < 0; i++) {
			data.push({
				key: i,
				name: `李大嘴${i}`,
				age: i,
				address: `西湖区湖底公园${i}号`,
				address1: `湖底公园${i}号`,
			});
		}

		return (
			<Table columns={columns} dataSource={data} 
			 pagination={this.state.pagination} size="middle" bordered>
			</Table>
		)
	}
}

function onFieldsChange(props, fields) {
	console.log("MedicalRecordsTable onFieldsChange")
}

function mapPropsToFields(props) {
	console.log("MedicalRecordsTable mapPropsToFields")
}

export default Form.create()(MedicalRecordsTable)