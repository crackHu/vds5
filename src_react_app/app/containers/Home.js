import React from 'react';
import {
	Card
} from 'antd';
import {
	PieChart,
	AreaChart,
} from 'react-d3-components';
import QueueAnim from 'rc-queue-anim';

export default class Home extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		var data = {
			label: 'somethingA',
			values: [{
				x: 'SomethingA',
				y: 10
			}, {
				x: 'SomethingB',
				y: 4
			}, {
				x: 'SomethingC',
				y: 3
			}]
		};

		var sort = null; // d3.ascending, d3.descending, func(a,b) { return a - b; }, etc...

		var data1 = [{
			label: 'somethingA',
			values: [{
				x: 0,
				y: 2
			}, {
				x: 1.3,
				y: 5
			}, {
				x: 3,
				y: 6
			}, {
				x: 3.5,
				y: 6.5
			}, {
				x: 4,
				y: 6
			}, {
				x: 4.5,
				y: 6
			}, {
				x: 5,
				y: 7
			}, {
				x: 5.5,
				y: 8
			}]
		}, {
			label: 'somethingB',
			values: [{
				x: 0,
				y: 3
			}, {
				x: 1.3,
				y: 4
			}, {
				x: 3,
				y: 7
			}, {
				x: 3.5,
				y: 8
			}, {
				x: 4,
				y: 7
			}, {
				x: 4.5,
				y: 7
			}, {
				x: 5,
				y: 7.8
			}, {
				x: 5.5,
				y: 9
			}]
		}]

		return (
			<QueueAnim delay={10}>
				<div className="survey" key="home">
					<Card>
						<PieChart
			                data={data}
			                width={600}
			                height={400}
			                margin={{top: 10, bottom: 10, left: 100, right: 100}}
			                sort={sort}
			                />
					</Card>
					<Card>
			            <AreaChart
			                data={data1}
			                width={600}
			                height={400}
			                yOrientation='left' // if you do not provide right default left orientation for yAxis will be used 
			                margin={{top: 10, bottom: 50, left: 50, right: 10}}/>
					</Card>
				</div>
			</QueueAnim>
		);
	}
}