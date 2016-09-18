import React, {
	Component,
	PropTypes
} from 'react'
import {
	Form,
	Input,
	Button,
	Checkbox,
	Radio,
	message,
	Row,
	Col,
	Select,
	DatePicker,
	TimePicker,
	Cascader,
	Table,
	Icon
} from 'antd';

import MedicalRecordsTable from '../components/MedicalRecordsTable'

class GeneralSituationForm extends React.Component {

	constructor(props) {
		super(props);

		this.handleSubmit = (e) => {
			e.preventDefault();
			console.log('收到表单值：', this.props.form.getFieldsValue());
		}
	}

	componentWillMount = () => {}

	componentDidMount = () => {}

	render() {

		const FormItem = Form.Item;
		const CheckboxGroup = Checkbox.Group;
		const RadioGroup = Radio.Group;
		const {
			getFieldProps
		} = this.props.form

		const options = [{
			value: 'guangzhou',
			label: '广州市',
			children: [{
				value: 'yuexiu',
				label: '越秀',
				children: [{
					value: 'dadongjie',
					label: '大东街',
					children: [{
						value: 'shuqian',
						label: '署前',
						children: [{
							value: 'miaoqianxijie',
							label: '庙前西街',
						}],
					}],
				}],
			}],
		}, {
			value: 'jiangsu',
			label: '江苏',
			children: [{
				value: 'nanjing',
				label: '南京',
				children: [{
					value: 'zhonghuamen',
					label: '中华门',
				}],
			}],
		}];
		const payOptions = [{
			label: '城镇职工基本医疗保险',
			value: 'Apple'
		}, {
			label: '城镇居民基本医疗保险',
			value: 'Pear'
		}, {
			label: '新型农村合作医疗',
			value: 'Orange'
		}, {
			label: '新型农村合作医疗',
			value: 'Orange2'
		}, {
			label: '新型农村合作医疗',
			value: 'Orange3'
		}, {
			label: '其他',
			value: 'Orange4'
		}];
		const medicineOptions = [{
			label: '肾上腺素',
			value: 'Apple'
		}, {
			label: '肾上腺素',
			value: 'Pear'
		}, {
			label: '肾上腺素',
			value: 'Orange'
		}, {
			label: '其他',
			value: 'Orange2'
		}]
		const exposedOptions = [{
			label: '毒物',
			value: 'Apple'
		}, {
			label: '射线',
			value: 'Pear'
		}]

		return (
			<Form inline onSubmit={this.handleSubmit}>
				<Row>
					<Col>
				        <FormItem label="性别" required>
				          <Select defaultValue="male" style={{ width: 80 }}>
						      <Option value="male">男</Option>
						      <Option value="female">女</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="出生日期" required>
				         <DatePicker />
				        </FormItem>
				        <FormItem label="身份证号" >
			        		<Input style={{ width: 254 }}/>
				        </FormItem>
				        <FormItem label="工作单位" >
				          <Select style={{ width: 320 }}>
						      <Option value="company">北京市海淀区西北旺东路10号院百度科技园1号楼</Option>
						    </Select>
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="&nbsp;&nbsp;&nbsp;&nbsp;现住址" required>
				        	<Cascader options={options} placeholder="请选择现住址" style={{ width: 320 }} />
				        </FormItem>
				        <FormItem>
			        		<Input placeholder="门牌号"  style={{ width: 150 }}/>
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="户籍地址" required>
				        	<Cascader options={options} placeholder="请选择现住址" style={{ width: 320 }} />
				        </FormItem>
				        <FormItem>
			        		<Input placeholder="门牌号"  style={{ width: 150 }}/>
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="本人电话" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">18814141114</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="联系人姓名">
				          <Select style={{ width: 150 }} >
						      <Option value="default">胡永刚</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="联系人电话" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">18814141114</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="常住类型" required>
				          <Select style={{ width: 150 }}>
						      <Option value="phone">户籍（辖区）</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="民族" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">汉族</Option>
						    </Select>
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="血型" >
				          <Select style={{ width: 174 }}>
						      <Option value="phone">O型</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="RH阴性" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">是</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="文化程度" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">本科</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="职业" >
				          <Select style={{ width: 320 }}>
						      <Option value="phone">商业服务人员</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="婚姻状况" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">已婚</Option>
						    </Select>
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="医疗费用支付方式" >
				        	<CheckboxGroup options={payOptions} defaultValue={['Pear']} />
				        </FormItem>
				        <FormItem>
			        		<Input placeholder="其他"  style={{ width: 150 }}/>
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="药物过敏" >
				        	<RadioGroup>
					        	<Radio key="a" value={1}>有</Radio>
								<Radio key="b" value={2}>无</Radio>
				        	</RadioGroup>
							</FormItem>
				        <FormItem>
				        	<CheckboxGroup options={medicineOptions} defaultValue={['Pear']} />
				        </FormItem>
				        <FormItem>
			        		<Input placeholder="其他"  style={{ width: 150 }}/>
			        	</FormItem>
				        <FormItem label="暴露史" >
				        	<RadioGroup>
					        	<Radio key="a" value={1}>有</Radio>
								<Radio key="b" value={2}>无</Radio>
				        	</RadioGroup>
				        </FormItem>
				        <FormItem>
				        	<CheckboxGroup options={exposedOptions} defaultValue={['Pear']} />
				        </FormItem>
				        <br />
				        <br />
				        <FormItem label="档案状态" >
				          <Select style={{ width: 150 }}>
						      <Option value="phone">在册</Option>
						    </Select>
				        </FormItem>
				        <FormItem label="建档人" >
				          <Input />
				        </FormItem>
				        <FormItem label="建档日期" >
				          <DatePicker />
				        </FormItem>
				        <FormItem label="录入人" >
				          <Input />
				        </FormItem>
				        <FormItem label="录入日期" >
				          <DatePicker />
				        </FormItem>
				        <br />
				        <br />
					</Col>
				</Row>
		        <Row>
		        	<FormItem label="既往史" />
		        	<div style={{width: 1156}}>
			        	<MedicalRecordsTable />
			        </div>
			    </Row>
		    </Form>
		)
	}
}

function onFieldsChange(props, fields) {
	console.log("GeneralSituationForm onFieldsChange")
}

function mapPropsToFields(props) {
	console.log("GeneralSituationForm mapPropsToFields")
}

export default Form.create()(GeneralSituationForm)