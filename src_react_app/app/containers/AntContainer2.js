import React, {
	PropTypes,
} from 'react';
import {
	connect
} from 'react-redux';
import {
	Table,
	Icon,
	Pagination,
	Button,
	Row,
	Col,
	Modal,
	Card
} from 'antd';
import QueueAnim from 'rc-queue-anim';

import * as ArchiveActions from '../actions/ArchiveActions';
import AdvancedSearch from './AdvancedSearch'

class AntContainer2 extends React.Component {

	constructor(props) {
		super(props)
		this.state = {
			modalVisible: false,
			archive: {}
		}

		/*modal event*/
		this.showModal = (e) => {
			this.setState({
				modalVisible: true,
			});
		}

		this.switchModalVisible = (param) => {
			this.setState({
				modalVisible: param,
			});
		}

		this.sendSearchCondition = (param) => {
			console.log("收到表单值：" + JSON.stringify(param))
		}

		this.test = (obj) => {
			console.log("test:")
			console.log(obj)
		}
	}

	componentWillMount = () => {}

	componentDidMount() {
		/*setTimeout(
			() => {
				this.setState({
					loading: false
				})
			}, 300)*/
		this.props.getArchiveList();
	}

	componentDidUpdate = () => {
		console.log("componentDidupdate")
	}

	render() {
		const ButtonGroup = Button.Group;
		const columns = [{
			title: '个人编号',
			dataIndex: 'grbh',
			key: 'grbh',
		}, {
			title: '姓名',
			dataIndex: 'grda_xm',
			key: 'grda_xm',
			render: (text) => <a href="#">{text}</a>,
		}, {
			title: '性别',
			dataIndex: 'grda_xb',
			key: 'grda_xb',
		}, {
			title: '出生日期',
			dataIndex: 'grda_csrq',
			key: 'grda_csrq',
		}, {
			title: '身份证号码',
			dataIndex: 'grda_sfzhm',
			key: 'grda_sfzhm',
		}, {
			title: '户口地址_街道(镇)名称',
			dataIndex: 'grda_hkdz_jdzmc',
			key: 'grda_hkdz_jdzmc',
		}, {
			title: '户口地址_居委(村)名称',
			dataIndex: 'grda_hkdz_jwcmc',
			key: 'grda_hkdz_jwcmc',
		}, {
			title: '户口地址_路_街_名称',
			dataIndex: 'grda_hkdz_ljmc',
			key: 'grda_hkdz_ljmc',
		}, {
			title: '户口类型',
			dataIndex: 'grda_hklx',
			key: 'grda_hklx',
		}, {
			title: '本人电话',
			dataIndex: 'grda_brdh',
			key: 'grda_brdh',
		}, {
			title: '家庭电话',
			dataIndex: 'grda_jtdh',
			key: 'grda_jtdh',
		}, {
			title: '操作',
			key: 'operation',
			render: (text, record) => (
				<span>
			      <a href="#">操作一{record.name}</a>
			      <span className="ant-divider"></span>
			      <a href="#">操作二</a>
			      <span className="ant-divider"></span>
			      <a href="#" className="ant-dropdown-link">
			        更多 <Icon type="down" />
			      </a>
			    </span>
			),
		}];

		const archiveProps = this.props.data.archive
		const data = archiveProps.data ? archiveProps.data.data : null;
		const loading = archiveProps.archiveListloading

		const pagination = data ? {
			total: this.props.data.archive.total,
			showSizeChanger: true,
			onShowSizeChange(current, pageSize) {
				console.log('Current: ', current, '; PageSize: ', pageSize);
			},
			onChange(current) {
				console.log('Current: ', current);
			},
		} : null;

		const advancedSearch = this.state.modalVisible ? [
			<AdvancedSearch key="advancedSearch" modalVisible={this.state.modalVisible} switchModalVisible={this.switchModalVisible} sendSearchCondition={this.sendSearchCondition}
			  fields={this.test} onFieldsChange={this.test}/>
		] : null;

		return (
			<QueueAnim delay={10}>
				<div className='module' key="buttonGroup">
					<Card>
						<ButtonGroup style={{marginBottom: "1em"}}>
					      <Button type="ghost" icon="file-text" size="large">新建档案</Button>
					      <Button type="ghost" icon="edit" size="large"	>修改档案</Button>
					      <Button type="ghost" icon="download" size="large">导入</Button>
					      <Button type="ghost" icon="search" size="large" onClick={this.showModal}>档案查询</Button>
					      {advancedSearch}
					    </ButtonGroup>
						<Table key="table" columns={columns} dataSource={data} pagination={pagination} loading={loading} bordered/>
				    </Card>
				</div>
			</QueueAnim>
		)
	}
}

AntContainer2.propTypes = {
	getArchiveList: PropTypes.func.isRequired,
	data: PropTypes.object.isRequired
}

function mapStateToProps(state) {
	return {
		data: state
	}
}

export default connect(mapStateToProps, ArchiveActions)(AntContainer2)