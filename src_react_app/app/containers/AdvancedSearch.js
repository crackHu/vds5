import React, {
	Component,
	PropTypes
} from 'react'
import {
	Form,
	Input,
	DatePicker,
	Select,
	Row,
	Col,
	Modal,
	Button
} from 'antd'
import PersonalDetailForm from '../components/PersonalDetailForm'

class AdvancedSearch extends Component {

	constructor(props) {
		super(props);
		this.state = {
			/*born: {
				startValue: null,
				endValue: null,
				endOpen: false,
			},
			build: {
				startValue: null,
				endValue: null,
				endOpen: false,
			},
			entry: {
				startValue: null,
				endValue: null,
				endOpen: false,
			},*/
			startValue_born: null,
			endValue_born: null,
			endOpen_born: false,

			startValue_build: null,
			endValue_build: null,
			endOpen_build: false,

			startValue_entry: null,
			endValue_entry: null,
			endOpen_entry: false,

			modalLoading: false,
		}

		/*modal event*/
		this.handleOk = (e) => {
			e.preventDefault();
			console.log('点了确认')
			console.log(this.props.form.getFieldsValue())
			this.props.sendSearchCondition(this.props.form.getFieldsValue());
			this.setState({
				modalLoading: true,
			});
			setTimeout(() => {
				this.setState({
					modalLoading: false,
				});
				this.props.switchModalVisible(false)
			}, 1500);
		}

		this.handleCancel = (e) => {
			console.log('点了取消')
			this.props.switchModalVisible(false)
		}

		/*出生时间 event*/
		this.disabledStartDate_born = (startValue) => {
			if (!startValue || !this.state.endValue_born) {
				return false;
			}
			return startValue.getTime() >= this.state.endValue_born.getTime();
		}
		this.disabledEndDate_born = (endValue) => {
			if (!endValue || !this.state.startValue_born) {
				return false;
			}
			return endValue.getTime() <= this.state.startValue_born.getTime();
		}
		this.onChange_born = (field, value) => {
			console.log(field, 'change', value);
			this.setState({
				[field]: value
			});
		}
		this.onStartChange_born = (value) => {
			this.onChange_born('startValue_born', value);
		}
		this.onEndChange_born = (value) => {
			this.onChange_born('endValue_born', value);
		}
		this.handleStartToggle_born = ({
			open
		}) => {
			if (!open) {
				this.setState({
					endOpen_born: true,
				});
			}
		}
		this.handleEndToggle_born = ({
			open
		}) => {
			this.setState({
				endOpen_born: open
			});
		}

		/*建档日期 event*/
		this.disabledStartDate_build = (startValue) => {
			if (!startValue || !this.state.endValue_build) {
				return false;
			}
			return startValue.getTime() >= this.state.endValue_build.getTime();
		}
		this.disabledEndDate_build = (endValue) => {
			if (!endValue || !this.state.startValue_build) {
				return false;
			}
			return endValue.getTime() <= this.state.startValue_build.getTime();
		}
		this.onChange_build = (field, value, build) => {
			console.log(field, 'change', value);
			console.log("onChange_build:" + build)
			this.setState({
				[field]: value
			});
		}
		this.onStartChange_build = (value) => {
			this.onChange_build('startValue_build', value);
		}
		this.onEndChange_build = (value) => {
			this.onChange_build('endValue_build', value);
		}
		this.handleStartToggle_build = ({
			open
		}) => {
			if (!open) {
				this.setState({
					endOpen_build: true,
				});
			}
		}
		this.handleEndToggle_build = ({
			open
		}) => {
			this.setState({
				endOpen_build: open
			});
		}

		/*录入日期 event*/
		this.disabledStartDate_entry = (startValue) => {
			if (!startValue || !this.state.endValue_entry) {
				return false;
			}
			return startValue.getTime() >= this.state.endValue_entry.getTime();
		}
		this.disabledEndDate_entry = (endValue) => {
			if (!endValue || !this.state.startValue_entry) {
				return false;
			}
			return endValue.getTime() <= this.state.startValue_entry.getTime();
		}
		this.onChange_entry = (field, value, entry) => {
			console.log(field, 'change', value);
			console.log("onChange_entry:" + entry)
			this.setState({
				[field]: value
			});
		}
		this.onStartChange_entry = (value) => {
			this.onChange_entry('startValue_entry', value);
		}
		this.onEndChange_entry = (value) => {
			this.onChange_entry('endValue_entry', value);
		}
		this.handleStartToggle_entry = ({
			open
		}) => {
			if (!open) {
				this.setState({
					endOpen_entry: true,
				});
			}
		}
		this.handleEndToggle_entry = ({
			open
		}) => {
			this.setState({
				endOpen_entry: open
			});
		}
	}

