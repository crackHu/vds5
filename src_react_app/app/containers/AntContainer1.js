import React from 'react';
import {
	Tabs,
	Button,
	message,
	Card
} from 'antd';
import QueueAnim from 'rc-queue-anim';
import fetch from 'isomorphic-fetch'

import PersonalDetailForm from '../components/PersonalDetailForm'
import HealthMedicalForm from '../components/HealthMedicalForm'

import GeneralSituationForm from '../components/GeneralSituationForm'
import FamiLivelHistoryFrom from '../components/FamiLivelHistoryFrom'

export default class AntContainer1 extends React.Component {

	constructor(props) {
		super(props);

		this.changeTab = (e) => {
			console.log(e);
		}

		this.saveForm = (e) => {
			const hide = message.loading('正在保存中...', 110);

			const url = "https://api.github.com/search/users?q=a"
			const init = {
				cache: 'no-cache'
			}
			fetch(url)
				.then(response => response.json())
				.then((data) => {
					console.log(data)
					hide()
					message.success('保存成功')
				})
				.catch((e) => {
					console.error("Oops, error", e)
					hide()
					message.warn('保存失败 ' + '[' + e + ']');
				})
		}

	}

	render() {
		const TabPane = Tabs.TabPane;
		const operations = <Button type="primary" onClick={this.saveForm}>保存</Button>

		return (
			<QueueAnim delay={10}>
				<div className='module' key="tabs">
					<Card>
						<Tabs defaultActiveKey="1" onChange={this.changeTab} tabBarExtraContent={operations}>
							<TabPane tab="个人基本信息表" key="1">
							  	<PersonalDetailForm />
							</TabPane>
							<TabPane tab="健康体检表" key="2">
							  	<HealthMedicalForm/>
							</TabPane>
						</Tabs>
					</Card>
					<Card>
						<Tabs defaultActiveKey="1" onChange={this.changeTab}>
							<TabPane tab="一般情况" key="1">
							  	<GeneralSituationForm/>
							</TabPane>
							<TabPane tab="家族史与生活情况" key="2">
							  	<FamiLivelHistoryFrom/>
							</TabPane>
						</Tabs>
					</Card>
				</div>
			</QueueAnim>
		)
	}
}