	componentWillMount = () => {}

	componentDidMount = () => {}

	componentDidUpdate = () => {}

	render() {
		const FormItem = Form.Item;
		const formItemLayout = {
			labelCol: {
				sm: 5
			},
			wrapperCol: {
				sm: 19
			},
		};
		const formDatePeriodLayout = {
			labelCol: {
				sm: 3
			},
			wrapperCol: {
				sm: 21
			},
		};
		const {
			getFieldProps
		} = this.props.form
		const username = getFieldProps('username');
		const password = getFieldProps('password');
		/*const password = getFieldProps('password', {
			rules: [{
				required: true,
				message: '请输入密码'
			}, ],
		});*/

		return (
			<div>
				<Modal title="档案查询" width={720} visible={this.props.modalVisible} maskClosable={false}
			      onCancel={this.handleCancel} onOk={this.handleOk} confirmLoading={this.state.modalLoading}
			      footer={[
		            <Button key="back" type="ghost" size="large" icon="rollback" onClick={this.handleCancel}>
		            	关 闭
		            </Button>,
		            <Button key="submit" type="primary" size="large" icon="search" onClick={this.handleOk} loading={this.state.modalLoading}>
		             	查 询
		            </Button>,
		          ]}>
          			<Form horizontal className="ant-advanced-search-form">
						<Row>
							<Col sm={16}>
								<FormItem label="个人编号"
						          labelCol={{ sm: 3 }}
						          wrapperCol={{ sm: 20 }}
						        >
						          <Input {...password}/>
						        </FormItem>
					        </Col>
					        <Col sm={8}>
								<FormItem label="姓&nbsp;&nbsp;&nbsp;&nbsp;名"
						          {...formItemLayout}
						        >
						          <Input {...username}/>
						        </FormItem>
					        </Col>
				        </Row>
				        <Row>
				        	<Col sm={16}>
				        		<FormItem label="出生时间" 
				        		  {...formDatePeriodLayout}
						        >
						          	<DatePicker
							          disabledDate={this.disabledStartDate_born}
							          showTime
							          format="yyyy-MM-dd HH:mm:ss"
							          value={this.state.startValue_born}
							          placeholder="开始日期"
							          onChange={this.onStartChange_born}
							          toggleOpen={this.handleStartToggle_born}
							        />
							        {' '}至{' '}
							        <DatePicker
							          disabledDate={this.disabledEndDate_born}
							          showTime
							          format="yyyy-MM-dd HH:mm:ss"
							          value={this.state.endValue_born}
							          placeholder="结束日期"
							          onChange={this.onEndChange_born}
							          open={this.state.endOpen_born}
							          toggleOpen={this.handleEndToggle_born}
								    />
						        </FormItem>
				        	</Col>
				        	<Col sm={8}>
				        		<FormItem label="性&nbsp;&nbsp;&nbsp;&nbsp;别"
						          {...formItemLayout}
				        		>
						          <Select defaultValue="male">
								      <Option value="male">男</Option>
								      <Option value="female">女</Option>
								    </Select>
						        </FormItem>
				        	</Col>
				        </Row>
				        <Row>
				        	<Col sm={8}>
				        		<FormItem label="常住类型" 
						          labelCol={{ sm: 6 }}
						          wrapperCol={{ sm: 18 }}
						        >
						          <Select>
								      <Option value="phone">户籍（辖区）</Option>
								    </Select>
						        </FormItem>
				        	</Col>
				        	<Col sm={16}>
				        		<FormItem label="身份证号" 
						          labelCol={{ sm: 6 }}
						          wrapperCol={{ sm: 18 }}
						         >
					        		<Input />
						        </FormItem>
				        	</Col>
				        </Row>
				        <Row>
				        	<Col sm={16}>
				        		<FormItem label="建档日期"
				        		  {...formDatePeriodLayout}
				        		>
						          	<DatePicker
							          disabledDate={this.disabledStartDate_build}
							          showTime
							          format="yyyy-MM-dd HH:mm:ss"
							          value={this.state.startValue_build}
							          placeholder="开始日期"
							          onChange={this.onStartChange_build}
							          toggleOpen={this.handleStartToggle_build}
							        />
							        {' '}至{' '}
							        <DatePicker
							          disabledDate={this.disabledEndDate_build}
							          showTime
							          format="yyyy-MM-dd HH:mm:ss"
							          value={this.state.endValue_build}
							          placeholder="结束日期"
							          onChange={this.onEndChange_build}
							          open={this.state.endOpen_build}
							          toggleOpen={this.handleEndToggle_build}
								    />
						        </FormItem>
				        	</Col>
				        	<Col sm={8}>
								<FormItem label="建档人"
				        		  {...formItemLayout}
				        		>
						          <Input />
						        </FormItem>
				        	</Col>
				        </Row>
				        <Row>
				        	<Col sm={16}>
				        		<FormItem label="录入日期"
				        		  {...formDatePeriodLayout}
				        		>
						          	<DatePicker
							          disabledDate={this.disabledStartDate_entry}
							          showTime
							          format="yyyy-MM-dd HH:mm:ss"
							          value={this.state.startValue_entry}
							          placeholder="开始日期"
							          onChange={this.onStartChange_entry}
							          toggleOpen={this.handleStartToggle_entry}
							        />
							        {' '}至{' '}
							        <DatePicker
							          disabledDate={this.disabledEndDate_entry}
							          showTime
							          format="yyyy-MM-dd HH:mm:ss"
							          value={this.state.endValue_entry}
							          placeholder="结束日期"
							          onChange={this.onEndChange_entry}
							          open={this.state.endOpen_entry}
							          toggleOpen={this.handleEndToggle_entry}
								    />
						        </FormItem>
				        	</Col>
				        	<Col sm={8}>
				        		<FormItem label="录入人"
				        		  {...formItemLayout}
				        		>
						          <Input />
						        </FormItem>
				        	</Col>
				        </Row>
				        <Row>
							<Col sm={8}>
								<FormItem label="档案状态"
						          labelCol={{ sm: 6 }}
						          wrapperCol={{ sm: 18 }}
						        >
						          <Select >
								      <Option value="phone">在册</Option>
								    </Select>
						        </FormItem>
				        	</Col>
				        	<Col sm={16}>
						        <FormItem label="专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;档" 
						          labelCol={{ sm: 6 }}
						          wrapperCol={{ sm: 18 }}
						        >
						          <Input />
						        </FormItem>
							</Col>
				        </Row>
					</Form>
		        </Modal>
			</div>
		)
	}
}

function onFieldsChange(props, fields) {
	console.log("onFieldsChange")
	console.log('change', fields);
	props.onFieldsChange({
		fields
	});
}

function mapPropsToFields(props) {
	console.log("mapPropsToFields")
	console.log(props)
	return props.fields;
}

export default Form.create({
	onFieldsChange,
	mapPropsToFields
})(AdvancedSearch)